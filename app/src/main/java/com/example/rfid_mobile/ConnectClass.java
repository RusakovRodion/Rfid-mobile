package com.example.rfid_mobile;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectClass implements Runnable {
    private volatile String msg = "client connected";
    Socket socket;
    DataOutputStream dos;
    BufferedReader dis;
    byte[] bt;
    String host = "25.66.192.245";
    int port = 777;
    String answer;

    @Override
    public void run() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            socket = new Socket(host,port);

            dos = new DataOutputStream(socket.getOutputStream());
            dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dos.write(msg.getBytes());
            answer = dis.readLine();
            dis.close();
            dos.flush();
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMsg(String msg)
    {
        this.msg = msg;
        run();
        return answer;
    }
}
