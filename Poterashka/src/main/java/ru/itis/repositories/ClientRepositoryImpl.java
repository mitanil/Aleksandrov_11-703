package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Client;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ClientRepositoryImpl implements ClientRepository {


    private JdbcTemplate jdbcTemplate;

    public ClientRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //    language=sql
    private static final String SQL_SELECT_BY_AUTH = "SELECT client.* FROM client NATURAL JOIN auth WHERE uuid = ?";
    //    language=sql
    private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM client WHERE client_login = ?";
    //    language=sql
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM client WHERE client_id = ?";
    //    language=sql
    private static final String SQL_CREATE_CLIENT = "INSERT INTO client (client_name, client_login, client_password) VALUES (?,?,?)";
    //    language=sql
    private static final String SQL_ADD_AUTH = "INSERT INTO auth (uuid, client_id) VALUES (?,?);";


    RowMapper<Client> clientRowMapper = ((resultSet, i) -> Client.builder()
            .clientId(resultSet.getInt("client_id"))
            .name(resultSet.getString("client_name"))
            .hashPassword(resultSet.getString("client_password"))
            .login(resultSet.getString("client_login"))
            .build()
    );

    @Override
    public Client createClient(String name, String login, String hasPassword) {
        try{
            jdbcTemplate.update(SQL_CREATE_CLIENT, name, login, hasPassword);
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_LOGIN, clientRowMapper, login);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Client getClientByLogin(String login) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_LOGIN, clientRowMapper, login);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Client getClient(Integer clientId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, clientRowMapper, clientId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Client getClientByUUID(String uuid) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_AUTH, clientRowMapper, uuid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Boolean createSession(String uuid, Integer clientId) {
        try{
            jdbcTemplate.update(SQL_ADD_AUTH, uuid, clientId);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
