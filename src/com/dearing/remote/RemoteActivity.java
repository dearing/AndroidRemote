package com.dearing.remote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RemoteActivity extends Activity {
	public MyApp appState;

	public void app_xbmc(View view) {
		appState.SendPayload("app_xbmc");
	}

	/*
	 * ======================================================= OnClick Events
	 * =======================================================
	 */

	public void emu_gba(View view) {
		startActivity(new Intent(getApplicationContext(), EmuGBA.class));
	}

	public void emu_n64(View view) {
		startActivity(new Intent(getApplicationContext(), EmuN64.class));
	}

	public void emu_nes(View view) {
		startActivity(new Intent(getApplicationContext(), EmuNES.class));
	}

	public void emu_psx(View view) {
		startActivity(new Intent(getApplicationContext(), EmuPSX.class));
	}

	public void emu_snes(View view) {
		startActivity(new Intent(getApplicationContext(), EmuSNES.class));
	}

	public void machine(View view) {
		startActivity(new Intent(getApplicationContext(), Machine.class));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		appState = ((MyApp) getApplicationContext());
	}

	public void open_preferences(View view) {
		startActivity(new Intent(getApplicationContext(),
				SettingsActivity.class));
	}

	// EOF
}