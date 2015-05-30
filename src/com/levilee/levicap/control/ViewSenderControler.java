package com.levilee.levicap.control;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewSenderControler {
	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private JPanel contentPane;
	private JTextField src_ip;
	private JTextField src_mac;
	private JTextField dst_ip;
	private JTextField dst_mac;
	private JButton set_src;
	private JButton set_dst;
	private JButton send;
	private JButton cancel;
	
	public ViewSenderControler(JFrame frame,JPanel contentPane,JTextField src_ip, JTextField src_mac,
			JTextField dst_ip,JTextField dst_mac,JButton set_src,JButton set_dst,JButton send,
			JButton cancel) {
		this.contentPane = contentPane;
		this.src_ip = src_ip;
		this.src_mac = src_mac;
		this.dst_ip = dst_ip;
		this.dst_mac = dst_mac;
		this.set_src = set_src;
		this.set_dst = set_dst;
		this.send = send;
		this.cancel = cancel;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getSrc_ip() {
		return src_ip;
	}

	public void setSrc_ip(JTextField src_ip) {
		this.src_ip = src_ip;
	}

	public JTextField getSrc_mac() {
		return src_mac;
	}

	public void setSrc_mac(JTextField src_mac) {
		this.src_mac = src_mac;
	}

	public JTextField getDst_ip() {
		return dst_ip;
	}

	public void setDst_ip(JTextField dst_ip) {
		this.dst_ip = dst_ip;
	}

	public JTextField getDst_mac() {
		return dst_mac;
	}

	public void setDst_mac(JTextField dst_mac) {
		this.dst_mac = dst_mac;
	}

	public JButton getSet_src() {
		return set_src;
	}

	public void setSet_src(JButton set_src) {
		this.set_src = set_src;
	}

	public JButton getSet_dst() {
		return set_dst;
	}

	public void setSet_dst(JButton set_dst) {
		this.set_dst = set_dst;
	}

	public JButton getSend() {
		return send;
	}

	public void setSend(JButton send) {
		this.send = send;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}
	

}
