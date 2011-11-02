package com.dearing.remote;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class RomsN64 extends ListActivity {

    MyApp appState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appState = ((MyApp) getApplicationContext());
        String[] x = appState.RequestFiles("files_n64");
        if (x != null)
            setListAdapter(new ArrayAdapter<String>(this, R.layout.roms_n64, x));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                appState.SendPayload("emu_n64+" + ((TextView) view).getText());
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
