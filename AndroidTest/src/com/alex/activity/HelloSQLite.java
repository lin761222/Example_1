package com.alex.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class HelloSQLite extends Activity {

	// 資料庫名稱
	static final String db_name = "testDB";
	// 資料表名稱
	static final String tb_name = "test";
	// 資料庫物件
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello_sqlite);

		// 開啟或建立資料庫
		db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
		String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name
				+ "(name VARCHAR(32)," + "phone VARCHAR(16),"
				+ "email VARCHAR(64))";

		// 建立資料表
		db.execSQL(createTable);

//		addData("Alex", "0913", "aaa@");
//		addData("Jeff", "0933", "bbb@");

		TextView txvs = (TextView) findViewById(R.id.txvs);
		txvs.setText("資料庫檔路徑：" + db.getPath() + "\n" + "資料庫分頁大小："
				+ db.getPageSize() + " Bytes\n" + "資料量上限："
				+ db.getMaximumSize() + "Bytes\n");

		// 查詢函數
		rawQuery();

		db.close();
	}

	private void rawQuery() {
		Cursor c = db.rawQuery("SELECT * FROM " + tb_name, null);
		if (c.getCount() == 0) {
			addData("Alex", "0913", "aaa@");
			addData("Jeff", "0933", "bbb@");
			c = db.rawQuery("SELECT * FROM " + tb_name, null);
		}

		if (c.moveToFirst()) {
			String str = "總共有 " + c.getCount() + "筆資料\n";
			str += "---------\n";
			do {
				str += "name:" + c.getString(0) + "\n";
				str += "phone:" + c.getString(1) + "\n";
				str += "email:" + c.getString(2) + "\n";
				str += "---------\n";
			} while (c.moveToNext());

			TextView txvs2 = (TextView) findViewById(R.id.txvs2);
			txvs2.setText(str);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hello_sqlite, menu);
		return true;
	}

	private void addData(String name, String phone, String email) {
		// 建立三個資料項目的物件
		ContentValues cv = new ContentValues(3);

		cv.put("name", name);
		cv.put("phone", phone);
		cv.put("email", email);

		// 將資料加到資料表
		db.insert(tb_name, null, cv);
	}
}
