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

    public Movie(String title, String date, String director, Integer genreId, String actorsString) {
        this.id = -1;
        this.title = title;
        this.date = date;
        this.genreId = genreId;
        this.genre = "";
        this.director = director;
        this.actorsString = actorsString;
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
    
    public Integer GetGenreId(){
        return this.genreId;
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
        objString += "ID: " + this.GetId().toString() + ", ";
        objString += "Title: " + this.GetTitle() + ", ";
        objString += "Date: " + this.GetDate() + ", ";
        objString += "Genre ID: " + this.GetGenreId() + ", ";
        objString += "Genre: " + this.GetGenre()+", ";
        objString += "Director: " + this.GetDirector()+", ";
        objString += "Actors: " + this.GetActorsString();
        
        return objString;
    }
}
