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
    
    private ActorManager actorManager = null;
    private MovieManager movieManager = null;
    private UserManager userManager = null;
    
    public MovieFacade(){
        this.actorManager = new ActorManager();
        this.movieManager = new MovieManager();
        this.userManager = new UserManager();
    }
    
    public void CreateMovie(String title, String director, String date, Integer genre, String actors){
        //Movie movie = new Movie(title, director, date, genre, actors);
        //this.movieManager.CreateMovie(movie);
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
    
    public ArrayList<Movie> getFavourits(){
        ArrayList favourits = new ArrayList();
        return favourits;
    }
    
    public void SetFavourit (int movieId, int userId){
        this.userManager.GetUser(userId).setFavourit(movieId);
    }
    
    public void Login(String username, String password){
        this.userManager.LoginUser(username, password);
    }
    
    
}
