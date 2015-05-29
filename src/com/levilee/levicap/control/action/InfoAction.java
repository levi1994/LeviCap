package com.levilee.levicap.control.action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.levilee.levicap.control.ViewControler;
import com.levilee.levicap.model.GlobalValue;
import com.levilee.levicap.model.analyzer.Analyzer;
public class InfoAction implements ActionListener {
	private Analyzer analyzer ;
	private ViewControler viewControler;
	public InfoAction(Analyzer analyzer,ViewControler viewControler) {
		this.analyzer = analyzer;
		this.viewControler = viewControler;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int t = viewControler.getComboBox_pacInfo().getSelectedIndex();
		if(t==-1) return;
		//��ȡ����Ϣ����ʾ
		GlobalValue.log.info("Comboboxѡ��ֵΪ��"+t);
		viewControler.getTextPane_info().setText(analyzer.infolist.get(t));
	}

}
