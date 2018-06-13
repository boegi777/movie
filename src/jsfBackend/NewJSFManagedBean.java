/*
 * WebTechnologien Übung2
 */
package jsfBackend;

import MovieAppAPI.AuthException;
import MovieAppAPI.MovieFacade;
import MovieAppAPI.Objects.Movie;
import TestFassade.TestBackground;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.*;
import javax.inject.Named;

/**
 * @author Mustermann
 */
@Named("ManagedBean")
@SessionScoped
public class NewJSFManagedBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private MovieFacade fassade;
    Map<String, Object> sessionMap;
    private String token;
    private ArrayList<Movie> filmList;
    private String filterAttribute;
    private String filterValue;
    private String favorite;
    private String name;
    private String password;
    private String title;
    private String date;
    private String genre;
    private String director;
    private String actor;
    private boolean ifLoggedIn = false;
    
    /*
    *   getter und setter
    */
    public boolean getIfLoggedIn(){
        return ifLoggedIn;
    }
    public void setIfLoggedIn(boolean ifLoggedIn){
        this.ifLoggedIn = ifLoggedIn;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getDirector(){
        return director;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public String getActor(){
        return actor;
    }
    public void setActor(String actor){
        this.actor = actor;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getFilterAttribute(){
        return filterAttribute;
    }
    public void setFilterAttribute(String filterAttribute){
        this.filterAttribute = filterAttribute;
    }
    public String getFilterValue(){
        return filterValue;
    }
    public void setFilterValue(String filterValue){
        this.filterValue = filterValue;
    }
    public ArrayList<Movie> getFilmList(){
        return filmList;
    }
    public void setFilmList(ArrayList<Movie> filmArray){
        this.filmList = filmArray;
    }
    public String getFavorite(){
        return favorite;
    }
    public void setFavorite(String favorite){
        //TODO Favoriten Setten
        this.favorite = favorite;
    }
    
    /*
    *   ansteuerung FASSADE
    */
    //Passwort bestätigen lassen
    public void logInUI(){
        if(name!=null&password!=null){
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            sessionMap = externalContext.getSessionMap();
            
            fassade = new MovieFacade();
            try {
                token = fassade.Login(name, password);
                ifLoggedIn = true;
                sessionMap.put("token", token);
                System.out.println("Fassade (logInUI) True");
            } catch (AuthException ex) {
                ifLoggedIn = false;
                System.out.println("Fassade (logInUI) False");
            }
        }
    }
    //ausloggen und Session löschen
    public void logOutUI(){
        //System.out.println("------------------------------------>"+sessionMap.get("token").toString());
        if(token != null){
            fassade.Logout(token);
            token = "";
        }
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    //gefilterte FilmListe abrufen
    public void showMoviesUI(){
        if(!filterAttribute.equals("")&&!filterValue.equals("")){
            System.out.println("Fassade (showMoviesUI): filterAttribute:\""+filterAttribute+"\"  filterValue:\""+filterValue+"\"");
            try {
                setFilmList(fassade.SearchMovies(filterValue, Integer.parseInt(filterAttribute)));
                //setFilmList(TestBackground.getList(filterAttribute, filterValue));
            } catch (SQLException ex) {
                Logger.getLogger(NewJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    public void showAllMoviesUI(){
        try {
            setFilmList(fassade.GetMovies());
        } catch (SQLException ex) {
            Logger.getLogger(NewJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setFilmList(TestBackground.getListAll());
        System.out.println("Fassade (showAllMoviesUI): --> ");
    }
    //Neuen Film anlegen
    public void createMovieUI(){
        if(!title.equals("") & !genre.equals("")){
            fassade.CreateMovie(title, director, date, Integer.parseInt(genre), actor);
            //String[] newMovieArray = {title, date, genre, director, actor}; 
            System.out.println( "Fassade (createMovieUI) --> ");
        }
    }
    //Favoritenliste abrufen
    public void showFavoriteMoviesUI(){
        try {
            setFilmList(fassade.getFavourites(token));
        } catch (AuthException | SQLException ex) {
            Logger.getLogger(NewJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setFilmList(TestBackground.getListOfFavorite());
        System.out.println("Fassade (showFavoriteMoviesUI): ohne filterAttribute und Value");
    }
    //neuen Favoriten anlegen
    public void saveFavoriteUI(String favoriteUI){
        
        //this.favorite = favoriteUI;
        //System.out.println("Fassade (showFavoriteMoviesUI): "+TestBackground.saveFavorite(favorite));
        //if(favoriteUI!=null){
            try {
                fassade.SetFavourit(Integer.parseInt(favoriteUI), token);
            } catch (AuthException ex) {
                //Logger.getLogger(NewJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestBackground.saveFavorite(favorite);
            System.out.println("Fassade (saveFavoriteUI):  "+favorite);
        //}
    }
    
    /*
    *   Hilfs-Methoden
    */
    
    public Films[] arrayToFilmObject(String[][] filmListTest){
        System.out.println("Logik (arrayToFilmObject(--->)):  "+filmListTest);
        Films[] Liste= new Films[filmListTest.length];
        for(int i=0; i<filmListTest.length; i++){
            Liste[i] = new Films(filmListTest[i][0],filmListTest[i][1],filmListTest[i][2],filmListTest[i][3],filmListTest[i][4],filmListTest[i][5]);
        }
        return Liste;
    }
    public String[] stringsToArray(String title,String date,String genre,String director,String actor){
        String[] myArray = {title, date, genre, director, actor}; 
        return myArray;
    }
    
}
