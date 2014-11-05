package com.alex.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TestCheckbox extends Activity implements OnCheckedChangeListener {

	ArrayList<CompoundButton> selected = new ArrayList<CompoundButton>();
	int visible;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_checkbox);
		int[] chk_id = { R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4, R.id.small };
		for (int id : chk_id) {
			CheckBox chk = (CheckBox) findViewById(id);
			chk.setOnCheckedChangeListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base_element2, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (buttonView.getId() == R.id.small) {
			if (isChecked) {
				((TextView) findViewById(R.id.txv)).setTextSize(15);
			} else
				((TextView) findViewById(R.id.txv)).setTextSize(25);
			return;
		}

		if (isChecked) {
			selected.add(buttonView);
			// visible = View.VISIBLE;
		} else {
			selected.remove(buttonView);
			// visible = View.GONE;
		}

	}

	public void show_order(View v) {
		String msg = "";

		for (CompoundButton chk : selected) {
			msg += "\n" + chk.getText();

			// 點第二次，圖片會有問題
			if (chk.isChecked()) {
				visible = View.VISIBLE;
			}else
				visible = View.GONE;
			
			switch (chk.getId()) {
			case R.id.chk1:
				findViewById(R.id.ImageView01).setVisibility(visible);
				break;
			case R.id.chk2:
				findViewById(R.id.ImageView02).setVisibility(visible);
				break;
			case R.id.chk3:
				findViewById(R.id.ImageView03).setVisibility(visible);
				break;
			case R.id.chk4:
				findViewById(R.id.ImageView04).setVisibility(visible);
				break;
			}
		}

		if (msg.length() > 0) {
			msg = "您訂購的是" + msg;
		} else
			msg = "請點餐";

		((TextView) findViewById(R.id.txv)).setText(msg);

	}
}
