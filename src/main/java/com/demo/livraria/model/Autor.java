package com.demo.livraria.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Autor implements Serializable {

	private String firstName;
	private String lastName;
	private Integer id;
	private String nomeCompleto;

	public Autor() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNomeCompleto() {
		return firstName + " " + lastName;
	}

}
