package com.dam.juegarte;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ScratchGame extends AppCompatActivity{

    FragmentTransaction transaction;
    Fragment fragmentScratch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_game);

     //   fragmentScratch = new ScratchFragment();
     //   getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragmentScratch).commit();
    }
}