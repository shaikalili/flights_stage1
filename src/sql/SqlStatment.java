package sql;

import java.sql.Connection;
import java.sql.Statement;

public abstract class SqlStatment {
   public Connection connection = PostGresConnection.getConnection();
   public Statement stm = PostGresConnection.getStatement();
}
