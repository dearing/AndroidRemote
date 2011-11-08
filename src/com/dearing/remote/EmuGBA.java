package com.dearing.remote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmuGBA extends Activity {
    public MyApp appState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emu_gba);
        appState = ((MyApp) getApplicationContext());

    }

    /*
    * =======================================================
    * 		OnClick Events
    * =======================================================
    */
    public void launch(View view) {
        startActivity(new Intent(getApplicationContext(), RomsGBA.class));
    }

    public void exit(View view) {
        appState.SendPayload("^x");
    }

    public void fullscreen(View view) {
        appState.SendPayload("{ESC}");
    }

    public void savestate(View view) {
        appState.SendPayload("+{F1}");
    }

    public void loadstate(View view) {
        appState.SendPayload("{F1}");
    }

    public void pause(View view) {
        appState.SendPayload("^p");
    }

    public void reset(View view) {
        appState.SendPayload("^r");
    }

    //EOF
}