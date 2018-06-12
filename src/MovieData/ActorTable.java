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
public class ActorTable {
    
    private static final MovieDatabaseManager mdb = new MovieDatabaseManager();      
    private static PreparedStatement pst = null;
    private static Connection con = null;   
    private static Statement stm = null;
    private static ResultSet rs = null;
    private static MovieTable movieTable;
    private static ActorMovieTable actorMovie;
    
   
    
    public void insertActor(String title, String[] actor){
        movieTable = new MovieTable();
        actorMovie = new ActorMovieTable();
        int movie_id = movieTable.getMovieIdByTitle(title);
        try{
             con = mdb.setConnection();
             con.setAutoCommit(false);
             for(String actName : actor){
                if(getActorIdByName(actName)== 0){
                    pst = con.prepareStatement("INSERT INTO actor(actor_name) VALUES(?)");
                    pst.setString(1, actName);
                    pst.execute();
                    con.commit();
                    actorMovie.insertActorMovie(actName, movie_id);
                }else{
                    actorMovie.insertActorMovie(actName,movie_id);
                }
             }     
        }catch(SQLException e){
           mdb.insertRollBack(con,e);
        }finally{
            mdb.ConnectionClose(con, stm, pst);
        }
    }
    
    public int getActorIdByName(String actor){
        int actor_id = 0;
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT actor_id FROM actor WHERE actor_name = \""+actor+"\""); 
            while(rs.next()){
                actor_id = rs.getInt(1);
          } 
        }catch(SQLException e){
            e.printStackTrace();
        }
        return actor_id;
    }
    
    public String getActorById(ArrayList<Integer> actor_id_list){
        String actors = "";
        for(int actor_id: actor_id_list){
            try{
                con = mdb.setConnection();
                stm = con.createStatement();
                rs = stm.executeQuery("SELECT actor_name FROM actor WHERE actor_id = \""+Integer.toString(actor_id)+"\"");
                while(rs.next()){
                    actors += rs.getString(1);
                 }
            }catch(SQLException e){
                    e.printStackTrace();
            }finally{
                    mdb.ConnectionClose(con, stm, pst);
            }
        }
        return actors;
    }
    
}

    

