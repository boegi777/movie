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
public class FavouriteTable {
    
    
    private static final MovieDatabaseManager mdb = new MovieDatabaseManager();
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static Statement stm = null;
    private static ResultSet rs = null;
    
    public void insertFavouriteMovie(int user_id,int movie_id){
        
        try{
            con = mdb.setConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement("INSERT INTO favourite VALUES(?,?)");
            pst.setInt(1, user_id);
            pst.setInt(2,movie_id);
            pst.execute();
            con.commit();
        }catch(SQLException e){
            mdb.insertRollBack(con,e);
        }finally{
            mdb.ConnectionClose(con,stm,pst);
        }
    }
    
      public ArrayList<Movie> selectFavouriteMovie(int user_id){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Movie movie;
        try{
            con = mdb.setConnection();
            stm = con.createStatement();
            String query = "";
            rs = stm.executeQuery("SELECT * FROM movie JOIN favourite ON movie.movie_id = favourite.movie_id WHERE favourite.user_id = "+Integer.toString(user_id));
            while(rs.next()){
                    
                    movie = new Movie(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                    movieList.add(movie);
                }
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
           mdb.ConnectionClose(con,stm,pst);
        }
        return movieList;
    } 
}

