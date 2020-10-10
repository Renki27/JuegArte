package com.dam.juegarte.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dam.juegarte.GameMode;
import com.dam.juegarte.Question;
import com.dam.juegarte.R;
import com.dam.juegarte.ScratchQuestion;
import com.dam.juegarte.stores.GameModesStore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionController {

    //	10.0.2.2 = Special alias to your host loopback interface (i.e., 127.0.0.1 on your development machine)
    private static final String BASE_URL = "http://10.0.2.2/juegarte-API";
    private Context context;

    public QuestionController(Context context) {
        this.context = context;
    }


    public void loadScratchQuestions() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);


        StringRequest request = new StringRequest(Request.Method.GET, BASE_URL + "/loadQuestions.php?request=loadScrQue",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("onResponse", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");


                            if (status.equals("Success")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("scratchQuestions");
                                ArrayList<Question> scratchQuestions = new ArrayList<>();


                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject questions = jsonArray.getJSONObject(i);



                                    String questionText = questions.getString("question_text").trim();
                                    String questionInformation = questions.getString("question_info").trim();
                                    String questionImage = questions.getString("question_img").trim();
                                    String option1 = questions.getString("option1").trim();
                                    String option2 = questions.getString("option2").trim();
                                    String option3 = questions.getString("option3").trim();
                                    String option4 = questions.getString("option4").trim();
                                    String answer = questions.getString("answer").trim();




                                    Question question = new ScratchQuestion(questionText, questionInformation, questionImage, option1, option2, option3, option4, answer);
                                    scratchQuestions.add(question);

                                }

                                Log.d("Scratch", scratchQuestions.toString());
                               // GameModesStore gameModesStore = new GameModesStore(context);
                               // gameModesStore.storeGameModes(gameModes);



                            } else {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, R.string.something_happened, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, R.string.sign_in_error, Toast.LENGTH_SHORT).show();

                        NetworkResponse response = error.networkResponse;
                        String errorMsg = "";
                        if (response != null && response.data != null) {
                            String errorString = new String(response.data);
                            Log.i("log error", errorString);
                        }

                    }
                });
        // Add the realibility on the connection.
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        request.setShouldCache(false);
        requestQueue.add(request);


    }
}
