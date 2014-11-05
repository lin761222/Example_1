package com.alex.activity;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TestToast extends Activity implements OnItemClickListener {
	String[] queArr = { "�ƻ���û������W�H", "�ƻ�F��S�H�R�Y�H", "�ƻ�ʤ���Y�H", "�ƻ򥬤����_",
			"�ƻ򹫳̷R���b", "���ƻ򤣥Ǫk" };
	String[] ansArr = { "�y��", "��", "�̥�", "�r��", "���O�p", "����" };
	Toast tos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_toast);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, queArr);
		ListView lv = (ListView) findViewById(R.id.lv);
		tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_toast, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//��ܮɶ�
		if (position % 2 == 0) {
			tos.setDuration(Toast.LENGTH_SHORT);
		} else
			tos.setDuration(Toast.LENGTH_LONG);
		
		//��ܦ�m
		tos.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 50);
		tos.setText("����:" + ansArr[position]);
		tos.show();
	}

}
