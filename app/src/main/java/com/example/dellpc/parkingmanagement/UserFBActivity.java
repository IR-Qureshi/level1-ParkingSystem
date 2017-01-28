package com.example.dellpc.parkingmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserFBActivity extends AppCompatActivity {
    public EditText userFB;
    public Button userFBPost;
    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;
    //referencing a specific database
    private DatabaseReference mDatabaseReference;

    UerFBClass userFBClass = new UerFBClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_fb);

        userFB = (EditText)findViewById(R.id.EduserFB);
        userFBPost = (Button) findViewById(R.id.BTN_PostFB);

        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        //giving reference till the child node of the firebase database.
        mDatabaseReference = mFirebaseDatabase.getReference().child("userfeedback");

        userFBPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UerFBClass userFBClass1 = new UerFBClass(userFB.getText().toString());
                mDatabaseReference.push().setValue(userFBClass1);
                userFB.setText("");
            }
        });
    }
}
