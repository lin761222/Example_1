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

	// ��m��s����G5000�@�� = 5��
	static final int MIN_TIME = 5000;
	// ��m��s����G5 ����
	static final float MIN_DIST = 5;
	// �w��޲z��
	LocationManager mgr;
	TextView txv;

	// �x�s�̪�q����
	Location myLocation;
	// �ΨӬd�ߦa�}��Geocoder����
	Geocoder geocoder;
	// �g�n�׿�J���
	EditText edtLat, edtLon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_location);

		txv = (TextView) findViewById(R.id.txv);
		mgr = (LocationManager) getSystemService(LOCATION_SERVICE);

		edtLat = (EditText) findViewById(R.id.edtLat);
		edtLon = (EditText) findViewById(R.id.edtLon);
		// �إ�Geocoder
		geocoder = new Geocoder(this, Locale.getDefault());
	}

	@Override
	protected void onResume() {
		super.onResume();
		// ���o�̨Ωw�촣�Ѫ�
		String best = mgr.getBestProvider(new Criteria(), true);
		if (best != null) {
			txv.setText("���o�w���T��...");
			// ���U��m�ƥ��ť��
			mgr.requestLocationUpdates(best, MIN_TIME, MIN_DIST, this);
		} else
			txv.setTag("�нT�{�w�}�ҩw��T��");
	}

	@Override
	protected void onPause() {
		super.onPause();
		// �������U��s�ƥ�
		mgr.removeUpdates(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_location, menu);
		return true;
	}

	// ��m��s
	@Override
	public void onLocationChanged(Location location) {
		// String str = "�w�촣�Ѫ̡G" + location.getProvider();
		// str += String.format("\n�n�סG%.5f\n�g�סG%.5f\n���סG%.2f����",
		// location.getLatitude(), location.getLongitude(),
		// location.getAltitude());
		// txv.setTag(str);

		myLocation = location;

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// �w�촣�Ѫ̳Q����
	}

	@Override
	public void onProviderEnabled(String provider) {
		// �w�촣�Ѫ̳Q�ҥ�
	}

	@Override
	public void onProviderDisabled(String provider) {
		// �w�촣�Ѫ̪��A����
	}

	public void setup(View v) {
		Intent it = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(it);
	}

	public void getLoaction(View v) {
		if (myLocation != null) {
			// �N�g���ন�r��
			edtLat.setText(Double.toString(myLocation.getLatitude()));
			// �N�n���ন�r��
			edtLon.setText(Double.toString(myLocation.getLongitude()));
		} else
			txv.setTag("�L�k���o�w����");
	}

	public void onQuery(View v) {
		// ����J���n�צr��
		String srtLat = edtLat.getText().toString();
		// ����J���g�צr��
		String strLon = edtLon.getText().toString();

		if (srtLat.length() == 0 || strLon.length() == 0)
			return;
		txv.setText("Ū����");
		// ���o�n�׭�
		double latitude = Double.parseDouble(srtLat);
		// ���o�g�׭�
		double longitude = Double.parseDouble(strLon);

		// �Ψӫإ����_��ܪ��T���r��
		String strAddr = "";
		try {
			// �θg�n�׬d�a�}�A�u�ݶǦ^�@�����
			List<Address> listAddr = geocoder.getFromLocation(latitude,
					longitude, 1);

			// �ˬd�O�_�����o�a�}
			if (listAddr == null || listAddr.size() == 0)
				strAddr += "�L�k���o�a�}";
			else {
				// ��List�����Ĥ@��(�]�O�ߤ@���@��)
				Address addr = listAddr.get(0);
				for (int i = 0; i <= addr.getMaxAddressLineIndex(); i++)
					strAddr += addr.getAddressLine(i) + "\n";
			}

		} catch (Exception e) {
			strAddr += "���o�a�}�o�Ϳ��~�G" + e.toString();
		}
		txv.setText(strAddr);
	}
}
