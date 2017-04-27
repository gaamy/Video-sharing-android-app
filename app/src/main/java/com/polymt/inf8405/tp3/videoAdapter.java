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

import com.polymt.inf8405.tp3.baseclass.VideoInfo;

import java.util.List;

import static com.polymt.inf8405.tp3.R.*;

/**
 * Created by Wassim on 25/04/2017.
 */


public class videoAdapter extends ArrayAdapter<VideoInfo> {

    private List<VideoInfo> videosList;
    private Context context;

    public videoAdapter(List<VideoInfo> videosList, Context context) {
        super(context, layout.view_videos, videosList);
        this.videosList = videosList;
        this.context = context;
    }

    private static class orderHolder {

        public TextView orderName;
        public Button delete;
    }

    @Override
    public View getView(int pos, View cv, ViewGroup parent) {

        View v = cv;

        orderHolder holder = new orderHolder();

        if (cv == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(layout.view_videos, null);

            VideoInfo o = videosList.get(pos);
            holder.orderName = (TextView) v.findViewById(id.namevideo);
            holder.orderName.setText(o.getName());

            holder.delete = (Button) v.findViewById(id.deletevideo);

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder build = new AlertDialog.Builder(context);

                    build.setTitle("Delete this video");
                    build.setMessage("Are you sure?");
                    build.setIcon(android.R.drawable.ic_dialog_alert);
                    final int pos = (int) v.getTag();
                    build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            videosList.remove(pos);
                            videoAdapter.this.notifyDataSetChanged();
                        }
                    });

                    build.setNegativeButton("No", null);
                    build.show();
                }
            });

        } else {
            holder.orderName = (TextView) v.findViewById(id.namevideo);
            holder.delete = (Button) v.findViewById(id.deletevideo);
        }
        holder.delete.setTag(pos);
        //holder.orderName.setText(o.getName());

        //holder.checkbox.setChecked(o.isSelected());

        return v;
    }
}