/**
 * ��������ڴ洢ȫ�ֱ�����Ϣ
 */
package com.levilee.levicap.model;
import java.util.ArrayList;
import java.util.List;

import jpcap.NetworkInterface;

import org.apache.log4j.Logger;

import com.levilee.levicap.model.bean.MyPacket;

public class GlobalValue {
	public static List<MyPacket> packetList = new ArrayList<MyPacket>();
	public static  Logger log = Logger.getLogger("log");
	public static  NetworkInterface[] devices;
	//��ֵ������¼��ǰ��ץ��״̬
	public  static boolean isLiveCapture;

}
