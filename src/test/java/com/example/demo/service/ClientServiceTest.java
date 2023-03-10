package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveClient() {
        // given
        Client client = new Client(1L, "Jane", "Doe");

        // when
        when(clientRepository.save(client)).thenReturn(client);

        // then
        Client savedClient = clientService.createClient(client);
        assertEquals("Jane", savedClient.getFirstName());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void shouldGetAllClients() {
        // given
        List<Client> list = new ArrayList<>();
        list.add(new Client(1L, "Jane", "Doe"));
        list.add(new Client(2L, "John", "Doe"));

        // when
        when(clientRepository.findAll()).thenReturn(list);

        // then
        List<Client> testList = clientService.getClients();
        assertEquals(2, testList.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void shouldGetClientById() {
        // given
        Client client = new Client(1L, "Jane", "Doe");

        // when
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // then
        Client client2 = clientService.getClient(1L);
        assertEquals("Jane", client2.getFirstName());
        assertEquals("Doe", client2.getLastName());
    }

}
