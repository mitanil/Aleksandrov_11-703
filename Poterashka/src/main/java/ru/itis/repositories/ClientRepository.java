package ru.itis.repositories;

import ru.itis.models.Client;

public interface ClientRepository {
    Client createClient(String name, String login, String hasPassword);
    Client getClientByLogin(String login);
    Client getClient(Integer clientId);
    Client getClientByUUID(String uuid);

    Boolean createSession(String uuid, Integer clientId);
}
