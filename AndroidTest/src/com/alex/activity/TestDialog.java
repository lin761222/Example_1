package com.alex.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class TestDialog extends Activity implements DialogInterface.OnClickListener
	{
	TextView txv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_dialog);
		txv = (TextView)findViewById(R.id.ans);
		
		new AlertDialog.Builder(this)
			.setMessage("�A���wAndroid����ܡH")
			.setCancelable(true)
			.setTitle("Android�ݨ��լd")
			.setIcon(android.R.drawable.ic_menu_edit)
			.setPositiveButton("���w",this)
			.setNegativeButton("�Q��", this)
			.setNeutralButton("�S�N��", null)
			.show();
		
//		AlertDialog.Builder bdr = 
//		new AlertDialog.Builder(this)
//			.setMessage("��͵��ܽd!\n"+"�Ы���^��")
//			.setTitle("�w����{")
//			.setIcon(R.drawable.ic_launcher)
//			.setCancelable(true)
//			.setNegativeButton("���n", null)
//			.setPositiveButton("�ܦn", null)
//			.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_dialog, menu);
		return true;
	}

	@Override
	public void onClick(DialogInterface dialog, int id) {
		if(id == DialogInterface.BUTTON_POSITIVE){
			txv.setText("�A���wAndroid���");
		}else if(id == DialogInterface.BUTTON_NEGATIVE)
			txv.setText("�A�Q��Android���");
	}

}
