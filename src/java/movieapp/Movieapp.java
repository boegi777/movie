/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;
import MovieAppAPI.MovieFacade;
import MovieAppAPI.Objects.Movie;
import java.util.ArrayList;

/**
 *
 * @author paul
 */
public class Movieapp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MovieFacade facade = new MovieFacade();
        
        ArrayList<Movie> movies = facade.SearchMovies("Godzilla", 0);
        
        for(Movie movie : movies){
            System.out.println(movie.toString() + "\n");
        }
    }
    
}
