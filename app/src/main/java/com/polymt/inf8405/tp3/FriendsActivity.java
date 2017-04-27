package com.polymt.inf8405.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.polymt.inf8405.tp3.baseclass.Friend;
import com.polymt.inf8405.tp3.baseclass.Me;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wassim on 11/04/2017.
 */

public class FriendsActivity extends AppCompatActivity {

    ListView lv;
    List<Friend> friendList;
    friendsAdapter friendAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);

        getSupportActionBar().setTitle("FriendsActivity list");
        // butListener();

        Me.getMe().getFriend();


        lv = (ListView) findViewById(R.id.listviewFriends);
        showFriendsList();
    }

    private void showFriendsList(){

        /*friendList = new ArrayList<>();
        friendList.add(new Friend("asd"));
        friendList.add(new Friend("asdasd"));*/
        friendList = Me.getMe().getFriend();
        friendAdap = new friendsAdapter(friendList, this);
//        friendAdap.addAll(friendList);

        //friendAdap.notifyDataSetChanged();
        lv.setAdapter(friendAdap);

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        getMenuInflater().inflate(R.menu.friends_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_videos:
                startActivity(new Intent(this, VideosActivity.class));
                return true;

            case R.id.action_map:
                startActivity(new Intent(this, MapsActivity.class));
                return true;

            case R.id.action_settings:
                startActivity(new Intent(this, Settings.class));
                return true;

            case R.id.action_addFriend:
                startActivity(new Intent(this, addFriend.class));
                return true;

            case R.id.action_logout:
                startActivity(new Intent(this,MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
