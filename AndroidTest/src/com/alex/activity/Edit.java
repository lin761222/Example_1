package com.alex.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends Activity {
	TextView txv;
	EditText edt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		Intent it = getIntent();
		int no = it.getIntExtra("½s¸¹", 0);
		String ss = it.getStringExtra("³Æ§Ñ");
		txv = (TextView) findViewById(R.id.textView1);

		txv.setText(ss.substring(0, 2));
		edt = (EditText) findViewById(R.id.editText1);
		if (ss.length() > 3) {
			edt.setText(ss.substring(3));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	public void onCancel(View v) {
		setResult(RESULT_CANCELED);
		finish();
	}

	public void onSave(View v) {
		Intent it2 = new Intent();
		it2.putExtra("³Æ§Ñ", txv.getText() + " " + edt.getText());
		setResult(RESULT_OK, it2);
		finish();
	}
}
