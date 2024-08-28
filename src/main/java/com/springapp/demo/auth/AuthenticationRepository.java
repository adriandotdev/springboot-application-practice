package com.springapp.demo.auth;

import com.springapp.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class AuthenticationRepository {

    @Autowired
    private JdbcTemplate template;

    public void register(User user) {
        this.template.update("INSERT INTO users (username, password) VALUES(?,?)", user.getUsername(), user.getPassword());
    }

    public Optional<User> getUserByUsername(String username) {

        RowMapper<User> rowMapper = (ResultSet rs, int row) -> {

            return new User(rs.getString("username"), rs.getString("password"));
        };

        User user = this.template.queryForObject("SELECT username, password FROM users WHERE username = ?", rowMapper, username);

        if (user != null) {
            return Optional.of(user);
        }
        else {
            return Optional.empty();
        }
    }
}
