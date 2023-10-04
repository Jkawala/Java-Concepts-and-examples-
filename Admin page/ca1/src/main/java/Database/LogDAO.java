package Database;

import java.sql.*;

public class LogDAO {
    public static void insertLog(String title, String description) {
        String sql = "INSERT INTO logs(title,description) VALUES(?,?)";
        System.out.println("log generated");

        try (Connection conn = DatabaseSQLite.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

   public static void getLog(String title){

        String query = "select * from logs where title = '"+title+"'";

       try {
           Connection conn = DatabaseSQLite.getInstance().getConnection();
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(query);

           while(rs.next()){
               System.out.println("title: "+rs.getString("title"));
               System.out.println("description: "+rs.getString("description"));

           }


       } catch (SQLException e) {
           System.out.println("Error in Statement");
            throw new RuntimeException(e);
        }

    }
}
