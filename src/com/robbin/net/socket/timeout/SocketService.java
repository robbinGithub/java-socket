package com.robbin.net.socket.timeout;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * SocketTimeoutException ���� 
 * 
 * java.net.SocketTimeoutException: Read timed out
 *	at java.net.SocketInputStream.socketRead0(Native Method)
 *	at java.net.SocketInputStream.read(SocketInputStream.java:152)
 *	at java.net.SocketInputStream.read(SocketInputStream.java:122)
 *	at java.net.SocketInputStream.read(SocketInputStream.java:210)
 *	at com.robbin.net.socket.timeout.T.run(SocketService.java:43)
 *	at java.lang.Thread.run(Thread.java:744)
	
 * @author robbin.zhang
 * @see http://cuisuqiang.iteye.com/blog/1725348
 *
 */
public class SocketService {
	public static void main(String[] args) {
		try {
			SocketAddress address = new InetSocketAddress("localhost", 30001);
			// ���������˿� 8001
			ServerSocket ss = new ServerSocket();
			ss.bind(address);
			// ��������
			Socket s = ss.accept();
			new Thread(new T(s)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class T implements Runnable {
	public void run() {
		try {
			System.out.println(socket.toString());
			socket.setKeepAlive(true);
			socket.setSoTimeout(5 * 1000);
			String _pattern = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat(_pattern);
			while (true) {
				System.out.println("��ʼ��" + format.format(new Date()));
				try {
					InputStream ips = socket.getInputStream();
					ByteArrayOutputStream bops = new ByteArrayOutputStream();
					int data = -1;
					while((data = ips.read()) != -1){
						System.out.println(data);
						bops.write(data);
					}
					System.out.println(Arrays.toString(bops.toByteArray()));
				}catch(SocketTimeoutException e){
					e.printStackTrace();
				}catch(SocketException e){
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(1000);
				System.out.println(socket.isBound()); // �Ƿ�
				System.out.println(socket.isClosed()); // �Ƿ�ر�
				System.out.println(socket.isConnected()); // �Ƿ�����
				System.out.println(socket.isInputShutdown()); // �Ƿ�ر�������
				System.out.println(socket.isOutputShutdown()); // �Ƿ�ر������
				System.out.println("������" + format.format(new Date()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Socket socket = null;
	public T(Socket socket) {
		this.socket = socket;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
