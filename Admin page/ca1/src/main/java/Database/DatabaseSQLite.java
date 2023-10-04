package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSQLite {


    private static DatabaseSQLite firstInstance = null;
    private static Connection conn = null;

    private DatabaseSQLite() {

    }

    public static DatabaseSQLite getInstance(){
        if(firstInstance == null){
            firstInstance = new DatabaseSQLite();
            createNewTable();
        }
        return firstInstance;
    }
    public static Connection getConnection(){

        String url = "jdbc:sqlite:application.db";
        try {
            conn = DriverManager.getConnection(url);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewTable() {
        String url = "jdbc:sqlite:application.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS logs (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	title text NOT NULL,\n"
                + "	description text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Database created ................>>>>>");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
