package com.johnebri.cutsession.dao;

import com.johnebri.cutsession.dto.clients.GetClientRequest;
import com.johnebri.cutsession.model.User;
import com.johnebri.cutsession.model.enums.UserTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author John on 12/1/22
 */

@Slf4j
@Component
public class UserDao implements DAO<User> {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<User> rowMapper = (rs, rowNum) -> {

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setDob(rs.getString("dob"));
        user.setCity(rs.getString("city"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setType(UserTypeEnum.valueOf(rs.getString("type")));
        return user;
    };

    @Override
    public List<User> list() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<User> getClients(GetClientRequest request) {

        StringBuilder sb = new StringBuilder();
        List<Object> paramsList = new ArrayList<>();

        sb.append("SELECT * FROM users WHERE type = ?");
        paramsList.add(request.getType().toString());

        if(request.getCity() != null) {
            sb.append(" AND city LIKE ?");
            paramsList.add(request.getCity());
        }

        if (request.getName() != null) {
            sb.append(" AND name = ?");
            paramsList.add(request.getName());
        }

        sb.append(" LIMIT ?");
        paramsList.add(request.getLimit());

        String sql = sb.toString();

        Object[] params = new Object[paramsList.size()];
        for(int x=0; x<paramsList.size(); x++) {
            params[x] = paramsList.get(x);
        }

        List<User> users = jdbcTemplate.query(sql,  params, rowMapper);

        return users;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (id, name, email, username, password, dob, city, phone_number, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int insert = jdbcTemplate.update(sql, user.getId(), user.getName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getDob(), user.getCity(), user.getPhoneNumber(), user.getType().toString());
        if(insert == 1) {
            log.info("New user created successfully : " + user.getId());
        }
    }

    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(User user, int id) {

    }

    @Override
    public void delete(int id) {

    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{username}, rowMapper);
        } catch (DataAccessException ex) {
            log.error("user not found : " + username);
        }
        return Optional.ofNullable(user);
    }

    // find by name
    public Optional<User> findByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{name}, rowMapper);
        } catch (DataAccessException ex) {
            log.error("user not found : " + name);
        }
        return Optional.ofNullable(user);
    }

    // find by email
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{email}, rowMapper);
        } catch (DataAccessException ex) {
            log.error("user not found : " + email);
        }
        return Optional.ofNullable(user);
    }

    // find by phone number
    public Optional<User> findByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{phone}, rowMapper);
        } catch (DataAccessException ex) {
            log.error("user not found : " + phone);
        }
        return Optional.ofNullable(user);
    }

    // find by phone number
    public Optional<User> findUserByUserId(String userId) {
        String sql = "SELECT * FROM users WHERE id = ? AND type = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{userId, "USER"}, rowMapper);
        } catch (DataAccessException ex) {
            log.error("user not found : " + userId);
        }
        return Optional.ofNullable(user);
    }


}
