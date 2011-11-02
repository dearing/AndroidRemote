package com.dearing.remote;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MyApp extends Application {

	  private String remoteHost;
	  private int remotePort;

	  public String getHost(){
	    return remoteHost;
	  }
	  public void setHost(String s){
		  remoteHost = s;
	  }
	  public void setPort(int s){
		  remotePort = s;
	  }
	  public int getPort(){
		   return remotePort;
	  }
	  
		private boolean isNetworkAvailable() {
	        getApplicationContext();
			ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
	        
	        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	        // if no network is available networkInfo will be null, otherwise check if we are connected
	        if (networkInfo != null && networkInfo.isConnected()) {
	            return true;
	        }
	        return false;
	    }
		
	  public void SendPayload(String payload){
	    	if(!isNetworkAvailable())
	    	{
	    		Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
	    		return;
	    	}
	    	
	    	try {
				Socket socket = new Socket(remoteHost, remotePort);
				OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
				out.write(payload);
				out.close();
				
			} catch (UnknownHostException e) {
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			}
	  }
	  
	}

