/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;
import MovieAppAPI.AuthException;
import MovieAppAPI.MovieFacade;
import MovieAppAPI.Objects.Movie;
import java.sql.SQLException;
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
        
        //facade.CreateMovie("Im WestNeues", "Son", "1945", 2, "Emil, Hermann, Hans, Franz, Dieter");
        //facade.CreateMovie("Im Westen nichts Neues", "Son Typ", "1945", 2, "Emil, Hermann, Hans, Franz, Dieter");
        
        try{
            String token = facade.Login("hans", "123");
            System.out.println(token);
            
            ArrayList<Movie> movies = facade.SearchMovies("Heinrich", 1);
            
            //facade.SetFavourit(1, token);
            ArrayList<Movie> favourites = facade.GetFavourites(token);
            
            for(Movie favourit : favourites){
                System.out.println(favourit.toString());
            }
        } catch (AuthException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
}
