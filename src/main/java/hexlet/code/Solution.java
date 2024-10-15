package hexlet.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Solution {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:hexlet");
        UserDAO userDAO = new UserDAO(connection);

        try (Statement statement = connection.createStatement();) {
            var sqlCreateTable = "CREATE TABLE users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            statement.execute(sqlCreateTable);
        }

        User user1 = new User("Maria", "+7900434334");
        User user2 = new User("Maria", "+7900434334");
        userDAO.save(user1);
        userDAO.save(user2);
        System.out.println(user1.getId()); // 1
        System.out.println(user2.getId()); // 2

        User user3 = userDAO.find(user1.getId()).get();
        System.out.println(user1.getId() == user3.getId()); // true
    }
}
