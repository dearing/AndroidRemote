package com.dearing.remote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class EmuN64 extends Activity {
	public MyApp appState;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emu_n64);
		appState = ((MyApp)getApplicationContext());
	
	}    
    /*
     * =======================================================
     * 		OnClick Events
     * =======================================================
     */
	public void launch(View view){appState.SendPayload("emu_n64");}
	public void exit(View view){appState.SendPayload("%{F4}");}
	public void fullscreen(View view){appState.SendPayload("%{ENTER}");}
	public void state(View view){appState.SendPayload("1");}
	public void savestate(View view){appState.SendPayload("{F5}");}
	public void loadstate(View view){appState.SendPayload("{F7}");}
	public void pause(View view){appState.SendPayload("{F2}");}
	public void hard_reset(View view){appState.SendPayload("{F1}");}
	public void soft_reset(View view){appState.SendPayload("{F1}");}
	
}
