package com.alex.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TestSQLite extends Activity implements AdapterView.OnItemClickListener {
	// 資料庫名稱
	static final String DB_NAME = "HotlineDB";
	// 資料表名稱
	static final String TB_NAME = "hotline";
	// 筆數上限
	static final int MAX = 8;
	// 資料表欄位名稱字串陣列
	static final String[] FROM = new String[] { "name", "phone", "email" };
	SQLiteDatabase db;
	Cursor cur;

	SimpleCursorAdapter adapter;
	EditText etName, etPhone, etEmail;
	Button btInsert, btUpdate, btDelete;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_sqlite);
		os();
		db();
		sAdapter();
	}

	private void os() {
		etName = (EditText) findViewById(R.id.etName);
		etPhone = (EditText) findViewById(R.id.etPhone);
		etEmail = (EditText) findViewById(R.id.etEmail);
		btInsert = (Button) findViewById(R.id.btInsert);
		btUpdate = (Button) findViewById(R.id.btUpdate);
		btDelete = (Button) findViewById(R.id.btDelete);
	}

	private void db() {
		db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		String createTable = "CREATE TABLE IF NOT EXISTS " + TB_NAME
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name VARCHAR(32)," + "phone VARCHER(16),"
				+ "email VARCHER(64))";

		db.execSQL(createTable);

		cur = db.rawQuery("SELECT * FROM " + TB_NAME, null);

		if (cur.getCount() == 0) {
			addData("xx1", "0201", "xx1@xx.tw");
			addData("xx2", "0302", "xx2@xx.tw");
		}
	}

	private void sAdapter() {
		int aa[] = new int[] { R.id.name, R.id.phone, R.id.email };
		adapter = new SimpleCursorAdapter(this, R.layout.test_sqlite_item, cur,
				FROM, aa, 0);
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		requery();

	}

	private void addData(String name, String phone, String email) {
		ContentValues cv = new ContentValues(3);
		cv.put(FROM[0], name);
		cv.put(FROM[1], phone);
		cv.put(FROM[2], email);
		db.insert(TB_NAME, null, cv);

	}

	private void update(String name, String phone, String email, int id) {
		ContentValues cv = new ContentValues(3);
		cv.put(FROM[0], name);
		cv.put(FROM[1], phone);
		cv.put(FROM[2], email);
		db.update(TB_NAME, cv, "_id=" + id, null);
	}

	// 重新查詢的自訂方法
	private void requery() {
		cur = db.rawQuery("SELECT * FROM " + TB_NAME, null);
		// 更改Adapter的Cursor
		adapter.changeCursor(cur);
		if (cur.getCount() == MAX)
			btInsert.setEnabled(false);
		else
			btInsert.setEnabled(true);

		btUpdate.setEnabled(false);
		btDelete.setEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_sqlite, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 移動Cursor至使用者選取的項目
		cur.moveToPosition(position);
		// 讀出姓名，電話，EMmail
		etName.setText(cur.getString(cur.getColumnIndex(FROM[0])));
		etPhone.setText(cur.getString(cur.getColumnIndex(FROM[1])));
		etEmail.setText(cur.getString(cur.getColumnIndex(FROM[2])));
		btUpdate.setEnabled(true);
		btDelete.setEnabled(true);
	}

	// 新增按鈕
	public void InsertOrUpdate(View v) {
		String nameStr = etName.getText().toString().trim();
		String phoneStr = etPhone.getText().toString().trim();
		String emailStr = etEmail.getText().toString().trim();
		if (nameStr.length() == 0 || phoneStr.length() == 0
				|| emailStr.length() == 0)
			return;

		if (v.getId() == R.id.btUpdate)
			update(nameStr, phoneStr, emailStr, cur.getInt(0));
		else
			addData(nameStr, phoneStr, emailStr);
		requery();
	}

	// 刪除按鈕
	public void delete(View v) {
		db.delete(TB_NAME, "_id=" + cur.getInt(0), null);
		requery();
	}

	// 電話按鈕
	public void call(View v) {
		String uri = "tel:" + cur.getString(cur.getColumnIndex(FROM[1]));
		Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		startActivity(it);
	}

	// 郵件按鈕
	public void mail(View v) {
		String uri = "mailto:" + cur.getString(cur.getColumnIndex(FROM[2]));
		Intent it = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
		startActivity(it);
	}

}
