package kr.co.expernet.relay.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GCSReceiver implements Runnable {
	private BufferedReader reader;
	private BufferedWriter writer;

	public GCSReceiver(Socket socket) {
		try {
			BufferedWriter previousWriter = Const.getGcs("GCS");
			if (previousWriter != null) {
				previousWriter.close();
				System.out.println("GCS previousWriter Close");
				Const.removeGcs("GCS");
			}
			writer = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream())));
			reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
			Const.addGcs("GCS", writer);
			System.out.println("GCS is connected.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Thread thread = Const.getGcsThread("GCS");
		String message;
		try {
			while (true) {
				if (thread.isInterrupted()) {
					reader.close();
					System.out.println("CC BufferedReader Close");
					writer.close();
					System.out.println("CC BufferedWriter Close");
					break;
				}
				if ((message = reader.readLine()) != null) {
					BufferedWriter cc = Const.getCc("CC");
					if (cc != null) {
						cc.write(message);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}