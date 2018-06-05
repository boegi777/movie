/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieAppAPI.Objects;

import java.util.ArrayList;
import java.util.List;

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
    private String genre;
    private String actorsString;

    public Movie(int id, String title, String date, Integer genreId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.genreId = genreId;
        this.genre = "";
        this.director = "";
        this.actorsString = "";
    }
    
    public Integer GetId(){
        return this.id;
    }
    
    public String GetTitle(){
        return this.title;
    }
    
    public void SetDirector(String director){
        this.director = director;
    }
    
    public String GetDirector(){
        return this.director;
    }
    
    public String GetDate(){
        return this.date;
    }
    
    public void SetGenre(String genre){
        this.genre = genre;
    }
    
    public String GetGenre(){
        return this.genre;
    }
    
    public void SetActorString(String actorString){
        this.actorsString = actorString;
    }
    
    public String GetActorsString(){
        return this.actorsString;
    }
    
    public String[] GetActorList(){
        return this.actorsString.split(",");
    }
    
    @Override
    public String toString(){
        String objString = "";
        objString += this.GetId().toString() + ", ";
        objString += this.GetTitle() + ", ";
        objString += this.GetDate() + ", ";
        objString += this.GetGenre()+", ";
        objString += this.GetDirector()+", ";
        objString += this.GetActorsString();
        
        return objString;
    }
}
