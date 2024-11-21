package com.ecommerce.api.Category;

import java.util.List;

import com.ecommerce.api.Product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	@Column(name = "categoryName",nullable = false)
	private String name;
	@Column(name="categoryDescription")
	private String description;
	@OneToMany(mappedBy ="category",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Product>product;
	
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Category(int id, String name, String description, List<Product> product) {
		super();
		categoryId = id;
		this.name = name;
		this.description = description;
		this.product = product;
	}


	public int getId() {
		return categoryId;
	}


	public void setId(int id) {
		categoryId = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Product> getProduct() {
		return product;
	}


	public void setProduct(List<Product> product) {
		this.product = product;
	}


	@Override
	public String toString() {
		return "Category [Id=" + categoryId + ", name=" + name + ", description=" + description + ", product=" + product + "]";
	}
	
	
	
	
	

}
