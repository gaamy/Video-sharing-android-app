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
    public void loggin(String name, String password){
        //TODO verify info are good
        if(true){

        }
    }


    void addUser(String userId, String userName, String userMail){
        //TODO:"unimplemented method";
    }

    String findUserId(String userName){
        //TODO:"unimplemented method";
        return "unimplemented method";
    }

    void addFriend(String userId, String friendUserId){
        //TODO:"unimplemented method";
    }

    void removeFriend(String userId, String friendUserId){
        //TODO:"unimplemented method";
    }

}
