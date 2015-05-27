package com.levilee.levicap.model.util;

/**
 * 
 * @author levi
 *���Ƕ������ļ���������
 *
 */

public class ByteUtil {
	/*��������macת��ΪString ���͵�mac��ַ*/
	public static String macToString(byte[] dst_mac){
		StringBuffer sb=new StringBuffer();
		int flag2 = 0;
		for (byte b : dst_mac) {
			flag2++;
			if (flag2 < dst_mac.length) {
				sb.append(Integer.toHexString(b & 0xff) + ":");
			} else{
				sb.deleteCharAt(sb.length()-1);
			}
			
		}
		String string = sb.toString();
		String[] array = string.split(":");
		String result = "";
		for(int i=0;i<array.length;i++){
			if(array[i].length()<2)  array[i]="0"+array[i];
			result=result+array[i]+":";
		}
		
		return result.substring(0, result.length()-1).toUpperCase();
	}
	//���������ļ�ת��Ϊ16���Ʊ��ڲ鿴
	public static String byteToString(byte[] bytes){
		StringBuffer sb=new StringBuffer();
		int flag = 0;
		for (byte b : bytes) {
			flag++;
			if (flag < bytes.length) {
				sb.append(Integer.toHexString(b & 0xff) + " ");
			} else{
				sb.deleteCharAt(sb.length()-1);
			}
		}
		String string = sb.toString();
		String[] array = string.split(" ");
		String result = "";
		for(int i=0;i<array.length;i++){
			if(array[i].length()<2)  array[i]="0"+array[i];
			if(i%10==0&&i!=0){
				result+="\n";
			}
			result=result+array[i]+" ";
		}
		return result;
	}
}
