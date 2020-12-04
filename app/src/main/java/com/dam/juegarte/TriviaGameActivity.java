package com.dam.juegarte;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dam.juegarte.controller.QuestionController;
import com.dam.juegarte.stores.TriviaQuestionsStore;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TriviaGameActivity extends AppCompatActivity {

    private TextView tvQuestionText;
    private ImageView ivQuestionImage;
    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnOption4;

    private int counter;
    int totalScore;
    private int t = -1;

    private String correctAnswer;

    private QuestionController questionController;
    private TriviaQuestionsStore questionStore;
    private ArrayList<TriviaQuestion> triviaQuestionsPool;

    private static final String IMAGES_URL = "http://10.0.2.2/juegarte-API";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        counter = 0;
        totalScore = 0;

        tvQuestionText = findViewById(R.id.tv_questionText);

        ivQuestionImage = findViewById(R.id.iv_question_image);

        btnOption1 = findViewById(R.id.btn_option_1);
        btnOption2 = findViewById(R.id.btn_option_2);
        btnOption3 = findViewById(R.id.btn_option_3);
        btnOption4 = findViewById(R.id.btn_option_4);

        //questionController = new QuestionController(this);

        questionStore = TriviaQuestionsStore.getInstance(this);

        //questionController.loadTriviaQuestions();

        triviaQuestionsPool = questionStore.getTriviaQuestions();

//        Log.d("trivia: ", triviaQuestionsPool.toString());

        if (triviaQuestionsPool != null){
            Collections.shuffle(triviaQuestionsPool);
            setQuestion(triviaQuestionsPool.get(counter));
        }
    }

    public void setQuestion(final TriviaQuestion question){

        tvQuestionText.setText(question.getQuestionText());
        btnOption1.setText(question.getOption1());
        btnOption2.setText(question.getOption2());
        btnOption3.setText(question.getOption3());
        btnOption4.setText(question.getOption4());
        tvQuestionText.setText(question.getQuestionText());
        correctAnswer = question.getAnswer();
        loadQuestionImage(triviaQuestionsPool.get(counter));

        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnOption1.getText().equals(question.getAnswer())){
                    totalScore += 100;
                    counter++;
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                } else {
                    counter++;
                    //showIncorrect();
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                }
            }
        });btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnOption2.getText().equals(question.getAnswer())){
                    totalScore += 100;
                    counter++;
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                } else {
                    counter++;
                    //showIncorrect();
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                }
            }
        });btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnOption3.getText().equals(question.getAnswer())){
                    totalScore += 100;
                    counter++;
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                } else {
                    counter++;
                    //showIncorrect();
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                }
            }
        });btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnOption4.getText().equals(question.getAnswer())){
                    totalScore += 100;
                    counter++;
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                } else {
                    counter++;
                    //showIncorrect();
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                }
            }
        });
    }

    public void loadQuestionImage(TriviaQuestion question) {
        Glide.with(this)
                .load(IMAGES_URL + question.getQuestionImage())
                .centerCrop()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivQuestionImage);
//        Log.d("img", IMAGES_URL + question.getQuestionImage());
    }

    public void recall() {
        if (counter < 5) {
            setQuestion(triviaQuestionsPool.get(counter));
        } else {
            //Toast.makeText(getActivity(), "Your total score is: " + totalScore, Toast.LENGTH_SHORT).show();
          //  int total = 100 * triviaQuestionsPool.size();
            int total = 500;
            deployEndDialog("Game complete!", "Your total score is: " + totalScore + " of " + total);
        }
    }

    public void deploySuccessDialog(String title, String message) {
        MaterialDialog mDialog = new MaterialDialog.Builder(TriviaGameActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setAnimation(R.raw.correct_animation)
                .setNeutralButton("Next", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        recall();
                    }
                })
                .build();

        // Show Dialog
        mDialog.show();

    }

    public void deployErrorDialog(String title, String message) {
        MaterialDialog mDialog = new MaterialDialog.Builder(TriviaGameActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setAnimation(R.raw.wrong_animation)
                .setNeutralButton("Next", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        recall();
                    }
                })
                .build();

        // Show Dialog
        mDialog.show();
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

    @Override
    public void onBackPressed() {
        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle(getString(R.string.exit))
                .setMessage(getString(R.string.game_exit_confirmation))
                .setCancelable(false)
                .setAnimation(R.raw.question_mark)
                .setPositiveButton(getString(R.string.exit), new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .build();

        mDialog.show();
    }

}