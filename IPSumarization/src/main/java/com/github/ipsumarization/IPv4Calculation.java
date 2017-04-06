/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ipsumarization;

import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import com.github.ipsumarization.core.IPV4;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jean
 */
public class IPv4Calculation extends IPCalculation{
    
    List<String> ips;
    List<IPV4> ipsv4 = new ArrayList<>();

    public IPv4Calculation(List<String> ips) {
        this.ips = ips;
        for (String ip : ips) {
            ipsv4.add(new IPV4(ip));
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
        IPV4 ipPadrao = ipsv4.get(0);
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
    
    private Integer descobreQuebra(){
        for (int i = 0; i < 32; i++) {
            String digito = null;
            for (IPV4 ipv4 : ipsv4) {
                if(digito == null){
                    digito = ipv4.getBinaryIP().charAt(i)+"";
                }else{
                    if(!digito.equals(ipv4.getBinaryIP().charAt(i)+"")){
                        return i;
                    }
                }                
            }            
        }
        return null;
    }
    
    
    
}
