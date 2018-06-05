/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieAppAPI;

import MovieAppAPI.Objects.Movie;
import MovieData.Gateway;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author paul
 */
public class MovieManager {
    public void CreateMovie(Movie movie){
        Gateway.addMovie(
                movie.GetTitle(), 
                movie.GetDate(), 
                movie.GetGenreId(), 
                movie.GetDirector(), 
                movie.GetActors());
    }
    
    public ArrayList<Movie> SearchMoviesWithTitle(String searchString){
        ArrayList<Movie> movies = null;
        movies = Gateway.getMovieTitle(searchString);
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithDirector(String searchString){
        ArrayList<Movie> movies = new ArrayList();
        
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithDate(String searchString){
        ArrayList<Movie> movies = new ArrayList();
        
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithGenre(String searchString){
        ArrayList<Movie> movies = new ArrayList();
        
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithActor(String searchString){
        ArrayList<Movie> movies = new ArrayList();
        
        return movies;
    }
}
