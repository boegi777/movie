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
public class GenreTable {
    
    private static final MovieDatabaseManager mdb = new MovieDatabaseManager();      
    private static Connection con = null;   
    private static Statement stm = null;
    private static ResultSet rs = null;
    private static PreparedStatement pst = null;
    
    public String selectGenre(int genreID) throws SQLException{
        con = mdb.setConnection();
        String selectedGenre="";
         try{
            stm = con.createStatement();
            rs = stm.executeQuery( "SELECT genre_name FROM genre WHERE genre_id =\""+Integer.toString(genreID)+"\"");
            while(rs.next()){
                selectedGenre = rs.getString(1);
            }
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            mdb.ConnectionClose(con, stm, pst);
        }
        return selectedGenre;     
    }
    
    
    
    public ArrayList<Movie> selectGenreMovie(String genre){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        int genre_id = 0;
        Movie movie;
         try{
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT genre_id FROM genre WHERE genre_name = \""+genre+"\"");
            while(rs.next()){
                    genre_id = rs.getInt(1);
            }
            rs = stm.executeQuery("SELECT * FROM movie WHERE genre_id = \""+Integer.toString(genre_id)+"\"");
            while(rs.next()){
                    movie = new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                    movieList.add(movie);
                }
        }catch(SQLException e){
            e.printStackTrace(); 
        }finally{
            mdb.ConnectionClose(con, stm, pst);
        }
        
        System.out.println("Genre Table -vorher- genre: "+genre);
        return movieList;
    }
        
    
}
    

