package com.levilee.levicap.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import com.levilee.levicap.control.ViewSenderControler;
import com.levilee.levicap.control.action2.FillDstInfo;
import com.levilee.levicap.control.action2.FillSrcInfo;
import com.levilee.levicap.control.action2.SendAction;
import jpcap.NetworkInterface;

public class SenderARP extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField src_ip;
	private JTextField src_mac;
	private JTextField dst_ip;
	private JTextField dst_mac;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel label;
	private JButton set_src;
	private JButton set_dst;
	private JButton send;
	private JButton cancel;
	private NetworkInterface device;
	private ViewSenderControler senderControler ;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SenderARP frame = new SenderARP(
//							JpcapCaptor.getDeviceList()[1]);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public SenderARP(NetworkInterface device) {
		setResizable(false);
		this.device = device;
		initialize();
		senderControler = new ViewSenderControler(this,contentPane, src_ip, src_mac,
				dst_ip, dst_mac, set_src, set_dst, send, cancel);
		initializeButton();
	}
	private void initializeButton(){
		set_src.addActionListener(new FillSrcInfo(senderControler, device));
		set_dst.addActionListener(new FillDstInfo(senderControler, device));
		send.addActionListener(new SendAction(senderControler, device));
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	private void initialize() {
		setTitle("发送ARP");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		src_ip = new JTextField();
		src_ip.setBounds(67, 30, 137, 21);
		contentPane.add(src_ip);
		src_ip.setColumns(10);

		src_mac = new JTextField();
		src_mac.setBounds(263, 30, 134, 21);
		contentPane.add(src_mac);
		src_mac.setColumns(10);

		dst_ip = new JTextField();
		dst_ip.setBounds(67, 86, 137, 21);
		contentPane.add(dst_ip);
		dst_ip.setColumns(10);

		dst_mac = new JTextField();
		dst_mac.setBounds(263, 86, 134, 21);
		contentPane.add(dst_mac);
		dst_mac.setColumns(10);

		lblNewLabel = new JLabel("IP");
		lblNewLabel.setBounds(24, 33, 24, 15);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("IP");
		lblNewLabel_1.setBounds(24, 89, 54, 15);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("MAC");
		lblNewLabel_2.setBounds(227, 33, 30, 15);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("MAC");
		lblNewLabel_3.setBounds(227, 89, 54, 15);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("发送方");
		lblNewLabel_4.setBounds(24, 10, 54, 15);
		contentPane.add(lblNewLabel_4);

		label = new JLabel("接收方");
		label.setBounds(24, 61, 54, 15);
		contentPane.add(label);

		set_src = new JButton("填入本机信息");
		set_src.setBounds(88, 6, 116, 23);
		contentPane.add(set_src);

		set_dst = new JButton("填入本机信息");
		set_dst.setBounds(88, 61, 116, 23);
		contentPane.add(set_dst);

		send = new JButton("发送");
		send.setBounds(102, 128, 66, 23);
		contentPane.add(send);

		cancel = new JButton("取消");
		cancel.setBounds(215, 128, 66, 23);
		contentPane.add(cancel);
	}
}
