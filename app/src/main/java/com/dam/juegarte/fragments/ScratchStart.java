package com.dam.juegarte.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dam.juegarte.GameMode;
import com.dam.juegarte.R;
import com.dam.juegarte.ScratchQuestion;
import com.dam.juegarte.stores.GameModesStore;
import com.dam.juegarte.stores.ScratchQuestionsStore;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class ScratchStart extends Fragment {


    GameModesStore gameModesStore;
    GameMode scratch;
    TextView title;
    TextView instructions;
    Button start;
    FragmentTransaction transaction;
    ScratchFragment fragmentScratch;
    ScratchQuestionsStore questionsStore;
    ArrayList<ScratchQuestion> scratchQuestionsPool;

    public ScratchStart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gameModesStore = GameModesStore.getInstance(getActivity());
        scratch = gameModesStore.getGameModes().get(2);

        return inflater.inflate(R.layout.fragment_scratch_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = getView().findViewById(R.id.tv_scratch_title);
        instructions = getView().findViewById(R.id.tv_scratch_instructions);
        start =  getView().findViewById(R.id.btn_start);



        title.setText(scratch.getGameMode());
        instructions.setText(scratch.getInstructions());


        questionsStore =  ScratchQuestionsStore.getInstance(getActivity());
        scratchQuestionsPool = questionsStore.getScratchQuestions();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentScratch = new ScratchFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Questions", Parcels.wrap(scratchQuestionsPool));
                fragmentScratch.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentScratch, "SCRATCH_GAME").addToBackStack("SCRATCH_GAME").commit();
            }
        });
        


    }
}