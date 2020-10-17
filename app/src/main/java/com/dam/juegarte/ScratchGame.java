package com.dam.juegarte;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dam.juegarte.controller.QuestionController;
import com.dam.juegarte.stores.ScratchQuestionsStore;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ScratchGame extends AppCompatActivity{

    FragmentTransaction transaction;
    ScratchFragment fragmentScratch;
    QuestionController questionController;
    ScratchQuestionsStore questionsStore;
    ArrayList<ScratchQuestion> scratchQuestionsPool;
    IQuestionsPool iQuestionsPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_game);

        questionController = new QuestionController(ScratchGame.this);
        questionsStore = new ScratchQuestionsStore(getApplicationContext());

        questionController.loadScratchQuestions();
        scratchQuestionsPool = questionsStore.getScratchQuestions();



        fragmentScratch = new ScratchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Questions", Parcels.wrap(scratchQuestionsPool));
        fragmentScratch.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_scratch, fragmentScratch).commit();

    //    Log.d("Scratch", scratchQuestionsPool.toString());




    //
    //   iQuestionsPool = (IQuestionsPool) fragmentScratch;
    //    getSupportFragmentManager().beginTransaction().add(R.id.fragment_scratch, fragmentScratch).commit();
    }

    public interface IQuestionsPool {
        void sendQuestions(ArrayList<ScratchQuestion> questions);
    }
}