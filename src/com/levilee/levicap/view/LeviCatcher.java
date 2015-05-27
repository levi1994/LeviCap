package com.levilee.levicap.view;
import java.awt.EventQueue;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import jpcap.JpcapCaptor;
import com.levilee.levicap.control.ViewControler;
import com.levilee.levicap.control.action.ExitAction;
import com.levilee.levicap.control.action.OpenAction;
import com.levilee.levicap.control.action.SaveAction;
import com.levilee.levicap.control.action.StartAction;
import com.levilee.levicap.control.action.StopAction;
import com.levilee.levicap.control.action.TableMouseEvent;
import com.levilee.levicap.model.GlobalValue;
/**
 * @author levi
 *
 */

public class LeviCatcher {
	private JFrame frame;
	private JComboBox<String> comboBox_filter;
	private JMenuBar menuBar;
	private JMenu menu_file;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;
	private JMenu mnMenu_other;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem_5;
	private JMenu menu_option;
	private JComboBox<String> comboBox_device;
	private JComboBox<String> comboBox_pacInfo;
	private JButton button_start;
	private JButton button_stop;
	private JButton button_save;
	private JButton button_read;
	private JTable jTable_list;
	private JTextPane textPane_info;
	private JTextPane textPane_data;
	private JLabel label_device;
	private JLabel label_filter;
	private JLabel label_packetInfo;
	private DefaultTableModel defaultTableModel;

	// ������ͼ������
	private ViewControler viewControler;

	
	private String[] devicesname;
	public static JpcapCaptor jCaptor;
	/**
	 * ����������
	 */
	public static void main(String[] args) {
		// �趨������
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		// ����������
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeviCatcher window = new LeviCatcher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public LeviCatcher() {
		// 1.���ؽӿ�����
		GlobalValue.log.info("���ڼ��ؽӿ�����");
		initializeInterface();
		// ��ʼ��������
		GlobalValue.log.info("���ڼ��ؽӿ�������");
		initialize();
		// 3.��ʼ�����������
		GlobalValue.log.info("���ڳ�ʼ�����������");
		viewControler = new ViewControler(frame, comboBox_filter, menuBar,
				menu_file, openMenuItem, saveMenuItem, exitMenuItem,
				mnMenu_other, mntmNewMenuItem_3, mntmNewMenuItem_4, mnNewMenu,
				mntmNewMenuItem_5, menu_option, comboBox_device,
				comboBox_pacInfo, button_start, button_stop, button_save,
				button_read, jTable_list, textPane_info, textPane_data,
				label_device, label_filter, label_packetInfo, defaultTableModel);
		//4.��ʼ����ť�¼�
		GlobalValue.log.info("����Ϊ��ʼ����ť�¼�");
		initializeButton();
	}

	/* Ϊ��ť��Ӽ����¼� */
	private void initializeButton() {
		button_start.addActionListener(new StartAction(viewControler));
		button_stop
				.addActionListener(new StopAction(button_start, button_stop));
		button_save.addActionListener(new SaveAction(viewControler));
		button_read.addActionListener(new OpenAction(viewControler));
		openMenuItem.addActionListener(new OpenAction(viewControler));
		saveMenuItem.addActionListener(new SaveAction(viewControler));
		exitMenuItem.addActionListener(new ExitAction());
		jTable_list.addMouseListener(new TableMouseEvent(viewControler));
	}

	/* ��ʼ������ */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 546, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menu_file = new JMenu("�ļ�");
		menuBar.add(menu_file);

		openMenuItem = new JMenuItem("��");
		menu_file.add(openMenuItem);

		saveMenuItem = new JMenuItem("����");
		menu_file.add(saveMenuItem);

		exitMenuItem = new JMenuItem("�˳�");
		menu_file.add(exitMenuItem);
		mnMenu_other = new JMenu("����");
		menuBar.add(mnMenu_other);

		mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnMenu_other.add(mntmNewMenuItem_3);

		mntmNewMenuItem_4 = new JMenuItem("New menu item");
		mnMenu_other.add(mntmNewMenuItem_4);

		mnNewMenu = new JMenu("menu ");
		mnMenu_other.add(mnNewMenu);

		mntmNewMenuItem_5 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_5);

