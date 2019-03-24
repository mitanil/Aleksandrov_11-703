package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.models.Client;
import ru.itis.repositories.ClientRepository;

import java.util.UUID;

public class ClientServicesImpl implements ClientServices{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClientServicesImpl() {
    }

    @Override
    public String createUUID(String login, String rawPassword) {
        try{
            Client client = clientRepository.getClientByLogin(login);
            if (client == null || !passwordEncoder.matches(rawPassword, client.getHashPassword()))
                return null;
            String uuid = UUID.randomUUID().toString();
            clientRepository.createSession(uuid, client.getClientId());
            return uuid;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.createClient(client.getName(), client.getLogin(),passwordEncoder.encode(client.getRawPassword()));
    }

    @Override
    public boolean isExistByCookie(String uuid) {
        return clientRepository.getClientByUUID(uuid) != null;
    }

    @Override
    public Client getUserByUUID(String uuid) {
        return clientRepository.getClientByUUID(uuid);
    }

    @Override
    public Client getClientById(Integer clientId) {
        if(clientId == null) return null;
        return clientRepository.getClient(clientId);
    }
}
