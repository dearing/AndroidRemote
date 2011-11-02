package com.dearing.remote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Machine extends Activity {
	public MyApp appState;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.machine);
		appState = ((MyApp)getApplicationContext());
	
	}
    /*
     * =======================================================
     * 		OnClick Events
     * =======================================================
     */
	public void shutdown(View view){appState.SendPayload("computer_shutdown");}
	public void suspend(View view){appState.SendPayload("computer_suspend");}
	public void hibernate(View view){appState.SendPayload("computer_hibernate");}
	public void reboot(View view){appState.SendPayload("computer_reboot");}
}
