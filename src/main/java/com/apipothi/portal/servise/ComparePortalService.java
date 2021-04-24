package com.apipothi.portal.servise;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import java.util.List;

import com.apipothi.portal.swagger.to.ComparePortalTO;

public interface ComparePortalService {

	public String addProductFromManufacturer(List<ComparePortalTO> manufacturerProductDetails);
	public List<ComparePortalTO> getProductID();
}
