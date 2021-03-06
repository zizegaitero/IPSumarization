/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ipsumarization.core;

import inet.ipaddr.IPAddressString;

/**
 *
 * @author jean
 */
public class IPV4 extends IP {
    
    private String mascara;
    private String octeto1;
    private String octeto2;
    private String octeto3;
    private String octeto4;

    public IPV4(String IPComplete) {
        this.IPComplete = IPComplete;
        String[] splitIP = IPComplete.split("/");
        this.prefixo = Integer.parseInt(splitIP[1]);
        IPAddressString ipAddressString = new IPAddressString(splitIP[0]);        
        this.IP = (ipAddressString.getAddress().toFullString());
        this.mascara = ipAddressString.getAddress().getNetwork().getNetworkMask(this.prefixo, false).toCanonicalString();
        String[] splitIPOc = this.IP.split("\\.");
        this.octeto1 = String.format("%8s", Integer.toBinaryString(Integer.parseInt(splitIPOc[0]))).replace(' ', '0');
        this.octeto2 = String.format("%8s", Integer.toBinaryString(Integer.parseInt(splitIPOc[1]))).replace(' ', '0');
        this.octeto3 = String.format("%8s", Integer.toBinaryString(Integer.parseInt(splitIPOc[2]))).replace(' ', '0');
        this.octeto4 = String.format("%8s", Integer.toBinaryString(Integer.parseInt(splitIPOc[3]))).replace(' ', '0');
    }   
    
    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getOcteto1() {
        return octeto1;
    }

    public void setOcteto1(String octeto1) {
        this.octeto1 = octeto1;
    }

    public String getOcteto2() {
        return octeto2;
    }

    public void setOcteto2(String octeto2) {
        this.octeto2 = octeto2;
    }

    public String getOcteto3() {
        return octeto3;
    }

    public void setOcteto3(String octeto3) {
        this.octeto3 = octeto3;
    }

    public String getOcteto4() {
        return octeto4;
    }

    public void setOcteto4(String octeto4) {
        this.octeto4 = octeto4;
    }
    
    @Override
    public String getBinaryIP(){
        return octeto1+octeto2+octeto3+octeto4;
    }    
}
