package kr.co.expernet.relay.server.connetion;

import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;

import kr.co.expernet.relay.server.util.Const;
import kr.co.expernet.relay.test.CCReceiverTest;
import kr.co.expernet.relay.test.GCSReceiverTest;

public class ConnectionRunnable implements Runnable {
	public Class<?> type;
	private int port;
	private ServerSocket serverSocket;

	public ConnectionRunnable(Class<?> type, int port) {
		this.type = type;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(port + " 포트 소켓 생성.");
			while (true) {
				Socket socket = serverSocket.accept();
				Constructor<? extends Object> con = type.getDeclaredConstructor(Socket.class);
				Runnable r = (Runnable) con.newInstance(socket);
				Thread thread = new Thread(r);
				thread.start();
				handleThread(thread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleThread(Thread thread) {
		if (type.isAssignableFrom(CCReceiverTest.class)) {
			Thread t = Const.getCcThread("CC");
			if (t != null) {
				t.interrupt();
				System.out.println("CC Thread Interrupt.");
			}
			Const.removeCcThread("CC");
			Const.addCcThread("CC", thread);
		}
		if (type.isAssignableFrom(GCSReceiverTest.class)) {
			Thread t = Const.getGcsThread("GCS");
			if (t != null) {
				t.interrupt();
				System.out.println("GCS Thread Interrupt.");
			}
			Const.removeGcsThread("GCS");
			Const.addGcsThread("GCS", thread);
		}
	}

}
