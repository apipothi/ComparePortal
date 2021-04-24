package com.apipothi.portal.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.apipothi.portal.response.ComparePortalResponse;

@FeignClient(name = "flipkart", url = "http://localhost:8081")
@RibbonClient(name = "flipkart")
public interface CFlipkartProxy {
	@GetMapping("getFlipkartProducts")
	public ComparePortalResponse getProductInFlipkart();
}
