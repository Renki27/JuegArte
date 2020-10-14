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
import com.dam.juegarte.stores.GameModesStore;
import com.dam.juegarte.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GameController {

    //	10.0.2.2 = Special alias to your host loopback interface (i.e., 127.0.0.1 on your development machine)
    private static final String BASE_URL = "http://10.0.2.2/juegarte-API";
    private Context context;

    public GameController(Context context) {
        this.context = context;
    }


    public void loadGameModes() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);


        StringRequest request = new StringRequest(Request.Method.GET, BASE_URL + "/loadGameModes.php?request=loadGameModes",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                   //     Log.d("onResponse", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");


                            if (status.equals("Success")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("gameModes");
                                ArrayList<GameMode> gameModes = new ArrayList<>();


                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject modes = jsonArray.getJSONObject(i);


                                    int idGame = Integer.parseInt(modes.getString("id_game").trim());
                                    String gameMode = modes.getString("game_mode").trim();
                                    String instructions = modes.getString("instructions").trim();
                                    int questionTimer = Integer.parseInt(modes.getString("question_timer_seconds").trim());


                                    GameMode mode = new GameMode(idGame, gameMode, instructions, questionTimer);
                                    gameModes.add(mode);

                                }
                                GameModesStore gameModesStore = new GameModesStore(context);
                                gameModesStore.storeGameModes(gameModes);



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
