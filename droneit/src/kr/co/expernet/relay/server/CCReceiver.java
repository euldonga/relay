package kr.co.expernet.relay.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CCReceiver implements Runnable {
	private BufferedReader reader;
	private BufferedWriter writer;

	public CCReceiver(Socket socket) {
		try {
			// 이전 스트림 제거.
			BufferedWriter previousWriter = Const.getCc("CC");
			if (previousWriter != null) {
				previousWriter.close();
				System.out.println("CC previousWriter Close");
				Const.removeCc("CC");
			}
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Const.addCc("CC", writer);
			System.out.println("CC is connected.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Thread thread = Const.getCcThread("CC");
		String message = null;
		while (true) {
			try {
				// 쓰레드 종료 확인.
				if (thread.isInterrupted()) {
					reader.close();
					System.out.println("CC BufferedReader Close");
					writer.close();
					System.out.println("CC BufferedWriter Close");
					break;
				}
				if ((message = reader.readLine()) != null) {
					BufferedWriter gcs = Const.getGcs("GCS");
					if (gcs != null) {
						gcs.write(message);
						gcs.newLine();
						gcs.flush();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}