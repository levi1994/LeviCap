/**
 * ��������ڼ������б�ĸ����¼�
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
		// ���ý�����ȡѡ����
		int t =viewControler.getjTable_list().getSelectedRow();
		String data = new Analyzer().getData(GlobalValue.packetList.get(t).getPacket());
		GlobalValue.log.debug("data���ݼ��سɹ�");
		// ����3��textPane_data
		viewControler.getTextPane_data().setText(data);
		// ��ʾ�����İ���Ϣ
		String base_info = "";
		base_info += "�����ȣ�" + GlobalValue.packetList.get(t).getPacket().len;
		// ����4��textPane_info
		viewControler.getTextPane_info().setText(base_info);
		// ��ʾѡ���б�

		// 1.�����Ϣ,����4��comboBox_pacInfo
		GlobalValue.log.debug("�����������");
		viewControler.getComboBox_pacInfo().removeAllItems();
		// 2.����������,����5��analyzer
		analyzer = new Analyzer();
		analyzer.getInfo(GlobalValue.packetList.get(t).getPacket());
		// ����¼�
		viewControler.getComboBox_pacInfo().addActionListener(new InfoAction(viewControler.getTextPane_info(),
				viewControler.getComboBox_pacInfo(), analyzer));
		// 3.���������б���Ŀ
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
