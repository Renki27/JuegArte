package com.dam.juegarte.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dam.juegarte.GameMode;
import com.dam.juegarte.R;
import com.dam.juegarte.ScratchGame;
import com.dam.juegarte.stores.GameModesStore;

import java.util.ArrayList;

public class GamesFragment extends Fragment implements View.OnClickListener{

    private AppCompatActivity activity;

    GameModesStore gameModesStore;
    ArrayList<GameMode> gameModes;

    Button gameMode1;
    Button gameMode2;
    Button gameMode3;

    public GamesFragment() {
        // Required empty public constructor
    }

    public static GamesFragment newInstance() {
        GamesFragment fragment = new GamesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games, container, false);

        gameModesStore = new GameModesStore(getActivity().getApplicationContext());
        gameModes = gameModesStore.getGameModes();
        // Log.d("GM", gameModes.toString());


        gameMode1 = view.findViewById(R.id.btn_game_1);
        gameMode2 = view.findViewById(R.id.btn_game_2);
        gameMode3 = view.findViewById(R.id.btn_game_3);

        setButtonsNames();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_game_3:
                startScratchGame();
                break;
            default:
                break;
        }
    }

    private void startScratchGame() {
        Intent intent = new Intent(activity, ScratchGame.class);
        startActivity(intent);
    }


    public void setButtonsNames() {
        gameMode1.setText(gameModes.get(0).getGameMode());
        gameMode2.setText(gameModes.get(1).getGameMode());
        gameMode3.setText(gameModes.get(2).getGameMode());
    }
}