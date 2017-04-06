/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.zizegaitero.ipsumarization;

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
    
    private static String help(){
        String help = "This software calculate the route sumarized both ip version "+System.getProperty("line.separator")
                + "   params:  <IP/prefix>...N<IP/prefix> "+System.getProperty("line.separator")
                + " Return the sumarized route";
        return help;
    }
    
    private static String getSumarizedRoute (IPAddressString ipString){
        StringBuilder result = new StringBuilder("The ip is: "+ipString.getAddress().toCanonicalString()).append(System.getProperty("line.separator"));
        if(ipString.getIPVersion().isIPv4()){
            result.append("The subnet mask is: ").append(ipString.getAddress().getNetwork().getNetworkMask(ipString.getNetworkPrefixLength(), false)).append(System.getProperty("line.separator"));
        }else{
            result.append("The other format is: ").append(ipString.getAddress().toFullString()).append(System.getProperty("line.separator"));
            result.append("The other format is: ").append(ipString.getAddress().toAddressString()).append(System.getProperty("line.separator"));
            result.append("The other format is: ").append(ipString.getAddress().toCompressedString()).append(System.getProperty("line.separator"));
        }
        return result.toString();
    }
    
}
