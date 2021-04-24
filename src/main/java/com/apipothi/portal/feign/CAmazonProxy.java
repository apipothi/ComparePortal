package com.apipothi.portal.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.apipothi.portal.response.ComparePortalResponse;

//@FeignClient(contextId = "CAmazonProxy", name = "apigateway-service")
@FeignClient(name = "amazon", url = "http://localhost:8082")
@RibbonClient(name = "amazon")
public interface CAmazonProxy {
	@GetMapping("/getProductByIDInAmazon/{id}")
	public ComparePortalResponse getProductByIDInAmazon(@PathVariable int id);

	@GetMapping("/getAmazonProduct")
	public ComparePortalResponse getAmazonProduct();
}
