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
	//该状态用于表示抓包的启动状态
	public  static boolean isLiveCapture;
    
    /**
     * @param comboBox_device  主界面的接口下拉菜单信息
     * @param comboBox_fil  主界面的过滤器下拉菜单信息
     * @param devices 接口设备信息
     * @param jTable_list  列表操作指针
     * @param defaultTableModel 列表模型指针
     */
    public StartAction(JButton button_start,JButton button_stop,JComboBox<String> comboBox_device,JComboBox<String> comboBox_fil,
                              NetworkInterface[] devices,JTable jTable_list,
                  			DefaultTableModel defaultTableModel) {
		/*初始化所有参数*/
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
		/*1.初始化抓包环境*/
		GlobalValue.packetList.clear();
	   defaultTableModel.setRowCount(0);
		jTable_list.removeAll();
		jTable_list.repaint();
		//1.1.获取接口
		device =  devices[comboBox_device.getSelectedIndex()];
		//1.2.获取过滤信息
		String fil = (String)comboBox_fil.getSelectedItem();
		try {
			log.info("选择的接口设备为："+device.description);
			log.info("选择的过滤条件为："+fil);
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
				log.debug("进入抓包线程");
				// TODO Auto-generated method stub
				isLiveCapture = true;
				//当位于抓取装填的时候，每次抓取一个包
				while (captureThread != null) {
					if (LeviCatcher.jCaptor.processPacket(1, myReciver) == 0 && !isLiveCapture)
						{log.debug("终止抓包");
						break;
						}
					//完成一次抓包之后，线程转换
					Thread.yield();
				}
				LeviCatcher.jCaptor.breakLoop();
			}
		});
		log.debug("开始抓包线程");
		executor.execute(captureThread);
		button_start.setEnabled(false);
		button_stop.setEnabled(true);
		
	}
	
}