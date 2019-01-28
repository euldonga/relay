package kr.co.expernet.relay.server;

import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;

public class Const {

	private static Map<String, BufferedWriter> ccMap = new HashMap<>();
	private static Map<String, BufferedWriter> gcsMap = new HashMap<>();
	private static Map<String, Thread> ccThreadMap = new HashMap<>();
	private static Map<String, Thread> gcsThreadMap = new HashMap<>();

	/**
	 * get CC BufferedWriter from hashmap
	 * 
	 * @param key
	 * @return
	 */
	public static BufferedWriter getCc(String key) {
		return ccMap.get(key);
	}

	/**
	 * add CC BufferedWriter to the hashmap
	 * 
	 * @param key
	 * @param writer
	 */
	public static void addCc(String key, BufferedWriter writer) {
		ccMap.put(key, writer);
	}

	/**
	 * remove CC BufferedWriter from hashmap
	 * 
	 * @param key
	 */
	public static void removeCc(String key) {
		ccMap.remove(key);
	}

	/**
	 * get GCS BufferedWriter from hashmap
	 * 
	 * @param key
	 * @return
	 */
	public static BufferedWriter getGcs(String key) {
		return gcsMap.get(key);
	}

	/**
	 * add GCS BufferedWriter to the hashmap
	 * 
	 * @param key
	 * @param writer
	 */
	public static void addGcs(String key, BufferedWriter writer) {
		gcsMap.put(key, writer);
	}

	/**
	 * remove GCS BufferedWriter from hashmap
	 * 
	 * @param key
	 */
	public static void removeGcs(String key) {
		gcsMap.remove(key);
	}

	/**
	 * get CC Thread form hashmap
	 * 
	 * @param key
	 * @return
	 */
	public static Thread getCcThread(String key) {
		return ccThreadMap.get(key);
	}

	/**
	 * add CC Thread to the hashmap
	 * 
	 * @param key
	 * @param thread
	 */
	public static void addCcThread(String key, Thread thread) {
		ccThreadMap.put(key, thread);
	}

	/**
	 * remove CC Thread from hashmap
	 * 
	 * @param key
	 */
	public static void removeCcThread(String key) {
		ccThreadMap.remove(key);
	}

	/**
	 * get GCS Thread from hashmap
	 * 
	 * @param key
	 * @return
	 */
	public static Thread getGcsThread(String key) {
		return gcsThreadMap.get(key);
	}

	/**
	 * add GCS Thread to the hashmap
	 * 
	 * @param key
	 * @param thread
	 */
	public static void addGcsThread(String key, Thread thread) {
		gcsThreadMap.put(key, thread);
	}

	/**
	 * remove GCS Thread from hashmap
	 * 
	 * @param key
	 */
	public static void removeGcsThread(String key) {
		gcsThreadMap.remove(key);
	}
}
