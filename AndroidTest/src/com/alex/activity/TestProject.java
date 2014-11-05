package com.alex.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TestProject extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_project);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_project, menu);
		return true;
	}

	int size = 30;

	public void bigger(View v) {
		TextView txv = (TextView) findViewById(R.id.txv);
		txv.setTextSize(++size);
	}

	public void smaller(View v) {
		TextView txv = (TextView) findViewById(R.id.txv);
		txv.setTextSize(--size);
	}

	public void hello(View v) {
		TextView txv1 = (TextView) findViewById(R.id.txv1);
		EditText name = (EditText) findViewById(R.id.name);
		String str = name.getText().toString().trim();
		if (str.length() == 0) {
			txv1.setText("請輸入文字");
		} else
			txv1.setText(str + " ,您好!" + "\t" + str.length() + "個字");
	}

	public void MainActivity(View v) {
		finish();
	}

}
