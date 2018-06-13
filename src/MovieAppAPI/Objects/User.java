/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieAppAPI.Objects;

import MovieData.Gateway;
import java.util.ArrayList;

/**
 *
 * @author paul
 */
public class User {
    
    private Integer id;
    private String name;
    private String token;
    private ArrayList<Movie> favourits;
    
    public User(Integer id, String name){
        this.id = id;
        this.name = name;
        this.token = "";
        this.favourits = null;
    }
    
    public Integer getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public void generateToken(){
        this.token = "1234" + this.name;
    }
    
    public String getToken(){
        return token;
    }
    
    public void setFavourit(Integer movieId){
        
    }
}
