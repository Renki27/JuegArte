package com.dam.juegarte.stores;

import android.content.Context;
import android.content.SharedPreferences;

import com.dam.juegarte.Achievements;
import com.dam.juegarte.GameMode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class AchievementsStore {

    public static final String SP_FILE = "Achievements";
    private SharedPreferences ACH_local_DB;
    private SharedPreferences.Editor editor;
    private static AchievementsStore achievementsStore;
    private Context context;
    private Gson gson = new Gson();

    public AchievementsStore(Context context) {
        this.context = context;
        ACH_local_DB = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        editor = ACH_local_DB.edit();
    }

    public static AchievementsStore getInstance(Context context) {
        if (achievementsStore == null) {
            achievementsStore = new AchievementsStore(context);
        }
        return achievementsStore;
    }


    public void storeAchievements(ArrayList<Achievements> achievements) {
        String json = gson.toJson(achievements);
        editor.putString("Achievements", json);
        editor.commit();
        editor.apply();
    }

    public ArrayList<Achievements> getAchievements() {
        String json = ACH_local_DB.getString("Achievements", "");
        Type type = new TypeToken<ArrayList<Achievements>>() {}.getType();
        ArrayList<Achievements> tempList = gson.fromJson(json, type);
        return  tempList;
    }

    public void clearAchievements() {
        editor.clear();
        editor.apply();
    }


}