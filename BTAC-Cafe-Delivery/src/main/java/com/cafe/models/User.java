package com.cafe.models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "cafe_users")
public class User {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@Column(name = "firstname", nullable = false)
	private String firstName;
	
	@Column(name = "lastname", nullable = false)
	private String lastName;

	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstName + ", lastname=" + lastName + ", username=" + username
				+ ", password=" + password + "]";
	}

	public User() {
		super();
	}
	
	public User(int id, String firstname, String lastname, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.username = username;
		this.password = password;
	}
	
	public User(String firstname, String lastname, String username, String password) {
		super();
		this.firstName = firstname;
		this.lastName = lastname;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
