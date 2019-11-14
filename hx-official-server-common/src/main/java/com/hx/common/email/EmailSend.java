package com.hx.common.email;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailSend {
    private String server_addr;
    private String from ;
    private String username;
    private String password;

    public EmailSend(String server_addr,String from,String username,String password)
    {
        this.server_addr=server_addr;
        this.from=from;
        this.username=username;
        this.password=password;
    }
    public  boolean send( String subject,String to ,String content){
        try {

            String copyto = "";
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
            // String subject = dateFormat.format( now )+"****";
            String filename = "";
            return  Mail.sendAndCc(server_addr, from, to, copyto, subject, content+"  <br />"+dateFormat.format( now ), username, password, filename);
            //return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

    }

    public  boolean sendForm( String subject,String to ,String content,String type){
        try {

            String copyto = "";
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
            // String subject = dateFormat.format( now )+"****";
            String filename = "";
            String message=MessageFormat.format(content,type);
            return  Mail.sendAndCc(server_addr, from, to, copyto, subject, message+"  <br />"+dateFormat.format( now ), username, password, filename);
            //return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

    }

    public  boolean sendTest( String subject,String to ,String content){
        try {

            String copyto = "";
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
            // String subject = dateFormat.format( now )+"****";
            String filename = "";
            return  Mail.sendAndCc(server_addr, from, to, copyto, subject,"  <br />"+dateFormat.format( now ), username, password, filename);
            //return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

    }
}
