package com.alex.activity;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TestLocation extends Activity implements LocationListener {

	// 位置更新條件：5000毫秒 = 5秒
	static final int MIN_TIME = 5000;
	// 位置更新條件：5 公尺
	static final float MIN_DIST = 5;
	// 定位管理員
	LocationManager mgr;
	TextView txv;

	// 儲存最近訂位資料
	Location myLocation;
	// 用來查詢地址的Geocoder物件
	Geocoder geocoder;
	// 經緯度輸入欄位
	EditText edtLat, edtLon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_location);

		txv = (TextView) findViewById(R.id.txv);
		mgr = (LocationManager) getSystemService(LOCATION_SERVICE);

		edtLat = (EditText) findViewById(R.id.edtLat);
		edtLon = (EditText) findViewById(R.id.edtLon);
		// 建立Geocoder
		geocoder = new Geocoder(this, Locale.getDefault());
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 取得最佳定位提供者
		String best = mgr.getBestProvider(new Criteria(), true);
		if (best != null) {
			txv.setText("取得定位資訊中...");
			// 註冊位置事件監聽器
			mgr.requestLocationUpdates(best, MIN_TIME, MIN_DIST, this);
		} else
			txv.setTag("請確認已開啟定位訊息");
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 取消註冊更新事件
		mgr.removeUpdates(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_location, menu);
		return true;
	}

	// 位置更新
	@Override
	public void onLocationChanged(Location location) {
		// String str = "定位提供者：" + location.getProvider();
		// str += String.format("\n緯度：%.5f\n經度：%.5f\n高度：%.2f公尺",
		// location.getLatitude(), location.getLongitude(),
		// location.getAltitude());
		// txv.setTag(str);

		myLocation = location;

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// 定位提供者被停用
	}

	@Override
	public void onProviderEnabled(String provider) {
		// 定位提供者被啟用
	}

	@Override
	public void onProviderDisabled(String provider) {
		// 定位提供者狀態改變
	}

	public void setup(View v) {
		Intent it = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(it);
	}

	public void getLoaction(View v) {
		if (myLocation != null) {
			// 將經度轉成字串
			edtLat.setText(Double.toString(myLocation.getLatitude()));
			// 將緯度轉成字串
			edtLon.setText(Double.toString(myLocation.getLongitude()));
		} else
			txv.setTag("無法取得定位資料");
	}

	public void onQuery(View v) {
		// 取輸入的緯度字串
		String srtLat = edtLat.getText().toString();
		// 取輸入的經度字串
		String strLon = edtLon.getText().toString();

		if (srtLat.length() == 0 || strLon.length() == 0)
			return;
		txv.setText("讀取中");
		// 取得緯度值
		double latitude = Double.parseDouble(srtLat);
		// 取得經度值
		double longitude = Double.parseDouble(strLon);

		// 用來建立鎖鑰顯示的訊息字串
		String strAddr = "";
		try {
			// 用經緯度查地址，只需傳回一筆資料
			List<Address> listAddr = geocoder.getFromLocation(latitude,
					longitude, 1);

			// 檢查是否有取得地址
			if (listAddr == null || listAddr.size() == 0)
				strAddr += "無法取得地址";
			else {
				// 取List中的第一筆(也是唯一的一筆)
				Address addr = listAddr.get(0);
				for (int i = 0; i <= addr.getMaxAddressLineIndex(); i++)
					strAddr += addr.getAddressLine(i) + "\n";
			}

		} catch (Exception e) {
			strAddr += "取得地址發生錯誤：" + e.toString();
		}
		txv.setText(strAddr);
	}
}
