package com.levilee.levicap.control.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextPane;

import org.apache.log4j.Logger;

import com.levilee.levicap.model.analyzer.Analyzer;

public class InfoAction implements ActionListener {
	private JTextPane textPane_info;
	private JComboBox<String> comboBox_pacInfo;
	private Analyzer analyzer ;
	private Logger log = Logger.getLogger("log");
	public InfoAction(JTextPane textPane_info,JComboBox<String> comboBox_pacInfo,Analyzer analyzer) {
		this.textPane_info = textPane_info;
		this.comboBox_pacInfo = comboBox_pacInfo;
		this.analyzer = analyzer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int t = comboBox_pacInfo.getSelectedIndex();
		if(t==-1) return;
		//获取包信息并显示
		log.info("Combobox选中值为："+t);
		textPane_info.setText(analyzer.infolist.get(t));
	}

}
