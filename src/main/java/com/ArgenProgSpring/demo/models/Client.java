package com.ArgenProgSpring.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clientId;

	private String name;
	private String surname;
	private String dni;

	public Client(String name, String surname, String dni) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
	}

}
