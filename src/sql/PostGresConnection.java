package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostGresConnection {
    private static  Connection connection=null;
    private static  Statement statement=null;

    public PostGresConnection() {}

    public static Connection getConnection() {
            try {
                Class.forName("org.postgresql.Driver");
                connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/FlightCompanyDb",
                        "postgres","shooka5192");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return connection;
        }

        public static Statement getStatement() {
            try {
                statement=connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return statement;
        }
    }

