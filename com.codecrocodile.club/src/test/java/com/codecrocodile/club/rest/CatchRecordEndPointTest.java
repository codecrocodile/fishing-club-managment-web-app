package com.codecrocodile.club.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codecrocodile.club.model.catchrecord.CatchRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/root-context.xml")
public class CatchRecordEndPointTest {
	
	@Autowired
	private CatchRecordEndPoint catchRecordEndPoint;
	
	@Test
	public void testSaveCatchRecordValidation() {
//		CatchRecord cr = new CatchRecord();
//		
//		catchRecordEndPoint.saveCatchRecord(cr);
	}

}
