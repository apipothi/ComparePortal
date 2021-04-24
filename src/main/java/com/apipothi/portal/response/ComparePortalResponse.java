package com.apipothi.portal.response;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import java.util.List;

import com.apipothi.portal.swagger.to.ComparePortalTO;

public class ComparePortalResponse {

	private int statuscode;
	private String message;
	private int port;
	private String appname;
	private List<ComparePortalTO> productDetails;

	public ComparePortalResponse() {
	}

	public ComparePortalResponse(int statuscode, String message, int port, String appname,
			List<ComparePortalTO> productDetails) {
		super();
		this.statuscode = statuscode;
		this.message = message;
		this.port = port;
		this.appname = appname;
		this.productDetails = productDetails;
	}

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public List<ComparePortalTO> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ComparePortalTO> productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public String toString() {
		return "ComparePortalResponse [statuscode=" + statuscode + ", message=" + message + ", port=" + port
				+ ", appname=" + appname + ", productDetails=" + productDetails + "]";
	}

}
