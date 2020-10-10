package com.dam.juegarte;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dam.juegarte.controller.QuestionController;

public class ScratchGame extends AppCompatActivity{

    FragmentTransaction transaction;
    Fragment fragmentScratch;
    QuestionController questionController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_game);

        questionController = new QuestionController(this);

        questionController.loadScratchQuestions();

     //   fragmentScratch = new ScratchFragment();
     //   getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragmentScratch).commit();
    }
}