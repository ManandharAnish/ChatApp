/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.chatapp;

import com.leapfrog.chatapp.handler.ClientListener;
import com.sun.jmx.remote.internal.ClientListenerInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Anish
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         
        
        
        
        
        int port=1501;
        System.out.println("Server is listening at:" + port);
        ServerSocket server= new ServerSocket(port);
        while(true){
            Socket client=server.accept();
            System.out.println("Connection request from:" + client.getInetAddress().getHostAddress());
            ClientListener clientlistener=new ClientListener(client);
            clientlistener.start();
            }
        
    }
    
}
