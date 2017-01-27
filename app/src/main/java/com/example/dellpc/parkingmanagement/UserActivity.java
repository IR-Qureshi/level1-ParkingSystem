package com.example.dellpc.parkingmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{
    //Request code
    //It is a flag which we'd placed uiAuth while we're logging in from diff UI.
    public static final int RC_SIGN_IN = 1;
    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;

    //referencing a specific database
    private DatabaseReference mDatabaseReference;
    //authenticating the state change
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage mFirebaseStorage;
    public static final String ANONYMOUS = "anonymous";
    private String mUsername;
    TextView Lslot1, Lslot2, Lslot3, Lslot4, Lslot5, Lslot6;
    TextView Rslot1, Rslot2, Rslot3, Rslot4, Rslot5, Rslot6;
    //fragments..
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private FrameLayout frameLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    //user is signed in
                    onSignedInInitialize(firebaseUser.getDisplayName());
                    //Toast.makeText(MainActivity.this, "You're Signed in Welcome to the Friendly Chat App", Toast.LENGTH_SHORT).show();

                } else {
                    //user is signed out

                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    //.setIsSmartLockEnabled(false)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER,
                                            AuthUI.FACEBOOK_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);

                }

            }
        };
        Lslot1 = (TextView) findViewById(R.id.L_slot1);
        Lslot1.setOnClickListener(this);
        Lslot2 = (TextView) findViewById(R.id.L_slot2);
        Lslot2.setOnClickListener(this);
        Lslot3 = (TextView) findViewById(R.id.L_slot3);
        Lslot3.setOnClickListener(this);
        Lslot4 = (TextView) findViewById(R.id.L_slot4);
        Lslot4.setOnClickListener(this);
        Lslot5 = (TextView) findViewById(R.id.L_slot5);
        Lslot5.setOnClickListener(this);
        Lslot6 = (TextView) findViewById(R.id.L_slot6);
        Lslot6.setOnClickListener(this);
        Rslot1 = (TextView) findViewById(R.id.R_slot1);
        Rslot1.setOnClickListener(this);
        Rslot2 = (TextView) findViewById(R.id.R_slot2);
        Rslot2.setOnClickListener(this);
        Rslot3 = (TextView) findViewById(R.id.R_slot3);
        Rslot3.setOnClickListener(this);
        Rslot4 = (TextView) findViewById(R.id.R_slot4);
        Rslot4.setOnClickListener(this);
        Rslot5 = (TextView) findViewById(R.id.R_slot5);
        Rslot5.setOnClickListener(this);
        Rslot6 = (TextView) findViewById(R.id.R_slot6);
        Rslot6.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        //removing the background image of userAvtivity frame layout
//        frameLayout = (FrameLayout) findViewById(R.id.activity_user);
//        frameLayout.setBackgroundResource(0);
//
//        mFragmentManager = getSupportFragmentManager();
//        mFragmentTransaction = mFragmentManager.beginTransaction();
//        mFragmentTransaction.replace(R.id.activity_user, new UserBookFragment()).commit();

        Intent intent = new Intent(UserActivity.this, UserBookActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {

                Log.d("Signin", "onActivityResult: " + " OK");
                Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show();
            } else if (requestCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }
    private void onSignedInInitialize(String username) {
        mUsername = username;
    }
    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
