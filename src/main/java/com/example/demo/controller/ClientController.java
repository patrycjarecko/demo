package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    private ClientService clientService;

    @GetMapping("")
    public List<Client> getAlClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId) {
        return ResponseEntity.ok().body(clientService.getClient(clientId));
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PutMapping
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable(value = "id") Long clientId) {
        clientService.deleteClient(clientId);
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
