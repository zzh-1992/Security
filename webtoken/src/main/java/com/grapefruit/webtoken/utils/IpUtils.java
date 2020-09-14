package com.grapefruit.webtoken.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Grapefruit
 * @version 1.0
 * @ModifyTime 2020/9/12 13:52:11
 */
public class IpUtils {

    /**
     * 获取本机ip
     * @return hostAddress(IP地址)
     */
    public static String  getIp() {
        String hostAddress = "";
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            hostAddress = ip4.getHostAddress();
            System.out.println(ip4.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostAddress.replace(".","");
    }
}
