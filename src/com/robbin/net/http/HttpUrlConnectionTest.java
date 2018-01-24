package com.robbin.net.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUrlConnectionTest {
	
	public static void main(String[] args) {
		new HttpUrlConnectionTest().httpUrlConnection();
	}
	
	
	private void httpUrlConnection() { 
		try { 
		String pathUrl = "172.20.0.206:8082/TestServelt/login.do"; 
		// �������� 
		URL url = new URL(pathUrl); 
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(); 

		 
		// //������������ 
		httpConn.setDoOutput(true);// ʹ�� URL ���ӽ������ 
		httpConn.setDoInput(true);// ʹ�� URL ���ӽ������� 
		httpConn.setUseCaches(false);// ���Ի��� 
		httpConn.setRequestMethod("POST");// ����URL���󷽷� 
		String requestString = "�ͷ���Ҫ��������ʽ���͵�����˵�����..."; 

		 
		// ������������ 
		// ��������ֽ����ݣ������������ı��룬���������������˴����������ı���һ�� 
		byte[] requestStringBytes = requestString.getBytes("UTF-8"); 
		httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length); 
		httpConn.setRequestProperty("Content-Type", "application/octet-stream"); 
		httpConn.setRequestProperty("Connection", "Keep-Alive");// ά�ֳ����� 
		httpConn.setRequestProperty("Charset", "UTF-8"); 
		// 
		String name = URLEncoder.encode("������", "utf-8"); 
		httpConn.setRequestProperty("NAME", name); 

		 
		// �������������д������ 
		OutputStream outputStream = httpConn.getOutputStream(); 
		outputStream.write(requestStringBytes); 
		outputStream.close(); 
		// �����Ӧ״̬ 
		int responseCode = httpConn.getResponseCode(); 

		 
		if (HttpURLConnection.HTTP_OK == responseCode) {// ���ӳɹ� 
		// ����ȷ��Ӧʱ�������� 
		StringBuffer sb = new StringBuffer(); 
		String readLine; 
		BufferedReader responseReader; 
		// ������Ӧ�����������������Ӧ������ı���һ�� 
		 responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8")); 
		while ((readLine = responseReader.readLine()) != null) { 
		sb.append(readLine).append("\n"); 
		} 
		responseReader.close(); 
//		tv.setText(sb.toString()); 
		} 
		} catch (Exception ex) { 
		ex.printStackTrace(); 
		} 

		 
		} 

}
