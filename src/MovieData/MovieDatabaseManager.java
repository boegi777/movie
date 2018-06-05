package MovieData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;

/**
 *
 * @author Mir
 */
public class MovieDatabaseManager {
        private String test = "test";
        private String url = "jdbc:mysql://localhost:8889/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        private String user = "root";
        private String password = "root";
        private static Connection con;
    
    MovieDatabaseManager(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            System.out.println("Database driver not found!");
        }
    }
    
    public Connection setConnection(){
            try {
                con = DriverManager.getConnection(this.url, this.user, this.password);
                return con;
            } catch(SQLException ex){
              
                System.out.println(ex.getMessage());
                return null;
         }
        }
    public String getAuthQuery(String username, String password)throws SQLException {
        if(username.equals("") || password.equals("")){
            throw  new SQLException ();
            }
        String query = "";
        query += "SELECT * FROM user WHERE ";
        query += "name = '" + username + "' AND ";
        query += "password = '" + password + "';";
        return query;
        }
    
    

}


