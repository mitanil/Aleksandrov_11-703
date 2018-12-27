package ru.itis.services;

import ru.itis.models.Client;

public interface ClientServices {
    String createUUID(String login, String password);
    Client createClient(Client client);

    boolean isExistByCookie(String uuid);

    Client getUserByUUID(String uuid);
    Client getClientById(Integer clientId);
}
