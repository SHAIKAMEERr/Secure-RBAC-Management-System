package com.example.authentication.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.authentication.model.UserEntity;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(UserEntity userEntity) {
        String sql = "INSERT INTO users (username, password, role) VALUES (:username, :password, :role)";
        var params = Map.of(
            "username", userEntity.getUsername(),
            "password", userEntity.getPassword(),
            "role", userEntity.getRole() != null ? userEntity.getRole() : "USER"
        );
        jdbcTemplate.update(sql, params);
    }

    public Optional<UserEntity> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = :username";
        var params = Map.of("username", username);
        return jdbcTemplate.query(sql, params, rs -> {
            if (rs.next()) {
                UserEntity user = new UserEntity();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return Optional.of(user);
            }
            return Optional.empty();
        });
    }
}
