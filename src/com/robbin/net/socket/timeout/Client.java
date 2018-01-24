package com.robbin.net.socket.timeout;
import java.net.Socket;
public class Client {
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 30001);
			socket.setKeepAlive(true);
			socket.close();
			while(true && null != socket){
				Thread.sleep(10 * 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}