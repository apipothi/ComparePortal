package com.apipothi.portal.controller;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apipothi.portal.feign.CAmazonProxy;
import com.apipothi.portal.feign.CFlipkartProxy;
import com.apipothi.portal.feign.CProductManufacturerProxy;
import com.apipothi.portal.response.ComparePortalResponse;
import com.apipothi.portal.response.ShopingResponse;
import com.apipothi.portal.servise.ComparePortalService;
import com.apipothi.portal.swagger.to.ComparePortalTO;

//db.amazon.find({  "A_PRODUCT_ID":"WIPAD" })

@RestController
public class ComparePortalController {
	private final static Logger logger = LoggerFactory.getLogger(ComparePortalController.class);

	@Value("${server.port}")
	int port;

	@Value("${spring.application.name}")
	String appname;

	@Autowired
	CProductManufacturerProxy productManufacturerProxy;

	@Autowired
	CAmazonProxy amazonProxy;

	@Autowired
	CFlipkartProxy flipkartProxy;

	@Autowired
	ComparePortalService service;

	/*
	 * Get All the product from PRODUCT MANUFACTURER and save it in to AMAZON DB
	 */
	@GetMapping("/addAllProductFromManufacturer")
	public ComparePortalResponse addAllProductFromManufacturer() {
		String statusMsg = "";
		ComparePortalResponse myresponse = new ComparePortalResponse();
		ComparePortalResponse proxyresponse = productManufacturerProxy.getFromWarehouseService();
		logger.info("Response form Manufacturer proxy {}" + proxyresponse);
		List<ComparePortalTO> manufacturerProductDetails = proxyresponse.getProductDetails();
		try {
			statusMsg = service.addProductFromManufacturer(manufacturerProductDetails);
			myresponse.setMessage(statusMsg);
			myresponse.setPort(proxyresponse.getPort());
			myresponse.setAppname(proxyresponse.getAppname());
			myresponse.setStatuscode(200);

		} catch (Exception e) {
			statusMsg = "Exception Occured during data insertion" + e;
			myresponse.setMessage(statusMsg);
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(400);
			logger.error("-Exception{}", e);
		}

		logger.info("-Response from Product Manufacturer-addAllProductFromManufacturer- {}", myresponse);
		return myresponse;
	}

