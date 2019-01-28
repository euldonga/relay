package kr.co.expernet.relay.server;

/**
 * Mavlink2 프로토콜 패킷 핸들링 클래스.
 * <hr><pre>
 * Mavlink2 패킷 포맷.
 * uint8_t magic;              < protocol magic marker
 * uint8_t len;                < Length of payload
 * uint8_t incompat_flags;     < flags that must be understood
 * uint8_t compat_flags;       < flags that can be ignored if not understood
 * uint8_t seq;                < Sequence of packet
 * uint8_t sysid;              < ID of message sender system/aircraft
 * uint8_t compid;             < ID of the message sender component
 * uint8_t msgid 0:7;          < first 8 bits of the ID of the message
 * uint8_t msgid 8:15;         < middle 8 bits of the ID of the message
 * uint8_t msgid 16:23;        < last 8 bits of the ID of the message
 * uint8_t target_sysid;       < Optional field for point-to-point messages, used for payload else
 * uint8_t target_compid;      < Optional field for point-to-point messages, used for payload else
 * uint8_t payload[max 253];   < A maximum of 253 payload bytes
 * uint16_t checksum;          < X.25 CRC
 * uint8_t signature[13];      < Signature which allows ensuring that the link is tamper-proof
 * </pre><hr>
 * @author kei
 */
public class MavlinkHandler {

	/**
	 * 시크릿 키 추출.
	 * 
	 * @param packet
	 * @return
	 */
	public String getSecretKey(String packet) {
		
		return "";
	}
}
