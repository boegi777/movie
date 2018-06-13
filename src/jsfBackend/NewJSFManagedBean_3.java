/*
 * WebTechnologien Übung2
 */
package jsfBackend;

import TestFassade.TestBackground;
import java.io.IOException;
import java.io.Serializable;
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
@Named("ManagedBean3")
@SessionScoped
public class NewJSFManagedBean_3 implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Films[] filmList;
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
    public Films[] getFilmList(){
        return filmList;
    }
    public void setFilmList(String[][] filmArray){
        this.filmList = arrayToFilms(filmArray);
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
            System.out.println("Fassade (logInUI) vorher--> "+ifLoggedIn);
            ifLoggedIn = TestBackground.logIn(name, password);
            System.out.println("Fassade (logInUI) --> "+ifLoggedIn);
        }
    }
    //ausloggen und Session löschen
    public void logOutUI(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    //gefilterte FilmListe abrufen
    public void showMoviesUI(){
        if(filterAttribute!=null&filterValue!=null){
            System.out.println("Fassade (showMoviesUI): filterAttribute:\""+filterAttribute+"\"  filterValue:\""+filterValue+"\"");
            setFilmList(TestBackground.getList(filterAttribute, filterValue));
        }

    }
    //Neuen Film anlegen
    public void createMovieUI(){
        if(title!=null & date!=null & genre!=null & director!=null & actor!=null){
            String[] newMovieArray = {title, date, genre, director, actor}; 
            System.out.println( "Fassade (createMovieUI) --> "+TestBackground.createMovie(newMovieArray));
        }

        
    }
    //Favoritenliste abrufen
    public void showFavoriteMoviesUI(){
        setFilmList(TestBackground.getListOfFavorite());
        System.out.println("Fassade (showFavoriteMoviesUI): ohne filterAttribute und Value");
    }
    //neuen Favoriten anlegen
    public void saveFavoriteUI(){
        if(favorite!=null){
            TestBackground.saveFavorite(favorite);
            System.out.println("Fassade (saveFavoriteUI):  "+favorite);
        }
    }
    
    /*
    *   Hilfs-Methoden
    */
    
    public Films[] arrayToFilms(String[][] filmListTest){
        System.out.println("Logik (arrayToFilms(--->)):  "+filmListTest);
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
