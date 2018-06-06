/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieAppAPI;

/**
 *
 * @author paul
 */
public class AuthException extends Exception {
    
    @Override 
    public String getMessage(){
        return "Authentifizierungsfehler";
    }
}
