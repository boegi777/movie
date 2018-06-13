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
import java.util.ArrayList;

/**
 *
 * @author Juri
 */
public class DirectorMovieTable {
    
    private static final MovieDatabaseManager mdb = new MovieDatabaseManager();      
    private static PreparedStatement pst = null;
    private static Connection con = null;   
    private static Statement stm = null;
    private static ResultSet rs = null;
    private static DirectorTable directorTable = new DirectorTable();
    private static MovieTable movieTable = new MovieTable();
    
    
    public void insertDirectorMovie(String director,int movie_id){
        int director_id = directorTable.getDirectorIdByName(director);
        try{
            con = mdb.setConnection();
            pst = con.prepareStatement("INSERT INTO directormovie VALUES(?,?)");
            pst.setInt(1, director_id);
            pst.setInt(2,movie_id);
            pst.execute(); 
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            mdb.ConnectionClose(con,stm,pst);
        }
    }
    public ArrayList<Movie> selectDirectorMovie(String director){
        ArrayList<Integer> movie_id_list = getMovieIdByDirector(director);
        return movieTable.selectMovieListById(movie_id_list);
    }
    
     public String selectDirectorMovie(int movie_id){
        int director_id = getDirectorIdByMovieId(movie_id);
        return directorTable.getDirectorById(director_id);
    }
     
     public int getDirectorIdByMovieId(int movie_id){
        int director_id = 0;
        try{
            con = mdb.setConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT director_id FROM directormovie WHERE movie_id = \""+movie_id+"\"");
            while(rs.next()){
              director_id = rs.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            mdb.ConnectionClose(con,stm,pst);
        }
        return director_id;
    }
     
    public ArrayList<Integer> getMovieIdByDirector(String director){
         ArrayList<Integer> movie_id_list = new ArrayList<Integer>();
         try{
            con = mdb.setConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT movie_id FROM directormovie WHERE director_id = (SELECT director_id FROM director WHERE director_name = \""+director+"\")");
            while(rs.next()){
                movie_id_list.add(rs.getInt(1));
            }
         }catch(SQLException e){
             e.printStackTrace();
         }finally{
             mdb.ConnectionClose(con,stm,pst);
         }
         return movie_id_list;
     }
     
}
