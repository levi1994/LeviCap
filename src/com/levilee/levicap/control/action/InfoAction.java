package com.levilee.levicap.control.action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.levilee.levicap.control.ViewControler;
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
		//获取包信息并显示
		viewControler.getTextPane_info().setText(analyzer.infolist.get(t));
	}

}
