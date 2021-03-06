package com.dam.juegarte.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.dam.juegarte.R;
import com.dam.juegarte.ScratchQuestion;
import com.dam.juegarte.UserAchievements;
import com.dam.juegarte.controller.AccountController;
import com.dam.juegarte.controller.GameController;
import com.dam.juegarte.stores.UserSessionStore;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dev.skymansandy.scratchcardlayout.listener.ScratchListener;
import dev.skymansandy.scratchcardlayout.ui.ScratchCardLayout;


public class ScratchFragment extends Fragment implements ScratchListener {

    private ScratchCardLayout scratchCardLayout;
    ArrayList<ScratchQuestion> scratchQuestionsPool;
    private static final String IMAGES_URL = "http://10.0.2.2/juegarte-API";
    private int counter = 0;
    TextView questionNumber;
    TextView questionText;
    TextView percentage;
    TextView score;
    int points = 0;
    int totalScore = 0;
    ImageView questionImage;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    private int t = -1;
    GameComplete gameCompleteF;
    UserSessionStore userStore;
    GameController gameController;
    AccountController accountController;
    UserAchievements userAchievements;

    public ScratchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments() != null) {
            scratchQuestionsPool = Parcels.unwrap(getArguments().getParcelable("Questions"));
            Collections.shuffle(scratchQuestionsPool);

        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scratch, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionText = view.findViewById(R.id.tv_questionText);
        questionNumber = view.findViewById(R.id.tv_pool);
        questionImage = view.findViewById(R.id.iv_question_image);
        percentage = view.findViewById(R.id.tv_percentage);
        score = view.findViewById(R.id.tv_points);
        option1 = view.findViewById(R.id.btn_option_1);
        option2 = view.findViewById(R.id.btn_option_2);
        option3 = view.findViewById(R.id.btn_option_3);
        option4 = view.findViewById(R.id.btn_option_4);

        userStore = UserSessionStore.getInstance(getContext());
        gameController = new GameController(getContext());
        accountController = new AccountController(getContext());


        // Log.d("Scratch", scratchQuestionsPool.toString());


        //Get view reference
        scratchCardLayout = getView().findViewById(R.id.scratchCard);

        //Set the listener
        scratchCardLayout.setScratchListener(this);

        //Set the drawable (programmatically)
        scratchCardLayout.setScratchDrawable(getResources().getDrawable(R.mipmap.cover));

        //Set scratch brush width
        scratchCardLayout.setScratchWidthDip(120f);

        //Reveal full layout when some percent of the view is scratched
        scratchCardLayout.setRevealFullAtPercent(100);

        //Scratching enable/disable
        scratchCardLayout.setScratchEnabled(true);

        //Remove all scratch made till now
        // scratchCardLayout.resetScratch();

        //Reveal scratch card (Shows the layout underneath the scratch)
        //  scratchCardLayout.revealScratch();

        if (scratchQuestionsPool != null) {
            Log.d("Question bundle", scratchQuestionsPool.toString());

            Log.d("Size", scratchQuestionsPool.size() + "");


            setCurrentQuestion(scratchQuestionsPool.get(counter));


        }


    }

    public void setCurrentQuestion(final ScratchQuestion question) {
        Log.d("Counter", counter + "");
        Log.d("totalscore", totalScore + "");
        scratchCardLayout.setScratchEnabled(true);
        scratchCardLayout.resetScratch();
        questionText.setText(question.getQuestionText());
        questionNumber.setText(getString(R.string.question) + " " + (counter + 1));
        option1.setText(question.getOption1());
        option2.setText(question.getOption2());
        option3.setText(question.getOption3());
        option4.setText(question.getOption4());
        loadQuestionImage(scratchQuestionsPool.get(counter));
        //   loadImage(Glide.with(this), IMAGES_URL + question.getQuestionImage(), questionImage);
        score.setText("Score: " + 100);
        onScratchProgress(scratchCardLayout, 0);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = option1.getText().toString();
                if (answer.equals(question.getAnswer())) {
                    scratchCardLayout.revealScratch();
                    totalScore += points;
                    counter++;
                    Log.d("total score", totalScore + "");
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                    //   recall();
                    //    setCurrentQuestion(scratchQuestionsPool.get(counter));
                    //TODO: popup + next question
                } else {
                    totalScore += 0;
                    counter++;
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                    //  recall();
                    //    setCurrentQuestion(scratchQuestionsPool.get(counter));
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = option2.getText().toString();
                if (answer.equals(question.getAnswer())) {
                    scratchCardLayout.revealScratch();
                    totalScore += points;
                    counter++;
                    Log.d("total score", totalScore + "");
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                    //TODO: popup + next question
                } else {
                    totalScore += 0;
                    counter++;
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = option3.getText().toString();
                if (answer.equals(question.getAnswer())) {
                    scratchCardLayout.revealScratch();
                    totalScore += points;
                    counter++;
                    Log.d("total score", totalScore + "");
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                    //TODO: popup + next question
                } else {
                    totalScore += 0;
                    counter++;
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = option4.getText().toString();
                if (answer.equals(question.getAnswer())) {
                    scratchCardLayout.revealScratch();
                    totalScore += points;
                    counter++;
                    Log.d("total score", totalScore + "");
                    deploySuccessDialog(getString(R.string.question_info), question.getQuestionInformation());
                    //TODO: popup + next question
                } else {
                    totalScore += 0;
                    counter++;
                    deployErrorDialog(getString(R.string.question_info), question.getQuestionInformation());
                }
            }
        });


    }

    public void recall() {
        if (counter < 5) {
            setCurrentQuestion(scratchQuestionsPool.get(counter));
        } else {
            //Toast.makeText(getActivity(), "Your total score is: " + totalScore, Toast.LENGTH_SHORT).show();
            //int total = 100 * scratchQuestionsPool.size();
            int total = 500;
            deployEndDialog("Game complete!", "Your total score is: " + totalScore + " of " + total);
        }
    }

    public void deployDialog(String title, String message) {
        new SweetAlertDialog(getActivity())
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText("Next")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        recall();
                    }
                })
                .show();
    }


    public void deploySuccessDialog(String title, String message) {
        MaterialDialog mDialog = new MaterialDialog.Builder(getActivity())
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
        MaterialDialog mDialog = new MaterialDialog.Builder(getActivity())
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
        final SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
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

                BottomSheetMaterialDialog mDialog = new BottomSheetMaterialDialog.Builder(getActivity())
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(false)
                        .setAnimation(R.raw.fireworks_animation)
                        .setNeutralButton(getString(R.string.exit), new BottomSheetMaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                                getActivity().finish();
                            }
                        })
                        .build();

                // Show Dialog
                mDialog.show();
                saveGameData(3, userStore.getUserData().username, totalScore);
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

    public void gameCompleted() {
        gameCompleteF = new GameComplete();
        //    Bundle bundle = new Bundle();
        //    bundle.putParcelable("Questions", Parcels.wrap(scratchQuestionsPool));
        // gameCompleteF.setArguments(bundle);
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, gameCompleteF, "GAME_COMPLETED").addToBackStack("GAME_COMPLETED").commit();

    }

    public void loadQuestionImage(ScratchQuestion question) {
        Glide.with(getActivity().getApplicationContext())
                .load(IMAGES_URL + question.getQuestionImage())
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_background)
                .into(questionImage);
        //    Log.d("img", IMAGES_URL + question.getQuestionImage());
    }

    static void loadImage(RequestManager glide, String url, ImageView view) {
        glide.load(url).into(view);
        //    Log.d("img", url);
    }

    @Override
    public void onScratchStarted() {

    }

    @Override
    public void onScratchComplete() {
        // percentage.setText("% " + 100);
        //   score.setText("Score: " + 0);
        scratchCardLayout.setScratchEnabled(false);
        //  scratchCardLayout.revealScratch();

    }

    //Scracth progress (NOTE: not guaranteed to be exact percent. consider it like atleast this much percent has been scratched)
    @Override
    public void onScratchProgress(ScratchCardLayout scratchCardLayout, int i) {
        if (i != 100) {
            percentage.setText("Revealed:  " + i + "%");
            if (i >= 30) {
                points = (130 - i);
                score.setText("Score: " + points);
            } else {
                points = 100;
            }


        } else {
            onScratchComplete();
        }
    }


}