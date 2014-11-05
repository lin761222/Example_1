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
		// 加入進度條
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.web_view);
		wv = (WebView) findViewById(R.id.wa);
		// 縮放功能
		wv.getSettings().setBuiltInZoomControls(true);
		// JS效果
		wv.getSettings().setJavaScriptEnabled(true);
		// 顯示縮放小工具
		wv.invokeZoomPicker();

		// 建立超連結
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

	// 攔截返回鍵
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
		editor.putString("關鍵字", query);
		editor.commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences myPref = getPreferences(MODE_PRIVATE);
		query = myPref.getString("關鍵字", "Taipei+101");
		if (wv.getUrl() == null)
			wv.loadUrl(baseURL + query);
	}
}
