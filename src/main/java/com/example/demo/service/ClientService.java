package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientService {
    private ClientRepository clientRepository;

    public List<Client> getClient(){
        return clientRepository.findAll();
    }

    @Autowired
    public void setCarRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
