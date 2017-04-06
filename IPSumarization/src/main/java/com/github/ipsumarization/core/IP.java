/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ipsumarization.core;

/**
 * Classe abstrata  que comtempla os dados cruciais do IP
 * IP e prefixo.
 * @author jean
 */
public abstract class IP {
    
    protected String IP;
    protected String IPComplete;
    protected Integer prefixo;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getIPComplete() {
        return IPComplete;
    }

    public void setIPComplete(String IPComplete) {
        this.IPComplete = IPComplete;
    }

    public Integer getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(Integer prefixo) {
        this.prefixo = prefixo;
    }
    
    /**
     * Retorna o valor do IP em Binário
     * @return 
     */
    public abstract String getBinaryIP();
    
    
    
}
