package kr.co.expernet.relay.test;

import java.io.BufferedOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ConstTest {

	private static Map<String, BufferedOutputStream> ccMap = new HashMap<>();
	private static Map<String, BufferedOutputStream> gcsMap = new HashMap<>();

	public static void addGCS(String key, BufferedOutputStream out) {
		gcsMap.put(key, out);
	}

	public static void removeGCS(String key) {
		gcsMap.remove(key);
	}

	public static BufferedOutputStream getGCS(String key) {
		return gcsMap.get(key);
	}

	public static void addCC(String key, BufferedOutputStream out) {
		ccMap.put(key, out);
	}

	public static void removeCC(String key) {
		ccMap.remove(key);
	}

	public static BufferedOutputStream getCC(String key) {
		return ccMap.get(key);
	}

}
