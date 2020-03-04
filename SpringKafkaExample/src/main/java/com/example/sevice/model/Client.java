package com.example.sevice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	
	

	@Id
	@Column(name = "client_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;
	
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "city")
	private String city;
	
	public Client() {
		
	}

	public Client(String name, String email, String city) {
		super();
		this.name = name;
		this.email = email;
		this.city = city;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", email=" + email + ", city=" + city + "]";
	}
	
	

}
