/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieAppAPI.Objects;

/**
 *
 * @author paul
 */
public class Movie {
    
    private Integer id;
    private String title;
    private String director;
    private String date;
    private Integer genreId;
    private String[] actors;
    
    public Movie(String title, String director, String date, Integer genreId, String[] actors){
        this.title = title;
        this.director = director;
        this.date = date;
        this.genreId = genreId;
        this.actors = actors;
    }

    public Movie(int id, String title, String date, int genreId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.genreId = genreId;
    }
    
    public Integer GetId(){
        return this.id;
    }
    
    public String GetTitle(){
        return this.title;
    }
    
    public String GetDirector(){
        return this.director;
    }
    
    public String GetDate(){
        return this.date;
    }
    
    public Integer GetGenreId(){
        return this.genreId;
    }
    
    public String[] GetActors(){
        return this.actors;
    }
    
    public String toString(){
        String objString = "";
        objString += this.GetId().toString() + ", ";
        objString += this.GetTitle() + ", ";
        //objString += this.GetDirector() + ", ";
        objString += this.GetDate() + ", ";
        objString += this.GetGenreId().toString() + ", ";
        //objString += this.GetActors().toString() + ", ";
        
        return objString;
    }
}
