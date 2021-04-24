package com.apipothi.portal.feign;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.apipothi.portal.response.ComparePortalResponse;

@FeignClient(contextId = "CProductManufacturerProxy",name = "apigateway-service")
@RibbonClient(name = "productmanufacturer")
public interface CProductManufacturerProxy {
	@GetMapping("/productmanufacturer/getallproduct")
	public ComparePortalResponse getFromWarehouseService();
}
