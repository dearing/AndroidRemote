package com.dearing.remote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EmuN64 extends Activity {
	private static final int LOAD_STATE = 0;
	private static final int SAVE_STATE = 1;
	public MyApp appState;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emu_n64);
		appState = ((MyApp) getApplicationContext());
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder;
		final CharSequence[] items = { "default", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		switch (id) {
		case LOAD_STATE:
			builder = new AlertDialog.Builder(this);
			builder.setTitle("Select State Index");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int item) {
					if (items[item].equals("default")) {
						Toast.makeText(getApplicationContext(), "selected default state", Toast.LENGTH_SHORT).show();
						appState.SendPayload("~|{F7}");
					} else {
						Toast.makeText(getApplicationContext(), "selected state " + items[item], Toast.LENGTH_SHORT).show();
						appState.SendPayload(items[item].toString() + "|{F7}");
					}
				}
			});
			return builder.create();
		case SAVE_STATE:
			builder = new AlertDialog.Builder(this);
			builder.setTitle("Select State Index");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int item) {
					if (items[item].equals("default")) {
						Toast.makeText(getApplicationContext(), "selected default state", Toast.LENGTH_SHORT).show();
						appState.SendPayload("~|{F5}");
					} else {
						Toast.makeText(getApplicationContext(), "selected state " + items[item],Toast.LENGTH_SHORT).show();
						appState.SendPayload(items[item].toString() + "|{F5}");
					}
				}
			});
			return builder.create();
		default:
			return null;
		}
	}


	/*
	 * ======================================================= 
	 *  OnClick Events
	 * =======================================================
	 */
	
	public void exit(View view) {
		appState.DialogConfirm(this, "exit", "Are you sure you want to exit?","%{F4}").show();
	}

	public void fullscreen(View view) {
		appState.SendPayload("%{ENTER}");
	}

	public void hard_reset(View view) {
		appState.DialogConfirm(this, "hard reset", "Are you sure you want to hard reset?", "{F1}").show();
	}

	public void launch(View view) {
		startActivity(new Intent(getApplicationContext(), RomsN64.class));
	}

	public void loadstate(View view) {
		showDialog(LOAD_STATE);
	}

	public void pause(View view) {
		appState.SendPayload("{F2}");
	}

	public void savestate(View view) {
		showDialog(SAVE_STATE);
	}

	public void soft_reset(View view) {
		appState.DialogConfirm(this, "soft reset", "Are you sure you want to soft reset?", "+{F1}").show();
	}

}
