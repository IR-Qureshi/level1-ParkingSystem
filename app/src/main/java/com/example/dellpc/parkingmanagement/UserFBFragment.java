package com.example.dellpc.parkingmanagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class UserFBFragment extends Fragment {

    private ListView mUserFBListView;


    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;

    //referencing a specific database
    private DatabaseReference mDatabaseReference;
    //to read from each child
    private ChildEventListener mChildEventListener;
    private UserFBAdapter mUserFBAdapter;

    UerFBClass userfbList = new UerFBClass();


    public UserFBFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_fb, container, false);

        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //giving reference till the child node of the firebase database.
        mDatabaseReference = mFirebaseDatabase.getReference().child("userfeedback");

        mUserFBListView = (ListView) view.findViewById(R.id.List_usrfb);

        // Initialize message ListView and its adapter
        List<UerFBClass> userFBList = new ArrayList<>();
        mUserFBAdapter = new UserFBAdapter(view.getContext(), R.layout.custom_user_feedback,userFBList);
        mUserFBListView.setAdapter(mUserFBAdapter);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //message will deserielize into the same pattern as in Friendly class we've created.
                //and attaching the message to an adapter.
                UerFBClass userfbClass1 = dataSnapshot.getValue(UerFBClass.class);
                mUserFBAdapter.add(userfbClass1);

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

}
