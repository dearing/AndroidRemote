package com.dearing.remote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmuNES extends Activity {
    public MyApp appState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emu_nes);
        appState = ((MyApp) getApplicationContext());

    }

    /*
    * =======================================================
    * 		OnClick Events
    * =======================================================
    */
    public void launch(View view) {
        startActivity(new Intent(getApplicationContext(), RomsNES.class));
    }

    public void exit(View view) {
        appState.SendPayload("%x");
    }

    public void fullscreen(View view) {
        appState.SendPayload("%{ENTER}");
    }

    public void escape(View view) {
        appState.SendPayload("{ESC}");
    }

    public void savestate(View view) {
        appState.SendPayload("+1");
    }

    public void loadstate(View view) {
        appState.SendPayload("1");
    }

    public void reset(View view) {
        appState.SendPayload("+r");
    }

    public void pause(View view) {
        //Shift+p
        appState.SendPayload("+p|{ENTER}");
    }
    //EOF
}