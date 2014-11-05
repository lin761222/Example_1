package com.alex.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class TestListener extends Activity implements OnClickListener,
		OnLongClickListener, OnTouchListener {
	TextView txv,textView1;
	Button btn;
	static int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_listener);
		tool();
	}

	public void tool() {
		txv = (TextView) findViewById(R.id.txv_listener);
		btn = (Button) findViewById(R.id.btn_listner);
		btn.setOnClickListener(this);
		txv.setOnClickListener(this);
		btn.setOnLongClickListener(this);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView1.setOnTouchListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_listener, menu);
		return true;
	}

	public void MainActivity(View v) {
		finish();
	}

	@Override
	public void onClick(View v) {
		String str = (String) txv.getText();
		if (str == "0") {
			count = 0;
			txv.setText(String.valueOf(++count));
		} else {
			txv.setText(String.valueOf(++count));
		}
	}

	@Override
	public boolean onLongClick(View v) {
		txv.setText("0");
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent e) {
		// 取得震動物件
		Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			vb.vibrate(5000);
		} else if (e.getAction() == MotionEvent.ACTION_UP)
			;
		{
			vb.cancel();
		}
		return true;
	}

}
