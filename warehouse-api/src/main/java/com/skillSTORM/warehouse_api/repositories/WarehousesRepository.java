package com.skillSTORM.warehouse_api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillSTORM.warehouse_api.models.Warehouses;

@Repository
public interface WarehousesRepository extends CrudRepository<Warehouses, Integer> {

}
