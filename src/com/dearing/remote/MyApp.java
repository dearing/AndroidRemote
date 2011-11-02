package com.dearing.remote;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyApp extends Application {

    private String remoteHost;
    private int remotePort;

    public String getHost() {
        return remoteHost;
    }

    public int getPort() {
        return remotePort;
    }

    public void setHost(String s) {
        remoteHost = s;
    }

    public void setPort(int p) {
        remotePort = p;
    }


    private boolean isNetworkAvailable() {
        getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null, otherwise check if we are connected
        return networkInfo.isConnected();
    }

    public String[] RequestFiles(String tag) {
        if (!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
            return null;
        }
        try {
            Socket socket = new Socket(remoteHost, remotePort);
            //OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
            //InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            if (socket.isConnected()) {
                //request payload

                bw.write(tag);
                bw.flush();
                // read payload

                String buffer = br.readLine();
                br.close();
                // return array from string dilimiter = |
                return buffer.trim().split("\\|");
            }

        } catch (UnknownHostException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return null;
    }

    public void SendPayload(String payload) {
        if (!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Socket socket = new Socket(remoteHost, remotePort);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(payload);
            bw.close();

        } catch (UnknownHostException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

