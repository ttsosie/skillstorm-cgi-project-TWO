package com.skillSTORM.warehouse_api.dtos;

import com.skillSTORM.warehouse_api.models.Warehouses;

public record ProductsDTO(String productName, double price, String details, Warehouses warehouseId, int quantity) {

}
