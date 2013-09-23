package com.dearing.remote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class MyApp extends Application {
	SharedPreferences preferences;

	/// Check active connection as WIFI
	private static boolean isWIFIConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = null;
		if (cm != null)
			networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		
		return networkInfo == null ? false : networkInfo.isConnected();
	}
	
	/// Property for stored target hostname of AR-Server
	public String getHost() {
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		return preferences.getString("machine", "unknown");
	}
		
	/// Create and return a confirm dialog
	public Dialog DialogConfirm(Context context, final String action, final String message, final String payload) {
		AlertDialog.Builder builder;
		builder = new AlertDialog.Builder(context);
		builder.setMessage(message)
				.setPositiveButton(action,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								SendPayload(payload);
							}
						})
				.setNegativeButton("cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		return builder.create();
	}

	/// Request String[] of potential filenames from AR-Server
	public String[] RequestFiles(String tag) {
		if (!isWIFIConnected(this)) {
			Toast.makeText(getApplicationContext(), "Network Error!",Toast.LENGTH_SHORT).show();
			return null;
		}
		try {
			InetAddress addy = InetAddress.getByName(getHost());
			if(addy.equals(null))
				return null;
			
			Socket socket = new Socket(addy, 6699);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			if (socket.isConnected()) {
				// request payload
				bw.write(tag);
				bw.flush();
				// read payload
				String buffer = br.readLine();
				br.close();
				// return array from string delimiter = |
				return buffer.trim().split("\\|");
			}

		} catch (UnknownHostException e) {
			Toast.makeText(getApplicationContext(),String.format("Unknown Host: %s", e.getMessage()),Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(),String.format("IOException: %s", e.getMessage()),Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		finally {
			
		}
		return null;
	}

	/// Send command to AR-Server
	public void SendPayload(String payload) {
		if (!isWIFIConnected(this)) {
			Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
			return;
		}

		try {
			InetAddress addy = InetAddress.getByName(getHost());
			if(addy.equals(null))
				return;
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new Socket(addy, 6699).getOutputStream()));
			bw.write(payload);
			bw.close();

		} catch (UnknownHostException e) {
			Toast.makeText(getApplicationContext(),String.format("Unknown Host: %s", e.getMessage()),Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(),String.format("IO Exception: %s", e.getMessage()),Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

}	// EOF