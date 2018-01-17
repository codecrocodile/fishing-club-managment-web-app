package com.codecrocodile.club.model.accounting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.codecrocodile.club.common.util.JsonDateDeserializer;
import com.codecrocodile.club.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Invoice {
	
	private long invoiceId;
	
	private long orderId;
	
	private Date date;
	
	private BigDecimal totalPrice = new BigDecimal(0);
	
	private List<InvoiceLine> invoiceLines = new ArrayList<>();

	
	public Invoice() {
		super();
	}

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}

	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTotalPrice() {
		for (InvoiceLine il : invoiceLines) {
			totalPrice = totalPrice.add(il.getTotalPrice());
		}
		
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}
	
}
