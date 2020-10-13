package com.dam.juegarte;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dam.juegarte.controller.QuestionController;
import com.dam.juegarte.stores.ScratchQuestionsStore;

import java.util.ArrayList;

public class ScratchGame extends AppCompatActivity{

    FragmentTransaction transaction;
    Fragment fragmentScratch;
    QuestionController questionController;
    ScratchQuestionsStore questionsStore;
    ArrayList<ScratchQuestion> scratchQuestionsPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_game);

        questionController = new QuestionController(ScratchGame.this);
        questionsStore = new ScratchQuestionsStore(getApplicationContext());

        questionController.loadScratchQuestions();
        scratchQuestionsPool = questionsStore.getScratchQuestions();

        Log.d("Scratch", scratchQuestionsPool.toString());




     //   fragmentScratch = new ScratchFragment();
     //   getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragmentScratch).commit();
    }
}