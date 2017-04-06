/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ipsumarization;

import inet.ipaddr.IPAddressString;
import com.github.ipsumarization.core.IPV6;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jean
 */
public class IPv6Calculation extends IPCalculation{
    

    public IPv6Calculation(List<String> ips) {
        super(ips);
        ips.forEach((ip) -> {
            this.IPs.add(new IPV6(ip));
        });
    }
    
    @Override
    public IPAddressString sumarizeRoute() throws Exception {
        Integer prefixo = descobreQuebra(128);
        IPAddressString descobreIP = descobreIP(prefixo);
        return (descobreIP);
    }
    
    @Override
    public IPAddressString descobreIP(Integer prefixo){
        IPV6 ipPadrao = (IPV6) this.IPs.get(0);
        String binary = ipPadrao.getBinaryIP();
        binary = binary.substring(0, prefixo);
        binary = String.format("%-128s", binary).replace(' ', '0');
        List<String> splitBin = Arrays.asList(binary.split("(?<=\\G.{4})"));
        String hexaReal = "";
        hexaReal = splitBin.stream().map((cadaHexa) -> Integer.toString(Integer.parseInt(cadaHexa, 2), 16)).reduce(hexaReal, String::concat);
        
        List<String> splitHexa = Arrays.asList(hexaReal.split("(?<=\\G.{4})"));
        String ipreal = "";
        ipreal = splitHexa.stream().map((quarteto) -> quarteto+":").reduce(ipreal, String::concat);
        ipreal = ipreal.substring(0, ipreal.length()-1);
        return new IPAddressString(ipreal+"/"+prefixo);
    }
    
}
