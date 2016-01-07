/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.chatapp.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Anish
 */
public class ClientListener extends Thread{
    private Socket client;
    
    public ClientListener(Socket client){
        this.client=client;
       
    }

    @Override
    public void run(){
        try{    
            String link="http://admissions.g30.nagoya-u.ac.jp/en/Program/undergraduate/";
         String content="",line1="";
         URL url=new URL(link);
         URLConnection conn= url.openConnection();
         BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
         StringBuilder builder=new StringBuilder();
         while((line1=reader.readLine()) !=null)
                 {
                     builder.append(line1);
                 }
                 reader.close();
          
        String regexjap="<td rowspan=\"2\"><a href=\"(.*?)\">(.*?)</a>";
        String regexj="<td rowspan=\"2\"><a href=\"(.*?)\">(.*?)</a>";
        Pattern pattern4=Pattern.compile(regexjap);
        
        Matcher matcher4=pattern4.matcher(builder.toString());
        
        
            PrintStream output=new PrintStream(client.getOutputStream());
            output.println("welcome to server");
            int i=1;
            while(true){
                    BufferedReader input=new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String line=input.readLine();
                    if(line.equalsIgnoreCase("Courses"))
                    {
                        System.out.println("There is a request of scrapping for courses by :"+client.getInetAddress());
                        output.println("Your expected results are as follows");
                        while(matcher4.find())
                        {
                            output.println(i +")"+matcher4.group(2).trim());
                            i++;
                        }    
                    }
                    else if(line.equalsIgnoreCase("disconnect"))
                    {
                        System.exit(0);
                    }
                  
                    else{
                        System.out.println(client.getInetAddress()+":"+line);
                        output.println("You sent a message:"+line);
                        output.println(" ");
                        output.flush();
                    }
                }
    
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
 }
}
