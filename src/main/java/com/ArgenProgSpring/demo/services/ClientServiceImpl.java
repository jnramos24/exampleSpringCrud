package com.ArgenProgSpring.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArgenProgSpring.demo.models.Client;
import com.ArgenProgSpring.demo.repositories.IClientRepository;

@Service
public class ClientServiceImpl implements IClientService{
	
	@Autowired
	private IClientRepository clientRepository;

	@Override
	public void saveClient(Client client) {
		clientRepository.save(client);
		
	}

	@Override
	public List<Client> getAllClients() {
		List<Client> allClients = clientRepository.findAll();
		return allClients;
	}

	@Override
	public Client getClientById(Integer id) {
		Client client = clientRepository.findById(id).orElse(null);
		return client;
	}

	@Override
	public void updateClient(Client client) {
		this.saveClient(client);
		
	}

	@Override
	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);	
	}

}
