package com.dam.juegarte.stores;

import android.content.Context;
import android.content.SharedPreferences;

import com.dam.juegarte.GameMode;
import com.dam.juegarte.Question;
import com.dam.juegarte.ScratchQuestion;
import com.dam.juegarte.TriviaQuestion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TriviaQuestionsStore {

    public static final String SP_FILE = "TriviaStore";
    private SharedPreferences SQ_local_DB;
    private SharedPreferences.Editor editor;
    private static TriviaQuestionsStore triviaQuestionsStore;
    private Context context;
    private Gson gson = new Gson();

    public TriviaQuestionsStore(Context context) {
        this.context = context;
        SQ_local_DB = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        editor = SQ_local_DB.edit();
    }

    public static TriviaQuestionsStore getInstance(Context context) {
        if (triviaQuestionsStore == null) {
            triviaQuestionsStore = new TriviaQuestionsStore(context);
        }
        return triviaQuestionsStore;
    }


    public void storeTriviaQuestions(ArrayList<TriviaQuestion> questions) {
        String json = gson.toJson(questions);
        editor.putString("TriviaQuestions", json);
        editor.commit();
        editor.apply();
    }

    public ArrayList<TriviaQuestion> getTriviaQuestions() {
        String json = SQ_local_DB.getString("TriviaQuestions", "");
        Type type = new TypeToken<ArrayList<TriviaQuestion>>() {}.getType();
        ArrayList<TriviaQuestion> tempList = gson.fromJson(json, type);
        return tempList;
    }

    public void clearSQPool() {
        editor.clear();
        editor.apply();
    }


}