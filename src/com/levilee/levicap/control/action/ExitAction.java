package com.levilee.levicap.control.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.Logger;
public class ExitAction implements ActionListener{
	private Logger log = Logger.getLogger("log");
    public ExitAction() {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		log.debug("ÍË³ö³ÌÐò");
		System.exit(0);
	}

}
