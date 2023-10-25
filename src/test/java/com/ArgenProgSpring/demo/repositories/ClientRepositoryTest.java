package com.ArgenProgSpring.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ArgenProgSpring.demo.models.Client;

@DataJpaTest
public class ClientRepositoryTest {

	@Autowired
	private IClientRepository clientRepository;

	private Client client;

	@BeforeEach
	void setup() {
		client = new Client("Lio", "Messi", "123456789");
	}

	@DisplayName("Test para guardar un cliente")
	@Test
	void testSaveClient() {
		// Given condicion previa o configuracion
		// Lo que hago en el metodo setup()

		// when accion o comportamiento a testear
		Client clientBD = clientRepository.save(client);

		// then verificar la salida o comportamiento esperado
		assertThat(clientBD).isNotNull();
		assertThat(clientBD.getClientId()).isGreaterThan(0);

	}

	@DisplayName("Test para listar todos los clientes")
	@Test
	void testListAllClients() {
		// given
		Client client2 = new Client("Roman", "Riquelme", "987654321");
		clientRepository.save(client);
		clientRepository.save(client2);

		// when
		List<Client> allClientBd = clientRepository.findAll();

		// then
		assertThat(allClientBd).isNotNull();
		assertThat(allClientBd.size()).isEqualTo(2);
	}

	@DisplayName("Test para obtener un cliente por id")
	@Test
	void testGetClientById() {
		// given
		clientRepository.save(client);

		// when
		Client clientBD = clientRepository.findById(client.getClientId()).get();

		// then
		assertThat(clientBD).isNotNull();
	}

	@DisplayName("Test para actualizar un cliente")
	@Test
	void testUpdateClient() {
		// given
		clientRepository.save(client);

		Client clientBD = clientRepository.findById(client.getClientId()).get();

		clientBD.setName("Nombre cambiado");
		clientBD.setSurname("Apellido cambiado");
		clientBD.setDni("000");

		// when

		Client clientUpdate = clientRepository.save(client);

		// then
		assertThat(clientUpdate.getName()).isEqualTo("Nombre cambiado");
		assertThat(clientUpdate.getSurname()).isEqualTo("Apellido cambiado");
		assertThat(clientUpdate.getDni()).isEqualTo("000");
	}

	@DisplayName("Test para eliminar un cliente")
	@Test
	void testDeleteClient() {
		// given
		clientRepository.save(client);

		// when
		clientRepository.deleteById(client.getClientId());

		// then

		Optional<Client> clientDeleted = clientRepository.findById(client.getClientId());

		assertThat(clientDeleted).isEmpty();
	}

}
