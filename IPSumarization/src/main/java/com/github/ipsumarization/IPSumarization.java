/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ipsumarization;

import inet.ipaddr.IPAddressString;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jean
 */
public class IPSumarization {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println(" try --h for help ");
            System.exit(0);
        }else if("--h".equals(args[0])){
            System.out.println(help());
            System.exit(0);
        }
        List<String> asList = Arrays.asList(args);
        validaIps(asList);
        boolean ipv4 = true;
        for (String arg : args) {
            if(arg.contains(":")){
                ipv4 = false;
            }
        }   
        IPCalculation iPCalculation;
        if(ipv4){
             iPCalculation = new IPv4Calculation(asList);
        }else{
            iPCalculation = new IPv6Calculation(asList);
        }
        System.out.println(getSumarizedRoute(iPCalculation.sumarizeRoute()));
        System.exit(0);
    }
    
    /**
     * retorna a String com a ajuda
     * @return 
     */
    private static String help(){
        String help = "This software calculate the route sumarized both ip version "+System.getProperty("line.separator")
                + "   params:  <IP/prefix>...N<IP/prefix> "+System.getProperty("line.separator")
                + " Return the sumarized route";
        return help;
    }
    
    /**
     * Retorna a String com os dados da rota sumrizada
     * @param ipString
     * @return 
     */
    private static String getSumarizedRoute (IPAddressString ipString){
        StringBuilder result = new StringBuilder("The ip is: ").append(ipString.getAddress().toCanonicalString()).append(System.getProperty("line.separator"));
        if(ipString.getIPVersion().isIPv4()){
            result.append("The subnet mask is: ").append(ipString.getAddress().getNetwork().getNetworkMask(ipString.getNetworkPrefixLength(), false)).append(System.getProperty("line.separator"));
        }else{
            result.append("The other format is: ").append(ipString.getAddress().toFullString()).append(System.getProperty("line.separator"));
            result.append("The other format is: ").append(ipString.getAddress().toAddressString()).append(System.getProperty("line.separator"));
            result.append("The other format is: ").append(ipString.getAddress().toCompressedString()).append(System.getProperty("line.separator"));
        }
        return result.toString();
    }
    
    /**
     * Valida se os paraêmtros de entrada são ips.
     * Se não forem mostra o log de erro e fecha a aplicação
     * @param ips 
     */
    private static void validaIps(List<String> ips) {
        IPAddressString ipad;
        for (String arg : ips) {
            ipad = new IPAddressString(arg);
            if (!ipad.isValid()){
                System.out.println("Este parâmetro não é um endereço IP: "+ arg+System.getProperty("line.separator")+" try --h for help ");
                System.exit(1);
            }            
        }  
    }
    
}
