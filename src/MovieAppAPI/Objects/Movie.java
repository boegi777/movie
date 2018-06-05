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
    private String genre;
    private String actors;

    public Movie(int id, String title, String date, String genre,String director,String actors) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
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
    
    public String GetGenre(){
        return this.genre;
    }
    
    public String GetActors(){
        return this.actors;
    }
    
    public String toString(){
        String objString = "";
        objString += this.GetId().toString() + ", ";
        objString += this.GetTitle() + ", ";
        //objString += this.GetDirector() + ", ";
        objString += this.GetDate() + ", ";
        objString += this.GetGenre()+", ";
        objString += this.GetDirector()+", ";
        objString += this.GetActors();
        
        return objString;
    }
}
