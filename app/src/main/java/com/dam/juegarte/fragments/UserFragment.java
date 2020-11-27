package com.dam.juegarte.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.dam.juegarte.R;
import com.dam.juegarte.User;
import com.dam.juegarte.stores.GameModesStore;
import com.dam.juegarte.stores.UserSessionStore;

public class UserFragment extends Fragment implements View.OnClickListener{

    private ImageView ivUserImage;
    private TextView tvUsername;
    private TextView tvEmail;
    private TextView tvPoints;
    private static final String BASE_URL = "http://10.0.2.2/juegarte-API";

    UserSessionStore userStore;
    GameModesStore gameModesStore;
    private User userData;

    private AppCompatActivity activity;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        ivUserImage = view.findViewById(R.id.iv_user_image);
        tvUsername = view.findViewById(R.id.tv_username);
        tvEmail = view.findViewById(R.id.tv_email);
        tvPoints = view.findViewById(R.id.tv_points);

        userStore = new UserSessionStore(getActivity().getApplicationContext());

        //para configurar el toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.bar_profile_name);
        toolbar.setBackgroundColor(Color.parseColor("#4ab2e6"));
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        userData = userStore.getUserData();
        setUserData();
        loadUserAvatar();

        return view;
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

    @Override
    public void onClick(View view) {

    }
}