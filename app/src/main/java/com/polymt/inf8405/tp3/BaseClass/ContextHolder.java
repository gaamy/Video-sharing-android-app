package com.polymt.inf8405.tp3.BaseClass;

import android.content.Context;


public class ContextHolder {
    private static Context mainContext = null;
    public static void setMainContext(Context context){
        mainContext = context;
    }
    public static Context getMainContext(){
        return mainContext;
    }

    private static Context currentContext = null;
    public static void setCurrentContext(Context context){
        currentContext = context;
    }
    public static Context getCurrentContext(){
        return currentContext;
    }

    public static boolean fillingState = false;
    public static void setFillingState(boolean b) {
        fillingState = true;
    }
}
