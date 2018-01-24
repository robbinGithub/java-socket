package com.robbin.net.socket.timeout;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * java socket 缺省超时时间
 * @author robbin.zhang
 * @date 2017/02/22 10:44
 */
public class SocketTimeoutTestCase {
	
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.baidu.com");
		URLConnection conn = url.openConnection();
		System.out.println(conn.getConnectTimeout()); // 0
		System.out.println(conn.getReadTimeout());  // 0
	}

}
