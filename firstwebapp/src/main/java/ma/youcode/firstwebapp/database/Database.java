package ma.youcode.firstwebapp.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection connection ;
    private final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_USER = "root";
    private final String DB_PASS = "";
    private final String Db_URL = "jdbc:mysql://localhost:3306/firstWebApp";

    /**
     * constructor
     */
    public Database() {
        super();
    }


    /**
     * @return connection to database
     */
    public Connection makeConnection() {

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(Db_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return connection;
    }
}
