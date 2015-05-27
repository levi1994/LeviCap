/**
 * 这个类用于监听包列表的各种事件
 */
package com.levilee.levicap.control.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.levilee.levicap.control.ViewControler;
import com.levilee.levicap.model.GlobalValue;
import com.levilee.levicap.model.analyzer.Analyzer;

public class TableMouseEvent implements MouseListener {
	private ViewControler viewControler;
	private Analyzer analyzer;
    public TableMouseEvent(ViewControler viewControler) {
		this.viewControler = viewControler;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// 调用界面层获取选中行
		int t =viewControler.getjTable_list().getSelectedRow();
		String data = new Analyzer().getData(GlobalValue.packetList.get(t).getPacket());
		GlobalValue.log.debug("data数据加载成功");
		// 参数3：textPane_data
		viewControler.getTextPane_data().setText(data);
		// 显示基本的包信息
		String base_info = "";
		base_info += "包长度：" + GlobalValue.packetList.get(t).getPacket().len;
		// 参数4：textPane_info
		viewControler.getTextPane_info().setText(base_info);
		// 显示选择列表

		// 1.清空信息,参数4：comboBox_pacInfo
		GlobalValue.log.debug("清空所有数据");
		viewControler.getComboBox_pacInfo().removeAllItems();
		// 2.解析包数据,参数5：analyzer
		analyzer = new Analyzer();
		analyzer.getInfo(GlobalValue.packetList.get(t).getPacket());
		// 添加事件
		viewControler.getComboBox_pacInfo().addActionListener(new InfoAction(viewControler.getTextPane_info(),
				viewControler.getComboBox_pacInfo(), analyzer));
		// 3.设置下拉列表项目
		for (int i = 0; i < analyzer.namelist.size(); i++) {
			viewControler.getComboBox_pacInfo().addItem(analyzer.namelist.get(i));
		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
