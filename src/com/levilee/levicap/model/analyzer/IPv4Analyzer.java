package com.levilee.levicap.model.analyzer;

import java.net.InetAddress;
import java.util.Hashtable;



import jpcap.packet.IPPacket;
import jpcap.packet.Packet;

public class IPv4Analyzer extends JDPacketAnalyzer
{
	public byte[] data;
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

	public final String[] valueNames={"IP版本",
		"优先级",
		"吞吐量",
		"可靠性",
		"总长度",
		"IP标识符",
		"不可分片",
		"不是最后分组",
		"分支",
		"生存期",
		"Protocol",
		"源IP",
		"目标IP",
		"源地址名称",
		"目标地址名称"};
	private Hashtable values=new Hashtable();
	
	public IPv4Analyzer(){
		layer=NETWORK_LAYER;
	}
	
	public boolean isAnalyzable(Packet p){
		if(p instanceof IPPacket && ((IPPacket)p).version==4) return true;
		else return false;
	}
	
	public String getProtocolName(){
		return "IPv4";
	}
	
	public String[] getValueNames(){
		return valueNames;
	}
	
	public void analyze(Packet packet){
		values.clear();
		if(!isAnalyzable(packet))	return;
		final IPPacket ip=(IPPacket)packet;
		values.put(valueNames[0],new Integer(4));
		values.put(valueNames[1],new Integer(ip.priority));
		values.put(valueNames[2],new Boolean(ip.t_flag));
		values.put(valueNames[3],new Boolean(ip.r_flag));
		values.put(valueNames[4],new Integer(ip.length));
		values.put(valueNames[5],new Integer(ip.ident));
		values.put(valueNames[6],new Boolean(ip.dont_frag));
		values.put(valueNames[7],new Boolean(ip.more_frag));
		values.put(valueNames[8],new Integer(ip.offset));
		values.put(valueNames[9],new Integer(ip.hop_limit));
		values.put(valueNames[10],new Integer(ip.protocol));
		values.put(valueNames[11],ip.src_ip.getHostAddress());
		values.put(valueNames[12],ip.dst_ip.getHostAddress());
		values.put(valueNames[13],ip.src_ip);
		values.put(valueNames[14],ip.dst_ip);
		data = ip.data;
	}
	
	public Object getValue(String valueName){
		return values.get(valueName);
	}
	
	Object getValueAt(int index){
		if(index<0 || index>=valueNames.length) return null;
		
		return getValue(valueNames[index]);
	}
	
	public Object[] getValues(){
		Object[] v=new Object[valueNames.length];
		
		for(int i=0;i<valueNames.length;i++)
			v[i]=getValueAt(i);
		
		return v;
	}
}
