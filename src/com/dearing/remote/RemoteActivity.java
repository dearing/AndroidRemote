package com.dearing.remote;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class RemoteActivity extends Activity {
    /** Called when the activity is first created. */
	
	public static final String remoteHost = "black";
	public static final int remotePort = 6699;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project64);
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        String remoteHost = settings.getString("remoteHost", "black");
        int remotePort = settings.getInt("remotePort", 6699);
    }
    
    
    @Override
    protected void onStop(){
       super.onStop();

      // We need an Editor object to make preference changes.
      // All objects are from android.context.Context
      SharedPreferences settings = getPreferences(MODE_PRIVATE);
      SharedPreferences.Editor editor = settings.edit();
      editor.putString("remoteHost", remoteHost);
      editor.putInt("remotePort", remotePort);

      // Commit the edits!
      editor.commit();
    }
    
    
    
    
    public void b_reboot(View view){SendPayload("computer_reboot");}
    public void b_shutdown(View view){SendPayload("computer_shutdown");}
    //public void b_suspend(View view){SendPayload("computer_suspend");}
    public void b_suspend(View view){
    	Intent i = new Intent(getApplicationContext(),StartN64.class);
    	startActivity(i);
    }
    public void b_hibernate(View view){SendPayload("computer_hibernate");}
    
    public void b_launch(View view){SendPayload("launch_n64");}
    public void b_exit(View view){SendPayload("%{F4}");}
    public void b_zap(View view){SendPayload("zap_n64");}
    
    public void b_fullscreen(View view){SendPayload("%{ENTER}");}
    public void b_pause(View view){SendPayload("{F2}");}
    public void b_savestate(View view){SendPayload("{F5}");}
    public void b_loadstate(View view){SendPayload("{F7}");}
    public void b_changestate(View view){SendPayload("1");}
    public void b_soft_reset(View view){SendPayload("{F1}");}
    public void b_hard_reset(View view){SendPayload("+{F1}");}
    
    
    public boolean isNetworkAvailable() {
        getApplicationContext();
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null, otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
    
    public void SendPayload(String payload)
    {
    	if(!isNetworkAvailable())
    	{
    		Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	try {
			//InetAddress serverAddr = InetAddress.getByName("192.168.0.105");
    		
			Socket socket = new Socket(remoteHost, remotePort);
			
			//Toast.makeText(getApplicationContext(), socket.toString(), Toast.LENGTH_SHORT).show();
			
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
			out.write(payload+"\00");
			out.close();
			
			Toast.makeText(getApplicationContext(), "sent: "+payload, Toast.LENGTH_SHORT).show();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), "HOST::"+e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), "IOex::"+e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
    }
}