package com.dam.juegarte;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.dam.juegarte.stores.ScratchQuestionsStore;

import org.parceler.Parcels;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ScratchGame extends AppCompatActivity {

    FragmentTransaction transaction;
    ScratchFragment fragmentScratch;

    ScratchQuestionsStore questionsStore;
    ArrayList<ScratchQuestion> scratchQuestionsPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_game);


        questionsStore = new ScratchQuestionsStore(this);
        scratchQuestionsPool = questionsStore.getScratchQuestions();


        //Log.d("Scratch SG", scratchQuestionsPool.toString());



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