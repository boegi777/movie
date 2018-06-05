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
    
    ArrayList<Movie> movies = null;
    
    public void CreateMovie(Movie movie){
        /*Gateway.addMovie(
                movie.GetTitle(), 
                movie.GetDate(), 
                movie.GetGenreId(), 
                movie.GetDirector(), 
                movie.GetActors());*/
    }
    
    public ArrayList<Movie> SearchMoviesWithTitle(String searchString){
        movies = Gateway.getMovieTitle(searchString);
        SetDataForMovies(movies);
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithDirector(String searchString){
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithDate(String searchString){

        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithGenre(String searchString){

        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithActor(String searchString){

        return movies;
    }
    
    private void SetDataForMovies(ArrayList<Movie> movies){
        for(Movie movie : movies){
            movie.SetGenre(Gateway.getGenre(movie.GetId()));
            movie.SetDirector(Gateway.getDirectorMovie(movie.GetId()));
            movie.SetActorString(Gateway.getActorMovie(movie.GetId()));
        }
    }
}
