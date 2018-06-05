/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieAppAPI;

import MovieAppAPI.Objects.User;
import java.util.ArrayList;

/**
 *
 * @author paul
 */
public class UserManager {
    ArrayList<User> users = null;
    
    public UserManager(){
        users = new ArrayList();
    }
    public void LoginUser(String username, String password){
        User user = new User();
        users.add(user);
    }
    public User GetUser(Integer userId){
        for(User user : users){
            if(user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }
}
