package com.example.dellpc.parkingmanagement;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell pc on 28-Jan-17.
 */

public class UserBookAdapter extends ArrayAdapter<UserBookClass> {

    public UserBookAdapter(Context context, int resource,List<UserBookClass> objects) {
        super(context, resource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.custom_user_info,parent,false);
        }

        TextView username = (TextView) convertView.findViewById(R.id.c_user_name);
        TextView slotno = (TextView) convertView.findViewById(R.id.c_slot_no);
        TextView time = (TextView) convertView.findViewById(R.id.c_tTime);

        UserBookClass userBookClass = getItem(position);

        username.setText(userBookClass.getUsername());
        slotno.setText(userBookClass.getSlotno());
       // time.setText(userBookClass.);

        return convertView;
    }
}
