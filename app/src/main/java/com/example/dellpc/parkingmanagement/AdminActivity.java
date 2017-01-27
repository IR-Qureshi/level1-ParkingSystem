package com.example.dellpc.parkingmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class AdminActivity extends AppCompatActivity {
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

    //Buttons..
    Button BTNparking;
    Button BTNalloted;
    Button BTNfeedback;
    Button BTNlogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();

        BTNparking = (Button) findViewById(R.id.BTN_AdminParkView);
        BTNalloted = (Button) findViewById(R.id.BTN_Admin_AllotView);
        BTNfeedback = (Button) findViewById(R.id.BTN_AdminFBView);
        BTNlogout = (Button) findViewById(R.id.BTN_Admin_Logout);

        BTNparking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the parkingRemaining in fragment in framelayout.
            }
        });
        BTNalloted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the parking alloted in fragment in frame layout.
            }
        });
        BTNfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the userfeed back in fragment in frame layout.
            }
        });
        BTNlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signing out..
                AuthUI.getInstance().signOut(AdminActivity.this);
            }
        });





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

    @Override
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
