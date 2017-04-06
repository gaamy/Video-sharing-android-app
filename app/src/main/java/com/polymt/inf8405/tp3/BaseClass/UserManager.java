package com.polymt.inf8405.tp3.BaseClass;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class UserManager {
    private UserManager m_userManager;
    public UserManager getIntance(){
        if(m_userManager == null){
            m_userManager = new UserManager();
        }
        return m_userManager;
    }
    private UserManager(){

    }
}
