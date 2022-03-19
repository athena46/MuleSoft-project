 package net.sqlitetutorial;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class SelectApp {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/Moviesdb.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    
    public void getMovieOnActor(String actor){
               String sql = "SELECT name, actor, actress, director, yearofrelease "
                          + "FROM Movies WHERE actor= ?";
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,actor);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("name") +  "\t" + 
                                   rs.getString("actor") + "\t" +
								   rs.getString("actress") +  "\t" + 
                                   rs.getString("director") + "\t" +
                                   rs.getInt("yearofrelease"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	 public static void main(String[] args) {
        SelectApp app = new SelectApp();
        app.getMovieOnActor("Tom Holland");
    }
}