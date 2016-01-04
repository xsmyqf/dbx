package xie.last.dbx;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class dingzhidialog extends Dialog {
	Handler dialoghandler; 
	LinearLayout main;
	LinearLayout neirong;
	LinearLayout anniu;
	Context ben;
	Button a;
	Button b;
	public dingzhidialog(Activity context) {
		super(context);
		setOwnerActivity(context);
		main=new LinearLayout(context);
		ben=context;
		// TODO Auto-generated constructor stub
		ViewGroup.LayoutParams mainparam=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
		neirong=new LinearLayout(context);
		main.addView(neirong);
		setanniu();
		main.setOrientation(LinearLayout.VERTICAL);
		main.setGravity(Gravity.CENTER_HORIZONTAL);
	//	main.setDividerDrawable();
		main.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
	
		
		 setContentView(main,mainparam);
		 setCanceledOnTouchOutside(false);
		
	}
	/*void settitle(String a){
		frow=new TableRow(ben);
		TextView title=new TextView(ben);
		title.setText(a);
		frow.addView(title);
		main.addView(frow);
		 setanniu();
	}*/
	 void setview(View a){
		 
		 neirong.addView(a);
		 
		
	 }
	 void setanniu(){
		 anniu=new LinearLayout(ben);
		 a=new Button(ben);
		 a.setText("同意");
		 b=new Button(ben);
		 b.setText("拒绝");
		anniu.setOrientation(LinearLayout.HORIZONTAL);
	/*RelativeLayout.LayoutParams mainparam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mainparam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); 
		mainparam.addRule(RelativeLayout.CENTER_HORIZONTAL); */
		anniu.addView(a);
		anniu.addView(b); 
		
		main.addView(anniu);
	 }
	 void setshijian(Button.OnClickListener aclick,Button.OnClickListener bclick){
		 a.setOnClickListener(aclick);
		 b.setOnClickListener(bclick);
	 }
	 public void startshow(){
		 dialoghandler = new Handler() {  
	              public void handleMessage(Message mesg) {   
	                throw new RuntimeException();  
	              }  
	          };  
	        super.show();  
	        try {  
	            Looper.getMainLooper().loop();  
	        }catch(RuntimeException e2)  
	        {  
	        }  
	 }
	 public void stopshow(){
		 dismiss();
		 Message m=dialoghandler.obtainMessage();
		 dialoghandler.sendMessage(m);
	 }
	 public void removebutton(){
		 main.removeView(anniu);
	 }
	public void removejujuebutton(){
		anniu.removeView(b);
	}
}
