package com.levilee.levicap.model.sender;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import jpcap.JpcapSender;
/**
 * ������������ARP���ݰ��Ĺ�����
 * 
 * devices ��Դ........��Frame---->���췽�����뷢��Frame----->���췽������ARPSender;
 */
import jpcap.NetworkInterface;
import jpcap.packet.ARPPacket;
import jpcap.packet.EthernetPacket;

public class ARPSender {
	public ARPSender(){
		
	}
	
	//α��ARP���ݰ�����
	public  boolean sendARP(NetworkInterface device,String send_addr,String target_addr) throws IOException{
		JpcapSender sender = JpcapSender.openDevice(device);
		ARPPacket arp = new ARPPacket();
		arp.hardtype=ARPPacket.HARDTYPE_ETHER; //Ӳ������
		arp.prototype=ARPPacket.PROTOTYPE_IP; //Э������
		arp.operation=ARPPacket.ARP_REQUEST; //ָ����ARP�����
		arp.hlen=6; //�����ַ����
		arp.plen=4; //Э���ַ����
		arp.sender_hardaddr=device.mac_address; //ARP���ķ��Ͷ���̫����ַ,�����Ｔ����������ַ
		arp.sender_protoaddr=Inet4Address.getByName(send_addr).getAddress(); //���Ͷ�IP��ַ, �����Ｔ����IP��ַ
		byte[] broadcast=new byte[]{(byte)255,(byte)255,(byte)255,(byte)255,(byte)255,(byte)255}; //�㲥��ַ
		arp.target_hardaddr=broadcast; //����Ŀ�Ķ˵���̫����ַΪ�㲥��ַ
		arp.target_protoaddr=InetAddress.getByName(target_addr).getAddress(); //Ŀ�Ķ�IP��ַ
		
		//������̫֡�ײ�
		EthernetPacket ether=new EthernetPacket();
		ether.frametype=EthernetPacket.ETHERTYPE_ARP; //֡����
		ether.src_mac=device.mac_address; //ԴMAC��ַ
		ether.dst_mac=broadcast; //��̫��Ŀ�ĵ�ַ���㲥��ַ
		arp.datalink=ether; //��arp���ĵ�������·���֡����Ϊ�ոչ������̫֡����
		sender.sendPacket(arp);
		return true;
	}
	
	//α��ARP���ݰ�����
	//��Ҫ�������繥��
	//ģ��arp���ذ�
	public  boolean sendARPReply(NetworkInterface device,String src_ip,String dst_ip,String src_mac,String dst_mac) throws IOException{
		JpcapSender sender = JpcapSender.openDevice(device);
		ARPPacket arp = new ARPPacket();
		//��ʼ������Ϣ
		arp.hardtype=ARPPacket.HARDTYPE_ETHER; //Ӳ������
		arp.prototype=ARPPacket.PROTOTYPE_IP; //Э������
		arp.operation=ARPPacket.ARP_REPLY; //ָ����ARP�����
		arp.hlen=6; //�����ַ����
		arp.plen=4; //Э���ַ����
		//ARP������Ӳ����ַ������α����һ����ַ�������ARP��ƭ�Ļ���ʹ�ñ�����ַ
		arp.sender_hardaddr=stomac(src_mac); 
		//���Ͷ�IP��ַ, ����ʹ��α��ip��ַ��arp����һ�������ص�ַ
		arp.sender_protoaddr=Inet4Address.getByName(src_ip).getAddress(); 
		//����Ŀ��mac��ַ�����Ҫ���𹥻��Ļ�����ַΪ����Ŀ���mac��ip��ַ
		arp.target_hardaddr=stomac(dst_mac); 
		arp.target_protoaddr=InetAddress.getByName(dst_ip).getAddress(); //Ŀ�Ķ�IP��ַ
		
		//������̫֡�ײ�
		EthernetPacket ether=new EthernetPacket();
		ether.frametype=EthernetPacket.ETHERTYPE_ARP; //֡����
		ether.src_mac=device.mac_address; //ԴMAC��ַ
//		byte[] broadcast=new byte[]{(byte)255,(byte)255,(byte)255,(byte)255,(byte)255,(byte)255}; //�㲥��ַ
		ether.dst_mac=stomac(dst_mac); //��̫��Ŀ�ĵ�ַ���㲥��ַ
		arp.datalink=ether; //��arp���ĵ�������·���֡����Ϊ�ոչ������̫֡����
		sender.sendPacket(arp);
		return true;
	}
	
	//���ַ���ת��Ϊmac��ַ,��ʽΪ16����:AA-AA-AA-AA-AA
	public static byte[] stomac(String str) {
		byte[] mac = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
		String[] temp = str.split("-");
		for (int x = 0; x < temp.length; x++) {
		mac[x] = (byte) ((Integer.parseInt(temp[x], 16)) & 0xff);
		}
		return mac;
		}
//	public static void main(String[] args) {
//		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
//		NetworkInterface devices1  = devices[1];
////		new ARPSender().sendARPReply(devices1, "localhost", "localhost", devices1.mac_address, devices1.mac_address);
//	}
}
