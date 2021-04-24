package com.apipothi.portal.dao;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apipothi.portal.model.ComparePortalProduct;

public interface ComparePortalDAO extends MongoRepository<ComparePortalProduct, Integer>{
	
	
}
