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
        Gateway.addMovie(
                movie.GetTitle(), 
                movie.GetDate(), 
                movie.GetDirector(),  
                movie.GetGenreId(),
                movie.GetActorList());
    }
    
    public ArrayList<Movie> GetMovies() throws SQLException{
        movies = Gateway.getAllMovies();
        SetDataForMovies(movies);
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithTitle(String searchString) throws SQLException{
        movies = Gateway.getMovieTitle(searchString);
        SetDataForMovies(movies);
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithDirector(String searchString) throws SQLException{
        movies = Gateway.getDirectorMovie(searchString);
        SetDataForMovies(movies);
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithDate(String searchString) throws SQLException{
        movies = Gateway.getMovieYear(searchString);
        SetDataForMovies(movies);
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithGenre(String searchString) throws SQLException{
        movies = Gateway.getGenreMovie(searchString);
        SetDataForMovies(movies);
        return movies;
    }
    
    public ArrayList<Movie> SearchMoviesWithActor(String searchString) throws SQLException{
        movies = Gateway.getActorMovie(searchString);
        SetDataForMovies(movies);
        return movies;
    }
    
    private void SetDataForMovies(ArrayList<Movie> movies) throws SQLException{
        for(Movie movie : movies){
            movie.SetGenre(Gateway.getGenre(movie.GetId()));
            movie.SetDirector(Gateway.getDirectorMovie(movie.GetId()));
            movie.SetActorString(Gateway.getActorMovie(movie.GetId()));
        }
    }
}
