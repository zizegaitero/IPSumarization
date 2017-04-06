/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.zizegaitero.ipsumarization.core;

import inet.ipaddr.IPAddressString;

/**
 *
 * @author jean
 */
public class IPV6 {
    
    private String IP;
    private String IPComplete;
    private Integer prefixo;
    private final String quart1;
    private final String quart2;
    private final String quart3;
    private final String quart4;
    private final String quart5;
    private final String quart6;
    private final String quart7;
    private final String quart8;


    public String getIPComplete() {
        return IPComplete;
    }

    public void setIPComplete(String IPComplete) {
        this.IPComplete = IPComplete;
    }

    public IPV6(String IPComplete) {
        this.IPComplete = IPComplete;
        String[] splitIP = IPComplete.split("/");
        this.prefixo = Integer.parseInt(splitIP[1]);
        IPAddressString ipAddressString = new IPAddressString(splitIP[0]);        
        this.IP = (ipAddressString.getAddress().toFullString());
        String[] splitIPOc = this.IP.split("\\:");
        this.quart1 = String.format("%16s", Integer.toString(Integer.parseInt(splitIPOc[0],16),2)).replace(' ', '0');
        this.quart2 = String.format("%16s", Integer.toString(Integer.parseInt(splitIPOc[1],16),2)).replace(' ', '0');
        this.quart3 = String.format("%16s", Integer.toString(Integer.parseInt(splitIPOc[2],16),2)).replace(' ', '0');
        this.quart4 = String.format("%16s", Integer.toString(Integer.parseInt(splitIPOc[3],16),2)).replace(' ', '0');
        this.quart5 = String.format("%16s", Integer.toString(Integer.parseInt(splitIPOc[4],16),2)).replace(' ', '0');
        this.quart6 = String.format("%16s", Integer.toString(Integer.parseInt(splitIPOc[5],16),2)).replace(' ', '0');
        this.quart7 = String.format("%16s", Integer.toString(Integer.parseInt(splitIPOc[6],16),2)).replace(' ', '0');
        this.quart8 = String.format("%16s", Integer.toString(Integer.parseInt(splitIPOc[7],16),2)).replace(' ', '0');
    }   
    
    
    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Integer getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(Integer prefixo) {
        this.prefixo = prefixo;
    }   
    
    public String getBinaryIP(){
        return quart1+quart2+quart3+quart4+quart5+quart6+quart7+quart8;
    }    
}
