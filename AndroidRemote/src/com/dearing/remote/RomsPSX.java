package com.dearing.remote;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RomsPSX extends ListActivity {
	MyApp appState;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appState = ((MyApp) getApplicationContext());
		String[] x = appState.RequestFiles("files_psx");
		if (x != null)
			setListAdapter(new ArrayAdapter<String>(this, R.layout.roms_psx, x));
		else {
			Toast.makeText(getApplicationContext(), "File list was null.",
					Toast.LENGTH_SHORT).show();
			finish();
		}

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				appState.SendPayload("emu_psx+" + ((TextView) view).getText());
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}
	// EOF
}