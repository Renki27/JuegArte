package com.dam.juegarte.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.dam.juegarte.ExampleDialog;
import com.dam.juegarte.MainMenu;
import com.dam.juegarte.R;
import com.dam.juegarte.SplashScreen;
import com.dam.juegarte.TrueFalseGameActivity;

public class SettingsFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private Switch switchSoundEff;
    private Switch switchMusic;
    private boolean playing = true;
    private boolean playingFirst = true;
    private Button btnHelp;
    private Button btnAbout;

    MainMenu mainMenu;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainMenu = new MainMenu();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        switchMusic = view.findViewById(R.id.switch_music);
        btnHelp = view.findViewById(R.id.btn_help);
        btnAbout = view.findViewById(R.id.btn_about);

        switchMusic.setChecked(true);

        switchMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing){
                    mainMenu.pause();
                    playing = false;
                } else {
                    mainMenu.play(getContext());
                    playing = true;
                }
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.google.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return view;
    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getFragmentManager(), "example dialog");
    }

}