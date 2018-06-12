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
    
    private static final MovieDatabaseManager mdb = new MovieDatabaseManager();
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static Statement stm = null;
    private static ResultSet rs = null;
    
    public void insertUser(String name,String password){
     
        try{
            con = mdb.setConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement("INSERT INTO `user`(user_name,password) VALUES(?,?)");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.execute();
            con.commit();       
        }catch(SQLException e ){     
            mdb.insertRollBack(con,e);
        }finally{
            mdb.ConnectionClose(con,stm,pst);
        }
    }
                
    public User selectUser(String user,String password){
        User selectedUser = null;
        try{
            con = mdb.setConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM `user` WHERE password = \""+password+"\" AND user_name = \""+user+"\"");   
            while(rs.next()){
                 selectedUser = new User(rs.getInt(1),rs.getString(2));
            }
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            mdb.ConnectionClose(con,stm,pst);
        }
        return selectedUser;     
    }
   
    
}
