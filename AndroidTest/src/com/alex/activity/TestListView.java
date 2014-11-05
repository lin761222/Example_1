package com.alex.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TestListView extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_list_view);
		ListView lv = (ListView)findViewById(R.id.listView1);
		lv.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_list_view, menu);
		return true;
	}

	ArrayList<String> selected = new ArrayList<String>();

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView txv = (TextView) view;
		String item = txv.getText().toString();
		if (selected.contains(item))
			selected.remove(item);
		else
			selected.add(item);

		String msg = "";
		if (selected.size() > 0) {
			msg = "你點了";
			for (String str : selected)
				msg += " " + str;
		} else
			msg = "請點餐!";
		TextView msgTxv = (TextView) findViewById(R.id.msgTxv);
		msgTxv.setText(msg);

	}
}
