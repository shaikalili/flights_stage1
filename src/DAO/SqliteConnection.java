package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteConnection {
    private Connection connection=null;
    private Statement statement=null;

    public SqliteConnection() {

    }

    public Connection getConnection(String url) {
        try {
            Class.forName("org.sqlite.JDBC");

            connection= DriverManager.getConnection
                    (url);//db db3 sql sql3
            System.out.println("Done!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.connection;
    }

    public Statement getStatement() {
        try {
            statement=this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
