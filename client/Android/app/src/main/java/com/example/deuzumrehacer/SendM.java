package com.example.deuzumrehacer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


import android.os.AsyncTask;

public class SendM {

    public class sendM extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;

        DataOutputStream dos;
        PrintWriter pw;

        sendM(){
            dstAddress = "10.0.2.2";
            dstPort = 50337;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Socket socket = null;

            try {
                socket = new Socket(dstAddress, dstPort);
                pw = new PrintWriter(socket.getOutputStream());
                pw.write("hash\n{}");
                pw.flush();
                pw.close();
                socket.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }
}
