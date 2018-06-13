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
import java.util.*;

/**
 *
 * @author Juri
 */
public class ActorMovieTable {
    
    private static final MovieDatabaseManager mdb = new MovieDatabaseManager();      
    private static PreparedStatement pst = null;
    private static Connection con = null;   
    private static Statement stm = null;
    private static ResultSet rs = null;
    private static MovieTable movieTable = new MovieTable();
    private static ActorTable actorTable = new ActorTable();
    
    
    public void insertActorMovie(String actor,int movie_id){
        int actor_id = actorTable.getActorIdByName(actor) ;
        try{
            con = mdb.setConnection();
            pst = con.prepareStatement("INSERT INTO actormovie VALUES(?,?)");
            pst.setInt(1, actor_id);
            pst.setInt(2,movie_id);
            pst.execute();  
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            mdb.ConnectionClose(con,stm,pst);
        }
    }
    public ArrayList<Movie> selectActorMovie(String actor){
        ArrayList<Integer> movie_id_list = getMovieIdByActor(actor);
        return movieTable.selectMovieListById(movie_id_list);
    }
    
     public String selectActorMovie(int movie_id){
        ArrayList<Integer> actor_id_list = getActorIdByMovieId(movie_id);
        return actorTable.getActorById(actor_id_list);
    }
     
     public ArrayList<Integer> getActorIdByMovieId(int movie_id){
        ArrayList<Integer> actor_id_list = new ArrayList<>();
        try{
            con = mdb.setConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT actor_id FROM actormovie WHERE movie_id = \""+movie_id+"\"");
            while(rs.next()){
              actor_id_list.add(rs.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            mdb.ConnectionClose(con, stm, pst);
        }
        return actor_id_list;
    }
     
    public ArrayList<Integer> getMovieIdByActor(String actor){
         ArrayList<Integer> movie_id_list = new ArrayList<>();
         try{
            con = mdb.setConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT movie_id FROM actormovie WHERE actor_id = (SELECT actor_id FROM actor WHERE actor_name = \""+actor+"\")");
            while(rs.next()){
                movie_id_list.add(rs.getInt(1));
            }
         }catch(SQLException e){
             e.printStackTrace();
         }finally{
                mdb.ConnectionClose(con, stm, pst);
         }
         return movie_id_list;
     }
}

