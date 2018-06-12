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
    
    private static final MovieDatabaseManager mdb = new MovieDatabaseManager();      
    private static PreparedStatement pst = null;
    private static Connection con = null;   
    private static Statement stm = null;
    private static ResultSet rs = null;
    private static MovieTable movieTable;
    private static DirectorMovieTable directorMovieTable;
    
  
    
    public void insertDirector(String title, String director){
        movieTable = new MovieTable();
        directorMovieTable = new DirectorMovieTable();
        int movie_id = movieTable.getMovieIdByTitle(title);
        try{
            con = mdb.setConnection();
            con.setAutoCommit(false);
            if(getDirectorIdByName(director) == 0){
                    pst = con.prepareStatement("INSERT INTO director(director_name) VALUES(?)");
                    pst.setString(1, director);
                    pst.execute();
                    con.commit();
                    directorMovieTable.insertDirectorMovie(director, movie_id);
                }else{
                    directorMovieTable.insertDirectorMovie(director,movie_id);
                }
        }catch(SQLException e){
            mdb.insertRollBack(con,e); 
        }finally{
            mdb.ConnectionClose(con, stm, pst); 
        }
    }
    
    public int getDirectorIdByName(String director_name){
        int director_id = 0;
        try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT director_id FROM director WHERE director_name = \""+director_name+"\""); 
            while(rs.next()){
                director_id = rs.getInt(1);
          } 
        }catch(SQLException e){
            e.printStackTrace();
        }
        return director_id;
    }
    
    public String getDirectorById(int director_id){
        String director = "";
        try{
            con = mdb.setConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT director_name FROM director WHERE director_id = \""+Integer.toString(director_id)+"\"");
            while(rs.next()){
              director= rs.getString(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            mdb.ConnectionClose(con, stm, pst);
        }
        return director;
    }
}
