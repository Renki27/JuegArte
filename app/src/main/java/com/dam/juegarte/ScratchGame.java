package com.dam.juegarte;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dam.juegarte.controller.QuestionController;
import com.dam.juegarte.stores.ScratchQuestionsStore;

import org.parceler.Parcels;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ScratchGame extends AppCompatActivity {

    FragmentTransaction transaction;
    ScratchFragment fragmentScratch;
    QuestionController questionController;
    ScratchQuestionsStore questionsStore;
    ArrayList<ScratchQuestion> scratchQuestionsPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_game);

        questionController = new QuestionController(ScratchGame.this);
        questionsStore = new ScratchQuestionsStore(getApplicationContext());

        questionController.loadScratchQuestions();
        scratchQuestionsPool = questionsStore.getScratchQuestions();


        fragmentScratch = new ScratchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Questions", Parcels.wrap(scratchQuestionsPool));
        fragmentScratch.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_scratch, fragmentScratch).commit();
    }

    @Override
    public void onBackPressed() {

        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText(getString(R.string.exit));
        dialog.setContentText(getString(R.string.game_exit_confirmation));
        dialog.setConfirmText(getString(R.string.exit));
        dialog.setCancelText(getString(R.string.cancel));
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                ScratchGame.this.finish();
            }
        });
        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        dialog.show();
    }
}