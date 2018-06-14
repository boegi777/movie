package MovieData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;

/**
 * 
 * @author Juri
 */
public class MovieDatabaseManager {
        private String test = "test";
        private String url = "jdbc:mysql://localhost:8889/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        private String user = "root";
        private String password = "root";
        public static Connection con;
    
    MovieDatabaseManager(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            System.out.println("Database driver not found!");
        }
    }
    
    public Connection setConnection() throws SQLException{
                con = DriverManager.getConnection(this.url, this.user, this.password);
                return con;
        }
      public void insertRollBack(Connection con,SQLException ex){
         try{
             if(con != null){
                con.rollback(); 
                System.out.println("Fehler beim Film anlegen");
                ex.printStackTrace();
             }
                
            }catch(SQLException e){
                System.out.println("MovieRollback fehlgeschlagen");
                e.printStackTrace();
            }
    }
    
    public void ConnectionClose(Connection con,Statement stm , PreparedStatement pst){
         try{
             if(con != null) con.close();
             if(stm !=  null) stm.close();
             if(pst != null) pst.close();
            }catch(SQLException e){
                System.out.println("Connection.close() fehlgeschlagen");
                e.printStackTrace();
            }    
    }
}


