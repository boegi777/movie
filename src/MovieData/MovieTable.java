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
    
    private static final MovieDatabaseManager mdb = new MovieDatabaseManager();      
    private static PreparedStatement pst = null;
    private static Connection con = null;   
    private static Statement stm = null;
    private static ResultSet rs = null;
    private static ActorTable actorTable = new ActorTable();
    private static DirectorTable directorTable = new DirectorTable();
 
    public void insertMovie(String title,String year,int genre_id,String director,String[]actor){
        try{
            con = mdb.setConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement("INSERT INTO movie(title,year,genre_id) VALUES(?,?,?)");
            pst.setString(1, title);
            if(year != "") pst.setString(2,year);
            pst.setInt(3, genre_id);
            pst.execute();
            con.commit();
           
        }catch(SQLException ex){
            mdb.insertRollBack(con,ex);
        }finally{
            mdb.ConnectionClose(con,stm,pst); 
            if(director != "") directorTable.insertDirector(title,director);
            if(actor.length != 0) actorTable.insertActor(title,actor);
            
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
    
    public ArrayList<Movie> selectMovieListById(ArrayList<Integer> movie_id_list){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Movie movie;
        for(int movie_id: movie_id_list){
            String query = "SELECT * FROM movie WHERE movie_id =\""+Integer.toString(movie_id)+"\"";
            try{
                con = mdb.setConnection();
                stm = con.createStatement();
                rs = stm.executeQuery(query);
                while(rs.next()){
                    movie = new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                    movieList.add(movie);
                }
            }catch(SQLException e){
                 e.printStackTrace(); 
            }finally{
                    mdb.ConnectionClose(con,stm,pst);
            }   
        }
        return movieList;
    }
        
    private ArrayList<Movie> executeQuery(String query){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Movie movie;
         try{
            con = mdb.setConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while(rs.next()){
                movie = new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                movieList.add(movie);
            }
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            mdb.ConnectionClose(con,stm,pst);
        }
        return movieList; 
    }
       
    public int getMovieIdByTitle(String title){
        int movie_id = 0;
        try{
            con = mdb.setConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT movie_id FROM movie WHERE title = \""+title+"\"");
            while(rs.next()){
                movie_id = rs.getInt(1);
          }  
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            mdb.ConnectionClose(con,stm,pst);
        }
        return movie_id;
    }
}
