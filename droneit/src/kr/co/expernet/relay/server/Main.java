package kr.co.expernet.relay.server;

import kr.co.expernet.relay.test.CCReceiverTest;
import kr.co.expernet.relay.test.GCSReceiverTest;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start relay server.");
		Runnable cc = new ConnectionRunnable(CCReceiverTest.class, 18110);
		Thread ccThread = new Thread(cc);
		ccThread.start();
		Runnable gcs = new ConnectionRunnable(GCSReceiverTest.class, 18120);
		Thread gcsThread = new Thread(gcs);
		gcsThread.start();
	}

}
