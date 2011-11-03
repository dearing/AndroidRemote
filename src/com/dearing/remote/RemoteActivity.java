package com.dearing.remote;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class RemoteActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public MyApp appState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appState = ((MyApp) getApplicationContext());
        //appState.setHost("black");
        //appState.setPort(6699);

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

    public void emu_n64(View view) {
        startActivity(new Intent(getApplicationContext(), EmuN64.class));
    }

    public void emu_psx(View view) {
        startActivity(new Intent(getApplicationContext(), EmuPSX.class));
    }

    public void open_preferences(View view) {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }

}