package com.example.deuzumrehacer;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender implements Runnable {

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;
    BufferedReader br;
    String command;
    JSONObject data;

    MessageSender(String commandt, JSONObject datat){
        data = datat;
        command = commandt;
    }

    @Override
    public void run() {

        try{
            s = new Socket("10.0.2.2",64321);
            pw = new PrintWriter(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw.write(command+"\n");
            pw.flush();
            pw.write(data.toString()+"\n");
            pw.flush();
            ServerRespond.result = br.readLine();
            pw.close();
            s.close();


        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
