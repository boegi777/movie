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
import java.util.*;

/**
 *
 * @author Juri
 */
public class ActorMovieTable {
    
    
    public void insertActorMovie(String actor,int movie_id){
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        PreparedStatement pst;
        ResultSet rs ;
        int actor_id = 0;
        
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT actor_id FROM actor WHERE actor_name = \""+actor+"\"");
            
            while(rs.next()){
                actor_id = rs.getInt(1);
            }
            pst = con.prepareStatement("INSERT INTO actormovie VALUES(?,?)");
            pst.setInt(1, actor_id);
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
    
    public ArrayList<String> selectActorMovie(String actor){
        
        ArrayList<String> movieList = new ArrayList<String>();
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        Statement stm2 = null;
        ResultSet rs ;
        ResultSet movRs;
        int actor_id = 0;
        
        
         try{
            stm = con.createStatement();
            stm2 = con.createStatement(); //Statement for selecting movie title
            rs = stm.executeQuery("SELECT actor_id FROM actor WHERE actor_name = \""+actor+"\"");
            
            while(rs.next()){
                actor_id = rs.getInt(1);
            }
            
            rs = stm.executeQuery("SELECT movie_id FROM actormovie WHERE actor_id = \""+Integer.toString(actor_id)+"\"");
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
    
     public String selectActorMovie(int movie_id){
        
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        Statement stm2 = null;
        ResultSet rs ;
        ResultSet actRs;
        int actor_id = 0;
        String actors = "";
        
         try{
            stm = con.createStatement();
            stm2 = con.createStatement(); //Statement for selecting movie title
            rs = stm.executeQuery("SELECT actor_id FROM actormovie WHERE movie_id = \""+movie_id+"\"");
            while(rs.next()){
                actor_id = rs.getInt(1);
                actRs = stm2.executeQuery("SELECT actor_name FROM actor WHERE actor_id = \""+actor_id+"\"");
                while(actRs.next()){
                    actors += actRs.getString(1)+", ";
                    
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
        
        return actors;
    }
    
}

