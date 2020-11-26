package com.dam.juegarte.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam.juegarte.R;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;



public class GameComplete extends Fragment {




    public GameComplete() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_complete, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  deployBottomDialog("Game complete!", "Your total score is: " + totalScore + " of " );
    }


    public void deployBottomDialog(String title, String message) {
        BottomSheetMaterialDialog mDialog = new BottomSheetMaterialDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setAnimation(R.raw.fireworks_animation)
                .setNeutralButton(getString(R.string.exit), new BottomSheetMaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        getActivity().finish();
                    }
                })
                .build();

        // Show Dialog
        mDialog.show();

    }
}