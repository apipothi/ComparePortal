package com.apipothi.portal.servise;

import java.util.List;

import com.apipothi.portal.swagger.to.ComparePortalTO;

public interface ComparePortalService {

	public String addProductFromManufacturer(List<ComparePortalTO> manufacturerProductDetails);
	public List<ComparePortalTO> getProductID();
}
