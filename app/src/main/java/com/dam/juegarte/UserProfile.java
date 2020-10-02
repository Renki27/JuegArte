package com.dam.juegarte;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dam.juegarte.stores.GameModesStore;
import com.dam.juegarte.stores.UserSessionStore;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivUserImage;
    private TextView tvUsername;
    private TextView tvEmail;
    private TextView tvPoints;
    private static final String BASE_URL = "http://10.0.2.2/juegarte-API";


    UserSessionStore userStore;
    GameModesStore gameModesStore;
    private User userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivUserImage = findViewById(R.id.iv_user_image);
        tvUsername = findViewById(R.id.tv_username);
        tvEmail = findViewById(R.id.tv_email);
        tvPoints = findViewById(R.id.tv_points);

        userStore = new UserSessionStore(this);
        gameModesStore = new GameModesStore(this);

        userData = userStore.getUserData();
        setUserData();
        loadUserAvatar();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    private void setUserData() {
        tvUsername.setText(userData.getUsername());
        tvEmail.setText(userData.getEmail());
        tvPoints.setText(userData.getPoints() + " Points");

    }


    public void loadUserAvatar() {
        Glide.with(this)
                .load(BASE_URL + userData.getImage())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivUserImage);
    }
}