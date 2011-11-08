package com.dearing.remote;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class RomsGBA extends ListActivity {
   MyApp appState;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appState = ((MyApp) getApplicationContext());
        String[] x = appState.RequestFiles("files_gba");
        if (x != null)
            setListAdapter(new ArrayAdapter<String>(this, R.layout.roms_gba, x));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                appState.SendPayload("emu_gba+" + ((TextView) view).getText());
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}