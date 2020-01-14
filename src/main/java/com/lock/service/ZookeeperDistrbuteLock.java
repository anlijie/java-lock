package com.lock.service;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkDataListener;

public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {

	@Override
	Boolean tryLock() {
		try {
			zkClient.createEphemeral(PATH);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	void waitLock() {

		// 使用事件监听，获取到节点被删除，
		IZkDataListener iZkDataListener = new IZkDataListener() {
			// 当节点被删除
			public void handleDataDeleted(String dataPath) throws Exception {
				if (countDownLatch != null) {
					// 唤醒
					countDownLatch.countDown();
				}

			}

			// 当节点发生改变
			public void handleDataChange(String dataPath, Object data) throws Exception {

			}
		};

		// 注册节点信息
		zkClient.subscribeDataChanges(PATH, iZkDataListener);
		if (zkClient.exists(PATH)) {
			// 创建信号量
			countDownLatch = new CountDownLatch(1);
			try {
				// 等待
				countDownLatch.await();
			} catch (Exception e) {

			}

		}
		// 删除事件通知
		zkClient.unsubscribeDataChanges(PATH, iZkDataListener);
	}

}
