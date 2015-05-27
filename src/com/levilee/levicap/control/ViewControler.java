/**
 * 使用这个类来对界面进行统一的管理
 * 控制层所有对界面的操作都要通过这个类
 */
package com.levilee.levicap.control;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;

public class ViewControler {
	private JFrame frame;
	private JComboBox<String> comboBox_filter;
	private JMenuBar menuBar;
	private JMenu menu_file;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem ;
	private JMenuItem exitMenuItem;
	private JMenu mnMenu_other ;
	private JMenuItem mntmNewMenuItem_3 ;
	private JMenuItem mntmNewMenuItem_4;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem_5;
	private JMenu menu_option ;
	private JComboBox<String> comboBox_device;
	private JComboBox<String> comboBox_pacInfo ;
	private JButton button_start;
	private JButton button_stop ;
	private JButton button_save;
	private JButton button_read;
	private JTable jTable_list ;
	private JTextPane textPane_info ;
	private JTextPane textPane_data;
	private JLabel label_device ;
	private JLabel label_filter ;
	private JLabel label_packetInfo;
	private DefaultTableModel defaultTableModel;
	public ViewControler(JFrame frame, JComboBox<String>  comboBox_filter,JMenuBar menuBar,JMenu menu_file,
			JMenuItem openMenuItem,JMenuItem saveMenuItem ,JMenuItem exitMenuItem,JMenu mnMenu_other ,JMenuItem mntmNewMenuItem_3 ,JMenuItem mntmNewMenuItem_4,
			JMenu mnNewMenu,JMenuItem mntmNewMenuItem_5,JMenu menu_option ,JComboBox<String> comboBox_device,JComboBox<String> comboBox_pacInfo ,
			JButton button_start,JButton button_stop ,JButton button_save,JButton button_read,JTable jTable_list,JTextPane textPane_info 
			,JTextPane textPane_data,JLabel label_device ,JLabel label_filter ,JLabel label_packetInfo,DefaultTableModel defaultTableModel){
		this.frame = frame;
		this.comboBox_filter = comboBox_filter;
		this.menuBar = menuBar;
		this.menu_file = menu_file;
		this.openMenuItem = openMenuItem;
		this.saveMenuItem = saveMenuItem;
        this.exitMenuItem = exitMenuItem;
        this.mnMenu_other = mnMenu_other;
        this.mntmNewMenuItem_3 = mntmNewMenuItem_3;
        this.mntmNewMenuItem_4 = mntmNewMenuItem_4;
        this.mntmNewMenuItem_5 = mntmNewMenuItem_5;
        this.mnNewMenu = mnNewMenu;
        this.menu_option = menu_option;
        this.comboBox_device = comboBox_device;
        this.comboBox_pacInfo = comboBox_pacInfo;
        this.button_start = button_start;
        this.button_save = button_save;
        this.button_stop = button_stop;
        this.button_read = button_read;
        this.jTable_list = jTable_list;
        this.textPane_info = textPane_info;
        this.textPane_data = textPane_data;
        this.label_device = label_device;
        this.label_filter = label_filter;
        this.label_packetInfo = label_packetInfo;
        this.defaultTableModel = defaultTableModel;
	}
	
	
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JComboBox<String> getComboBox_filter() {
		return comboBox_filter;
	}
	public void setComboBox_filter(JComboBox<String> comboBox_filter) {
		this.comboBox_filter = comboBox_filter;
	}
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}
	public JMenu getMenu_file() {
		return menu_file;
	}
	public void setMenu_file(JMenu menu_file) {
		this.menu_file = menu_file;
	}
	public JMenuItem getOpenMenuItem() {
		return openMenuItem;
	}
	public void setOpenMenuItem(JMenuItem openMenuItem) {
		this.openMenuItem = openMenuItem;
	}
	public JMenuItem getSaveMenuItem() {
		return saveMenuItem;
	}
	public void setSaveMenuItem(JMenuItem saveMenuItem) {
		this.saveMenuItem = saveMenuItem;
	}
	public JMenuItem getExitMenuItem() {
		return exitMenuItem;
	}
	public void setExitMenuItem(JMenuItem exitMenuItem) {
		this.exitMenuItem = exitMenuItem;
	}
	public JMenu getMnMenu_other() {
		return mnMenu_other;
	}
	public void setMnMenu_other(JMenu mnMenu_other) {
		this.mnMenu_other = mnMenu_other;
	}
	public JMenuItem getMntmNewMenuItem_3() {
		return mntmNewMenuItem_3;
	}
	public void setMntmNewMenuItem_3(JMenuItem mntmNewMenuItem_3) {
		this.mntmNewMenuItem_3 = mntmNewMenuItem_3;
	}
	public JMenuItem getMntmNewMenuItem_4() {
		return mntmNewMenuItem_4;
	}
	public void setMntmNewMenuItem_4(JMenuItem mntmNewMenuItem_4) {
		this.mntmNewMenuItem_4 = mntmNewMenuItem_4;
	}
	public JMenu getMnNewMenu() {
		return mnNewMenu;
	}
	public void setMnNewMenu(JMenu mnNewMenu) {
		this.mnNewMenu = mnNewMenu;
	}
	public JMenuItem getMntmNewMenuItem_5() {
		return mntmNewMenuItem_5;
	}
	public void setMntmNewMenuItem_5(JMenuItem mntmNewMenuItem_5) {
		this.mntmNewMenuItem_5 = mntmNewMenuItem_5;
	}
	public JMenu getMenu_option() {
		return menu_option;
	}
	public void setMenu_option(JMenu menu_option) {
		this.menu_option = menu_option;
	}
	public JComboBox<String> getComboBox_device() {
		return comboBox_device;
	}
	public void setComboBox_device(JComboBox<String> comboBox_device) {
		this.comboBox_device = comboBox_device;
	}
	public JComboBox<String> getComboBox_pacInfo() {
		return comboBox_pacInfo;
	}
	public void setComboBox_pacInfo(JComboBox<String> comboBox_pacInfo) {
		this.comboBox_pacInfo = comboBox_pacInfo;
	}
	public JButton getButton_start() {
		return button_start;
	}
	public void setButton_start(JButton button_start) {
		this.button_start = button_start;
	}
	public JButton getButton_stop() {
		return button_stop;
	}
	public void setButton_stop(JButton button_stop) {
		this.button_stop = button_stop;
	}
	public JButton getButton_save() {
		return button_save;
	}
	public void setButton_save(JButton button_save) {
		this.button_save = button_save;
	}
	public JButton getButton_read() {
		return button_read;
	}
	public void setButton_read(JButton button_read) {
		this.button_read = button_read;
	}
	public JTable getjTable_list() {
		return jTable_list;
	}
	public void setjTable_list(JTable jTable_list) {
		this.jTable_list = jTable_list;
	}
	public JTextPane getTextPane_info() {
		return textPane_info;
	}
	public void setTextPane_info(JTextPane textPane_info) {
		this.textPane_info = textPane_info;
	}
	public JTextPane getTextPane_data() {
		return textPane_data;
	}
	public void setTextPane_data(JTextPane textPane_data) {
		this.textPane_data = textPane_data;
	}
	public JLabel getLabel_device() {
		return label_device;
	}
	public void setLabel_device(JLabel label_device) {
		this.label_device = label_device;
	}
	public JLabel getLabel_filter() {
		return label_filter;
	}
	public void setLabel_filter(JLabel label_filter) {
		this.label_filter = label_filter;
	}
	public JLabel getLabel_packetInfo() {
		return label_packetInfo;
	}
	public void setLabel_packetInfo(JLabel label_packetInfo) {
		this.label_packetInfo = label_packetInfo;
	}
	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}
	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}
	public Logger getLog() {
		return log;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
	private  Logger log = Logger.getLogger("log");
	public ViewControler() {
		
	}


}
