package com.polymt.inf8405.tp3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.polymt.inf8405.tp3.baseclass.Friend;
import com.polymt.inf8405.tp3.baseclass.Me;
import com.polymt.inf8405.tp3.baseclass.User;

import java.util.List;

import static com.polymt.inf8405.tp3.R.*;

/**
 * Created by Wassim on 25/04/2017.
 */

public class friendsAdapter extends ArrayAdapter<User> {

    private List<User> friendsList;
    private Context context;

    public friendsAdapter(List<User> friendsList, Context context) {
        super(context, layout.view_friends, friendsList);
        this.friendsList = friendsList;
        this.context = context;
    }

    private static class orderHolder {

        public TextView orderName;
        public Button deleteFriend;
    }

    public void update(){

    }
    @Override
    public View getView(int pos, View cv, ViewGroup parent) {

        View v = cv;

        orderHolder holder = new orderHolder();

        if (cv == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(layout.view_friends, null);
            final User o = friendsList.get(pos);
            holder.orderName = (TextView) v.findViewById(id.friendname);
            holder.orderName.setText(o.getName());
            holder.deleteFriend = (Button) v.findViewById(id.deleteFriend);

            holder.deleteFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder build = new AlertDialog.Builder(context);

                    build.setTitle("Delete this Friend");
                    build.setMessage("Are you sure?");
                    build.setIcon(android.R.drawable.ic_dialog_alert);
                    final int pos = (int) v.getTag();
                    build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            update();
                            Me.getMe().removeFriend(o.getUniqueId());
                            friendsAdapter.this.notifyDataSetChanged();
                        }
                    });

                    build.setNegativeButton("No", null);
                    build.show();
                }
            });

        } else {
            holder.orderName = (TextView) v.findViewById(id.friendname);
            holder.deleteFriend = (Button) v.findViewById(id.deleteFriend);
        }
        holder.deleteFriend.setTag(pos);

        //holder.orderName.setText(o.getName());

        //holder.checkbox.setChecked(o.isSelected());

        return v;
    }

}