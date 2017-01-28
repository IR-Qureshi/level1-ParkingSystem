package com.example.dellpc.parkingmanagement;

import android.app.TimePickerDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class UserBookActivity extends AppCompatActivity {

    //EditText..
    public EditText userName;
    public EditText slotno;
    public EditText startTime;
    public EditText endTime;
    //button..
    private Button BTN_post;

    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;
    //referencing a specific database
    private DatabaseReference mDatabaseReference;
    //authenticating the state change
    private FirebaseAuth mFirebaseAuth;

    private Calendar mcurrentTime;
    private int hour;
    private int minute;
    private TimePickerDialog mTimePicker;
    UserBookClass userBookClass = new UserBookClass();
    //fragments..
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_book);
        userName = (EditText) findViewById(R.id.editUsrName);
        slotno = (EditText) findViewById(R.id.editUsrSlot);
        startTime = (EditText) findViewById(R.id.eTslotFrom);
        endTime = (EditText) findViewById(R.id.eTslotTo);
        BTN_post = (Button) findViewById(R.id.BTNpost);

        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        //giving reference till the child node of the firebase database.
        mDatabaseReference = mFirebaseDatabase.getReference().child("userinfo");

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mcurrentTime = Calendar.getInstance();
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(UserBookActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Start Time");
                mTimePicker.show();

            }

        });
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               mcurrentTime = Calendar.getInstance();
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(UserBookActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select End Time");
                mTimePicker.show();

            }

        });

        BTN_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBookClass userBookClass1 = new UserBookClass(userName.getText().toString(),slotno.getText().toString(),startTime.getText().toString(),endTime.getText().toString());
                mDatabaseReference.push().setValue(userBookClass1);
                userName.setText("");
                slotno.setText("");
                startTime.setText("");
                endTime.setText("");

                frameLayout = (FrameLayout) findViewById(R.id.userBookFrame);
                frameLayout.setBackgroundResource(0);

                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.userBookFrame, new UserFBFragment()).commit();
            }
        });


    }
}
