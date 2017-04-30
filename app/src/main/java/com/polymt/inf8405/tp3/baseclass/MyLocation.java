package com.polymt.inf8405.tp3.baseclass;

import android.location.Location;

/**
 * Created by gamyot on 2017-04-30.
 */

public class MyLocation extends Location {
    public MyLocation(){
        super("");
    }

    public MyLocation(String provider) {
        super(provider);
    }

}
