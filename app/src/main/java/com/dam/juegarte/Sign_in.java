package com.dam.juegarte;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.dam.juegarte.controller.AccountController;
import com.dam.juegarte.stores.UserSessionStore;

public class Sign_in extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    UserSessionStore userStore;
    AccountController accountController;
    private static final String BASE_URL = "http://10.0.2.2/juegarte-API";

    public Sign_in(Context context) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        accountController = new AccountController(Sign_in.this);
        userStore = new UserSessionStore(this);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        userStore = new UserSessionStore(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                SignIn();
                break;
            case R.id.btn_create_account:
                // TODO: Realizar el cambio de activities
                createAccount();
                break;
            default:
                break;
        }
    }

    private void SignIn() {
        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            etUsername.setError(getString(R.string.error_name));
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError(getString(R.string.error_password));
            return;
        }
       accountController.login(username, password);
    }

    private void createAccount() {
        Intent intent = new Intent(Sign_in.this, Sign_up.class);
        startActivity(intent);
    }

    public String validate(String userName, String password)
    {
        if(userName.equals("user") && password.equals("user"))
            return "Login was successful";
        else
            return "Invalid login!";
    }
}

/*        RequestQueue requestQueue = Volley.newRequestQueue(Sign_in.this);


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

                                    Intent intent = new Intent(Sign_in.this, MainMenu.class);
                                    intent.putExtra("username", username);
                                    intent.putExtra("email", email);
                                    intent.putExtra("points", points);
                                    intent.putExtra("image", image);
                                    startActivity(intent);
                                    finish();

                                    // Toast.makeText(context, R.string.sign_in_success + " " + username, Toast.LENGTH_SHORT).show();

                                    //  Toast.makeText(context, username + " " + email + " " + points + " " + image, Toast.LENGTH_SHORT).show();
                                    //  user = new User(username, email, points, image);

                                }


                            } else {
                                Toast.makeText(Sign_in.this, message, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(Sign_in.this, R.string.something_happened, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Sign_in.this, R.string.sign_in_error, Toast.LENGTH_SHORT).show();

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
        requestQueue.add(request);*/



//        Log.d("arr", userData.toString());
// User user = accountController.returnResponseUser();
/*
        if (user != null) {
            Toast.makeText(Sign_in.this, R.string.sign_in_success + " " + user.username, Toast.LENGTH_SHORT).show();

            Toast.makeText(Sign_in.this, user.username + " " + user.email + " " + user.points + " " + user.image, Toast.LENGTH_SHORT).show();

/*
            Intent intent = new Intent(Sign_in.this, MainMenu.class);
            intent.putExtra("username", user.username);
            intent.putExtra("email", user.email);
            intent.putExtra("points", user.points);
            intent.putExtra("image", user.image);
            startActivity(intent);
            finish();


        } else {
            Log.i("data ", "null user");
        }
*/

/*        // TODO: Se tiene que sustituir con la logica de autenticación de la aplicación
        User tempUser = userStore.getUserData();
        if (tempUser != null) {
            if (email.equalsIgnoreCase(tempUser.email) && password.equalsIgnoreCase(tempUser.password)) {

                // TODO: almacenar en el storage el usuario logueado

                userStore.storeUserData(tempUser);
                userStore.setUserLoggedIn(true);

                Toast.makeText(this, R.string.sign_in_success, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, R.string.error_password, Toast.LENGTH_SHORT).show();
            }
        }*/