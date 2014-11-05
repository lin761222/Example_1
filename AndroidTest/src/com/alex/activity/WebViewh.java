package com.alex.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class WebViewh extends Activity {
	WebView wv;
	String query = "";
	String baseURL = // "http://www.google.com.tw";
	"http://m.flickr.com/#/search/advanced/_QM_q_IS_";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �[�J�i�ױ�
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.web_view);
		wv = (WebView) findViewById(R.id.wa);
		// �Y��\��
		wv.getSettings().setBuiltInZoomControls(true);
		// JS�ĪG
		wv.getSettings().setJavaScriptEnabled(true);
		// ����Y��p�u��
		wv.invokeZoomPicker();

		// �إ߶W�s��
		wv.setWebViewClient(new WebViewClient());
		wv.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				setProgress(progress * 100);
			}
		});

		// wv.loadUrl("http://www.google.com.tw");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_view, menu);
		return true;
	}

	// �d�I��^��
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {
			wv.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void search(View v) {
		EditText keyText = (EditText) findViewById(R.id.keywords);
		String[] keywords = keyText.getText().toString().split("\\s+");

		query = "";
		for (String s : keywords)
			query += s + "+";

		query = query.substring(0, query.length() - 1);

		// if (URLUtil.isHttpsUrl(baseURL + query))
		wv.loadUrl(baseURL + query);

	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
		editor.putString("����r", query);
		editor.commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences myPref = getPreferences(MODE_PRIVATE);
		query = myPref.getString("����r", "Taipei+101");
		if (wv.getUrl() == null)
			wv.loadUrl(baseURL + query);
	}
}
