package com.skillSTORM.warehouse_api.dtos;

import java.time.LocalDate;

import com.skillSTORM.warehouse_api.models.Customers;
import com.skillSTORM.warehouse_api.models.Products;

public record OrdersDTO(Customers customerId, Products productId, LocalDate date) {

}
