package hexlet.code;

import java.sql.*;
import java.util.Optional;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(User user) throws SQLException {
        if (user.getId() == null) {
            var sql = "INSERT INTO users (username, phone) VALUES (?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getNumber());
                ps.executeUpdate();
                ResultSet key = ps.getGeneratedKeys();
                if (key.next()) {
                    user.setId(key.getLong(1));
                }
            }
        }
    }

    public Optional<User> find(Long id) throws SQLException {
        var sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet pointer = ps.executeQuery();

            if (pointer.next()) {
                User user = new User();
                user.setUsername(pointer.getString("username"));
                user.setNumber(pointer.getString("phone"));
                user.setId(id);

                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}
