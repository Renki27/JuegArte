package com.dam.juegarte.stores;

import android.content.Context;
import android.content.SharedPreferences;

import com.dam.juegarte.GameMode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GameModesStore {

    public static final String SP_FILE = "GameModes";
    private SharedPreferences GM_local_DB;
    private SharedPreferences.Editor editor;
    private static GameModesStore gameModesStore;
    private Context context;
    private Gson gson = new Gson();

    public GameModesStore(Context context) {
        this.context = context;
        GM_local_DB = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        editor = GM_local_DB.edit();
    }

    public static GameModesStore getInstance(Context context) {
        if (gameModesStore == null) {
            gameModesStore = new GameModesStore(context);
        }
        return gameModesStore;
    }


    public void storeGameModes(ArrayList<GameMode> gameModes) {
        String json = gson.toJson(gameModes);
        editor.putString("GameModes", json);
        editor.commit();
        editor.apply();
    }

    public ArrayList<GameMode> getGameModes() {
        String json = GM_local_DB.getString("GameModes", "");
        Type type = new TypeToken<ArrayList<GameMode>>() {}.getType();
        ArrayList<GameMode> tempList = gson.fromJson(json, type);
        return  tempList;
    }

    public void clearGameModes() {
        editor.clear();
        editor.apply();
    }


}
