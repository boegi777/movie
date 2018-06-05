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

/**
 *
 * @author Juri
 */
public class ActorTable {
    
    public static int actor_id;
    
      public void insertActor(int movie_id, String[] actor){
          
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        PreparedStatement pst;
        ActorMovieTable actmov= new ActorMovieTable();
        
        try{
            
            for(String a : actor){
                if(selectActor(a)==""){
                    pst = con.prepareStatement("INSERT INTO actor VALUES(?,?)");
                    actor_id++;
                    pst.setInt(1,actor_id);
                    pst.setString(2, a);
                    pst.execute();
                    actmov.insertActorMovie(a, movie_id);
                }else{
                    actmov.insertActorMovie(a,movie_id);
                }
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
    }
    
    public String selectActor(String actor){
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        ResultSet rs ;
        String selectedActor = "";
        
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT actor_name FROM actor WHERE actor_name = \""+actor+"\"");
            
            while(rs.next()){
                selectedActor = rs.getString(1);
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
        return selectedActor;     
    }
}

    

