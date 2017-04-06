/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ipsumarization;

import inet.ipaddr.IPAddressString;
import com.github.ipsumarization.core.IPV4;
import java.util.List;

/**
 *
 * @author jean
 */
public class IPv4Calculation extends IPCalculation {


    public IPv4Calculation(List<String> ips) {
        super(ips);
        ips.forEach((ip) -> {
            this.IPs.add(new IPV4(ip));
        });
    }    

    @Override
    public IPAddressString sumarizeRoute() throws Exception {
        Integer prefixo = descobreQuebra(32);
        IPAddressString descobreIP = descobreIP(prefixo);
        return (descobreIP);
    }
    
    @Override
    protected IPAddressString descobreIP(Integer prefixo){
        IPV4 ipPadrao = (IPV4) IPs.get(0);
        String binary = ipPadrao.getBinaryIP().substring(0, prefixo);
        binary = String.format("%-32s", binary).replace(' ', '0');
        String[] splitOc = binary.split("(?<=\\G.{8})");
        String octeto1 = splitOc[0];
        String octeto2 = splitOc[1];
        String octeto3 = splitOc[2];
        String octeto4 = splitOc[3]; 
        return new IPAddressString(Integer.parseInt(octeto1, 2)+"."+
                                    Integer.parseInt(octeto2, 2)+"."+
                                    Integer.parseInt(octeto3, 2)+"."+
                                    Integer.parseInt(octeto4, 2)+"/"+prefixo);
    }
    
}
