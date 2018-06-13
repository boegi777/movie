/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfBackend;

/**
 *
 * @author Mustermann
 */
public class Films{
    String title;
    String date;
    String genre;
    String director;
    String actor;
    String id;

    public Films(String title, String date, 
                    String genre, String director, String actor, String id){

            this.title = title;
            this.date = date;
            this.genre = genre;
            this.director = director;
            this.actor = actor;
            this.id = id;
    }

    public String getTitle(){
        return this.title;
    }
    public String getDate(){
        return this.date;
    }
    public String getGenre(){
        return this.genre;
    }
    public String getDirector(){
        return this.director;
    }
    public String getActor(){
        return this.actor;
    }
    public String getId(){
        return this.id;
    }

    public void setTitle(String x){
        this.title = x;
    }
    public void setDate(String x){
        this.date = x;
    }
    public void setGenre(String x){
        this.genre = x;
    }
    public void setDirector(String x){
        this.director = x;
    }
    public void setActor(String x){
        this.actor = x;
    }
    public void setId(String x){
        this.id = x;
    }
}
