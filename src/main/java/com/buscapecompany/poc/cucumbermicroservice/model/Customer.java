package com.buscapecompany.poc.cucumbermicroservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	private String surName;
	private String lastName;
	private Integer age;
	
	public Customer() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Customer(String firstName, String surName, String lastName, Integer age) {
		super();
		this.firstName = firstName;
		this.surName = surName;
		this.lastName = lastName;
		this.age = age;
	}

	public Customer(Long id, String firstName, String surName, String lastName, Integer age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surName = surName;
		this.lastName = lastName;
		this.age = age;
	}
}
