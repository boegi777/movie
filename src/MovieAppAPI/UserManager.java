package MovieAppAPI;

import MovieAppAPI.Objects.Movie;
import MovieAppAPI.Objects.User;
import MovieData.Gateway;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author paul
 */
public class UserManager {
    ArrayList<User> users = null;
    
    
    public UserManager(){
        users = new ArrayList();
    }
    public String AuthenticateUser(String username, String password) throws AuthException{
        User user = Gateway.getUser(username, password);
        if(user == null){
            throw new AuthException();
        }
        user.generateToken();
        users.add(user);
        return user.getToken();
    }
    public void RemoveUser(String token){
        for(User user : users){
            if(user.getToken().equals(token)){
                users.remove(user);
                break;
            }
        }
    }

    public User GetUser(String token) throws AuthException {
        for(User user : this.users){
            if(user.getToken().equals(token)){
                return user;
            }
        }
        throw new AuthException();
    }
    
    public ArrayList<Movie> GetFavouritesFromUser(String token) throws AuthException, SQLException{
        User user = GetUser(token);
        ArrayList<Movie> favourites = Gateway.getFavouriteMovie(user.getId());
        MovieManager.SetDataForMovies(favourites);
        return favourites;
    }
    
    public void SetFavouritForUser(Integer movieId, String token) throws AuthException{
        User user = GetUser(token);
        Gateway.addFavouriteMovie(user.getId(), movieId);
    }
}
