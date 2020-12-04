package com.dam.juegarte;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dam.juegarte.controller.AccountController;
import com.dam.juegarte.controller.GameController;
import com.dam.juegarte.controller.QuestionController;
import com.dam.juegarte.stores.TrueFalseQuestionStore;
import com.dam.juegarte.stores.UserSessionStore;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TrueFalseGameActivity extends AppCompatActivity {

    private TextView tv_question;
    private ImageView iv_image;
    private String correct_answer;
    private ImageButton btn_false;
    private ImageButton btn_true;
    private int num_question;

    int points;
    int totalScore;

    public Dialog info;
    private TextView tv_state;
    private TextView tv_info;
    public Button btn_continue;

    QuestionController questionController;
    TrueFalseQuestionStore questionStore;
    ArrayList<TrueFalseQuestion> trueFalseQuestionsPool;
    private static final String IMAGES_URL = "http://10.0.2.2/juegarte-API";

    private int t = -1;

    UserSessionStore userStore;
    GameController gameController;
    AccountController accountController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false_game);

        tv_question = findViewById(R.id.question);
        iv_image = findViewById(R.id.iv_img);
        btn_false = findViewById(R.id.false_btn);
        btn_true = findViewById(R.id.true_btn);
        num_question = 0;
        points = 100;
        totalScore = 0;

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

        userStore = new UserSessionStore(this);
        gameController = new GameController(this);
        accountController = new AccountController(this);

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
                    num_question++;
                    totalScore += points;
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                } else {
                    num_question++;
                    totalScore += 0;
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
        if (num_question < 5){
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
                dialog.dismiss();

                BottomSheetMaterialDialog mDialog = new BottomSheetMaterialDialog.Builder(TrueFalseGameActivity.this)
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(false)
                        .setAnimation(R.raw.fireworks_animation)
                        .setNeutralButton(getString(R.string.exit), new BottomSheetMaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        })
                        .build();

                // Show Dialog
                mDialog.show();
                saveGameData(2, userStore.getUserData().username, totalScore);
                int points = userStore.getUserData().getPoints() + totalScore;
                updatePoints(userStore.getUserData().username, points);
            }
        }.start();
    }

    public void saveGameData(int idGame, String username, int obtainedPoints) {
        gameController.saveGameData(idGame, username, obtainedPoints);

    }

    public void updatePoints(String username, int points) {
        accountController.updatePoints(username, points);
        userStore.updatePoints(points);
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