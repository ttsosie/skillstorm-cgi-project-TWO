package com.skillSTORM.warehouse_api.models;

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
public class Products {
	// this specifies that this property is the primary key for this table
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column
		private int id;
		
		@Column
		private String productName;
		
		@Column
		private double price;
		
		@Column
		private String details;
		
		//Many of the product type goes to 1 warehouse of Warehouse type
		@ManyToOne
		@JoinColumn(name = "warehouse_id", referencedColumnName = "id")
		private Warehouses warehouseId;
		
		@Column
		private int quantity;
		
		public Products() {
			super();
		}
		
		public Products(int id, String productName, double price, String details,Warehouses warehouseId, int quantity) {
			super();
			this.id = id;
			this.productName = productName;
			this.price = price;
			this.details = details;
			this.warehouseId = warehouseId;
			this.quantity = quantity;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
			this.details = details;
		}

		public Warehouses getWarehouseId() {
			return warehouseId;
		}

		public void setWarehouseId(Warehouses warehouseId) {
			this.warehouseId = warehouseId;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
}
