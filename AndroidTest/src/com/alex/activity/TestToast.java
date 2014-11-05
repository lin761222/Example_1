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
	String[] queArr = { "甚麼門永遠關不上？", "甚麼東西沒人愛吃？", "甚麼瓜不能吃？", "甚麼布切不斷",
			"甚麼鼠最愛乾淨", "偷甚麼不犯法" };
	String[] ansArr = { "球門", "虧", "傻瓜", "瀑布", "環保署", "偷笑" };
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
		//顯示時間
		if (position % 2 == 0) {
			tos.setDuration(Toast.LENGTH_SHORT);
		} else
			tos.setDuration(Toast.LENGTH_LONG);
		
		//顯示位置
		tos.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 50);
		tos.setText("答案:" + ansArr[position]);
		tos.show();
	}

}
