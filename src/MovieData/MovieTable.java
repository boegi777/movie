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
import MovieAppAPI.Objects.*;


/**
 *
 * @author Juri
 */
public class MovieTable {
    
    public void insertMovie(String title,String year,int genre_id,String director,String[]actor){
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        PreparedStatement pst;
        ActorTable actorTable = new ActorTable();
        DirectorTable directorTable = new DirectorTable();
        
        try{
            pst = con.prepareStatement("INSERT INTO movie(title,year,genre_id) VALUES(?,?,?)");
        
            pst.setString(1, title);
            if(year != "") pst.setString(2,year);
            pst.setInt(3, genre_id);
            pst.execute();
            //if(director != "") directorTable.insertDirector(movie_id,director);
            //if(actor.length != 0) actorTable.insertActor(movie_id,actor);
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                 con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }  
        }
    }
    
    public ArrayList<Movie> selectAllMovies(){
        String query = "Select * From movie";
        return executeQuery(query);
    }
    
    public ArrayList<Movie> selectMovie(String title){
       
        String query = "SELECT * FROM movie WHERE title LIKE \"%"+title+"%\"";
       
        return executeQuery(query);
           
    }
    
    public ArrayList<Movie> selectMovieYear(String year){
        
      String query = "SELECT * FROM movie WHERE year = \""+year+"\"";
      
      return executeQuery(query);
             
    }
        
      
    private ArrayList<Movie> executeQuery(String query){
        
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        ResultSet rs;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Movie movie;
        
         try{
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            
            while(rs.next()){
                movie = new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                movieList.add(movie);
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
