package com.dam.juegarte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dam.juegarte.stores.UserSessionStore;

public class SplashScreen extends Activity {

    private int delay = 3000;
    UserSessionStore userStore;
    boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        userStore = new UserSessionStore(this);
        loggedIn = userStore.getUserLoggedIn();

        Intent intent;
        if (loggedIn) {
            intent = new Intent(SplashScreen.this, MainMenu.class);

        } else {
            intent = new Intent(SplashScreen.this, Sign_in.class);
        }
        startActivity(intent);
        finish();
/*
//        Hilo para correr el splash screen antes de entrar a la aplicacion
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if ( loggedIn == true) {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreen.this, Sign_in.class);
                    startActivity(intent);
                    finish();
                    //importante el finish para que no se pueda volver a esta pantalla
                }
            }
        }, delay);
        */

    }
}
