/**
 * 这个类用于监听列表的各种事件
 */
package com.levilee.levicap.control.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.levilee.levicap.control.ViewControler;
import com.levilee.levicap.model.GlobalValue;
import com.levilee.levicap.model.analyzer.Analyzer;

public class TableMouseEvent implements MouseListener {
	private ViewControler viewControler;
	private Analyzer analyzer= new Analyzer();
    public TableMouseEvent(ViewControler viewControler) {
		this.viewControler = viewControler;
	}
    
	@Override
	public void mouseClicked(MouseEvent e) {
		// 调用界面层获取选中行,加载并显示包内数据信息
		int t =viewControler.getjTable_list().getSelectedRow();
		String data = new Analyzer().getData(GlobalValue.packetList.get(t).getPacket());
		GlobalValue.log.debug("data数据加载成功");
		viewControler.getTextPane_data().setText(data);
		
		// 显示基本的包信息
		String base_info = "";
		base_info += "包长度：" + GlobalValue.packetList.get(t).getPacket().len;
		viewControler.getTextPane_info().setText(base_info);

		// 1.清空信息
		GlobalValue.log.debug("清空所有数据");
		viewControler.getComboBox_pacInfo().removeAllItems();
		analyzer.infolist.clear();
		analyzer.namelist.clear();
		//2.调用Analyzer的方法初始化信息
		analyzer.getInfo(GlobalValue.packetList.get(t).getPacket());
		//3.为下拉菜单添加事件
		viewControler.getComboBox_pacInfo().addActionListener(new InfoAction(analyzer,viewControler));
		// 4.设置下拉列表项目
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
