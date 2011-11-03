package com.dearing.remote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class Machine extends Activity {
    public MyApp appState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine);
        appState = ((MyApp) getApplicationContext());

    }

    private Dialog d_confirm(final String action, final String payload) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage(String.format("Confirm request for machine `%s` to %s?", appState.getHost(), action))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        appState.SendPayload(payload);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    /*
    * =======================================================
    * 		OnClick Events
    * =======================================================
    */
    public void shutdown(View view) {
        d_confirm("shutdown", "computer_shutdown").show();
    }

    public void suspend(View view) {
        d_confirm("suspend", "computer_suspend").show();
    }

    public void hibernate(View view) {
        d_confirm("hibernate", "computer_hibernate").show();
    }

    public void reboot(View view) {
        d_confirm("reboot", "computer_reboot").show();
    }

    // EOF
}
