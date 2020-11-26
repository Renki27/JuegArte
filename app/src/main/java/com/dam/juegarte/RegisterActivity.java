package com.dam.juegarte;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dam.juegarte.controller.AccountController;
import com.dam.juegarte.middleware.UserMiddleware;
import com.dam.juegarte.stores.UserSessionStore;


public class Sign_up extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private Button btnSignIn;
    UserSessionStore userStore;
    AccountController accountController;
    UserMiddleware userMiddleware;

    public Sign_up() {
    }

    public Sign_up(Context context) {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        accountController = new AccountController(Sign_up.this);
        userMiddleware = new UserMiddleware();

        etUsername = findViewById(R.id.et_Username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPasswordConfirm = findViewById(R.id.et_passwordConfirm);
        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSignIn.setPaintFlags(btnSignIn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        userStore = new UserSessionStore(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                signIn();
                break;
            case R.id.btn_create_account:
                Register();
                break;
            default:
                break;
        }
    }

    private void signIn() {
        Intent intent = new Intent(Sign_up.this, Sign_in.class);
        startActivity(intent);
    }

    private void Register() {
        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String passwordConfirm = etPasswordConfirm.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();

        //middleware

        if (username.isEmpty()) {
            etUsername.setError(getString(R.string.error_name));
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError(getString(R.string.error_email));
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError(getString(R.string.error_password));
            return;
        }

        if (passwordConfirm.isEmpty()) {
            etPasswordConfirm.setError(getString(R.string.error_password));
            return;
        }

        String validation = userMiddleware.validateUser(username, password, passwordConfirm, email);
        if (validation.equals("")) {

            final User user = new User(username, password, email);

            accountController.createAccount(user);

            Intent intent = new Intent(Sign_up.this, Sign_in.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, validation, Toast.LENGTH_SHORT).show();
        }

    }

    public String validate(String email, String username, String password, String passwordConfirm) {
        if (email.equals("email") && username.equals("user") && password.equals("password") && passwordConfirm.equals("password"))
            return "Register was successful";
        else
            return "Invalid register!";
    }
}
