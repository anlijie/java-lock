package com.lock.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.itmayiedu.OrderNumGenerator;

// 订单生成调用业务逻辑
public class OrderService implements Runnable {
	// 生成订单号
	OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
	// 重入锁
	// private Lock lock = new ReentrantLock();
	private com.itmayiedu.service.Lock lock = new ZookeeperDistrbuteLock();

	public void run() {
		try {
			// 上锁
			lock.getLock();
			// synchronized (this) {
			// 模拟用户生成订单号
			getNumber();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 釋放鎖資源
			lock.unLock();
		}
	}

	public void getNumber() {
		String number = orderNumGenerator.getNumber();
		System.out.println(Thread.currentThread().getName() + ",##number:" + number);
	}

	public static void main(String[] args) {
		System.out.println("##模拟生成订单号开始...");

		for (int i = 0; i < 100; i++) {
			new Thread(new OrderService()).start();
		}
	}

}
