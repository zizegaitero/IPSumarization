/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.zizegaitero.ipsumarization;

import inet.ipaddr.IPAddressString;
import com.github.zizegaitero.ipsumarization.core.IPV6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jean
 */
public class IPv6Calculation extends IPCalculation{
    
    List<String> ips;
    List<IPV6> ipsv6 = new ArrayList<>();

    public IPv6Calculation(List<String> ips) {
        this.ips = ips;
        for (String ip : ips) {
            ipsv6.add(new IPV6(ip));
        }
    }
    
    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    @Override
    public IPAddressString sumarizeRoute() throws Exception {
        Integer prefixo = descobreQuebra();
        IPAddressString descobreIP = descobreIP(prefixo);
        return (descobreIP);
    }
    
    private IPAddressString descobreIP(Integer prefixo){
        IPV6 ipPadrao = ipsv6.get(0);
        String binary = ipPadrao.getBinaryIP();
        binary = binary.substring(0, prefixo);
        binary = String.format("%-128s", binary).replace(' ', '0');
        List<String> splitBin = Arrays.asList(binary.split("(?<=\\G.{4})"));
        String hexaReal = "";
        for (String cadaHexa : splitBin) {
            hexaReal += Integer.toString(Integer.parseInt(cadaHexa, 2), 16);
        }
        
        List<String> splitHexa = Arrays.asList(hexaReal.split("(?<=\\G.{4})"));
        String ipreal = "";
        for (String quarteto : splitHexa) {
            ipreal += quarteto+":";
        }
        ipreal = ipreal.substring(0, ipreal.length()-1);
        return new IPAddressString(ipreal+"/"+prefixo);
    }
    
    private Integer descobreQuebra(){
        for (int i = 0; i < 128; i++) {
            String digito = null;
            for (IPV6 ipv6 : ipsv6) {
                if(digito == null){
                    digito = ipv6.getBinaryIP().charAt(i)+"";
                }else{
                    if(!digito.equals(ipv6.getBinaryIP().charAt(i)+"")){
                        return i;
                    }
                }                
            }            
        }
        return null;
    }
    
    
    
}
