/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieAppAPI;

import MovieAppAPI.Objects.Movie;
import java.util.ArrayList;

/**
 *
 * @author paul
 */
public class MovieFacade {
    
    private MovieManager movieManager = null;
    private UserManager userManager = null;
    
    public MovieFacade(){
        this.movieManager = new MovieManager();
        this.userManager = new UserManager();
    }
    
    public void CreateMovie(String title, String director, String date, Integer genre, String actors){
        Movie movie = new Movie(title, date, director, genre, actors);
        this.movieManager.CreateMovie(movie);
    }
    
    public ArrayList<Movie> GetMovies(){
        return this.movieManager.GetMovies();
    }
    
    public ArrayList<Movie> SearchMovies(String searchString, int searchId){
        ArrayList<Movie> movies = null;
        switch(searchId){
            case 0:
                movies = this.movieManager.SearchMoviesWithTitle(searchString);
                break;
            case 1:
                movies = this.movieManager.SearchMoviesWithDirector(searchString);
                break;
            case 2:
                movies = this.movieManager.SearchMoviesWithDate(searchString);
                break;
            case 3:
                movies = this.movieManager.SearchMoviesWithGenre(searchString);
                break;
            case 4:
                movies = this.movieManager.SearchMoviesWithActor(searchString);
                break;
        }
        
        return movies;
    }
    
    public ArrayList<Movie> getFavourites(String token) throws AuthException{
        return this.userManager.GetFavouritesFromUser(token);
    }
    
    public void SetFavourit (int movieId, String token) throws AuthException{
        this.userManager.SetFavouritForUser(movieId, token);
    }
    
    public String Login(String username, String password) throws AuthException{
        return this.userManager.AuthenticateUser(username, password);
    }
    
    public void GetAuthenticatedUser(String token) throws AuthException{
        this.userManager.GetUser(token);
    }
    
    public void Logout(String token){
        this.userManager.RemoveUser(token);
    }
    
    
}
