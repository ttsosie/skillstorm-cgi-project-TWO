package com.skillSTORM.warehouse_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillSTORM.warehouse_api.models.Warehouses;
import com.skillSTORM.warehouse_api.repositories.WarehousesRepository;

@Service
public class WarehousesService {
	
	@Autowired
	private WarehousesRepository repo;
	
	public Iterable<Warehouses> finalAll(){
		return this.repo.findAll();
	}

}
