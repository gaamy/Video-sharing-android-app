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

import java.util.List;

/**
 * Created by Wassim on 25/04/2017.
 */

class friend {

    String name;
    boolean selected= false;

    public friend(String name){

        super();
        this.name= name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

public class friendsAdapter extends ArrayAdapter<Friends> {

    private List<Friends> friendsList;
    private Context context;

    public friendsAdapter(List<Friends> friendsList, Context context) {
        super(context, R.layout.view_friends, friendsList);
        this.friendsList = friendsList;
        this.context = context;
    }

    private static class orderHolder {

        public TextView orderName;
        public Button deleteFriend;
    }

    @Override
    public View getView(int pos, View cv, ViewGroup parent) {

        View v = cv;

        orderHolder holder = new orderHolder();

        if (cv == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.view_videos, null);

            holder.orderName = (TextView) v.findViewById(R.id.name);
            holder.deleteFriend = (Button) v.findViewById(R.id.delete);

            holder.deleteFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder build = new AlertDialog.Builder(context);

                    build.setTitle("Delete this video");
                    build.setMessage("Are you sure?");
                    build.setIcon(android.R.drawable.ic_dialog_alert);
                    final int pos = (int) v.getTag();
                    build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            friendsList.remove(pos);
                            friendsAdapter.this.notifyDataSetChanged();
                        }
                    });

                    build.setNegativeButton("No", null);
                    build.show();
                }
            });

        } else {
            holder.orderName = (TextView) v.findViewById(R.id.name);
            holder.deleteFriend = (Button) v.findViewById(R.id.delete);
        }
        holder.deleteFriend.setTag(pos);
        Friends o = friendsList.get(pos);
        //holder.orderName.setText(o.getName());

        //holder.checkbox.setChecked(o.isSelected());

        return v;
    }

}