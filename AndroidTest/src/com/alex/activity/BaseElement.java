package com.alex.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class BaseElement extends Activity implements OnCheckedChangeListener,
		TextWatcher {
	RadioGroup unit;
	EditText value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_element);
		tool();
		unit.setOnCheckedChangeListener(this);
		value.addTextChangedListener(this);
		RadioGroup r1 = (RadioGroup)findViewById(R.id.radioGroup1);
		r1.setOnCheckedChangeListener(this);
		
	}

	public void tool() {
		unit = (RadioGroup) findViewById(R.id.unit);
		value = (EditText) findViewById(R.id.value);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base_element, menu);
		return true;
	}

	public void show(View v) {
		TextView show_baseElement = (TextView) findViewById(R.id.show_baseElement);
		RadioGroup show1 = (RadioGroup) findViewById(R.id.radioGroup1);
		RadioGroup show2 = (RadioGroup) findViewById(R.id.radioGroup2);
		String str = "您購買的是 ";

		int id;
		id = show1.getCheckedRadioButtonId();
		RadioButton type = (RadioButton) findViewById(id);
		id = show2.getCheckedRadioButtonId();
		RadioButton number = (RadioButton) findViewById(id);
		show_baseElement.setText(str + " " + type.getText() + " "
				+ number.getText());
		// switch(show.getCheckedRadioButtonId()){
		// case R.id.adult:
		// show_baseElement.setText(str + "全票");
		// break;
		// case R.id.child:
		// show_baseElement.setText(str + "半票");
		// break;
		// case R.id.senior:
		// show_baseElement.setText(str + "敬老票");
		// break;
		// }
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTextChanged(Editable s) {
		calc();
	}

	private void calc() {
		TextView degF = (TextView) findViewById(R.id.degF);
		TextView degC = (TextView) findViewById(R.id.degC);
		double f = 0, c = 0;
		if (unit.getCheckedRadioButtonId() == R.id.unitF) {

			if (value.length() > 0) {
				System.out.println("333333");
				f = Double.parseDouble(value.getText().toString());
				c = (f - 32) * 5 / 9;
			} else {
				System.out.println("111111");
			}
		} else {
			if (value.length() > 0) {
				System.out.println("6");
				c = Double.parseDouble(value.getText().toString());
				f = c * 9 / 5 + 32;
			} else {
				System.out.println("222222");
			}
		}
		degC.setText(String.format("%.1f", c)
				+ getResources().getString(R.string.charC));
		degF.setText(String.format("%.1f", f)
				+ getResources().getString(R.string.charF));
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(group.getId()==R.id.radioGroup1){
			RadioButton g = (RadioButton)findViewById(checkedId);
			g.getText();
			System.out.println(g.getText());
		}

	}
}
