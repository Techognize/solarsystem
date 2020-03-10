package com.google.ar.sceneform.samples.solarsystem.ui.account;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.ar.sceneform.samples.solarsystem.DashboardActivity;
import com.google.ar.sceneform.samples.solarsystem.MainActivity;
import com.google.ar.sceneform.samples.solarsystem.R;
import com.google.ar.sceneform.samples.solarsystem.SolarActivity;
import com.google.ar.sceneform.samples.solarsystem.StartActivity;

import java.net.URI;

public class AccountFragment extends Fragment {

    Button arScreen;

    GoogleSignInClient mGoogleSignInClient;
    Button sign_out;
    TextView nameTV;
    TextView emailTV;
    ImageView photoIV;

    String personName;
    String personEmail;

    private AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        accountViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        sign_out = root.findViewById(R.id.log_out);
        nameTV = root.findViewById(R.id.name);
        emailTV = root.findViewById(R.id.email);
        photoIV = root.findViewById(R.id.photo);

        /*Intent intent = getActivity().getIntent();
        String personName = intent.getStringExtra("personName");
        String personEmail = intent.getStringExtra("personEmail");
        Uri personPhoto = intent.getData();*/

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(DashboardActivity.this);
        if (acct != null) {
             personName = acct.getDisplayName();
             personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            nameTV.setText("Name: "+personName);
            emailTV.setText("Email: "+personEmail);
            //Glide.with(this).load(personPhoto).into(photoIV);
        }


      /*  nameTV.setText(personName);
        emailTV.setText(personEmail);*/

       /* sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });*/

        /*arScreen = findViewById(R.id.id_arscreen);
        arScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(MainActivity.this, SolarActivity.class);
                startActivity(nextScreen);
            }
        });*/

        return root;

    }

   /* private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AccountFragment.this,"Successfully signed out", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, StartActivity.class));
                        finish();
                    }
                });
    }*/

    /*public void updateName(String name){
        nameTV.setText(name);
    }
    public void updateEmail(String email){
        emailTV.setText(email);
    }
    public void updatePhoto(Uri photo){
        Glide.with(getActivity()).load(photo).into(photoIV);
    }*/

}