/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Juri
 */
public class FavouriteTable {
    
    public void insertFavouriteMovie(String user,String movie){
        
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        PreparedStatement pst;
        ResultSet rs ;
        int user_id = 0;
        int movie_id = 0;
        
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT user_id FROM user WHERE user_name = \""+user+"\"");
            
            while(rs.next()){
                user_id = rs.getInt(1);
            }
            rs = stm.executeQuery("SELECT movie_id FROM movie WHERE title = \""+movie+"\"");
             while(rs.next()){
                movie_id = rs.getInt(1);
            }
            
            pst = con.prepareStatement("INSERT INTO favourite VALUES(?,?)");
            pst.setInt(1, user_id);
            pst.setInt(2,movie_id);
            pst.execute();
            
            
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            try{
                stm.close();
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
      public ArrayList<String> selectFavouriteMovie(String user){
        
        ArrayList<String> movieList = new ArrayList<String>();
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        Statement stm2 = null;
        ResultSet rs ;
        ResultSet movRs;
        int user_id = 0;
        
        
         try{
            stm = con.createStatement();
            stm2 = con.createStatement(); //Statement for selecting movie title
            rs = stm.executeQuery("SELECT user_id FROM user WHERE user_name = \""+user+"\"");
            
            while(rs.next()){
                user_id = rs.getInt(1);
            }
            
            rs = stm.executeQuery("SELECT movie_id FROM favourite WHERE user_id = \""+Integer.toString(user_id)+"\"");
            while(rs.next()){
                movRs = stm2.executeQuery("SELECT title FROM movie WHERE movie_id =\""+Integer.toString(rs.getInt(1))+"\"");
                while(movRs.next()){
                    movieList.add(movRs.getString(1));
                }
        
            }
      
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            try{
                stm.close();
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        return movieList;
    }
      
  }

