package com.ArgenProgSpring.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ArgenProgSpring.demo.models.Client;
import com.ArgenProgSpring.demo.services.ClientServiceImpl;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientServiceImpl clientService;

	@PostMapping("/create")
	public ResponseEntity<String> createClient(@RequestBody Client client) {

		try {
			clientService.saveClient(client);
			return new ResponseEntity<>("Cliente creado", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("")
	public ResponseEntity<List<Client>> getAllClients() {
		List<Client> allClients = clientService.getAllClients();

		if (!allClients.isEmpty()) {
			return new ResponseEntity<>(allClients, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<Client> getclientById(@PathVariable("ClientId") Integer client_id) {
		Client client = clientService.getClientById(client_id);

		if (client != null) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		try {
			clientService.updateClient(client);
			Client clientUpdated = clientService.getClientById(client.getClientId());

			if (clientUpdated != null) {
				return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{client_id}")
	public ResponseEntity<String> deleteClientById(@PathVariable Integer client_id) {
		try {
			Client client = clientService.getClientById(client_id);
			if (client != null) {
				clientService.deleteClient(client_id);
				return new ResponseEntity<>("Cliente borrado", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe cliente con ese ID", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