	/*
	 * Get the Best price comparing FLIPKART and AMAZON 
	 */
	@GetMapping("/getBestPrice")
	public  List<ShopingResponse>  getManufacturerProductID() {

		logger.info("*********START-getBestPrice-*********");

		ComparePortalResponse amazonProxyResponse = new ComparePortalResponse();
		ComparePortalResponse flipkartProxyResponse = new ComparePortalResponse();

		List<ComparePortalTO> listOfAmazonProduct = new ArrayList<ComparePortalTO>();
		List<ComparePortalTO> listOfFlipkartProduct = new ArrayList<ComparePortalTO>();

		Map<String, Long> amazonProductidPrice = new HashMap<String, Long>();
		Map<String, Long> flipkartProductidPrice = new HashMap<String, Long>();

		List<ShopingResponse> shopingResponselist = new ArrayList<ShopingResponse>();
		

		String statusMsg = "";
		long amazonProductPrice = 0L;
		long flipkartProductPrice = 0L;

		try {

			// List of Product ID from Distributer
			List<ComparePortalTO> productidlist = service.getProductID();

			/* Flipkart Product Details */
			flipkartProxyResponse = flipkartProxy.getProductInFlipkart();
			listOfFlipkartProduct = flipkartProxyResponse.getProductDetails();
			logger.info("*********flipkartProxyResponse-*********",flipkartProxyResponse);
			
			/* Amazon Product Details */
			amazonProxyResponse = amazonProxy.getAmazonProduct();
			listOfAmazonProduct = amazonProxyResponse.getProductDetails();
			logger.info("*********amazonProxyResponse-*********",amazonProxyResponse);
			
			for (ComparePortalTO amazonProduct : listOfAmazonProduct) {
				amazonProductidPrice.put(amazonProduct.getWproductid(), amazonProduct.getWproductprice());
			}

			for (ComparePortalTO flipkartProduct : listOfFlipkartProduct) {
				flipkartProductidPrice.put(flipkartProduct.getWproductid(), flipkartProduct.getWproductprice());
			}

			for (ComparePortalTO productid : productidlist) {

				ShopingResponse shopingResponse = new ShopingResponse();
				
				if (productid.getWproductid() != null) {

					if (amazonProductidPrice.containsKey(productid.getWproductid()) && flipkartProductidPrice.containsKey(productid.getWproductid())) {

						amazonProductPrice = amazonProductidPrice.get(productid.getWproductid());
						flipkartProductPrice = flipkartProductidPrice.get(productid.getWproductid());

						if (amazonProductPrice > flipkartProductPrice) {
							
							// recommend Flipkart Price
							shopingResponse.setMessage(statusMsg);
							shopingResponse.setAmazonPort(amazonProxyResponse.getPort());
							shopingResponse.setFlipkartPort(flipkartProxyResponse.getPort());
							shopingResponse.setPort(port);
							shopingResponse.setAppname(amazonProxyResponse.getAppname()+" - "+flipkartProxyResponse.getAppname());
							shopingResponse.setStatuscode(200);
							shopingResponse.setProductid(productid.getWproductid());
							shopingResponse.setFlipkartprice(flipkartProductPrice);
							shopingResponse.setAmazonprice(amazonProductPrice);
							shopingResponse.setGrossMargin(amazonProductPrice - flipkartProductPrice);
							shopingResponse.setVendor("FLIPKART");
							shopingResponse.setRecommendedMessage("Congratulationas !!!  FLIPKART offering you best price ");
							shopingResponselist.add(shopingResponse);
							
						} else if(amazonProductPrice == flipkartProductPrice){
							shopingResponse.setMessage(statusMsg);
							shopingResponse.setAmazonPort(amazonProxyResponse.getPort());
							shopingResponse.setFlipkartPort(flipkartProxyResponse.getPort());
							shopingResponse.setPort(port);//amazonProxyResponse
							shopingResponse.setAppname(amazonProxyResponse.getAppname()+" - "+flipkartProxyResponse.getAppname());
							shopingResponse.setStatuscode(200);
							shopingResponse.setProductid(productid.getWproductid());
							shopingResponse.setFlipkartprice(flipkartProductPrice);
							shopingResponse.setAmazonprice(amazonProductPrice);
							shopingResponse.setGrossMargin(amazonProductPrice - flipkartProductPrice);
							shopingResponse.setVendor("Either FLIPKART OR AMAZON ");
							shopingResponse.setRecommendedMessage("Congratulationas !!!  FLIPKART & AMAZON both offering you best price ");
							shopingResponselist.add(shopingResponse);
						}else if(amazonProductPrice < flipkartProductPrice){
							// recommend Amazon Price
							shopingResponse.setMessage(statusMsg);
							shopingResponse.setAmazonPort(amazonProxyResponse.getPort());
							shopingResponse.setFlipkartPort(flipkartProxyResponse.getPort());
							shopingResponse.setPort(port);//amazonProxyResponse
							shopingResponse.setAppname(amazonProxyResponse.getAppname()+" - "+flipkartProxyResponse.getAppname());
							shopingResponse.setStatuscode(200);
							shopingResponse.setProductid(productid.getWproductid());
							shopingResponse.setFlipkartprice(flipkartProductPrice);
							shopingResponse.setAmazonprice(amazonProductPrice);
							shopingResponse.setGrossMargin(flipkartProductPrice - amazonProductPrice);
							shopingResponse.setVendor("AMAZON");
							shopingResponse.setRecommendedMessage("Congratulationas !!!  AMAZON offering you best price ");
							shopingResponselist.add(shopingResponse);
						}

					} else {
						System.out.println("Product ID " + productid + " not Exist in to Amazon or Flipkart");
						String str = "Product ID" + productid + " not Exist in to Amazon or Flipkart";
						shopingResponse.setMessage(str);
						shopingResponse.setAmazonPort(amazonProxyResponse.getPort());
						shopingResponse.setFlipkartPort(flipkartProxyResponse.getPort());
						shopingResponse.setPort(port);
						shopingResponse.setAppname(amazonProxyResponse.getAppname()+" - "+flipkartProxyResponse.getAppname());
						shopingResponse.setStatuscode(400);
						shopingResponselist.add(shopingResponse);
					}

				} else {
					System.out.println("****Product ID is NULL");
					System.out.println("Product ID " + productid + " IS NULL");
					String str = "Product ID" + productid + " IS NULL";
					shopingResponse.setMessage(str);
					shopingResponse.setPort(port);
					shopingResponse.setAppname(appname);
					shopingResponse.setStatuscode(400);
					shopingResponselist.add(shopingResponse);
				}
			}

			System.out.println("amazonProductidProce MAP-***" + amazonProductidPrice);
			System.out.println("flipkartProductidProce MAP-***" + flipkartProductidPrice);

		} catch (Exception e) {
			statusMsg = "Exception Occured during Gross Margin cALCULATION" + e;
			logger.error("statusMsg", e);
		}

		// 1. Get the list of PRODUCT ID from "compareportal portal"
		// 2. Get the price from FLIPKART against the PRODUCT ID from "compareportal
		// portal"
		// 3. Get the price from AMAZON against the PRODUCT ID from "compareportal
		// portal"
		// 4. Logic to compare the price

		return shopingResponselist;
	}

}
