package com.ArgenProgSpring.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ArgenProgSpring.demo.models.Client;

@Service
public interface IClientService {
	
	public void saveClient(Client client);
	
	public List<Client> getAllClients();
	
	public Client getClientById(Integer id);
	
	public void updateClient(Client client);
	
	public void deleteClient(Integer id);

}
