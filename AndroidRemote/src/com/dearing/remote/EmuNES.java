package com.dearing.remote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EmuNES extends Activity {
	private static final int LOAD_STATE = 0;
	private static final int SAVE_STATE = 1;
	public MyApp appState;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emu_nes);
		appState = ((MyApp) getApplicationContext());

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder;
		final CharSequence[] items = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		switch (id) {
		case LOAD_STATE:
			builder = new AlertDialog.Builder(this);
			builder.setTitle("Select LOAD State Index");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int item) {
					Toast.makeText(getApplicationContext(),"selected state " + items[item], Toast.LENGTH_SHORT).show();
					appState.SendPayload(items[item].toString());
				}
			});
			return builder.create();
		case SAVE_STATE:
			builder = new AlertDialog.Builder(this);
			builder.setTitle("Select SAVE State Index");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int item) {
					Toast.makeText(getApplicationContext(),"selected state " + items[item], Toast.LENGTH_SHORT).show();
					appState.SendPayload("+" + items[item].toString());
				}
			});
			return builder.create();
		default:
			return null;
		}
	}


	// Events

	public void escape(View view) {
		appState.SendPayload("{ESC}");
	}

	public void exit(View view) {
		appState.DialogConfirm(this, "exit", "Are you sure you want to exit?", "%x").show();
	}

	public void fullscreen(View view) {
		appState.SendPayload("%{ENTER}");
	}

	public void launch(View view) {
		startActivity(new Intent(getApplicationContext(), RomsNES.class));
	}

	public void loadstate(View view) {
		showDialog(LOAD_STATE);
	}

	public void pause(View view) {
		// Shift+p
		appState.SendPayload("+p");
	}

	public void reset(View view) {
		appState.DialogConfirm(this, "reset", "Are you sure you want to reset?", "+r").show();
	}

	public void savestate(View view) {
		showDialog(SAVE_STATE);
	}
	
}	//EOF