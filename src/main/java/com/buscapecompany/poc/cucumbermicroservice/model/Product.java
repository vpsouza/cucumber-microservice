package com.buscapecompany.poc.cucumbermicroservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = {"handler", "hibernateLazyInitializer"})
public class Product {

	@Id
	@GeneratedValue
	@ApiModelProperty(required=true)
	private Long id;
	
	@Column
	@ApiModelProperty(required=true)
	private String name;
	
	@Column
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="parent_id")
	@JsonManagedReference(value="parent")
	private Product parent;
	
	@OneToMany(mappedBy="parent", orphanRemoval = true, cascade=CascadeType.ALL)
	@JsonBackReference(value="parent")
	private Set<Product> children = new HashSet<Product>();
	
	public Product() {
		
	}
	
	public Product(Long id, String name, String description, Product parent) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.parent = parent;
	}
	
	public Product(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Product(String name, String description, Product parent) {
		super();
		this.name = name;
		this.description = description;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Product getParent() {
		return parent;
	}
	
	public void setParent(Product parent) {
		this.parent = parent;
	}

	public Set<Product> getChildren() {
		return children;
	}
	
	public void setChildren(Set<Product> children) {
		this.children = children;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
