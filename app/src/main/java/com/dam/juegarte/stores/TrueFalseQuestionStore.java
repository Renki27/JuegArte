package com.dam.juegarte.stores;

import android.content.Context;
import android.content.SharedPreferences;

import com.dam.juegarte.TrueFalseQuestion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TrueFalseQuestionStore {

    public static final String SP_FILE = "TrueFalseQuestions";
    private SharedPreferences SQ_local_DB;
    private SharedPreferences.Editor editor;
    private static TrueFalseQuestionStore trueFalseQuestionStore;
    private Context context;
    private Gson gson = new Gson();

    public TrueFalseQuestionStore(Context context) {
        this.context = context;
        SQ_local_DB = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        editor = SQ_local_DB.edit();
    }

    public static TrueFalseQuestionStore getInstance(Context context) {
        if (trueFalseQuestionStore == null) {
            trueFalseQuestionStore = new TrueFalseQuestionStore(context);
        }
        return trueFalseQuestionStore;
    }

    public void storeTrueFalseQuestions(ArrayList<TrueFalseQuestion> questions){
        String json = gson.toJson(questions);
        editor.putString("TrueFalseQuestions", json);
        editor.commit();
        editor.apply();
    }

    public ArrayList<TrueFalseQuestion> getTrueFalseQuestions(){
        String json = SQ_local_DB.getString("TrueFalseQuestions", "");
        Type type = new TypeToken<ArrayList<TrueFalseQuestion>>() {}.getType();
        ArrayList<TrueFalseQuestion> tempList = gson.fromJson(json, type);
        return tempList;
    }

    public void clearSQPool() {
        editor.clear();
        editor.apply();
    }
}
