package hexlet.code;

import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet")) {
            String sql = "CREATE TABLE cars (id BIGINT PRIMARY KEY AUTO_INCREMENT, brand VARCHAR(255), model VARCHAR(255))";
            try (Statement statement = conn.createStatement()) {
                statement.execute(sql);
            }

            sql = "INSERT INTO cars (brand, model) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "kia");
                statement.setString(2, "sorento");
                statement.executeUpdate();

                statement.setString(1, "bmw");
                statement.setString(2, "x5");
                statement.executeUpdate();

                statement.setString(1, "audi");
                statement.setString(2, "q7");
                statement.executeUpdate();
            }

            sql = "SELECT * FROM cars ORDER BY brand ASC";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    System.out.printf("%s %s\n", resultSet.getString("brand"), resultSet.getString("model"));
                }
            }
        }
    }
}