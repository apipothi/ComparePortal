package com.apipothi.portal.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.apipothi.portal.model.ComparePortalProduct;

public interface ComparePortalDAO extends MongoRepository<ComparePortalProduct, Integer>{
	
	
}
