
package com.example.userauth.dao;

import com.example.userauth.dto.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean verifyUser(UserDTO userDTO) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userDTO.getUsername(), userDTO.getPassword());
        return count > 0;
    }
}
