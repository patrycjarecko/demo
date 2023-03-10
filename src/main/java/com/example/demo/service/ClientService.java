package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Client with this id doesn't exist"));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional(rollbackFor = Exception.class)
    public Client updateClient(Client client) {
        Client existingClient = clientRepository.findById(client.getId()).orElseThrow(() ->
                new RuntimeException("Client with this id doesn't exist"));
        existingClient.setFirstName(client.getFirstName());
        existingClient.setLastName(client.getLastName());

        return clientRepository.save(existingClient);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Autowired
    public void setCarRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
