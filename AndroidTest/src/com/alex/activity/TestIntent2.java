package com.alex.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class TestIntent2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_intent2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_intent2, menu);
		return true;
	}
	
	//���q��
	public void onClick1(View v) {
		Intent it = new Intent();
		it.setAction(Intent.ACTION_VIEW);
		it.setData(Uri.parse("tel:800"));
		startActivity(it);
	}

	//����ܸ��X���q��
	public void onClick2(View v) {
		Intent it = new Intent();
		it.setAction(Intent.ACTION_CALL);
		it.setData(Uri.parse("tel:800"));
		startActivity(it);
	}

	public void onClick3(View v) {
		Intent it = new Intent(Intent.ACTION_VIEW);
		switch (v.getId()) {
		case R.id.btnEmail:
			it.setData(Uri.parse("mailto:alex.chen1222@gmail.com"));
			//�ƥ�
			it.putExtra(Intent.EXTRA_CC, new String[] { "test@flag.com" });
			// �D��
			it.putExtra(Intent.EXTRA_SUBJECT, "��Ƥw����");
			// ����
			it.putExtra(Intent.EXTRA_TEXT, "�z�n,\n�w����,����");
			break;
		case R.id.btnSms:
			it.setData(Uri.parse("sms:0913922152?body=�z�n!"));
			break;
		case R.id.btnWeb:
			it.setData(Uri.parse("http://www.google.com"));
			break;
		case R.id.btnGps:
			it.setData(Uri.parse("geo:25.047095,121.517308"));
			break;
		case R.id.btnWebSearch:
			// �ʧ@�אּ�j�M
			it.setAction(Intent.ACTION_WEB_SEARCH);
			it.putExtra(SearchManager.QUERY, "�X�ХX��");
			break;
		case R.id.btnSearch:
			it.setAction(Intent.ACTION_SEARCH);
			it.putExtra(SearchManager.QUERY, "�j�w�˪L����");
			break;
		}
		startActivity(it);
	}

}
