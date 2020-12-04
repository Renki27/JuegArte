package com.dam.juegarte.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.dam.juegarte.AudioPlayer;
import com.dam.juegarte.ExampleDialog;
import com.dam.juegarte.R;

public class SettingsFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private Switch switchSoundEff;
    private Switch switchMusic;
    public boolean playing = true;
    private Button btnHelp;
    private Button btnAbout;

    AudioPlayer audioPlayer;

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

        audioPlayer = AudioPlayer.getInstance(getContext());
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
                    audioPlayer.pause();
                    playing = false;
                } else {
                    audioPlayer.play(getContext());
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
                Uri uri = Uri.parse("http://https://support.google.com/googleplay/?hl=es#topic=3364260/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                audioPlayer.pause();
            }
        });

        return view;
    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getFragmentManager(), "example dialog");
    }

}