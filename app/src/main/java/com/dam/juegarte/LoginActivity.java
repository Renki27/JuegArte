package com.dam.juegarte;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.dam.juegarte.controller.AccountController;
import com.dam.juegarte.stores.UserSessionStore;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private ProgressBar loading;
    private Button btnSignUp;
    UserSessionStore userStore;
    AccountController accountController;
    private static final String BASE_URL = "http://10.0.2.2/juegarte-API";

    public LoginActivity() {

    }

    public LoginActivity(Context context) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        accountController = new AccountController(LoginActivity.this);

        userStore =  UserSessionStore.getInstance(this);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnSignUp = findViewById(R.id.btn_create_account);
        btnSignUp.setPaintFlags(btnSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                SignIn();
                break;
            case R.id.btn_create_account:

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
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public String validate(String userName, String password) {
        if (userName.equals("user") && password.equals("user")) {
            return "Login was successful";
        } else if (userName.equals("") && password.equals("")){
            return "Void data";
        } else {
            return "Invalid login!";
        }

    }
}
