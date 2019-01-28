package kr.co.expernet.relay.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CcClientTest {
	public static void main(String[] args) {
		try {
			Runnable r = new CcTest(new Socket("127.0.0.1", 18110));
			Thread t = new Thread(r);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class CcTest implements Runnable {
		private BufferedReader reader;
		private BufferedWriter writer;

		public CcTest(Socket socket) {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String message = null;
			while (true) {
				try {
					Thread.sleep(2000);
					System.out.println("CC 실행중...");
					message = "CC 메세지: " + System.currentTimeMillis();
					writer.write(message);
					writer.newLine();
					writer.flush();
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

/**
 * uint8_t magic;              ///< protocol magic marker
 * uint8_t len;                ///< Length of payload
 * uint8_t incompat_flags;     ///< flags that must be understood
 * uint8_t compat_flags;       ///< flags that can be ignored if not understood
 * uint8_t seq;                ///< Sequence of packet
 * uint8_t sysid;              ///< ID of message sender system/aircraft
 * uint8_t compid;             ///< ID of the message sender component
 * uint8_t msgid 0:7;          ///< first 8 bits of the ID of the message
 * uint8_t msgid 8:15;         ///< middle 8 bits of the ID of the message
 * uint8_t msgid 16:23;        ///< last 8 bits of the ID of the message
 * uint8_t target_sysid;       ///< Optional field for point-to-point messages, used for payload else
 * uint8_t target_compid;      ///< Optional field for point-to-point messages, used for payload else
 * uint8_t payload[max 253];   ///< A maximum of 253 payload bytes
 * uint16_t checksum;          ///< X.25 CRC
 * uint8_t signature[13];      ///< Signature which allows ensuring that the link is tamper-proof
 */

// STX: 1
// LEN: 1
// INC (FLAGS): 1
// CMP (FLAGS): 1
// SYS: 1
// COMP: 1
// MSG ID: 3
// PAYLOAD: 0-255
// CHECKSUM: 2
// SIGNATURE: 13
// fd 3e 00 00 35 c7 016900 00c374fb7f000000000f01f0bd625fddbdda11dbc052496ebc32a4c43a60454a3b2adf15bc9e7f5ebe6dd0dcbe00000000d2cccc3d1a86f54300000042ff1b 48d3