package com.robbin.net.socket;
/**
 * @author robbin.zhang
 * @date 2016/11/03
 *
 */
public class Utils {
	
	public static byte[] LongToBytes(long values) {  // long ox12 ox34 ox56 ox78 
	     byte[] buffer = new byte[8]; 
	     for (int i = 0; i < 8; i++) {   
	          int offset = 64 - (i + 1) * 8;    
	          buffer[i] = (byte) ((values >> offset) & 0xff); // byte[i] ox12 ox34 ox56 ox78  低地址存放高字节
	      }
	     return buffer;  
	}

	 public static long BytesToLong(byte[] buffer) {   
	    long  values = 0;   
	    for (int i = 0; i < 8; i++) {    // 低地址存放高字节 ox00 ox00 ox00 ox00 
	        values <<= 8; values|= (buffer[i] & 0xff);   
	    }   
	    return values;  
	 } 
	 
	 
	 public static byte[] ConvertIntToByteArray(int i) {
	     byte[] arry = new byte[4];
	     arry[3] = (byte)(((i & 0xFF000000) >> 24) & 0xFF);  // 右移位，取低地址(高字节)->&0xFF
	     arry[2] = (byte)(((i & 0x00FF0000) >> 16) & 0xFF);  
	     arry[1] = (byte)(((i & 0x0000FF00) >> 8) & 0xFF);  
	     arry[0] = (byte)((i & 0x000000FF) & 0xFF);
	     return arry;
	 }
	 
	 public static int ConvertByteToInt(byte[] arry){
	     int i = 0;
	       
	     i += (int)(arry[0] & 0xFF);
	     i += (int)((arry[1] & 0xFF) << 8);
	     i += (int)((arry[2] & 0xFF) << 16);
	     i += (int)((arry[3] & 0xFF) << 24);
	     return i;
	 }

}
