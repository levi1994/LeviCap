package com.levilee.levicap.model.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.levilee.levicap.model.util.ByteUtil;

import jpcap.packet.Packet;

public class Analyzer {
	// infolist用于保存各种情况分析包的具体信息;
	public  List<String> infolist = new ArrayList<String>();
	//namelist用于保存各种分析情况名称，便于前台显示
	public  List<String> namelist = new ArrayList<String>();

	public  void getInfo(Packet packet) {
		//初始化分析工具
		EthernetAnalyzer ethernetAnalyzer = new EthernetAnalyzer();
		IPv4Analyzer iPv4Analyzer = new IPv4Analyzer();
		IPv6Analyzer iPv6Analyzer = new IPv6Analyzer();
		TCPAnalyzer tcpAnalyzer = new TCPAnalyzer();
		UDPAnalyzer udpAnalyzer = new UDPAnalyzer();
		if (ethernetAnalyzer.isAnalyzable(packet)) {
			String result = "";
			ethernetAnalyzer.analyze(packet);
			Object[] table = ethernetAnalyzer.getValues();
			for (int i = 0; i < ethernetAnalyzer.getValuenames().length; i++) {
				result += ethernetAnalyzer.getValuenames()[i] + ":" + table[i] + "\n";
			}
			infolist.add(result);
			namelist.add("Ethernet");
		}
		if (iPv4Analyzer.isAnalyzable(packet)) {
			String result = "";
			iPv4Analyzer.analyze(packet);
			Object[] table = iPv4Analyzer.getValues();
			for (int i = 0; i < iPv4Analyzer.valueNames.length; i++) {
				result += iPv4Analyzer.valueNames[i] + ":" + table[i] + "\n";
			}
			infolist.add(result);
			namelist.add("iPv4");
		}
		if (iPv6Analyzer.isAnalyzable(packet)) {
			String result = "";
			iPv6Analyzer.analyze(packet);
			Object[] table = iPv6Analyzer.getValues();
			for (int i = 0; i < iPv6Analyzer.valueNames.length; i++) {
				result += iPv6Analyzer.valueNames[i] + ":" + table[i] + "\n";
			}
			infolist.add(result);
			namelist.add("iPv6");
		}
		if (tcpAnalyzer.isAnalyzable(packet)) {
			String result = "";
			tcpAnalyzer.analyze(packet);
			Object[] table = tcpAnalyzer.getValues();
			for (int i = 0; i < tcpAnalyzer.valueNames.length; i++) {
				result += tcpAnalyzer.valueNames[i] + ":" + table[i] + "\n";
			}
			infolist.add(result);
			namelist.add("tcp");
		}
		if (udpAnalyzer.isAnalyzable(packet)) {
			String result = "";
			udpAnalyzer.analyze(packet);
			Object[] table = udpAnalyzer.getValues();
			for (int i = 0; i < udpAnalyzer.valueNames.length; i++) {
				result += udpAnalyzer.valueNames[i] + ":" + table[i] + "\n";
			}
			infolist.add(result);
			namelist.add("udp");
		}
	}
	
	public String getData(Packet packet){
		byte[] b = packet.data;
		String data = ByteUtil.byteToString(b);
		return data.toUpperCase();
		
	}

}
