package com.levilee.levicap.model.bean;

import java.io.Serializable;
import java.util.Date;

import jpcap.packet.Packet;

/**
 * @author levi
 *�����Զ�������ݰ�������
 */
public class MyPacket  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6414435096595284271L;
	private int index;
	private Date date ;

	private Packet packet;
	public int getIndex() {
		return index;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Packet getPacket() {
		return packet;
	}
	public void setPacket(Packet packet) {
		this.packet = packet;
	}

}
