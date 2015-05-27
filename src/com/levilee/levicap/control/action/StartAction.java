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

import com.levilee.levicap.model.GlobalValue;
import com.levilee.levicap.model.bean.MyReciver;
import com.levilee.levicap.view.LeviCatcher;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

public class StartAction implements ActionListener{
    private JComboBox<String> comboBox_device;
    private JComboBox<String> comboBox_fil;
    private NetworkInterface[] devices;
    private NetworkInterface device;
    private JTable jTable_list;
	private DefaultTableModel defaultTableModel;
	private JButton button_start,button_stop;
	private MyReciver myReciver;
	private  Logger log = Logger.getLogger("log");
	private Executor executor = Executors.newCachedThreadPool();
	//��״̬���ڱ�ʾץ��������״̬
	public  static boolean isLiveCapture;
    
    /**
     * @param comboBox_device  ������Ľӿ������˵���Ϣ
     * @param comboBox_fil  ������Ĺ����������˵���Ϣ
     * @param devices �ӿ��豸��Ϣ
     * @param jTable_list  �б����ָ��
     * @param defaultTableModel �б�ģ��ָ��
     */
    public StartAction(JButton button_start,JButton button_stop,JComboBox<String> comboBox_device,JComboBox<String> comboBox_fil,
                              NetworkInterface[] devices,JTable jTable_list,
                  			DefaultTableModel defaultTableModel) {
		/*��ʼ�����в���*/
    	this.comboBox_device = comboBox_device;
    	this.comboBox_fil = comboBox_fil;
    	this.devices  =devices;
    	this.defaultTableModel = defaultTableModel;
        this.jTable_list = jTable_list;
        this.button_start = button_start;
        this.button_stop = button_stop;
	}
    public StartAction() {
    }
    
	/*
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*1.��ʼ��ץ������*/
		GlobalValue.packetList.clear();
	   defaultTableModel.setRowCount(0);
		jTable_list.removeAll();
		jTable_list.repaint();
		//1.1.��ȡ�ӿ�
		device =  devices[comboBox_device.getSelectedIndex()];
		//1.2.��ȡ������Ϣ
		String fil = (String)comboBox_fil.getSelectedItem();
		try {
			log.info("ѡ��Ľӿ��豸Ϊ��"+device.description);
			log.info("ѡ��Ĺ�������Ϊ��"+fil);
			LeviCatcher.jCaptor = JpcapCaptor.openDevice(device, 2048, true, 100);
			LeviCatcher.jCaptor.setFilter(fil, true);
			myReciver  = new MyReciver(jTable_list,defaultTableModel);
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
				log.debug("����ץ���߳�");
				// TODO Auto-generated method stub
				isLiveCapture = true;
				//��λ��ץȡװ���ʱ��ÿ��ץȡһ����
				while (captureThread != null) {
					if (LeviCatcher.jCaptor.processPacket(1, myReciver) == 0 && !isLiveCapture)
						{log.debug("��ֹץ��");
						break;
						}
					//���һ��ץ��֮���߳�ת��
					Thread.yield();
				}
				LeviCatcher.jCaptor.breakLoop();
			}
		});
		log.debug("��ʼץ���߳�");
		executor.execute(captureThread);
		button_start.setEnabled(false);
		button_stop.setEnabled(true);
		
	}
	
}