package com.dam.juegarte.stores;

import android.content.Context;
import android.content.SharedPreferences;

import com.dam.juegarte.GameMode;
import com.dam.juegarte.Question;
import com.dam.juegarte.ScratchQuestion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ScratchQuestionsStore {

    public static final String SP_FILE = "ScratchQuestions";
    private SharedPreferences SQ_local_DB;
    private SharedPreferences.Editor editor;
    private static ScratchQuestionsStore scratchQuestionsStore;
    private Context context;
    private Gson gson = new Gson();

    public ScratchQuestionsStore(Context context) {
        this.context = context;
        SQ_local_DB = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        editor = SQ_local_DB.edit();
    }

    public static ScratchQuestionsStore getInstance(Context context) {
        if (scratchQuestionsStore == null) {
            scratchQuestionsStore = new ScratchQuestionsStore(context);
        }
        return scratchQuestionsStore;
    }


    public void storeScratchQuestions(ArrayList<ScratchQuestion> questions) {
        String json = gson.toJson(questions);
        editor.putString("ScratchQuestions", json);
        editor.commit();
        editor.apply();
    }

    public ArrayList<ScratchQuestion> getScratchQuestions() {
        String json = SQ_local_DB.getString("ScratchQuestions", "");
        Type type = new TypeToken<ArrayList<ScratchQuestion>>() {}.getType();
        ArrayList<ScratchQuestion> tempList = gson.fromJson(json, type);
        return  tempList;
    }

    public void clearSQPool() {
        editor.clear();
        editor.apply();
    }


}
