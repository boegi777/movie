/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieData;

import MovieAppAPI.Objects.Movie;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Juri
 */
public class Gateway {
     private static final  UserTable userTable = new UserTable();
     private static final MovieTable movieTable = new MovieTable();
     private static final ActorMovieTable actorMovieTable = new ActorMovieTable();
     private static final DirectorMovieTable directorMovieTable = new DirectorMovieTable();
     private static final GenreTable genreTable = new GenreTable();
     private static final FavouriteTable favouriteTable = new FavouriteTable();
     
    
    public static void addUser(String user,String password){
       
        userTable.insertUser(user,password);   
    }
    
    public static String getGenre(int movie_id){
        return genreTable.selectGenre(movie_id);
    }
    
    public static String getDirectorMovie(int movie_id){
        return directorMovieTable.selectDirectorMovie(movie_id);
    }
    
    public static String getActorMovie(int movie_id){
        return actorMovieTable.selectActorMovie(movie_id);
    }
    
    public static String getUser(String password){
        return userTable.selectUser(password);
    }
    
    public static void addMovie(String title,String year,int genre_id,String director,String[]actor){
        movieTable.insertMovie(title,year,genre_id,director,actor);
        
    }
    
    public static ArrayList<Movie> getAllMovies(){
        return movieTable.selectAllMovies();
    }
    
    public static ArrayList<Movie> getMovieTitle(String movie){
        return movieTable.selectMovie(movie);
    }
    
    public static  ArrayList<Movie> getMovieYear(String year){
        return movieTable.selectMovieYear(year);
    }
    
    public static  ArrayList<Movie> getActorMovie(String actor){
        return actorMovieTable.selectActorMovie(actor);
      
    }
    
    public static ArrayList<Movie> getDirectorMovie(String director){
        return directorMovieTable.selectDirectorMovie(director);
    }
    
    public static ArrayList<Movie> getGenreMovie(String genre){
        return genreTable.selectGenreMovie(genre);
    }
    
    public static void addFavouriteMovie(String user,String movie){
        favouriteTable.insertFavouriteMovie(user, movie);
        
    }
    
    public static ArrayList<String> getFavouriteMovie(String user){
        return favouriteTable.selectFavouriteMovie(user);
    }
   
}
