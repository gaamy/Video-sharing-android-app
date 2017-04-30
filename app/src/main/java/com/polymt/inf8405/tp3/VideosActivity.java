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
import com.polymt.inf8405.tp3.baseclass.MyLocation;
import com.polymt.inf8405.tp3.baseclass.User;
import com.polymt.inf8405.tp3.baseclass.VideoInfo;
import com.polymt.inf8405.tp3.baseclass.VideoManager;
import com.polymt.inf8405.tp3.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wassim on 04/04/2017.
 */

public class VideosActivity extends AppCompatActivity {

    ListView lv;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    VideoView vid;
    List<VideoInfo> videoList;
    videoAdapter vidAdap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);

        vid = (VideoView) findViewById(R.id.VideoView);


        ContextHolder.setMainContext(this);

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.fillDatabaseForDemo();

        User me = databaseManager.gatherUserByMail("gabriel.amyot@gmail.com");

        while (me == null){
            me = databaseManager.gatherUserByMail("gabriel.amyot@gmail.com");
        }

        Me.setMe(me.getUniqueId());
        //TODO set the user instead of asd

        getSupportActionBar().setTitle("My Videos");

        VideoManager.getInstance().loadMyVideo();


        lv = (ListView) findViewById(R.id.listview);
        videoList = new ArrayList<>();
        vidAdap = new videoAdapter(videoList, this){
            @Override
            public void update() {
                showVideosList();
            }
        };
        showVideosList();

    }

    private void showVideosList(){

        /*videoList = new ArrayList<VideoInfo>();
        videoList.add(new VideoInfo("TestListVideo",Me.getMe().getLocation(),"Descrition lala",Me.getMe().getName()));
        videoList.add(new VideoInfo("SecondTestVideo",Me.getMe().getLocation(),"Descrition lala",Me.getMe().getName()));
*/
        vidAdap.clear();
        videoList = VideoManager.getInstance().loadMyVideo();
        vidAdap.addAll(videoList);
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
                VideoInfo vi = new VideoInfo("video", (MyLocation) Me.getMe().getLocation(),"no description",Me.getMe().getName());
                VideoManager.getInstance().postVideo(vi);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_friends:
                startActivity(new Intent(this, FriendsActivity.class));
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
