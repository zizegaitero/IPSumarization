package com.github.ipsumarization;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author jean
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }

    @Test
    public void calcIPv4() {
        try {
            List<String> ips = new ArrayList<>();
            ips.add("192.168.16.0/27");
            ips.add("192.168.70.0/30");
            ips.add("192.168.1.0/30");
            IPv4Calculation clac = new IPv4Calculation(ips);
            System.out.println(clac.sumarizeRoute());
        } catch (Exception ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void calcIPv6() {
        try {
            List<String> ips = new ArrayList<>();
            ips.add("2001:0DB8:acad:4::/64");
            ips.add("2001:0DB8:acad:7::/64");            
            ips.add("2001:0DB8:acad:5::/64");
            ips.add("2001:0DB8:acad:6::/64");            
            IPv6Calculation clac = new IPv6Calculation(ips);
            System.out.println(clac.sumarizeRoute());
        } catch (Exception ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
