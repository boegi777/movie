/*
 * WebTechnologien Übung2
 */
package jsfBackend;

import java.io.Serializable;
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 * @author Mustermann
 */
@ManagedBean(name="ManagedBeany")
@SessionScoped
public class NewJSFManagedBean1 implements Serializable{

    private static final long serialVersionUID = 1L;
    //private final String[][] FILM_LIST_TEST = {{"Terminator", "01.01.1990", "Action", "Arnold", "Schwarzenegger"}, {"Eis am Stiel", "01.01.1970", "Comedy", "Tommy", "Thompson"}};
    private static final Films[] filmList = new Films[]{

        new Films("Terminator", "01.01.1990", "Action", "Arnold", "Schwarzenegger"),
        new Films("Eis am Stiel", "01.01.1970", "Comedy", "Tommy", "Thompson"),
        new Films("Terminator 2", "01.01.1990", "Action", "Arnold", "Schwarzenegger"),
        new Films("Eis am Stiel 2", "01.01.1970", "Comedy", "Tommy", "Thompson"),
        new Films("Eis am Stiel 3", "01.01.1970", "Comedy", "Tommy", "Thompson"),
    };

    public Films[] getFilmList(){
            return filmList;
    }
    // Class for Films (needed for Table)
    public static class Films{
        String title;
        String date;
        String genre;
        String director;
        String actor;

        public Films(String title, String date, 
                        String genre, String director, String actor){

                this.title = title;
                this.date = date;
                this.genre = genre;
                this.director = director;
                this.actor = actor;
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
    }
}