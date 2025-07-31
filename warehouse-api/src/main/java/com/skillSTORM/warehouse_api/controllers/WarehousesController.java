package com.skillSTORM.warehouse_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillSTORM.warehouse_api.models.Warehouses;
import com.skillSTORM.warehouse_api.services.WarehousesService;

@RestController
@RequestMapping("/warehouses")
public class WarehousesController {
	
	@Autowired
	private WarehousesService service;
	
	@GetMapping
	public Iterable<Warehouses> findAll() {
		return this.service.finalAll();
	}

}
