package com.dam.juegarte;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dam.juegarte.controller.GameController;
import com.dam.juegarte.stores.GameModesStore;
import com.dam.juegarte.stores.UserSessionStore;
import com.google.gson.Gson;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {
    UserSessionStore userStore;
    GameModesStore gameModesStore;
    GameController gameController;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        gson = new Gson();
        userStore = new UserSessionStore(this);
        gameModesStore = new GameModesStore(this);

        userStore.checkLogin();
        User userData = userStore.getUserData();


        gameController = new GameController(MainMenu.this);
        gameController.loadGameModes();

/*
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        int points = Integer.parseInt(intent.getStringExtra("points"));
        String image = intent.getStringExtra("image");
        User userData = new User(username, email, points, image);

 */


        //para agregar el toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        // toolbar.setTitle("Welcome");
        setSupportActionBar(toolbar);


    }

    //para el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu_main es el del archivo xml
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void logout() {
        userStore.setUserLoggedIn(false);
        userStore.clearUserData();
        gameModesStore.clearGameModes();
        //   Intent intent = new Intent(this, Sign_in.class);
        //   startActivity(intent);
        //  finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_profile:
                openProfile();
                break;
            case R.id.btn_games:
                selectGameMenu();
            default:
                break;
        }

    }

    private void selectGameMenu() {
        Intent intent = new Intent(this, GameModeMenu.class);
        startActivity(intent);
    }

    private void openProfile() {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);

    }


/*
    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {

        }
    }

    public boolean authenticate() {
        return userStore.getUserLoggedIn();
    }
*/
}