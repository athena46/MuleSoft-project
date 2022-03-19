package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertMovies {

    
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

    
    public void insert(String name, String actor, String actress,String director,int yearofrelease) {
        String sql = "INSERT INTO Movies(name,actor,actress,director,yearofrelease) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, actor);
			pstmt.setString(3, actress);
            pstmt.setString(4, director);
			pstmt.setInt(5, yearofrelease);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        InsertMovies app = new InsertMovies();
        // insert three new rows
        app.insert("Love and Leashes","Lee Jun-young","Seohyun","Park Hyun-jin",2022);
        app.insert("Shershaah","Sidharth Malhotra","Kiara Advani","Vishnuvardhan",2021);
        app.insert("Spider-Man: No Way Home","Tom Holland","Zendaya","Jon Watts",2021);
    }

}