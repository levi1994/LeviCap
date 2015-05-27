package com.levilee.levicap.control.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import com.levilee.levicap.model.GlobalValue;

public class StopAction implements ActionListener{
	private Logger log = Logger.getLogger("log");
	private JButton button_start;
	private JButton button_stop;
	public StopAction() {
       
	}
	public StopAction(JButton button_start,JButton button_stop){
		this.button_start = button_start;
		this.button_stop = button_stop;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GlobalValue.isLiveCapture =  false;
		log.debug("×¥°üÍ£Ö¹");
		button_start.setEnabled(true);
		button_stop.setEnabled(false);
	}

}
