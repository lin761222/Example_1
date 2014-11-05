package com.alex.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestIntent extends Activity implements OnItemClickListener,
		OnItemLongClickListener {
	String[] aMemo = { "1. ���@�U�i�H�s��Ƨ�", "2. �����i�H�M���Ƨ�", "3.", "4.", "5.", "6." };
	ListView lv;
	ArrayAdapter<String> aa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_intent);
		lv = (ListView) findViewById(R.id.listView1);
		aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, aMemo);

		lv.setAdapter(aa);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_intent, menu);
		return true;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int pos,
			long id) {
		aMemo[pos] = (pos + 1) + ".";
		// �q��ListView��s��ܤ��e
		aa.notifyDataSetChanged();
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent it = new Intent(this, Edit.class);
		it.putExtra("�s��", pos + 1);
		it.putExtra("�Ƨ�", aMemo[pos]);
		startActivityForResult(it,0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent it) {
		if (resultCode == RESULT_OK) {
			aMemo[requestCode] = it.getStringExtra("�Ƨ�");
			aa.notifyDataSetChanged();
		}

	}
}
