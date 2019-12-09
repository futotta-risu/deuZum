package com.example.deuzumrehacer;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class MyServerThread implements Runnable{

    Socket s;
    ServerSocket ss;
    InputStreamReader isr;
    BufferedReader br;
    String message;
    Handler h = new Handler();
    Activity activity;

    @Override
    public void run() {

        try {
            ss = new ServerSocket(50337);
            while (true){
                s = ss.accept();
                isr = new InputStreamReader(s.getInputStream());
                br = new BufferedReader(isr);
                message = br.readLine();

                h.post(new Runnable() {
                    @Override
                    public void run() {
                        activity = new Activity();
                        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
