package com.alex.activity;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TestLayout extends Activity {

	EditText name1, name2, phone;
	TextView txv01, txvR, txvG, txvB;
	Button btn;
	View colorBlock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_layout);
		tool();
	}

	public void tool() {
		name1 = (EditText) findViewById(R.id.name1);
		name2 = (EditText) findViewById(R.id.name2);
		phone = (EditText) findViewById(R.id.phone);
		txv01 = (TextView) findViewById(R.id.txv01);
		txvR = (TextView) findViewById(R.id.txvR);
		txvG = (TextView) findViewById(R.id.txvG);
		txvB = (TextView) findViewById(R.id.txvB);
		colorBlock = findViewById(R.id.colorBlock);
		btn = (Button) findViewById(R.id.button2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_layout, menu);
		return true;
	}

	public void MainActivity(View v) {
		finish();
	}

	public void onclick(View v) {
		String str = name1.getText().toString().trim();
		String str1 = name2.getText().toString().trim();
		if (str.length() == 0) {
			txv01.setText("請輸入姓氏");
			txv01.setTextColor(Color.rgb(217, 0, 0));
		} else if (str1.length() == 0) {
			txv01.setText("請輸入名稱");
			txv01.setTextColor(Color.rgb(218, 0, 0));
		} else {
			txv01.setText(name1.getText().toString() + name2.getText()
					+ "的電話是 " + phone.getText());
		}
	}

	public void changeColor(View v) {
		Random x = new Random();
		int red = x.nextInt(256);
		txvR.setText("紅：" + red);
		txvR.setTextColor(Color.rgb(red, 0, 0));
		int green = x.nextInt(256);
		txvG.setText("綠：" + green);
		txvG.setTextColor(Color.rgb(0, green, 0));
		int blue = x.nextInt(256);
		txvB.setText("藍：" + blue);
		txvB.setTextColor(Color.rgb(0, 0, blue));
		btn.setTextColor(Color.rgb(red, green, blue));
		colorBlock.setBackgroundColor(Color.rgb(red, green, blue));
	}

}
