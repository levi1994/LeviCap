package com.levilee.levicap.model.sender;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import jpcap.JpcapSender;
/**
 * 这是用来发送ARP数据包的功能类
 * 
 * devices 来源........主Frame---->构造方法传入发包Frame----->构造方法传入ARPSender;
 */
import jpcap.NetworkInterface;
import jpcap.packet.ARPPacket;
import jpcap.packet.EthernetPacket;

public class ARPSender {
	public ARPSender(){
		
	}
	
	//伪造ARP数据包发送
	public  boolean sendARP(NetworkInterface device,String send_addr,String target_addr) throws IOException{
		JpcapSender sender = JpcapSender.openDevice(device);
		ARPPacket arp = new ARPPacket();
		arp.hardtype=ARPPacket.HARDTYPE_ETHER; //硬件类型
		arp.prototype=ARPPacket.PROTOTYPE_IP; //协议类型
		arp.operation=ARPPacket.ARP_REQUEST; //指明是ARP请求包
		arp.hlen=6; //物理地址长度
		arp.plen=4; //协议地址长度
		arp.sender_hardaddr=device.mac_address; //ARP包的发送端以太网地址,在这里即本地主机地址
		arp.sender_protoaddr=Inet4Address.getByName(send_addr).getAddress(); //发送端IP地址, 在这里即本地IP地址
		byte[] broadcast=new byte[]{(byte)255,(byte)255,(byte)255,(byte)255,(byte)255,(byte)255}; //广播地址
		arp.target_hardaddr=broadcast; //设置目的端的以太网地址为广播地址
		arp.target_protoaddr=InetAddress.getByName(target_addr).getAddress(); //目的端IP地址
		
		//构造以太帧首部
		EthernetPacket ether=new EthernetPacket();
		ether.frametype=EthernetPacket.ETHERTYPE_ARP; //帧类型
		ether.src_mac=device.mac_address; //源MAC地址
		ether.dst_mac=broadcast; //以太网目的地址，广播地址
		arp.datalink=ether; //将arp报文的数据链路层的帧设置为刚刚构造的以太帧赋给
		sender.sendPacket(arp);
		return true;
	}
	
	//伪造ARP数据包发送
	//主要用于网络攻击
	//模拟arp返回包
	public  boolean sendARPReply(NetworkInterface device,String src_ip,String dst_ip,String src_mac,String dst_mac) throws IOException{
		JpcapSender sender = JpcapSender.openDevice(device);
		ARPPacket arp = new ARPPacket();
		//初始化包信息
		arp.hardtype=ARPPacket.HARDTYPE_ETHER; //硬件类型
		arp.prototype=ARPPacket.PROTOTYPE_IP; //协议类型
		arp.operation=ARPPacket.ARP_REPLY; //指明是ARP请求包
		arp.hlen=6; //物理地址长度
		arp.plen=4; //协议地址长度
		//ARP包发送硬件地址，这里伪造了一个地址，如进行ARP欺骗的话可使用本机地址
		arp.sender_hardaddr=stomac(src_mac); 
		//发送端IP地址, 这里使用伪造ip地址，arp攻击一般用网关地址
		arp.sender_protoaddr=Inet4Address.getByName(src_ip).getAddress(); 
		//设置目标mac地址，如果要发起攻击的话，地址为攻击目标的mac和ip地址
		arp.target_hardaddr=stomac(dst_mac); 
		arp.target_protoaddr=InetAddress.getByName(dst_ip).getAddress(); //目的端IP地址
		
		//构造以太帧首部
		EthernetPacket ether=new EthernetPacket();
		ether.frametype=EthernetPacket.ETHERTYPE_ARP; //帧类型
		ether.src_mac=device.mac_address; //源MAC地址
//		byte[] broadcast=new byte[]{(byte)255,(byte)255,(byte)255,(byte)255,(byte)255,(byte)255}; //广播地址
		ether.dst_mac=stomac(dst_mac); //以太网目的地址，广播地址
		arp.datalink=ether; //将arp报文的数据链路层的帧设置为刚刚构造的以太帧赋给
		sender.sendPacket(arp);
		return true;
	}
	
	//将字符串转化为mac地址,格式为16进制:AA-AA-AA-AA-AA
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
