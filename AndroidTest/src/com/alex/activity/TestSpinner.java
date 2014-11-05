package com.alex.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TestSpinner extends Activity implements OnItemSelectedListener {
	TextView txv, total, txvRate;
	EditText weight, timeSpan;
	Spinner cinema, sports;
	double[] energyRate = { 3.1, 4.4, 13.2, 9.7, 5.1 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_spinner);

		txv = (TextView) findViewById(R.id.txv);
		cinema = (Spinner) findViewById(R.id.cinemas);
		total = (TextView) findViewById(R.id.total);
		txvRate = (TextView) findViewById(R.id.txvRate);
		weight = (EditText) findViewById(R.id.weight);
		timeSpan = (EditText) findViewById(R.id.timeSpan);
		sports = (Spinner) findViewById(R.id.sports);
		sports.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_spinner, menu);
		return true;
	}

	public void order(View v) {
		String[] cinemas = getResources().getStringArray(R.array.cinemas);
		int index = cinema.getSelectedItemPosition();
		txv.setText("訂 " + cinemas[index] + " 的票");
	}

	public void calc(View v) {
		String w1 = weight.getText().toString();
		String t1 = timeSpan.getText().toString();
		if (w1.length() == 0 | t1.length() == 0) {
			System.out.println("111");
		} else {
			double w = Double.valueOf(w1);
			double t = Double.valueOf(t1);

			int selected = sports.getSelectedItemPosition();

			long sum = Math.round(energyRate[selected] * w * t);
			total.setText(String.format("消耗能量\n %d千卡", sum));
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		txvRate.setText(String.valueOf(energyRate[position]));

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}
