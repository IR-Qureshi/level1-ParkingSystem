package com.example.dellpc.parkingmanagement;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParkAllotedFrament extends Fragment {

    private ListView mUserInfoListView;


    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;

    //referencing a specific database
    private DatabaseReference mDatabaseReference;
    //to read from each child
    private ChildEventListener mChildEventListener;
    private UserBookAdapter mUserBookAdapter;

    UserBookClass userinfoList = new UserBookClass();


    public ParkAllotedFrament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_park_alloted_frament, container, false);

        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //giving reference till the child node of the firebase database.
        mDatabaseReference = mFirebaseDatabase.getReference().child("userinfo");

        mUserInfoListView = (ListView) view.findViewById(R.id.List_parkAllot);

        // Initialize message ListView and its adapter
        List<UserBookClass> userinfoList = new ArrayList<>();
        mUserBookAdapter = new UserBookAdapter(view.getContext(), R.layout.custom_user_info,userinfoList);
        mUserInfoListView.setAdapter(mUserBookAdapter);

            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    //message will deserielize into the same pattern as in Friendly class we've created.
                    //and attaching the message to an adapter.
                    UserBookClass userBookClass = dataSnapshot.getValue(UserBookClass.class);
                    mUserBookAdapter.add(userBookClass);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mDatabaseReference.addChildEventListener(mChildEventListener);


        return view;
    }
//    private void attachDatabaseReadListener(){
//        if(mChildEventListener == null) {
//            mChildEventListener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                    //message will deserielize into the same pattern as in Friendly class we've created.
//                    //and attaching the message to an adapter.
//                    UserBookClass userBookClass = dataSnapshot.getValue(UserBookClass.class);
//                    mUserBookAdapter.add(userBookClass);
//
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            };
//
//            mDatabaseReference.addChildEventListener(mChildEventListener);
//        }
//    }
//    private void detachDatabaseReadListener(){
//        if(mChildEventListener != null) {
//            mDatabaseReference.removeEventListener(mChildEventListener);
//            mChildEventListener = null;
//        }
//
//    }


}
