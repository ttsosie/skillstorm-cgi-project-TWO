package com.skillSTORM.warehouse_api.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//this annotation states that this class is a DB entity
@Entity

//this annotation states which table this class corresponds to
@Table

public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	//Many orders can belong to 1 customer
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customers customerId;
	
	//Many orders can from from 1 product
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Products productId;
	
	@Column(name = "date")
	private LocalDate date;
	
	
	public Orders() {
		super();
	}
	
	public Orders(int id, Customers customerId, Products productId, LocalDate date) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.productId = productId;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customers getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customers customerId) {
		this.customerId = customerId;
	}

	public Products getProductId() {
		return productId;
	}

	public void setProductId(Products productId) {
		this.productId = productId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
