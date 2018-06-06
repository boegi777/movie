/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieData;

import MovieAppAPI.Objects.Movie;
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
    
    public void insertFavouriteMovie(int user_id,int movie_id){
        
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        PreparedStatement pst;
        ResultSet rs ;
        
        
        try{
         
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
    
      public ArrayList<Movie> selectFavouriteMovie(int user_id){
        
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm ;
        ResultSet rs ;
        Movie movie;
        
         try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT title FROM movie WHERE movie_id = (SELECT movie_id FROM favourite WHERE user_id = \""+Integer.toString(user_id)+"\")");
            while(rs.next()){
                    movie = new Movie(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                    movieList.add(movie);
                }
        
            
      
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        return movieList;
    }
      
  }

