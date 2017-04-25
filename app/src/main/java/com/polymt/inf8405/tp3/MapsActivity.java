package com.polymt.inf8405.tp3;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.polymt.inf8405.tp3.baseclass.Invokable;
import com.polymt.inf8405.tp3.baseclass.Me;
import com.polymt.inf8405.tp3.baseclass.VideoInfo;
import com.polymt.inf8405.tp3.baseclass.VideoManager;

import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        getMenuInflater().inflate(R.menu.map_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_videos:
                startActivity(new Intent(this, Videos.class));
                return true;

            case R.id.action_friends:
                startActivity(new Intent(this, MapsActivity.class));
                return true;

            case R.id.action_settings:
                startActivity(new Intent(this, Settings.class));
                return true;

            case R.id.action_logout:
                startActivity(new Intent(this,MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        map.setOnMarkerClickListener(this);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        Invokable in = new Invokable(){
            @Override
            public void invoke() {
                Location loc = Me.getMe().getLocation();
                LatLng coordinate = new LatLng(loc.getLatitude(), loc.getLongitude());
                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                        coordinate,18);
                //CameraUpdate location = CameraUpdateFactory.newLatLng(
                //        coordinate);
                map.moveCamera(location);

                int radius = 15;//TODO need to figure out the radius
                List<VideoInfo> videoToPutMarker = VideoManager.getInstance().findVideoSurrounding(loc,radius);
                if(videoToPutMarker==null)
                    return;
                for(VideoInfo vi : videoToPutMarker){
                    Marker m = map.addMarker(new MarkerOptions()
                            .position(vi.getCoordinate())
                            .title(vi.getName())
                            .snippet(vi.getDescription())
                            .icon(BitmapDescriptorFactory.fromBitmap(vi.getThumbnail())));
                    m.setTag(vi.getUniqueId());
                }
            }
        };
        Me.getMe().setInvokeLocationFunc(in);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        int uniqueId = (int)marker.getTag();
        //TODO pop message to show video

        return false;
    }
}
