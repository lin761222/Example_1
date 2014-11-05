package com.alex.activity;

import com.alex.copy.Copy1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	// Button TestProject, TestLayout, TestListener, BaseElement, TestCheckbox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int btn_id[] = { R.id.TestProject, R.id.TestLayout, R.id.TestListener,
				R.id.BaseElement, R.id.TestCheckbox, R.id.TestSpinner,
				R.id.TestListview, R.id.TestAdapter, R.id.TestToast,
				R.id.TestDialog, R.id.copy1, R.id.TestIntent, R.id.TestIntent2,
				R.id.WebView, R.id.TestLocation, R.id.HelloSQLite,
				R.id.TestSQLite };

		for (int b : btn_id) {
			Button btn = (Button) findViewById(b);
			btn.setOnClickListener(this);
		}
		// TestProject = (Button) findViewById(R.id.TestProject);
		// TestProject.setOnClickListener(this);
		// TestLayout = (Button) findViewById(R.id.TestLayout);
		// TestLayout.setOnClickListener(this);
		// TestListener = (Button) findViewById(R.id.TestListener);
		// TestListener.setOnClickListener(this);
		// BaseElement = (Button) findViewById(R.id.BaseElement);
		// BaseElement.setOnClickListener(this);
		// TestCheckbox = (Button) findViewById(R.id.TestCheckbox);
		// TestCheckbox.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
			new AlertDialog.Builder(this).setTitle("我的地圖").setMessage("體驗版")
					.setPositiveButton("關閉", null).show();
			break;
		case R.id.setGPS:
			Intent it = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(it);
			break;
		case R.id.finish:
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.TestProject:
			Intent it = new Intent(this, TestProject.class);
			startActivity(it);
			break;
		case R.id.TestLayout:
			it = new Intent(this, TestLayout.class);
			startActivity(it);
			break;
		case R.id.TestListener:
			it = new Intent(this, TestListener.class);
			startActivity(it);
			break;
		case R.id.BaseElement:
			it = new Intent(this, BaseElement.class);
			startActivity(it);
			break;
		case R.id.TestCheckbox:
			it = new Intent(this, TestCheckbox.class);
			startActivity(it);
			break;
		case R.id.TestSpinner:
			it = new Intent(this, TestSpinner.class);
			startActivity(it);
			break;
		case R.id.TestListview:
			it = new Intent(this, TestListView.class);
			startActivity(it);
			break;
		case R.id.TestAdapter:
			it = new Intent(this, TestAdapter.class);
			startActivity(it);
			break;
		case R.id.TestToast:
			it = new Intent(this, TestToast.class);
			startActivity(it);
			break;
		case R.id.TestDialog:
			it = new Intent(this, TestDialog.class);
			startActivity(it);
			break;
		case R.id.copy1:
			it = new Intent(this, Copy1.class);
			startActivity(it);
			break;
		case R.id.TestIntent:
			it = new Intent(this, TestIntent.class);
			startActivity(it);
			break;
		case R.id.TestIntent2:
			it = new Intent(this, TestIntent2.class);
			startActivity(it);
			break;
		case R.id.WebView:
			it = new Intent(this, WebViewh.class);
			startActivity(it);
			break;
		case R.id.TestLocation:
			it = new Intent(this, TestLocation.class);
			startActivity(it);
			break;
		case R.id.HelloSQLite:
			it = new Intent(this, HelloSQLite.class);
			startActivity(it);
			break;
		case R.id.TestSQLite:
			it = new Intent(this, TestSQLite.class);
			startActivity(it);
			break;
		}
	}

}
