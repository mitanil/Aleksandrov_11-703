package ru.itis.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Auth;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.UUID;

public class AuthRepositoryJdbcImpl implements AuthRepository {

    JdbcTemplate jdbcTemplate;


    //    language=sql
    private static final String SQL_CREATE_SESSION = "INSERT INTO auth (uuid, user_id) VALUES (?,?)";

    //    language=sql
    private static final String SQL_GET_SESSION = "SELECT * FROM auth WHERE uuid = ?";


    public AuthRepositoryJdbcImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Auth> authRowMapper = ((resultSet, i) -> Auth.builder()
            .userId(resultSet.getLong("user_id"))
            .uuid(resultSet.getString("uuid"))
            .build());

    @Override
    public Auth createSession(User user) {
        String uuid = UUID.randomUUID().toString();
        jdbcTemplate.update(SQL_CREATE_SESSION, uuid, user.getUserId());
        try{
            return jdbcTemplate.queryForObject(SQL_GET_SESSION, authRowMapper, uuid);
        }catch (DataAccessException e){
            return null;
        }
    }
}
