/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Juri
 */
public class GenreTable {
    
    public String selectGenre(int genreID){
        
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        ResultSet rs ;
        String selectedGenre="";
        
         try{
            stm = con.createStatement();
            rs = stm.executeQuery( "SELECT genre_name FROM genre WHERE genre_id ="+Integer.toString(genreID));
            
            while(rs.next()){
                selectedGenre = rs.getString(1);
            }
            
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            try{
                if(stm != null && con != null){
                stm.close();
                con.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return selectedGenre;     
    }
    
    
    
    public ArrayList<String> selectGenreMovie(int genre_id){
        
        ArrayList<String> movieList = new ArrayList<String>();
        MovieDatabaseManager mdb = new MovieDatabaseManager();
        Connection con = mdb.setConnection();
        Statement stm = null;
        ResultSet rs ;
       
        
        
         try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT title FROM movie WHERE genre_id = \""+genre_id+"\"");
            
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
        
    
}
    

