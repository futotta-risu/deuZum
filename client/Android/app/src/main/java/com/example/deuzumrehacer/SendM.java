package com.example.deuzumrehacer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


import android.os.AsyncTask;

public class SendM {

    public class sendM extends AsyncTask<String, Void, Void> {

        Socket s;
        DataOutputStream dos;
        PrintWriter pw;

        @Override
        protected Void doInBackground(String... voids) {

            String message = voids[0];

            try {
                s = new Socket("10.0.2.2",50337);
                pw = new PrintWriter(s.getOutputStream());
                pw.write(message);
                pw.flush();
                pw.close();
                s.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }
}
