package com.ecommerce.api.Product;



import com.ecommerce.api.Category.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="Products")
public class Product {

	@Id
	@Column(name="productId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	@Column(name="productName",nullable = false,unique = true)
	private String name;
	@Column(name="productDescription")
	private String description;
	@Column(name="productPrice")
	private float price;
	@ManyToOne

	@JoinColumn(name = "categoryId",nullable = false)
	
	private Category category;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int pid, String name, String description, float price, Category category) {
		super();
		this.pid = pid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", category=" + category + "]";
	}
	
	
	
}
