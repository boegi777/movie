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
 * @author Mir
 */
public class DirectorMovieTable {
    
      public void insertDirectorMovie(String director,int movie_id){
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        PreparedStatement pst;
        ResultSet rs ;
        int director_id = 0;
        
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT director_id FROM director WHERE director_name = \""+director+"\"");
            
            while(rs.next()){
                director_id = rs.getInt(1);
            }
            pst = con.prepareStatement("INSERT INTO directormovie VALUES(?,?)");
            pst.setInt(1, director_id);
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
    
    public ArrayList<String> selectDirectorMovie(String director){
        
        ArrayList<String> movieList = new ArrayList<String>();
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        Statement stm2 = null;
        ResultSet rs ;
        ResultSet movRs;
        int director_id = 0;
        
        
         try{
            stm = con.createStatement();
            stm2 = con.createStatement(); //Statement for selecting movie title
            rs = stm.executeQuery("SELECT director_id FROM director WHERE director_name = \""+director+"\"");
            
            while(rs.next()){
                director_id = rs.getInt(1);
            }
            
            rs = stm.executeQuery("SELECT movie_id FROM directormovie WHERE director_id = \""+Integer.toString(director_id)+"\"");
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
