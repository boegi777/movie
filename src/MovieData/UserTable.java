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
    private static final MovieDatabaseManager mdb = new MovieDatabaseManager();
    private static final Connection con = mdb.setConnection();
    private static PreparedStatement pst = null;
    private static Statement stm = null;
    private static ResultSet rs;
    
    
    public static void close(){
        try{
                if(pst != null) pst.close();
                if(con != null) con.close();
                if(stm!= null) stm.close();
                
            }catch(SQLException e){
                
                e.printStackTrace();
          }
    }
       
    public void insertUser(String name,String password){
     
        try{
            
            pst = con.prepareStatement("INSERT INTO `user` VALUES(?,?,?)");
            user_id++;
            UserTable.pst.setInt(1,user_id);
            pst.setString(2, name);
            pst.setString(3, password);
            pst.execute();
                    
        }catch(SQLException e ){
                
            e.printStackTrace();
            
        }finally{
            
            close();
        }
    }
                
    public User selectUser(String user,String password){
        
        User selectedUser = null;
        
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM `user` WHERE password = \""+password+"\" AND user_name = \""+user+"\"");
                    
            while(rs.next()){
                 selectedUser = new User(rs.getInt(1),rs.getString(2));
            }
           
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            close();
        }
        return selectedUser;     
    }
   
    
}
