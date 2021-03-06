package com.dam.juegarte;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dam.juegarte.adapters.MainViewPagerAdapter;
import com.dam.juegarte.controller.GameController;
import com.dam.juegarte.controller.QuestionController;
import com.dam.juegarte.fragments.AchievementsFragment;
import com.dam.juegarte.fragments.GamesFragment;
import com.dam.juegarte.fragments.SettingsFragment;
import com.dam.juegarte.fragments.UserFragment;
import com.dam.juegarte.stores.AchievementsStore;
import com.dam.juegarte.stores.GameModesStore;
import com.dam.juegarte.stores.UserSessionStore;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {
    UserSessionStore userStore;
    GameModesStore gameModesStore;
    AchievementsStore achievementsStore;
    GameController gameController;
    QuestionController questionController;
    private Gson gson;

    private ViewPager vPager;
    private TabLayout tlTabs;
    private TabItem tiGames;
    private TabItem tiAchievements;
    private TabItem tiSettings;
    private TabItem tiUser;

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tlTabs = findViewById(R.id.tl_tabs);

        vPager = findViewById(R.id.vp_pager);

        tiGames = findViewById(R.id.ti_games);
        tiAchievements = findViewById(R.id.ti_achievements);
        tiSettings = findViewById(R.id.ti_settings);
        tiUser = findViewById(R.id.ti_user);

        setupViewPager();
        setupTabIcons();

        gson = new Gson();
        userStore = UserSessionStore.getInstance(this);
        gameModesStore =  GameModesStore.getInstance(getApplicationContext());
        achievementsStore = AchievementsStore.getInstance(getApplicationContext());

        userStore.checkLogin();
        User userData = userStore.getUserData();



        gameController = new GameController(MainMenu.this);
        gameController.loadGameModes();
        gameController.loadAchievements();

        questionController = new QuestionController(MainMenu.this);
        questionController.loadScratchQuestions();
        questionController.loadTriviaQuestions();
        questionController.loadTrueFalseQuestions();

        AudioPlayer audioPlayer = AudioPlayer.getInstance(this);
        audioPlayer.play(this);

    }

    private void setupViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);

        fragments.add(GamesFragment.newInstance());
        fragments.add(AchievementsFragment.newInstance());
        fragments.add(UserFragment.newInstance());
        fragments.add(SettingsFragment.newInstance());



        vPager.setAdapter(mainViewPagerAdapter);
        tlTabs.setupWithViewPager(vPager);

    }

    private void setupTabIcons() {
        tlTabs.getTabAt(0).setIcon(R.drawable.ic_games);
        tlTabs.getTabAt(1).setIcon(R.drawable.ic_achievements);
        tlTabs.getTabAt(2).setIcon(R.drawable.ic_user);
        tlTabs.getTabAt(3).setIcon(R.drawable.ic_settings);
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
        //  gameModesStore.clearGameModes();
           Intent intent = new Intent(this, LoginActivity.class);
           startActivity(intent);
          finish();
    }

    @Override
    public void onClick(View view) {
        /*switch (view.getId()) {
            case R.id.btn_profile:
                openProfile();
                break;
            case R.id.btn_games:
                selectGameMenu();
            default:
                break;
        }*/

    }

    private void selectGameMenu() {
        Intent intent = new Intent(this, GameModeMenu.class);
        startActivity(intent);
    }

    private void openProfile() {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);

    }


    // public method to be accessed by host activity.
    public void sendGameModes(final GameMode gameModes) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            if ((mPlayer != null) && (mPlayer.isPlaying())) {
                mPlayer.stop();

            }
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }

    }

}