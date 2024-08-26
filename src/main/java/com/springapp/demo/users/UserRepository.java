package com.springapp.demo.users;

import com.springapp.demo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class UserRepository implements  IUserRepository {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Optional<User> findUserByUsername(String username) {

        final String SQL_QUERY = "SELECT * FROM users WHERE username = ?";

        RowMapper<User> rowMapper = (ResultSet rs, int rowNum) ->
                new User(
                        rs.getString("username"),
                        rs.getString("password")
                );


        try {
            User user = this.template.queryForObject(SQL_QUERY, rowMapper, username);

            return Optional.ofNullable(user);
        }
        catch(DataAccessException err) {
            return Optional.empty();
        }
    }
}
