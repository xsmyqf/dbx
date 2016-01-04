package xie.last.dbx;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


public class Welcome extends Activity {
	RelativeLayout welcome;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		welcome=new RelativeLayout(this);
		TableRow frow=new TableRow(this);
		TableRow srow=new TableRow(this);
		Button lianxi=new Button(this);
		lianxi.setText("练习模式");
		lianxi.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openlianxi=new Intent();
				openlianxi.setClass(Welcome.this,First.class);
				startActivity(openlianxi);

			}});
		
		
		Button pk=new Button(this);
		pk.setText("PK模式");
		pk.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openpk=new Intent();
				openpk.setClass(Welcome.this,Pkmodel.class);
				startActivity(openpk);
			}});

		frow.addView(lianxi);
		srow.addView(pk);
		TableLayout zhongjiananniu=new TableLayout(this);
		zhongjiananniu.addView(frow);
		zhongjiananniu.addView(srow);
		RelativeLayout.LayoutParams centerparam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		centerparam.addRule(RelativeLayout.CENTER_IN_PARENT);
		welcome.addView(zhongjiananniu,centerparam);

		setContentView(welcome);
	}
}
