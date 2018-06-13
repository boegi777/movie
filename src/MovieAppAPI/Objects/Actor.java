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
public class Actor {
    
    private Integer id;
    private String name;
    
    public Actor(Integer id, String name){
        this.id = id;
        this.name = name;
    }
    
    public Integer GetId(){
        return this.id;
    }
    
    public String GetName(){
        return this.name;
    }
}
