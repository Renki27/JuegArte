package com.dam.juegarte;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.dam.juegarte.controller.QuestionController;
import com.dam.juegarte.stores.TrueFalseQuestionStore;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TrueFalseGameActivity extends AppCompatActivity {

    private TextView tv_question;
    private ImageView iv_image;
    private String correct_answer;
    private Button btn_false;
    private Button btn_true;
    private int num_question;
    public int points;

    public Dialog info;
    private TextView tv_state;
    private TextView tv_info;
    public Button btn_continue;

    QuestionController questionController;
    TrueFalseQuestionStore questionStore;
    ArrayList<TrueFalseQuestion> trueFalseQuestionsPool;
    private static final String IMAGES_URL = "http://10.0.2.2/juegarte-API";

    private int t = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false_game);

        tv_question = findViewById(R.id.question);
        iv_image = findViewById(R.id.iv_img);
        btn_false = findViewById(R.id.false_btn);
        btn_true = findViewById(R.id.true_btn);
        num_question = 0;
        points = 0;

        info = new Dialog(this);

        questionController = new QuestionController(this);
        questionStore = new TrueFalseQuestionStore(this);

        questionController.loadTrueFalseQuestions();
        trueFalseQuestionsPool = questionStore.getTrueFalseQuestions();

        //Log.d("True false: ", trueFalseQuestionsPool.toString());

        info.setContentView(R.layout.info_question);
        tv_state = info.findViewById(R.id.tv_state);
        tv_info = info.findViewById(R.id.tv_info);
        btn_continue = info.findViewById(R.id.btn_continue);



        if (trueFalseQuestionsPool != null){
            Collections.shuffle(trueFalseQuestionsPool);
            setQuestion(trueFalseQuestionsPool.get(num_question));
        }

    }

    @Override
    public void onBackPressed() {

        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText(getString(R.string.exit));
        dialog.setContentText(getString(R.string.game_exit_confirmation));
        dialog.setConfirmText(getString(R.string.exit));
        dialog.setCancelText(getString(R.string.cancel));
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                finish();
            }
        });
        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        dialog.show();
    }

    public void setQuestion(final TrueFalseQuestion question){
        tv_question.setText(question.getQuestionText());
        correct_answer = question.getTf_answer();
        tv_info.setText(question.getQuestionInformation());
        loadQuestionImage(trueFalseQuestionsPool.get(num_question));
        Log.i("ANSWER", correct_answer);

        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correct_answer.equals("true")){
                    points = points + 5;
                    num_question++;
                    //showCorrect();
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                } else {
                    num_question++;
                    //showIncorrect();
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                }
            }
        });

        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correct_answer.equals("false")){
                    points = points + 5;
                    num_question++;
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                } else {
                    num_question++;
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                }
            }
        });

    }

    public void recall(){
        if (num_question < trueFalseQuestionsPool.size()){
            setQuestion(trueFalseQuestionsPool.get(num_question));
        } else {
//            tv_state.setText("Game completed!");
//            tv_info.setText("You got: " + String.valueOf(points) + " points");
//            info.show();
//            btn_continue.setText("Finish");
//            btn_continue.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finish();
//                }
//            });
            deployEndDialog("Game complete!", "Your total score is: " + points);
        }
    }

    public void deploySuccessDialog(String title, String message) {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText(title);
        dialog.setContentText(message);
        dialog.setConfirmText("Next");
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                recall();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    public void deployErrorDialog(String title, String message) {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        dialog.setTitleText(title);
        dialog.setContentText(message);
        dialog.setConfirmText("Next");
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                recall();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    public void deployEndDialog(final String title, final String message) {
        final SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText(getString(R.string.loading));
        dialog.setContentText(getString(R.string.saving_data));
        dialog.setCancelable(false);
        dialog.show();
        new CountDownTimer(500 * 7, 500) {
            public void onTick(long millisUntilFinished) {
                // you can change the progress bar color by ProgressHelper every 800 millis
                t++;
                switch (t) {
                    case 0:
                        dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                        break;
                    case 1:
                        dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                        break;
                    case 2:
                        dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                        break;
                    case 3:
                        dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                        break;
                    case 4:
                        dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                        break;
                    case 5:
                        dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                        break;
                    case 6:
                        dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
                        break;
                }
            }

            public void onFinish() {
                t = -1;
                dialog.setTitleText(title)
                        .setContentText(message)
                        .setConfirmText(getString(R.string.dialog_ok))
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            }
        }.start();

        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                finish();
            }
        });
    }

//    public void showCorrect(){
//        tv_state.setText(getString(R.string.correct));
//        btn_continue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                info.dismiss();
//                recall();
//            }
//        });
//        info.show();
//    }
//
//    public void showIncorrect(){
//        tv_state.setText(getString(R.string.incorrect));
//        btn_continue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                info.dismiss();
//                recall();
//            }
//        });
//        info.show();
//    }

    public void loadQuestionImage(TrueFalseQuestion question) {
        Glide.with(this)
                .load(IMAGES_URL + question.getQuestionImage())
                .centerCrop()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .into(iv_image);
            Log.d("img", IMAGES_URL + question.getQuestionImage());
    }

}