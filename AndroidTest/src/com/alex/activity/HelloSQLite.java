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

	// ��Ʈw�W��
	static final String db_name = "testDB";
	// ��ƪ�W��
	static final String tb_name = "test";
	// ��Ʈw����
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello_sqlite);

		// �}�ҩΫإ߸�Ʈw
		db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
		String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name
				+ "(name VARCHAR(32)," + "phone VARCHAR(16),"
				+ "email VARCHAR(64))";

		// �إ߸�ƪ�
		db.execSQL(createTable);

//		addData("Alex", "0913", "aaa@");
//		addData("Jeff", "0933", "bbb@");

		TextView txvs = (TextView) findViewById(R.id.txvs);
		txvs.setText("��Ʈw�ɸ��|�G" + db.getPath() + "\n" + "��Ʈw�����j�p�G"
				+ db.getPageSize() + " Bytes\n" + "��ƶq�W���G"
				+ db.getMaximumSize() + "Bytes\n");

		// �d�ߨ��
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
			String str = "�`�@�� " + c.getCount() + "�����\n";
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
		// �إߤT�Ӹ�ƶ��ت�����
		ContentValues cv = new ContentValues(3);

		cv.put("name", name);
		cv.put("phone", phone);
		cv.put("email", email);

		// �N��ƥ[���ƪ�
		db.insert(tb_name, null, cv);
	}
}
