/**
 * ��������ڼ����б�ĸ����¼�
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
		// ���ý�����ȡѡ����,���ز���ʾ����������Ϣ
		int t =viewControler.getjTable_list().getSelectedRow();
		String data = new Analyzer().getData(GlobalValue.packetList.get(t).getPacket());
		GlobalValue.log.debug("data���ݼ��سɹ�");
		viewControler.getTextPane_data().setText(data);
		
		// ��ʾ�����İ���Ϣ
		String base_info = "";
		base_info += "�����ȣ�" + GlobalValue.packetList.get(t).getPacket().len;
		viewControler.getTextPane_info().setText(base_info);

		// 1.�����Ϣ
		GlobalValue.log.debug("�����������");
		viewControler.getComboBox_pacInfo().removeAllItems();
		analyzer.infolist.clear();
		analyzer.namelist.clear();
		//2.����Analyzer�ķ�����ʼ����Ϣ
		analyzer.getInfo(GlobalValue.packetList.get(t).getPacket());
		//3.Ϊ�����˵�����¼�
		viewControler.getComboBox_pacInfo().addActionListener(new InfoAction(analyzer,viewControler));
		// 4.���������б���Ŀ
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
