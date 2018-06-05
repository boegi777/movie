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
public class DirectorTable {
    
    public static int director_id;
    
      public void insertDirector(int movie_id, String director){
          
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        PreparedStatement pst;
        DirectorMovieTable dimov= new DirectorMovieTable();
        
        try{
            if(selectDirector(director)==""){
                    pst = con.prepareStatement("INSERT INTO director VALUES(?,?)");
                    director_id++;
                    pst.setInt(1,director_id);
                    pst.setString(2, director);
                    pst.execute();
                    dimov.insertDirectorMovie(director, movie_id);
                }else{
                    dimov.insertDirectorMovie(director,movie_id);
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
    
    public String selectDirector(String director){
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        ResultSet rs ;
        String selectedDirector = "";
        
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT director_name FROM director WHERE director_name = \""+director+"\"");
            
            while(rs.next()){
                selectedDirector = rs.getString(1);
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
        return selectedDirector;     
    }
    
}
