package com.levilee.levicap.control.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;

import org.apache.log4j.Logger;

import com.levilee.levicap.control.ViewControler;
import com.levilee.levicap.model.GlobalValue;
import com.levilee.levicap.model.bean.MyPacket;
import com.levilee.levicap.model.util.ByteUtil;


public class OpenAction implements ActionListener {
	private JFileChooser jFileChooser;
	private ViewControler viewControler;
	public OpenAction(ViewControler viewControler) {
      this.viewControler = viewControler;
	}
	/*
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * ʹ�ø÷������ļ��ж���list����
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		jFileChooser = new JFileChooser();
		GlobalValue.log.info("���ڼ����ļ�ѡ�񴰿�");
		int ret = jFileChooser.showOpenDialog(jFileChooser);
		GlobalValue.log.info("ret="+ret);
		File file = null;
		if (ret == JFileChooser.APPROVE_OPTION) {
			file = jFileChooser.getSelectedFile();
		}
		//����������������ⱨ��
		if(file==null) {
			GlobalValue.log.debug("��δѡ���ļ�");
			return;
		}
		GlobalValue.log.info("filepath:"+file.getPath());
		try {
			FileInputStream in = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(in);
			Object readObject=null;
			try {
				readObject = ois.readObject();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			final List<MyPacket> list = (List<MyPacket>) readObject;
			GlobalValue.packetList.clear();
			GlobalValue.packetList = list;
			GlobalValue.log.debug("�ļ���ȡ�ɹ�");
			in.close();
			ois.close();
		   /*ˢ�´���*/
			GlobalValue.log.debug("����ˢ�´���");
			//�����������
			viewControler.getDefaultTableModel().setRowCount(0);
			viewControler.getjTable_list().removeAll();
			viewControler.getjTable_list().repaint();
			Thread flush = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(MyPacket mypacket : list){
						Packet packet = mypacket.getPacket();
						/*��������ʾ���б�*/
						Vector<String> vectorRow = new Vector<String>();
						/*�����߼��������⣬��Ҫ��д*/
						/*�Ե��������з������������ͻ�ȡ��Ϣ*/
						vectorRow.addElement(mypacket.getIndex() + "");
						if (packet instanceof IPPacket) {
							IPPacket ipPacket = (IPPacket) packet;
							String src_ip;
							String dst_ip;
							try{
							src_ip = ipPacket.src_ip.toString().substring(1);
							}catch(NullPointerException e){
								GlobalValue.log.error("src_ip�޷���ȡ");
								src_ip ="not found";
							}
							try{
								dst_ip = ipPacket.dst_ip.toString().substring(1);
								}catch(NullPointerException e){
									GlobalValue.log.error("src_ip�޷���ȡ");
									dst_ip ="not fount";
								}
							EthernetPacket arpPacket = (EthernetPacket) packet.datalink;
							String src_mac = ByteUtil.macToString(arpPacket.src_mac);
							String dst_mac = ByteUtil.macToString(arpPacket.dst_mac);
							vectorRow.addElement(src_mac);
							vectorRow.addElement(dst_mac);
							vectorRow.addElement(src_ip);
							vectorRow.addElement(dst_ip);
						}
                        viewControler.getDefaultTableModel().addRow(vectorRow);
                        viewControler.getjTable_list().setModel( viewControler.getDefaultTableModel());
                        viewControler.getjTable_list().revalidate();
						//�����߼�ִ����ϣ���Ȩ
						Thread.yield();
					}
				}
			});
			flush.start();
		} catch (java.io.IOException ex) {
			ex.printStackTrace();
			GlobalValue.log.error("�ļ���ȡʧ��");
			JOptionPane.showMessageDialog(
				viewControler.getFrame(),
				"Can't save file: " + file.getPath());
		}
	}

}
