package com.alex.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TestAdapter extends Activity implements OnItemSelectedListener {
	Spinner drink, temp;
	TextView txv;
	String[] tempSet1 = { "¦B", "¥h¦B", "·Å" };
	String[] tempSet2 = { "¦B", "¥h¦B" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_adapter);

		txv = (TextView) findViewById(R.id.order);
		temp = (Spinner) findViewById(R.id.temp);
		drink = (Spinner) findViewById(R.id.drink);
		drink.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_adapter, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		String[] tempSet;
		if (position == 3)
			tempSet = tempSet2;
		else
			tempSet = tempSet1;

		ArrayAdapter<String> tempAd = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, tempSet);
		tempAd.setDropDownViewResource(android.R.layout.simple_spinner_item);

		temp.setAdapter(tempAd);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	public void showOrder(View v) {
		String msg = drink.getSelectedItem() + ", " + temp.getSelectedItem();
		txv.setText(msg);
	}

}
