package com.polymt.inf8405.tp3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.VideoView;

import com.polymt.inf8405.tp3.baseclass.ContextHolder;
import com.polymt.inf8405.tp3.baseclass.Me;
import com.polymt.inf8405.tp3.baseclass.VideoManager;

import java.util.ArrayList;

/**
 * Created by Wassim on 04/04/2017.
 */

public class Videos extends AppCompatActivity {

    ListView lv;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    VideoView vid;
    ArrayList<video> videoList;
    videoAdapter vidAdap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);

        vid = (VideoView) findViewById(R.id.VideoView);


        ContextHolder.setMainContext(this);
        Me.setMe("ASD");
        //TODO set the user instead of asd

        getSupportActionBar().setTitle("My Videos");

        VideoManager.getInstance().loadMyVideo();


        lv = (ListView) findViewById(R.id.listview);
        showVideosList();

    }

    private void showVideosList(){

        videoList = new ArrayList<video>();
        videoList.add(new video("asd"));
        videoList.add(new video("asdasd"));

//        vidAdap = new videoAdapter(videoList, this);
//        vidAdap.addAll(videoList);
        //vidAdap.notifyDataSetChanged();
        lv.setAdapter(vidAdap);

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        getMenuInflater().inflate(R.menu.videos_menu, menu);
        return true;
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            if (intent != null && intent.getExtras() != null) {
                Uri videoUri = intent.getData();
                vid.setVideoURI(videoUri);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_friends:
                startActivity(new Intent(this, Friends.class));
                return true;
            case R.id.action_map:
                startActivity(new Intent(this, MapsActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, Settings.class));
                return true;

            case R.id.action_addVideo:
                dispatchTakeVideoIntent();
                return true;

            case R.id.action_logout:
                startActivity(new Intent(this,MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
