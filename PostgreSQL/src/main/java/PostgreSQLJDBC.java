import java.sql.*;

public class PostgreSQLJDBC {
    private static Statement statement = null;

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/students","postgres","postgres")) {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            //insert();
            //update();
            //delete();
            readAll();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void readAll() throws SQLException {
        //Список всех служащих
        ResultSet rs = statement.executeQuery("select name from student");
        while (rs.next()) {
            System.out.printf("name: %s%n", rs.getString("name"));
        }
    }

    public static void insert() throws SQLException {
        statement.executeUpdate("insert into student(name) values('лжедмитрий')");
    }

    public static void update() throws SQLException {
        statement.executeUpdate("update student set surname = 'загадочный', univ_id=4 WHERE name = 'лжедмитрий'");
    }

    public static void delete() throws SQLException {
        statement.executeUpdate("delete from student WHERE name = 'лжедмитрий'");
    }
} 