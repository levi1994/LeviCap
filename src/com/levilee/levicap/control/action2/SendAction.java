package com.levilee.levicap.control.action2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import jpcap.NetworkInterface;

import com.levilee.levicap.control.ViewSenderControler;
import com.levilee.levicap.model.GlobalValue;
import com.levilee.levicap.model.sender.ARPSender;

public class SendAction implements ActionListener {
	private ViewSenderControler senderControler;
	private NetworkInterface device;
	public SendAction(ViewSenderControler senderControler,NetworkInterface device) {
		this.senderControler = senderControler;
		this.device = device;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//从界面获取数据
		String src_ip = senderControler.getSrc_ip().getText();
		String src_mac = senderControler.getSrc_mac().getText();
		String dst_ip = senderControler.getDst_ip().getText();
		String dst_mac  = senderControler.getDst_mac().getText();
		//发送数据
		System.out.println(device.description);
		if(src_ip.equals("")|| src_mac.equals("") || dst_ip.equals("") || dst_mac.equals("")){
			JOptionPane.showMessageDialog(
					senderControler.getFrame(),
				"请填写发送信息 " );
			return;
		}
		
		try {
			new ARPSender().sendARPReply(device, src_ip, dst_ip, src_mac, dst_mac);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			GlobalValue.log.error("ARP发送失败");
		}
		System.out.println("发送成功");

	}

}
