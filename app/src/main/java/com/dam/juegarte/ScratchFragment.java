package com.dam.juegarte;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import org.parceler.Parcels;

import java.util.ArrayList;

import dev.skymansandy.scratchcardlayout.listener.ScratchListener;
import dev.skymansandy.scratchcardlayout.ui.ScratchCardLayout;


public class ScratchFragment extends Fragment  implements ScratchListener {

    private ScratchCardLayout scratchCardLayout;
    ArrayList<ScratchQuestion> scratchQuestionsPool;
    private static final String IMAGES_URL = "http://10.0.2.2/juegarte-API";
    private Question q;
    private int counter = 0;
    TextView questionNumber;
    TextView questionText;
    TextView percentage;
    TextView score;
    int points = 0;
    ImageView questionImage;
    Button option1;
    Button option2;
    Button option3;
    Button option4;

    public ScratchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getArguments() != null) {
            scratchQuestionsPool = Parcels.unwrap(getArguments().getParcelable("Questions"));
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



        // Log.d("Scratch", scratchQuestionsPool.toString());



        //Get view reference
        scratchCardLayout = getView().findViewById(R.id.scratchCard);

        //Set the listener
        scratchCardLayout.setScratchListener(this);

        //Set the drawable (programmatically)
        scratchCardLayout.setScratchDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));

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

        if(scratchQuestionsPool != null){
            //  Log.d("Question bundle", scratchQuestionsPool.toString());

            Log.d("Size", scratchQuestionsPool.size() + "");

            setCurrentQuestion(scratchQuestionsPool.get(counter));






        }
    }

    public void setCurrentQuestion(ScratchQuestion question) {

        if (counter < scratchQuestionsPool.size()) {
            questionText.setText(question.getQuestionText());
            questionNumber.setText(counter + " / " + scratchQuestionsPool.size());
            option1.setText(question.getOption1());
            option2.setText(question.getOption2());
            option3.setText(question.getOption3());
            option4.setText(question.getOption4());
            loadQuestionImage(scratchQuestionsPool.get(counter));
            //   loadImage(Glide.with(this), IMAGES_URL + question.getQuestionImage(), questionImage);
            onScratchProgress(scratchCardLayout, 0);


        }



    }

    public void loadQuestionImage(ScratchQuestion question) {
        Glide.with(getActivity().getApplicationContext())
                .load(IMAGES_URL + question.getQuestionImage())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(questionImage);
        Log.d("img", IMAGES_URL + question.getQuestionImage());
    }

    static void loadImage(RequestManager glide, String url, ImageView view) {
        glide.load(url).into(view);
        Log.d("img", url);
    }

    @Override
    public void onScratchStarted() {

    }

    @Override
    public void onScratchComplete() {
        percentage.setText("% " +100);
        score.setText("Score: " + 0);
        scratchCardLayout.setScratchEnabled(false);
        //  scratchCardLayout.revealScratch();

    }

    //Scracth progress (NOTE: not guaranteed to be exact percent. consider it like atleast this much percent has been scratched)
    @Override
    public void onScratchProgress(ScratchCardLayout scratchCardLayout, int i) {
        if (i!= 100) {
            //   percentage.setText("% " + i);
            points = (100 - i);
            //  score.setText("Score: " + points);

        } else {
            onScratchComplete();
        }


    }

}