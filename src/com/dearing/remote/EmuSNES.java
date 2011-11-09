package com.dearing.remote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmuSNES extends Activity {
	public MyApp appState;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emu_snes);
		appState = ((MyApp) getApplicationContext());

	}

	public void exit(View view) {
		appState.SendPayload("%{F4}");
	}

	public void fullscreen(View view) {
		appState.SendPayload("%{ENTER}");
	}

	public void launch(View view) {
		startActivity(new Intent(getApplicationContext(), RomsSNES.class));
	}

	public void loadstate(View view) {
		appState.SendPayload("{F2}");
	}

	public void pause(View view) {
		appState.SendPayload("+p");
	}

	public void reset(View view) {
		appState.SendPayload("+r");
	}

	public void savestate(View view) {
		appState.SendPayload("+{F2}");
	}

	public void screenshot(View view) {
		appState.SendPayload("{F12}");
	}
	// EOF
}