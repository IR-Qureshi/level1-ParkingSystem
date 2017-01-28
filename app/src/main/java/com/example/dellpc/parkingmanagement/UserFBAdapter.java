package com.example.dellpc.parkingmanagement;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by dell pc on 28-Jan-17.
 */

public class UserFBAdapter extends ArrayAdapter<UerFBClass>{
    public UserFBAdapter(Context context, int resource, int textViewResourceId, UerFBClass[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.custom_user_feedback,parent,false);
        }
        UerFBClass uerFBClass = getItem(position);

        TextView userfeedbk = (TextView) convertView.findViewById(R.id.c_userfb);
        userfeedbk.setText(uerFBClass.getUserFB());

        return convertView;
    }
}
