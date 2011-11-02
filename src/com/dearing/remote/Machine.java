package com.dearing.remote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class Machine extends Activity {
    private static final int DIALOG_CONFIRM = 0;
    private static final int DIALOG_STATE = 1;
    public MyApp appState;

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder;
        Dialog dialog = null;
        switch (id) {
            case DIALOG_CONFIRM:
                // do the work to define the pause Dialog
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to reboot?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                appState.SendPayload("computer_reboot");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                dialog = builder.create();
                break;
            case DIALOG_STATE:
                final CharSequence[] items = {"~", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Select State Index");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                        appState.SendPayload(items[item].toString());
                    }
                });
                dialog = builder.create();
                break;
            default:
                dialog = null;
        }
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine);
        appState = ((MyApp) getApplicationContext());

    }

    /*
    * =======================================================
    * 		OnClick Events
    * =======================================================
    */
    public void shutdown(View view) {
        appState.SendPayload("computer_shutdown");
    }

    public void suspend(View view) {
        appState.SendPayload("computer_suspend");
    }

    public void hibernate(View view) {
        appState.SendPayload("computer_hibernate");
    }

    public void reboot(View view) {
        appState.SendPayload("computer_reboot");
    }

    //public void reboot(View v) {    showDialog(DIALOG_STATE);}
}
