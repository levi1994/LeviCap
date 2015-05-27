package com.levilee.levicap.control.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.levilee.levicap.control.ViewControler;
import com.levilee.levicap.model.GlobalValue;
import com.levilee.levicap.model.bean.MyReciver;
import com.levilee.levicap.view.LeviCatcher;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

public class StartAction implements ActionListener{
    private NetworkInterface device;
	private MyReciver myReciver;
	private Executor executor = Executors.newCachedThreadPool();
	private ViewControler viewControler;
	//��״̬���ڱ�ʾץ��������״̬
	

    public StartAction(ViewControler viewControler) {
		this.viewControler  =viewControler;
	}

	/*
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*1.��ʼ��ץ������*/
		GlobalValue.packetList.clear();
		System.out.println(viewControler.getDefaultTableModel().toString());
		viewControler.getDefaultTableModel().setRowCount(0);
		viewControler.getjTable_list().removeAll();
		viewControler.getjTable_list().repaint();
		//1.1.��ȡ�ӿ�
		device = GlobalValue.devices[viewControler.getComboBox_device().getSelectedIndex()];
		//1.2.��ȡ������Ϣ
		String fil = (String)viewControler.getComboBox_filter().getSelectedItem();
		try {
			GlobalValue.log.info("ѡ��Ľӿ��豸Ϊ��"+device.description);
			GlobalValue.log.info("ѡ��Ĺ�������Ϊ��"+fil);
			LeviCatcher.jCaptor = JpcapCaptor.openDevice(device, 2048, true, 100);
			LeviCatcher.jCaptor.setFilter(fil, true);
			myReciver  = new MyReciver(viewControler);
			startCapThread();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}
	Thread captureThread ;
	private void startCapThread(){
		
		captureThread= new Thread(new Runnable() {
			@Override
			public void run() {
				GlobalValue.log.debug("����ץ���߳�");
				// TODO Auto-generated method stub
				GlobalValue.isLiveCapture = true;
				//��λ��ץȡװ���ʱ��ÿ��ץȡһ����
				while (captureThread != null) {
					if (LeviCatcher.jCaptor.processPacket(1, myReciver) == 0 && !GlobalValue.isLiveCapture)
						{GlobalValue.log.debug("��ֹץ��");
						break;
						}
					//���һ��ץ��֮���߳�ת��
					Thread.yield();
				}
				LeviCatcher.jCaptor.breakLoop();
			}
		});
		GlobalValue.log.debug("��ʼץ���߳�");
		executor.execute(captureThread);
		viewControler.getButton_start().setEnabled(false);
		viewControler.getButton_stop().setEnabled(true);
	}
	
}