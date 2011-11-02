package com.dearing.remote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class EmuPSX extends Activity{
	public MyApp appState;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emu_psx);
		appState = ((MyApp)getApplicationContext());
	
	}    
    /*
     * =======================================================
     * 		OnClick Events
     * =======================================================
     */
	public void launch(View view){appState.SendPayload("emu_psx");}
	public void exit(View view){appState.SendPayload("%{F4}|%{F4}");}
	public void fullscreen(View view){appState.SendPayload("{F12}");}
	public void escape(View view){appState.SendPayload("{ESC}");}
	public void savestate1(View view){appState.SendPayload("{F2}");}
	public void loadstate1(View view){appState.SendPayload("{F8}");}
	public void savestate2(View view){appState.SendPayload("{F3}");}
	public void loadstate2(View view){appState.SendPayload("{F9}");}
	public void pause(View view){appState.SendPayload("{F6}");}
	public void sync(View view){appState.SendPayload("{F4}");}
	public void run(View view){appState.SendPayload("{F5}");}
	public void analog(View view){appState.SendPayload("{F11}");}
}
