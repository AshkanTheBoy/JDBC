import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteJDBC {
    static Statement statement;
    public static void main(String[] args) {
        try(
            // create a database connection
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\gk\\Desktop\\Sqlite\\SqliteModule\\src\\main\\resources\\employees.db");
        ) {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            //insert();
            //delete();
            update();
            select();
        }
        catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
    }

    public static void delete() throws SQLException {
        String delete = "delete from employer where id = (select max(id) from employer)";
        statement.executeUpdate(delete);
    }

    public static void insert() throws SQLException {
        String insert = "insert into employer (name,job,salary) values ('NEW EMP','NEW JOB',150000)";
        statement.executeUpdate(insert);
    }

    public static void select() throws SQLException {
        ResultSet rs = statement.executeQuery("select name,job,salary from employer");
        while(rs.next()) {
            System.out.print(rs.getString("name")+" ");
            System.out.print(rs.getString("job")+" ");
            System.out.println(rs.getFloat("salary"));
        }
    }
    public static void update() throws SQLException {
        String update = "update employer set salary = (select salary from employer)+152";
        statement.executeUpdate(update);
    }
} 