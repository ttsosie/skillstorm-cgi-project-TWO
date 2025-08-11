package com.skillSTORM.warehouse_api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillSTORM.warehouse_api.models.Customers;

@Repository
public interface CustomersRespository extends CrudRepository<Customers, Integer>{

}
