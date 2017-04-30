package com.polymt.inf8405.tp3.baseclass;


import com.polymt.inf8405.tp3.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class UserManager {
    private static UserManager m_userManager;
    private DatabaseManager databaseManager;

    public static UserManager getIntance(){
        if(m_userManager == null){
            m_userManager = new UserManager();
        }
        return m_userManager;
    }
    private UserManager(){
        this.databaseManager = DatabaseManager.getInstance();
    }

    public void logging(String uId){
        Me.setMe(uId);
    }


    public void addUser(String userName, String userMail){
        User newUser = new User(userName,userMail);
        databaseManager.createNewUSer(newUser);
    }

    String findUserId(String userName){
        String id = databaseManager.findUserId(userName);
        if(id == null){
            return null;
        }
        return id;
    }

    public boolean addFriend(String userId, String friendMail){
        //gather involved users
        if(databaseManager.gatherUserByMail(friendMail) != null){
            User friend = databaseManager.gatherUserByMail(friendMail);
            User meUser = Me.getMe();

            //update local users
            friend.getFriends().put(meUser.getUniqueId(),true);
            meUser.getFriends().put(friend.getUniqueId(),true);

            //propagate the change
            databaseManager.updateDatabaseUser(meUser);
            databaseManager.updateDatabaseUser(friend);
            return true;
        } else {
            return false;
        }
    }

    public List<User> getUserFriends(String uid){
        List<User> friends = new ArrayList<>();
        List<User> users = databaseManager.getUsers();
        for (User user:users){
            if(user.getFriends().containsKey(uid)){
                friends.add(user);
            }
        }
        return friends;
    }

    public void removeFriend(String userId, String friendUserId){
        //gather involved users
        User friend = databaseManager.gatherUser(friendUserId);
        User meUser = Me.getMe();

        //update local users
        if(friend.getFriends().get(meUser.getUniqueId())){
            friend.getFriends().remove(userId);
        }
        if(meUser.getFriends().get(friend.getUniqueId())){
            meUser.getFriends().remove(friendUserId);
        }

        //propagate the change
        databaseManager.updateDatabaseUser(meUser);
        databaseManager.updateDatabaseUser(friend);
    }
}
