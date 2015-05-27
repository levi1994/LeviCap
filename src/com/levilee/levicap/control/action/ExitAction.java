package com.levilee.levicap.control.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.levilee.levicap.model.GlobalValue;
public class ExitAction implements ActionListener{
    public ExitAction() {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GlobalValue.log.debug("ÍË³ö³ÌÐò");
		System.exit(0);
	}

}
