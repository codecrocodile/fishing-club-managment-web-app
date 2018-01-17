package com.codecrocodile.club.model.accounting;

import java.math.BigDecimal;

public class InvoiceLine {
	
	private long invoiceLineId;
	
	private long invoiceId;
	
	private long productId;
	
	private int productQuantity;
	
	private String productName;
	
	private String productDescription;
	
	private BigDecimal totalPrice = new BigDecimal(0);
	
	
	public InvoiceLine() {
		super();
	}

	public long getInvoiceLineId() {
		return invoiceLineId;
	}

	public void setInvoiceLineId(long invoiceLineId) {
		this.invoiceLineId = invoiceLineId;
	}

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
