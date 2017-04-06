/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ipsumarization;

import com.github.ipsumarization.core.IP;
import inet.ipaddr.IPAddressString;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que define os métodos para que cada versão de IP
 * implemente a sumarização
 * @author jean
 */
public abstract class IPCalculation {
    
    protected List<IP> IPs;

    /**
     * Força o construtor receber uma lista de String contendo o IP
     * Inicializa a Lista de IP
     * Deve-se na implementação preencher a lista de IP com os valores recebidos
     * no contrutor
     * @param ips 
     */
    public IPCalculation(List<String> ips) {
        this.IPs = new ArrayList<>();
    }    
    /**
     * Faz a sumarização das rotas
     * @return
     * @throws Exception 
     */
    public abstract IPAddressString sumarizeRoute() throws Exception;
    
    /**
     * Gera o novo ip sumarizado baseado na lista de IP
     * @param prefixo
     * @return 
     */
    protected abstract IPAddressString descobreIP(Integer prefixo);
    
    /**
     * Descobre em qual AND lógico acontece a quebra
     * @param quantidadeBits O tamanho da rede em bits Cara implementação usa a sua
     * @return 
     */
    public Integer descobreQuebra(Integer quantidadeBits){
        for (int i = 0; i < quantidadeBits; i++) {
            String digito = null;
            for (IP ipv6 : IPs) {
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
