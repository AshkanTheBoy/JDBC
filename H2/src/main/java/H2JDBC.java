import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2JDBC {
    static Statement statement;
    public static void main(String[] args) {
        String link = "jdbc:h2:C:\\Users\\gk\\Desktop\\Sqlite\\H2\\src\\main\\resources\\dbase.db";
        try(
            Connection connection = DriverManager.getConnection(link,"sa","")
        ) {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            //insert();
            //delete();
            //update();
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
        List<Character> characters = new ArrayList<>();
        ResultSet rs = statement.executeQuery("select name from character");
        while(rs.next()) {
            characters.add(new Character(rs.getString("name")));
        }
        characters.forEach(System.out::println);
    }
    public static void update() throws SQLException {
        String update = "update employer set salary = (select salary from employer)+152";
        statement.executeUpdate(update);
    }
} 