package kr.co.expernet.relay.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GcsClientTest {

	public static void main(String[] args) {
		Runnable r;
		try {
			r = new GcsTest(new Socket("127.0.0.1", 18120));
			Thread t = new Thread(r);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class GcsTest implements Runnable {
		private BufferedReader reader;
		private BufferedWriter writer;

		public GcsTest(Socket socket) {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String message;
			while (true) {
				try {
					System.out.println("GcsTest...");
					message = reader.readLine();
					System.out.println(message);
					if (message != null) {
						System.out.println(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
