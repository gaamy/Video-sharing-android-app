package com.polymt.inf8405.tp3.baseclass;



/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class UserManager {
    private static UserManager m_userManager;
    public static UserManager getIntance(){
        if(m_userManager == null){
            m_userManager = new UserManager();
        }
        return m_userManager;
    }
    private UserManager(){

    }

    public void logging(String uId){

        //TODO verify info are good
        if(true){
            Me.setMe(uId);
        }
    }
}
