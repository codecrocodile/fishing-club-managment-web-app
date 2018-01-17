package com.codecrocodile.dao.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codecrocodile.club.model.accounting.Invoice;

public interface AccountingMapper {
	
	public List<Invoice> getMemberInvoices(@Param("userId") int userId);

}
