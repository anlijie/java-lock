package com.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

//生成订单号规则 使用时间戳+业务id
public class OrderNumGenerator {
	// 业务ID
	private static int count = 0;

	// 生成订单号
	public String getNumber() {
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
		SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return simpt.format(new Date()) + "-" + ++count;

	}

}
