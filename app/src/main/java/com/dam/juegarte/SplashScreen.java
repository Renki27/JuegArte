package com.dam.juegarte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dam.juegarte.stores.UserSessionStore;

public class SplashScreen extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    UserSessionStore userStore;
    boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        userStore = new UserSessionStore(this);
        loggedIn = userStore.getUserLoggedIn();

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent();
                /* Create an Intent that will start the Menu-Activity. */
                if (loggedIn) {
                    mainIntent = new Intent(SplashScreen.this, MainMenu.class);
                } else {
                    mainIntent = new Intent(SplashScreen.this, Sign_in.class);
                }
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
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
