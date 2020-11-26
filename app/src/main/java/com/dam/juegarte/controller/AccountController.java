package com.dam.juegarte.controller;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dam.juegarte.MainMenu;
import com.dam.juegarte.Sign_in;
import com.dam.juegarte.User;
import com.dam.juegarte.stores.UserSessionStore;
import com.dam.juegarte.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccountController {

    //	10.0.2.2 = Special alias to your host loopback interface (i.e., 127.0.0.1 on your development machine)
    private static final String BASE_URL = "http://10.0.2.2/juegarte-API";
    private Context context;

    public AccountController(Context context) {
        this.context = context;
    }


    public void createAccount(final User user) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);


        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL + "/signup.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
           //             Log.d("onResponse", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");
                            if (status.equals("Success")) {
                                Toast.makeText(context, R.string.account_created, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, R.string.sign_up_error, Toast.LENGTH_SHORT).show();

                        NetworkResponse response = error.networkResponse;
                        String errorMsg = "";
                        if (response != null && response.data != null) {
                            String errorString = new String(response.data);
                            Log.i("log error", errorString);
                        }

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", user.getUsername());
                params.put("password", user.getPassword());
                params.put("email", user.getEmail());
                Log.i("sending ", params.toString());
                return params;
            }
/*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

                Log.d("getHeaders", params.toString());
                return params;
            }

 */
        };
        // Add the realibility on the connection.
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        request.setShouldCache(false);
        requestQueue.add(request);

    }


    public void login(final String username, final String password) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);


        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL + "/signin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("onResponse", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");


                            if (status.equals("Success")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("userData");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject userData = jsonArray.getJSONObject(i);

                                    String username = userData.getString("username").trim();
                                    String email = userData.getString("email").trim();
                                    int points = Integer.parseInt(userData.getString("points").trim());
                                    String image = userData.getString("image").trim();

                                    User tempUser = new User(username, email, points, image);
                                    UserSessionStore userSessionStore = new UserSessionStore(context);
                                    userSessionStore.storeUserData(tempUser);
                                    userSessionStore.setUserLoggedIn(true);

                                    Intent intent = new Intent(context, MainMenu.class);
                                    context.startActivity(intent);
                                    ((Sign_in) context).finish();


                                    // Toast.makeText(context, R.string.sign_in_success + " " + username, Toast.LENGTH_SHORT).show();

                                    //  Toast.makeText(context, username + " " + email + " " + points + " " + image, Toast.LENGTH_SHORT).show();
                                    //  user = new User(username, email, points, image);

                                }


                            } else {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();

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
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                Log.i("sending ", params.toString());
                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

                Log.d("getHeaders", params.toString());
                return params;
            }


        };
        // Add the realibility on the connection.
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        request.setShouldCache(false);
        requestQueue.add(request);


    }

}
