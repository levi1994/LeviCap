package com.levilee.levicap.control.action2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import jpcap.NetworkInterface;

import com.levilee.levicap.control.ViewSenderControler;
import com.levilee.levicap.model.GlobalValue;
import com.levilee.levicap.model.util.ByteUtil;

public class FillDstInfo implements ActionListener{
	private ViewSenderControler controler;
	private NetworkInterface device;
	public FillDstInfo(ViewSenderControler controler,NetworkInterface device) {
		this.controler = controler;
		this.device = device;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String mac = ByteUtil.macToString(device.mac_address);
		String ipinfo = null;
		String[] ip1 = null;
		try {
		ipinfo = InetAddress.getLocalHost().toString();
		 ip1 = ipinfo.split("/");
		} catch (UnknownHostException ex) {
       GlobalValue.log.error("无法获取ip");
		}
		controler.getDst_ip().setText(ip1[1]);
		controler.getDst_mac().setText(mac);
	}
}
