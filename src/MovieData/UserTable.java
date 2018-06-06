/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieData;

import MovieAppAPI.Objects.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Juri
 */
public class UserTable {
    
    private static int user_id;
  
    
    public void insertUser(String name,String password){
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        PreparedStatement pst = null;
        
        try{
            pst = con.prepareStatement("INSERT INTO `user` VALUES(?,?,?)");
            user_id++;
            pst.setInt(1,user_id);
            pst.setString(2, name);
            pst.setString(3, password);
            pst.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(pst != null && con != null){
                      pst.close();
                      con.close();
                }
              
            }catch(SQLException e){
                e.printStackTrace();
            }  
        }
    }
    
    public User selectUser(String user,String password){
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        ResultSet rs;
        User selectedUser = null;
        
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM `user` WHERE passsword = \""+password+"\" AND user_name = \""+user+"\"");
                    
            while(rs.next()){
                 selectedUser = new User(rs.getInt(1),rs.getString(2));
            }
           
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            try{
                 if(stm != null && con != null){
                      stm.close();
                      con.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return selectedUser;     
    }
   
    
}
