package com.codecrocodile.club.model.catchrecord;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CatchRecordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		System.out.println("checking supports.................");
		
		return CatchRecord.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "date", "name.empty");
		CatchRecord cr = (CatchRecord) target;
		
		System.out.println("validating");
		
		if (cr.getDate() == null) {
			e.rejectValue("date", "", "Date is required");
		}
	}

}
