package com.dam.juegarte;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dam.juegarte.fragments.ScratchStart;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

public class ScratchGame extends AppCompatActivity {


    ScratchStart scratchStart;

    public ScratchGame(){

    }

    public ScratchGame(Context context){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_game);

/*
        questionsStore = new ScratchQuestionsStore(this);
        scratchQuestionsPool = questionsStore.getScratchQuestions();
*/

        //Log.d("Scratch SG", scratchQuestionsPool.toString());

        scratchStart = new ScratchStart();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, scratchStart).commit();

/*
        fragmentScratch = new ScratchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Questions", Parcels.wrap(scratchQuestionsPool));
        fragmentScratch.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_scratch, fragmentScratch).commit();

*/
    }

    public String questionBase(String optionCorrect){

        String optionA = "A";
        String optionB = "B";
        String optionC = "C";
        String optionD = "D";

        if(optionCorrect == optionA){
            return "A";
        } else if(optionCorrect == optionB){
            return "B";
        } else if(optionCorrect == optionC){
            return "C";
        } else if(optionCorrect == optionD){
            return "D";
        } else {
            return "ERROR";
        }

    }

    @Override
    public void onBackPressed() {
        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle(getString(R.string.exit))
                .setMessage(getString(R.string.game_exit_confirmation))
                .setCancelable(false)
                .setAnimation(R.raw.question_mark)
                .setPositiveButton(getString(R.string.exit), new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        ScratchGame.this.finish();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .build();

        // Show Dialog
        mDialog.show();
    }
}