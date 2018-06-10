package com.daohu.runlife.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JiadianApiApplicationTests {

	@Test
	public void contextLoads() {
//		BigInteger bal = new BigInteger(1584112);
//		DecimalFormat df=new DecimalFormat("0.000000");
//		String retBalance = df.format(Long.parseLong(String.valueOf(1584112))/1000000f);
		System.out.println(new Date());
	}

}
