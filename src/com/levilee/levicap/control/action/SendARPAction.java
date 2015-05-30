package com.levilee.levicap.control.action;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.levilee.levicap.control.ViewControler;
import com.levilee.levicap.model.GlobalValue;
import com.levilee.levicap.view.SenderARP;

public class SendARPAction implements ActionListener{
    private ViewControler viewControler;
    public SendARPAction(ViewControler viewControler) {
    	this.viewControler = viewControler;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int t = viewControler.getComboBox_device().getSelectedIndex();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlobalValue.log.debug(GlobalValue.devices[t].description);
					SenderARP frame = new SenderARP(
						GlobalValue.devices[t]);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
