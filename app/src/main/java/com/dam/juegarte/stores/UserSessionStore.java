package com.dam.juegarte.stores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.dam.juegarte.LoginActivity;
import com.dam.juegarte.MainMenu;
import com.dam.juegarte.User;

public class UserSessionStore {
    public static final String SP_FILE = "UserSession";
    private SharedPreferences userLocal_DB;
    private SharedPreferences.Editor editor;
    private static UserSessionStore userSessionStore;
    private Context context;

    public UserSessionStore(Context context) {
        this.context = context;
        userLocal_DB = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        editor = userLocal_DB.edit();
    }

    public static UserSessionStore getInstance(Context context) {
        if (userSessionStore == null) {
            userSessionStore = new UserSessionStore(context);
        }
        return userSessionStore;
    }


    public void storeUserData(User user) {
        editor.putString("username", user.username);
        editor.putString("email", user.email);
        editor.putInt("points", user.points);
        editor.putString("image", user.image);
        editor.apply();
    }

    public User getUserData() {
        String username = userLocal_DB.getString("username", "");
        String email = userLocal_DB.getString("email", "");
        int points = userLocal_DB.getInt("points", 0);
        String image = userLocal_DB.getString("image", "");

        return new User(username, email, points, image);


    }

    public void setUserLoggedIn(boolean loggedIn) {
        String name = userLocal_DB.getString("username", "");
        editor.putBoolean("loggedIn", loggedIn);
        editor.apply();
    }

    public void updatePoints(int points) {
        editor.putInt("points", points);
        editor.apply();
    }

    public boolean getUserLoggedIn() {
        return userLocal_DB.getBoolean("loggedIn", false);
    }

    public void clearUserData() {
        editor.clear();
        editor.apply();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((MainMenu) context).finish();
    }

    public void checkLogin() {
        if (!getUserLoggedIn()) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            ((MainMenu) context).finish();
        }
    }

}
