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
    private static int movie_id;
    
    public void insertMovie(String title,String year,int genre_id,String director,String[]actor){
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        PreparedStatement pst;
        ActorTable actorTable = new ActorTable();
        DirectorTable directorTable = new DirectorTable();
        
        try{
            pst = con.prepareStatement("INSERT INTO movie VALUES(?,?,?,?)");
            movie_id++;
            
            pst.setInt(1,movie_id);
            pst.setString(2, title);
            
            if(year != ""){
                pst.setString(3,year);
            }else{
                pst.setString(3,null);
            }
            
            pst.setInt(4, genre_id);
            
            
            pst.execute();
            
            if(director != "") directorTable.insertDirector(movie_id,director);
            
            if(actor.length != 0) actorTable.insertActor(movie_id,actor);
            
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
    
    public ArrayList<Movie> selectMovie(String title){
        //DirectorMovieTable directorMovie = new DirectorMovieTable();
        //ActorMovieTable actorMovie = new ActorMovieTable();
        //GenreTable genre = new GenreTable();    
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        ResultSet rs = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Movie movie;
   
        
        
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM movie WHERE title LIKE \"%"+title+"%\"");
            
            while(rs.next()){
                //movie= new Movie(rs.getInt(1),rs.getString(2),rs.getString(3),genre.selectGenre(rs.getInt(4)),directorMovie.selectDirectorMovie(rs.getInt(1)),actorMovie.selectActorMovie(rs.getInt(1)));
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
    
    public ArrayList<String> selectMovieYear(String year){
        
        ArrayList<String> movieList = new ArrayList<String>();
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        ResultSet rs ;
       
        
        
         try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT title FROM movie WHERE year = \""+year+"\"");
            
            while(rs.next()){
                 movieList.add(rs.getString(1));
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
        
      
    
    public static void main(String[]args){
        MovieTable table = new MovieTable();
        String[] list = {"hans","franz","Dieter"};
        String[] actor = {"hans","franz","riter"};
        
        table.insertMovie("Hey", "1999", 1, "Jericho", list);
        table.insertMovie("H", "1999", 2, "Jericho", actor);
        table.insertMovie("hu", "1929", 2, "Hans", actor);
    }
    
    
}
