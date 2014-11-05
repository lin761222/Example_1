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
			.setMessage("你喜歡Android手機嗎？")
			.setCancelable(true)
			.setTitle("Android問卷調查")
			.setIcon(android.R.drawable.ic_menu_edit)
			.setPositiveButton("喜歡",this)
			.setNegativeButton("討厭", this)
			.setNeutralButton("沒意見", null)
			.show();
		
//		AlertDialog.Builder bdr = 
//		new AlertDialog.Builder(this)
//			.setMessage("交談窗示範!\n"+"請按返回鍵")
//			.setTitle("歡迎光臨")
//			.setIcon(R.drawable.ic_launcher)
//			.setCancelable(true)
//			.setNegativeButton("不好", null)
//			.setPositiveButton("很好", null)
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
			txv.setText("你喜歡Android手機");
		}else if(id == DialogInterface.BUTTON_NEGATIVE)
			txv.setText("你討厭Android手機");
	}

}
