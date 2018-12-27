package ru.itis.repositories;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.models.User;

import javax.sql.DataSource;


public class UserRepositoryJdbcImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    //    language=sql
    private static final String SQL_GET_USER_BY_UUID =
            "SELECT * " +
                    "FROM users " +
                    "JOIN auth a on users.user_id = a.user_id " +
                    "WHERE uuid = ?";

    //    language=sql
    private static final String SQL_GET_USER_BY_LOGIN =
            "SELECT * " +
                    "FROM users " +
                    "WHERE user_login = ?";

    //    language=sql
    private static final String SQL_GET_USER_BY_ID =
            "SELECT * " +
                    "FROM users " +
                    "WHERE user_id = ?";

//    language=SQL
    private static final String SQL_CREATE_USER = "INSERT INTO users (user_login, user_hash_password) VALUES (?,?)";


    RowMapper<User> userRowMapper = ((resultSet, i) -> User.builder()
            .userId(resultSet.getLong("user_id"))
            .login(resultSet.getString("user_login"))
            .hashPassword(resultSet.getString("user_hash_password"))
            .build());

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserByUUID(String uuid) {
        try{
            return jdbcTemplate.queryForObject(SQL_GET_USER_BY_UUID, userRowMapper , uuid);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try{
            return jdbcTemplate.queryForObject(SQL_GET_USER_BY_LOGIN, userRowMapper , login);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public User getUserById(Long id) {
        try{
            return jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, userRowMapper , id);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public User createUser(String login, String rawPassword) {
        try{
            String hashPassword = (new BCryptPasswordEncoder()).encode(rawPassword);
            jdbcTemplate.update(SQL_CREATE_USER, login, hashPassword);
            return getUserByLogin(login);
        }catch (DataAccessException e){
            return null;
        }
    }

}