		menu_option = new JMenu("����");
		menuBar.add(menu_option);
		frame.getContentPane().setLayout(null);

		comboBox_device = new JComboBox<String>(devicesname);
		comboBox_device.setBounds(46, 0, 89, 21);
		frame.getContentPane().add(comboBox_device);

		button_start = new JButton("\u5F00\u59CB");
		button_start.setBounds(281, -1, 56, 23);

		frame.getContentPane().add(button_start);

		button_stop = new JButton("\u505C\u6B62");
		button_stop.setEnabled(false);
		button_stop.setBounds(342, -1, 56, 23);

		frame.getContentPane().add(button_stop);

		button_save = new JButton("\u4FDD\u5B58");
		button_save.setBounds(397, -1, 56, 23);
		frame.getContentPane().add(button_save);

		button_read = new JButton("\u6253\u5F00");
		button_read.setBounds(455, -1, 56, 23);
		frame.getContentPane().add(button_read);

		jTable_list = new JTable(new Object[][] {}, new Object[] { "NO.",
				"ԴMAC", "Ŀ��MAC", "ԴIP", "Ŀ��IP" });
		defaultTableModel = new DefaultTableModel(new Object[][] {},
				new Object[] { "NO", "ԴMAC", "Ŀ��MAC", "ԴIP", "Ŀ��IP" });
		
		JScrollPane jsp_list = new JScrollPane(jTable_list);
		jsp_list.setEnabled(false);
		jsp_list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		jsp_list.setBackground(SystemColor.info);
		jsp_list.setBounds(10, 23, 520, 143);
		frame.getContentPane().add(jsp_list);

		textPane_info = new JTextPane();
		textPane_info.setEditable(false);
		JScrollPane jsp_info = new JScrollPane(textPane_info);
		textPane_info.setBackground(SystemColor.info);
		textPane_info.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		jsp_info.setBounds(10, 197, 191, 150);
		frame.getContentPane().add(jsp_info);

		textPane_data = new JTextPane();
		StyledDocument doc = textPane_data.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontSize(center, 18);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		textPane_data.setEditable(false);
		textPane_data.setBackground(SystemColor.info);
		JScrollPane jsp_data = new JScrollPane(textPane_data);
		jsp_data.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		jsp_data.setBounds(207, 176, 323, 171);
		frame.getContentPane().add(jsp_data);

		String[] filter = { "", "ip", "tcp", "arp", "udp", "icmp" };
		comboBox_filter = new JComboBox<String>(filter);
		comboBox_filter.setBounds(180, 0, 89, 21);
		frame.getContentPane().add(comboBox_filter);

		label_device = new JLabel("\u7F51\u5361\uFF1A");
		label_device.setBounds(10, 3, 40, 15);
		frame.getContentPane().add(label_device);

		label_filter = new JLabel("\u8FC7\u6EE4\u5668\uFF1A");
		label_filter.setBounds(136, 3, 54, 15);
		frame.getContentPane().add(label_filter);

		comboBox_pacInfo = new JComboBox<String>();
		comboBox_pacInfo.setBounds(56, 176, 145, 21);
		frame.getContentPane().add(comboBox_pacInfo);

		label_packetInfo = new JLabel("\u5305\u4FE1\u606F");
		label_packetInfo.setBounds(10, 176, 40, 15);
		frame.getContentPane().add(label_packetInfo);
	}

	// ��ʼ���ӿ�
	private void initializeInterface() {
		GlobalValue.devices = JpcapCaptor.getDeviceList();
		devicesname = new String[GlobalValue.devices.length];
		for (int i = 0; i < GlobalValue.devices.length; i++) {
			devicesname[i] = GlobalValue.devices[i].description;
		}
	}
}
