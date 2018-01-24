package com.robbin.net.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class SingleFileHTTPServer {
	
	private static final Logger logger = Logger.getLogger("SingleFileHttpServer");
	
	private  byte[] content;
	private  byte[] header;
	private  int port;
	private  String encoding;
	
	public SingleFileHTTPServer(String data, String encoding,String mimetype, int port) throws UnsupportedEncodingException {
		this(data.getBytes(encoding),encoding,mimetype,port);
	}

	public SingleFileHTTPServer(byte[] data, String encoding,String mimetype, int port){
		this.content = data;
		this.port = port;
		this.encoding = encoding;
		String header = "HTTP/1.0 200 OK\r\n"
				+ "Server:OneFile 2.0\r\n"
				+ "Content-length: "+this.content.length+"\r\n"
				+ "Content-type: "+mimetype+";charset="+encoding+"\r\n\r\n";
		this.header = header.getBytes();
	}
	
	public void start(){
		
		ExecutorService pool = Executors.newFixedThreadPool(100);
		ServerSocket server = null;
		try {
			server = new ServerSocket(this.port);
			while(true){
				Socket connection = server.accept();
				pool.submit(new HTTPHandler(connection));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(server != null){
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class HTTPHandler implements Callable<Void> {
		
		private final Socket connection;
		
		public HTTPHandler(Socket connection) {
			this.connection = connection;
		}


		@Override
		public Void call() {
		    try {
				InputStream in = new BufferedInputStream(connection.getInputStream());
				OutputStream out = new BufferedOutputStream(connection.getOutputStream());
				StringBuilder sb = new StringBuilder();
				while(true){
					int c = in.read();
					if(c == '\r' || c == '\n' || c == -1) break;
					sb.append((char)c);
				}
				if(sb.toString().indexOf("HTTP/") != -1){
					out.write(header);
				}
				out.write(content);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		 }
	}
	
	public static void main(String[] args) {
		
	}
}
