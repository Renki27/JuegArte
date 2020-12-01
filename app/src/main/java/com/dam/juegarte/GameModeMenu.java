package com.dam.juegarte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dam.juegarte.stores.GameModesStore;

import java.util.ArrayList;

public class GameModeMenu extends AppCompatActivity implements View.OnClickListener {

    GameModesStore gameModesStore;
    ArrayList<GameMode> gameModes;

    Button gameMode1;
    Button gameMode2;
    Button gameMode3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_modes);

        gameModesStore = new GameModesStore(getApplicationContext());
        gameModes = gameModesStore.getGameModes();
        // Log.d("GM", gameModes.toString());


        gameMode1 = findViewById(R.id.btn_game_1);
        gameMode2 = findViewById(R.id.btn_game_2);
        gameMode3 = findViewById(R.id.btn_game_3);

        setButtonsNames();
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
        Intent intent = new Intent(this, ScratchGame.class);
        startActivity(intent);
    }


    public void setButtonsNames() {
        gameMode1.setText(gameModes.get(0).getGameMode());
        gameMode2.setText(gameModes.get(1).getGameMode());
        gameMode3.setText(gameModes.get(2).getGameMode());
    }
}