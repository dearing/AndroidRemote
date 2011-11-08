package com.dearing.remote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RemoteActivity extends Activity {
    public MyApp appState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appState = ((MyApp) getApplicationContext());
    }

    /*
    * =======================================================
    * 		OnClick Events
    * =======================================================
    */

    public void machine(View view) {
        startActivity(new Intent(getApplicationContext(), Machine.class));
    }

    public void app_xbmc(View view) {
        appState.SendPayload("app_xbmc");
    }

    public void emu_nes(View view) {
        startActivity(new Intent(getApplicationContext(), EmuNES.class));
    }
    public void emu_snes(View view) {
        startActivity(new Intent(getApplicationContext(), EmuSNES.class));
    }
    public void emu_gba(View view) {
        startActivity(new Intent(getApplicationContext(), EmuGBA.class));
    }
    public void emu_n64(View view) {
        startActivity(new Intent(getApplicationContext(), EmuN64.class));
    }

    public void emu_psx(View view) {
        startActivity(new Intent(getApplicationContext(), EmuPSX.class));
    }

    public void open_preferences(View view) {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }

    //EOF
}