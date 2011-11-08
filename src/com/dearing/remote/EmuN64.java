package com.dearing.remote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class EmuN64 extends Activity {
    public MyApp appState;
    private static final int DIALOG_STATE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emu_n64);
        appState = ((MyApp) getApplicationContext());

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder;
        switch (id) {
            case DIALOG_STATE:
                final CharSequence[] items = {"default", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Select State Index");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {


                        if(items[item].equals("default")) {
                            Toast.makeText(getApplicationContext(), "selected default state", Toast.LENGTH_SHORT).show();
                            appState.SendPayload("~");
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "selected state " + items[item], Toast.LENGTH_SHORT).show();
                            appState.SendPayload(items[item].toString());
                        }
                    }
                });
                return builder.create();
            default:
                return null;
        }
    }

    /*
    * =======================================================
    * 		OnClick Events
    * =======================================================
    */
    public void launch(View view) {
        startActivity(new Intent(getApplicationContext(), RomsN64.class));
    }

    public void exit(View view) {
        appState.SendPayload("%{F4}");
    }

    public void fullscreen(View view) {
        appState.SendPayload("%{ENTER}");
    }

    public void state(View view) {
        showDialog(DIALOG_STATE);
    }

    public void savestate(View view) {
        appState.SendPayload("{F5}");
    }

    public void loadstate(View view) {
        appState.SendPayload("{F7}");
    }

    public void pause(View view) {
        appState.SendPayload("{F2}");
    }

    public void hard_reset(View view) {
        appState.SendPayload("{F1}");
    }

    public void soft_reset(View view) {
        appState.SendPayload("{F1}");
    }

}
