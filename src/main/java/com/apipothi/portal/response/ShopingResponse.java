package com.apipothi.portal.response;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
public class ShopingResponse {

	private int statuscode;
	private String message;
	private int manufacturerport;
	private int flipkartPort;
	private int amazonPort;
	private int port;
	private String appname;
	private Long flipkartprice;
	private Long amazonprice;
	private long grossMargin;
	private String vendor;
	private String recommendedMessage;
	private String productid;

	public ShopingResponse() {
	}

	public ShopingResponse(int statuscode, String message, int manufacturerport, int flipkartPort, int amazonPort,
			int port, String appname, Long flipkartprice, Long amazonprice, long grossMargin, String vendor,
			String recommendedMessage, String productid) {
		super();
		this.statuscode = statuscode;
		this.message = message;
		this.manufacturerport = manufacturerport;
		this.flipkartPort = flipkartPort;
		this.amazonPort = amazonPort;
		this.port = port;
		this.appname = appname;
		this.flipkartprice = flipkartprice;
		this.amazonprice = amazonprice;
		this.grossMargin = grossMargin;
		this.vendor = vendor;
		this.recommendedMessage = recommendedMessage;
		this.productid = productid;
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

	public int getManufacturerport() {
		return manufacturerport;
	}

	public void setManufacturerport(int manufacturerport) {
		this.manufacturerport = manufacturerport;
	}

	public int getFlipkartPort() {
		return flipkartPort;
	}

	public void setFlipkartPort(int flipkartPort) {
		this.flipkartPort = flipkartPort;
	}

	public int getAmazonPort() {
		return amazonPort;
	}

	public void setAmazonPort(int amazonPort) {
		this.amazonPort = amazonPort;
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

	public Long getFlipkartprice() {
		return flipkartprice;
	}

	public void setFlipkartprice(Long flipkartprice) {
		this.flipkartprice = flipkartprice;
	}

	public Long getAmazonprice() {
		return amazonprice;
	}

	public void setAmazonprice(Long amazonprice) {
		this.amazonprice = amazonprice;
	}

	public long getGrossMargin() {
		return grossMargin;
	}

	public void setGrossMargin(long grossMargin) {
		this.grossMargin = grossMargin;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getRecommendedMessage() {
		return recommendedMessage;
	}

	public void setRecommendedMessage(String recommendedMessage) {
		this.recommendedMessage = recommendedMessage;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "ShopingResponse [statuscode=" + statuscode + ", message=" + message + ", manufacturerport="
				+ manufacturerport + ", flipkartPort=" + flipkartPort + ", amazonPort=" + amazonPort + ", port=" + port
				+ ", appname=" + appname + ", flipkartprice=" + flipkartprice + ", amazonprice=" + amazonprice
				+ ", grossMargin=" + grossMargin + ", vendor=" + vendor + ", recommendedMessage=" + recommendedMessage
				+ ", productid=" + productid + "]";
	}

}
