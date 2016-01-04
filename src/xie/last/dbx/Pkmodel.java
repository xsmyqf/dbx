package xie.last.dbx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;













import java.util.regex.Pattern;







import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputFilter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.StrictMode;


public class Pkmodel extends Activity {
	static int DUANKOUDAN=4000;
	static int DUANKOUDUO=3666;
	static int BUFMAX=100;
	//RelativeLayout main;
	int stage=0;//stage :1 刚进入let‘s pk的界面;2 ready 界面 3开始游戏但没完成 4 完成游戏等待结果 5 显示游戏结果
	 InetAddress bendiip;
	 InetAddress duifangdanip;
	 InetAddress dailiip;
	 InetAddress xiaoxiduiyingip;
	 InetAddress serverip;
	 InetAddress zanshiip;
	 InetAddress guangbofa=null;
	 
	 
	 InetSocketAddress danfa;
	DatagramSocket danfaserver;
	  
	serverdanfa danfaserverxian;
	EditText shuru1,shuru2;
	grouplistadapter adapter=null;
	grouplistadapter2 adapter2=null;
	ListView listview=null;
	List<totalthing> list=null;
	List<String> listtag=null;
	List<totalthing> listtidai=null;
	grouplistadapter3 adapter3=null;
	ListView oncreatelist=null;
	List<totalthing>listzhuji=null;
	int isserver=0;
	int daijiaru=0;
	zongtijianting zongti;

	List<duiliejiegou> duilie=null;
	//和界面有关变量定义开始
	 	static int MAXNUM=5;
		final static int ANDIS=100;
		final static int ANNIUDIS=60;
		static int flagxianshi=1;
		static int MAXSHUSHANG=100;
		final static double PI=3.1415926;
		static double chushir;
		static double centerx;
		static double centery;
		mainsuan  zhuchengxu;
		RelativeLayout mainbuju;
//	Button ready=null;
//	Button restart=null;
	CheckBox xianflag;
	textshu buttongroup[];
	boardertext viewgroup[];
	chuli2 jieguo2[][];
	int totali=2;
	int flag[];
	int readyflag=0;
	meicihua2 meici;
	RelativeLayout.LayoutParams textparam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
	
	
	String timestring;int inttime=0;TextView xianshitime;//秒表相关变量
	Boolean timestop=false;
	int temptime;int stoptime;
	timethread timer;
	huoquzhujiip huoquzhu;
	 private GestureDetector gestureDetector=null;
	//和界面有关变量定义结束
	
	//多人游戏有关变量定义开始
	 int totalplayers=0;
	 int finishtotalplayers=0;
	 dingzhidialog dengdai=null;
	 int chongxinshuaxin=0;
	 //多人游戏有关变量定义结束
	
	 //获取ip有关变量定义开始
	 int jisuanjiange=0;
	 int isap=-1;
	 //获取ip有关变量定义结束
	
	 void benjiserverqidong(){		//服务器应该显示一个列表,这个表记录了该游戏过程中的每个人所用的所有东西，并且是按3秒更新的
		byte bufluyou[];
		bufluyou=new byte[BUFMAX];
		try {
			bufluyou=new String("1;").getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		sendguangfa(bufluyou);		//发出向各个客户邀请
		isserver=1;
	//	serverip=bendiip;
	//	ready();//服务器显示就绪页面
	//	addjiaru();//最后测试一下自己给自己发送包有没有问题。
	}
	//画图有关开始
	
	void getcenter(){
		int width;
		int height;
		timestring=new String();
			DisplayMetrics metric = new DisplayMetrics();
		 	getWindowManager().getDefaultDisplay().getMetrics(metric);
	        width = metric.widthPixels;  // 屏幕宽度（像素）
	        height = metric.heightPixels;  // 屏幕高度（像素）
	  
	        mainbuju=new RelativeLayout(this);
	           
	        
	        centerx=width/2;
	        centery=height/2;
	      chushir=centery*0.5;
	}
	point zhuanxi(double jiaodu,double r,int anniudis)
	{
		
		point a=new point();
		a.x=centerx+r*Math.cos(jiaodu);
		a.x=a.x-anniudis/2;
		a.y=centery+r*Math.sin(jiaodu);
		a.y=a.y-anniudis/2;
		return a;
	}
	public boolean onTouchEvent(MotionEvent event) {    
        return gestureDetector.onTouchEvent(event);  
    }
	
	  public class MyGestureListener extends SimpleOnGestureListener{
		  public MyGestureListener(Context context) {
			  
			  // TODO Auto-generated constructor stub
			  //mContext=context;
			 }
		 
		  public boolean onDoubleTap(MotionEvent e)
		  {
			 // timer.timehandle.sendEmptyMessageDelayed(2,1);
			  //xianshitime.setText("");
			  //mainbuju.removeAllViews();
			  if(stage==3){
			  int a;
		  	totali=2;
			  for(a=0;a<MAXNUM;a++)flag[a]=0;
			  chushihuatu();
			
			 
			  }else if(stage==4){
         		 new AlertDialog.Builder(Pkmodel.this)
             	 .setTitle("您已完成这局！")
         		 .setPositiveButton("确定",null)
         		 	.show();

			  }
			return true;
			  
		  }
	  }
	
	
	void chushihuatu(){
		int i=1;
		int j;
		
		double r;
		 point linshi=new point();
		 getcenter();
		 if(i==1){
			 meici=new meicihua2(this);  	
			 mainbuju.addView(meici);
			 meici.setcan(i,(float)(zhuchengxu.jieguo[i][1].shutheata/(2*PI)*360),(float)((zhuchengxu.jieguo[i][Pkmodel.MAXNUM-i+1].shutheata-zhuchengxu.jieguo[i][1].shutheata)/(2*PI)*360),1);
			 		
		        textparam.addRule(RelativeLayout.CENTER_HORIZONTAL); 
		        textparam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		    
		        xianshitime=new TextView(this);
		        mainbuju.addView(xianshitime,textparam);
		        //xianshitime.setText("hello world!");
			 //按钮插入结束
		       
			 setContentView(mainbuju);
			}
		  
			r=chushir-chushir/(MAXNUM-1)*(i-1);
			 for(j=1;j<=MAXNUM-i+1;j++)
		        {
				 	  linshi=zhuanxi(zhuchengxu.jieguo[i][j].shutheata,r,ANDIS);
				 	  buttongroup[j]=new textshu(this);
		    	      buttongroup[j].setText(Integer.toString((int)zhuchengxu.jieguo[i][j].shu));//
		    	      buttongroup[j].setX((float)linshi.x);
		    	      buttongroup[j].setY((float)linshi.y);
		    	    	//buttongroup[j].setVisibility(1);
		    	      buttongroup[j].setWidth(ANDIS);
		    	      buttongroup[j].setHeight(ANDIS);
		    	      buttongroup[j].setGravity(Gravity.CENTER); 	
		    	      mainbuju.addView(buttongroup[j]);
		            
		           
		            if(j!=MAXNUM-i+1){
		            	linshi=zhuanxi(zhuchengxu.jieguo[i][j].futheata,r,ANNIUDIS);
			        	viewgroup[j]=new boardertext(this);
			       	viewgroup[j].setText(String.valueOf(zhuchengxu.jieguo[i][j].fu));
			    		viewgroup[j].setX((float)linshi.x);
			     		viewgroup[j].setY((float)linshi.y);
			     		viewgroup[j].setWidth(ANNIUDIS);
			     		viewgroup[j].setHeight(ANNIUDIS);
			     		viewgroup[j].setGravity(Gravity.CENTER); 	
			     		mainbuju.addView(viewgroup[j]);
			     		if(i==1)viewgroup[j].setOnTouchListener(new usernext(i,j));
		                }
		            
		        }
	}
	
	 class usernext implements OnTouchListener
	  {int a,dijii;
		 usernext(int b,int a){
		  this.a=a;
		  dijii=b;
		 }
		  public boolean onTouch (View v, MotionEvent event)
		  {
			 if(flag[dijii]==1){return true;}
			 if(totali<=MAXNUM){ 
			  if(event.getAction() == MotionEvent.ACTION_UP)
					  {
				  //mainbuju.removeAllViews();
				 // mainbuju.invalidate();
				  usernextf(totali,a);
				  flag[totali-1]=1;
				  totali++;
					  }
			 }
			  return true;
			 
		  }
	  }
	 String timezhuan(int time){
		 int firstdeal,seconddeal,firstdealyu,seconddealyu;
     	firstdeal=time/10;
     	firstdealyu=time%10;
     	seconddeal=firstdeal/60;
     	seconddealyu=firstdeal%60;
     	timestring=String.valueOf(seconddeal)+"分:     "+String.valueOf(seconddealyu)+"."+String.valueOf(firstdealyu)+"秒";
     	return timestring;
	 }
	 class timethread extends Thread
	  	{Handler timehandle=new Handler(){  				
		        public void handleMessage(android.os.Message msg) {  
 		        	switch(msg.what)
 		        	{
 		        	case 1:
 		        	 	if(!timestop){
 			            	if(inttime<12000){
 			                	inttime++;
 			                	zhutimehandle.sendEmptyMessage(inttime);
 			              
 			                timehandle.sendEmptyMessageDelayed(1, 100);  
 			                break;
 			            	}else{
 			            		inttime=0;
 			            		 new AlertDialog.Builder(Pkmodel.this)
 	                        	 .setTitle("打盹了吧！")
 	                    		 .setPositiveButton("确定",null)
 	                    		 	.show();
 			            		
 			            		 mainbuju.removeAllViews();
 			   				  int a;
 			   			  	totali=2;
 			   				  for(a=0;a<MAXNUM;a++)flag[a]=0;
 			   				  chushihuatu();
 			            		 break;
 			            	}
 			            	}else break;
 			            case 2:
 			            	stoptime=inttime;
 			            	 inttime=0;
 			          		timestop=true;
 			            	break;
 			            default: break;  
 			            }
 		        	}
 		        
  			 };
	    	private Looper neilooper;
	  		public void run(){
	  			Looper.prepare();
	  			neilooper=Looper.myLooper();  			
	  	
	  			
				Looper.loop();
	  			}
	  		void exit()
	  		{
	  			neilooper.quit();
	  		}
	  	}
	  
	 
	 
	 class huoquzhujiip extends Thread
	  	{Handler huoquzhuji=new Handler(){  				
		        public void handleMessage(android.os.Message msg) {  
		        	switch(msg.what)
		        	{
		        	case 1:
		        	 	if(stage==1){
			            	if(jisuanjiange==3){
			            		Log.i("hele","yici");
			                	jisuanjiange=0;
			                	if(isap==0){
			                		listzhuji.clear();
			                		byte bufluyou1[];
			            			bufluyou1=new byte[BUFMAX];
			            			try {
			            				String thing=new String();
			            				thing="k;"+bendiip+";";
			            				bufluyou1=thing.getBytes("ISO-8859-1");
			            			} catch (UnsupportedEncodingException e) {
			            				// TODO Auto-generated catch block
			            				e.printStackTrace();
			            			}	
			            			sendguangfa(bufluyou1);	
			                	}
			                	if(isap==1){
			                		listzhuji.clear();
			                		byte bufluyou1[];
			            			bufluyou1=new byte[BUFMAX];
			            			try {
			            				String thing=new String();
			            				thing="m;"+bendiip+";";
			            				bufluyou1=thing.getBytes("ISO-8859-1");
			            			} catch (UnsupportedEncodingException e) {
			            				// TODO Auto-generated catch block
			            				e.printStackTrace();
			            			}	
			            			senddanfa(bufluyou1,dailiip);
			                	}
			                	adapter3.notifyDataSetChanged();
			                huoquzhuji.sendEmptyMessageDelayed(1, 1000);  
			              
			            	}else{
			            		
			            		jisuanjiange++;
			            		huoquzhuji.sendEmptyMessageDelayed(1, 1000);
			            	}
		        	 	}
			            
			            
			            	break;
			            default:
			            	break;  
			            }
		        	}
		        
			 };
	    	private Looper neilooper;
	  		public void run(){
	  			Looper.prepare();
	  			neilooper=Looper.myLooper();  			
	  	
	  			
				Looper.loop();
	  			}
	  		void exit()
	  		{
	  			neilooper.quit();
	  		}
	  	}
	 
	 
	 
	 
		void usernextf(int i,int weizhi)
		{
			int j,m=1,n=weizhi;
			
	
			
//进行处理jieguo2开始
			
//			 if(i==MAXNUM){
//				 timer.timehandle.sendEmptyMessageDelayed(2,1); 
//				
//			 }
			 if(i==2)
				 for(j=1;j<=MAXNUM-i+2;j++)
				 {jieguo2[i-1][j]=new chuli2();
  			   jieguo2[i-1][j].left=j;
              jieguo2[i-1][j].right=j;
              jieguo2[i-1][j].shu=zhuchengxu.firstdealshu[j];
              jieguo2[i-1][j].fu=zhuchengxu.firstdealfu[j];
              jieguo2[i-1][j].shutheata=2*PI/MAXNUM*(j-1);
              jieguo2[i-1][j].futheata=jieguo2[i-1][j].shutheata+2*PI/MAXNUM/2;
				 }
			
			
			        for(j=1;j<=MAXNUM-i+1;j++)
			        {jieguo2[i][j]=new chuli2();
			           
			            if(i>1){
			                if(j!=n){

			                	jieguo2[i][j].fu=jieguo2[i-1][m].fu;//终于知道了，这是传递的指针，不是实体。
			                    jieguo2[i][j].shutheata=jieguo2[i-1][m].shutheata;
			                    jieguo2[i][j].shu=jieguo2[i-1][m].shu;
			                    jieguo2[i][j].futheata=jieguo2[i-1][m].futheata;
			                    jieguo2[i][j].left=jieguo2[i-1][m].left;
			                    jieguo2[i][j].right=jieguo2[i-1][m].right;
			                    m++;
			               				}
			                if(j==n){
			                    
			                    	
			                    jieguo2[i][j].shu=zhuchengxu.yunsuan(jieguo2[i-1][n].fu,jieguo2[i-1][m].shu,jieguo2[i-1][m+1].shu);
			                    jieguo2[i][j].shutheata=((jieguo2[i-1][m+1].shutheata-jieguo2[i-1][m].shutheata)/2+jieguo2[i-1][m].shutheata);
			                    jieguo2[i][j].left=jieguo2[i-1][m].left;
			                    jieguo2[i][j].right=jieguo2[i-1][m+1].right;
			                    jieguo2[i][j].fu=jieguo2[i-1][m+1].fu;

			                    			m=m+2;
			                		}
			            	}
			        }
			            if(i!=MAXNUM){
			            if(n==1){jieguo2[i][1].futheata=(jieguo2[i][2].shutheata-jieguo2[i][1].shutheata)/2+jieguo2[i][1].shutheata;}
			            if(n==MAXNUM-i+1){jieguo2[i][n-1].futheata=(jieguo2[i][n].shutheata-jieguo2[i][n-1].shutheata)/2+jieguo2[i][n-1].shutheata;}
			            				}
			            if((n>1)&&(n<MAXNUM-i+1)){jieguo2[i][n-1].futheata=(jieguo2[i][n].shutheata-jieguo2[i][n-1].shutheata)/2+jieguo2[i][n-1].shutheata;
			                                  jieguo2[i][n].futheata=(jieguo2[i][n+1].shutheata-jieguo2[i][n].shutheata)/2+jieguo2[i][n].shutheata;
			                                   		}
			   
//处理jieguo2结束
			            
			            
//结束条件开始
if(i==MAXNUM){
	if(jieguo2[i][1].shu!=zhuchengxu.jieguo[i][1].shu){
		new AlertDialog.Builder(Pkmodel.this)
		.setTitle("您没有得到最大值，双击可以清屏")
		.setPositiveButton("确定",null)
		.show();	
	}
	if(jieguo2[i][1].shu==zhuchengxu.jieguo[i][1].shu){
		new AlertDialog.Builder(Pkmodel.this)
    	 .setTitle("您已完成请等待其他人")
		 .setPositiveButton("确定",null)
		 	.show();
		//Log.i("inttime",String.valueOf(inttime));
		//Log.i("temptime",String.valueOf(temptime));
	/*	if(inttime<=temptime){
			new AlertDialog.Builder(Pkmodel.this)
			.setTitle("您用的时间比上一次短了")
			.setPositiveButton("确定",null)
			.show();
		}else{
			
			new AlertDialog.Builder(Pkmodel.this)
			.setTitle("您用的时间比上一次长了")
			.setPositiveButton("确定",null)
			.show();
		}
		temptime=inttime;*/
		timer.timehandle.sendEmptyMessageDelayed(2,1); 
		stage=4;
		String thing=new String();
		thing="7;";
		thing=thing+inttime+";";
		
		byte buf[]=null;
		try {
			buf=thing.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		/*buf=new byte[BUFMAX];
 		buf[0]=2;*/
		senddanfa(buf,serverip);
		/*dengdai=new dingzhidialog(this);
		dengdai.setTitle("您已完成请等待其他人。。。");
		dengdai.removebutton();
		dengdai.startshow();*/
	}
	

				
				
}
//结束条件结束
			            
			            
//开始画图
			        double r;
					 point linshi=new point();
				
					
					 if(flagxianshi==2){
						 mainbuju.removeAllViews();
						 
						 				 
						 
						 meici=new meicihua2(this);  	
						 mainbuju.addView(meici);
						 meici.setcan(i,(float)(jieguo2[i][1].shutheata/(2*PI)*360),(float)((jieguo2[i][Pkmodel.MAXNUM-i+1].shutheata-jieguo2[i][1].shutheata)/(2*PI)*360),flagxianshi);
						 		if(i!=1)
						 		{
						 			meici.hebing1=(float)(jieguo2[i-1][n].shutheata/(2*PI)*360);
						 			meici.hebing2=(float)(jieguo2[i-1][n+1].shutheata/(2*PI)*360);
						 		}
						 		
						 		xianshitime=new TextView(this);
						 		xianshitime.setText(timestring);
						 		mainbuju.addView(xianshitime,textparam);
				
						 setContentView(mainbuju);	
					}
					 
					 r=chushir;
					if(flagxianshi==1)r=chushir-chushir/(MAXNUM-1)*(i-1);
						 for(j=1;j<=MAXNUM-i+1;j++)
					        {
							 	  linshi=zhuanxi(jieguo2[i][j].shutheata,r,ANDIS);
							 	  buttongroup[j]=new textshu(this);
					    	      buttongroup[j].setText(Integer.toString((int)jieguo2[i][j].shu));//
					    	      buttongroup[j].setX((float)linshi.x);
					    	      buttongroup[j].setY((float)linshi.y);
					    	     // 	buttongroup[j].setVisibility(1);
					    	      buttongroup[j].setWidth(ANDIS);
					    	      buttongroup[j].setHeight(ANDIS);
					    	      buttongroup[j].setGravity(Gravity.CENTER); 	
					    	      mainbuju.addView(buttongroup[j]);
					            
					           
					            if(j!=MAXNUM-i+1){
					            	linshi=zhuanxi(jieguo2[i][j].futheata,r,ANNIUDIS);
						        	viewgroup[j]=new boardertext(this);
						       	viewgroup[j].setText(String.valueOf(jieguo2[i][j].fu));
						    		viewgroup[j].setX((float)linshi.x);
						     		viewgroup[j].setY((float)linshi.y);
						       	viewgroup[j].setOnTouchListener(new usernext(i,j));
						       	viewgroup[j].setWidth(ANNIUDIS);
						       	viewgroup[j].setHeight(ANNIUDIS);
						       	viewgroup[j].setGravity(Gravity.CENTER); 	
						       	mainbuju.addView(viewgroup[j]);

					            }
					            
					        }
						//parent.removeView(mainbuju);
						  //mainbuju.removeAllViews();
					if(flagxianshi==1){
						 meici=new meicihua2(this);  	
						 mainbuju.addView(meici);
						 meici.setcan(i,(float)(jieguo2[i][1].shutheata/(2*PI)*360),(float)((jieguo2[i][Pkmodel.MAXNUM-i+1].shutheata-jieguo2[i][1].shutheata)/(2*PI)*360),flagxianshi);
						 		if(i!=1)
						 		{
						 			meici.hebing1=(float)(jieguo2[i-1][n].shutheata/(2*PI)*360);
						 			meici.hebing2=(float)(jieguo2[i-1][n+1].shutheata/(2*PI)*360);
						 		}
						 setContentView(mainbuju);	
					}
//画图结束
		}
	
	 
	 
	 
	 
	void chushicanshu(final String chushishufu){
		
		   TableRow duihuafrow=new TableRow(Pkmodel.this);
		   TableRow duihuasrow=new TableRow(Pkmodel.this);
		   TableRow duihuatrow=new TableRow(Pkmodel.this);
	      TableLayout duihua=new TableLayout(Pkmodel.this);
			TextView diyi=new TextView(Pkmodel.this);
			diyi.setText("生成数大小上限(1-49)：");
			TextView dier=new TextView(Pkmodel.this);
			dier.setText("生成数个数上限(3-12)：");
			TextView disan=new TextView(Pkmodel.this);
			disan.setText("生成数显示方式是否用叠加圆：");
			
			shuru1=new EditText(Pkmodel.this);
			shuru2=new EditText(Pkmodel.this);
			xianflag=new CheckBox(Pkmodel.this);
			
			shuru1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)}); 
			shuru2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
			shuru1.setWidth(100);
			shuru2.setWidth(100);
			
			shuru1.setText(Integer.toString(MAXSHUSHANG));
			shuru2.setText(Integer.toString(MAXNUM));
			
			if(chushishufu!=null){
				shuru1.setFocusable(false);
				shuru2.setFocusable(false);
				}
			if(flagxianshi==1)xianflag.setChecked(true);
			if(flagxianshi==2)xianflag.setChecked(false);
			duihuafrow.addView(diyi);
			duihuafrow.addView(shuru1);
			
			duihuasrow.addView(dier);
			duihuasrow.addView(shuru2);
			
			duihuatrow.addView(disan);
			duihuatrow.addView(xianflag);
			
			duihua.addView(duihuafrow);
			duihua.addView(duihuasrow);
			duihua.addView(duihuatrow);
		
		
		
		
		final dingzhidialog b=new dingzhidialog(Pkmodel.this);
		 b.setview(duihua);
			b.setTitle("请输入初始参数：");
			b.removejujuebutton();
			b.setshijian(new OnClickListener(){
					
				@Override
				public void onClick(View v) {
					   //按钮事件
               	 Pattern pat1 = Pattern.compile("[1-9]|[1-4][0-9]");
               	 Pattern pat2 = Pattern.compile("[3-9]|[1][0-2]");
           
               	 /* 	//调试代码开始
               	 TextView tiaoshi=new TextView(First.this);
           	     tiaoshi.setText(shuru1.getText().toString());
             	 new AlertDialog.Builder(First.this)
               	 .setTitle("生成数个数不符合条件！")
           		 	.setView(tiaoshi)
           		 .setPositiveButton("确定",null)
           		 	.show();
               	 //调试代码结束*/               	  
               	  
              	 if(!pat1.matcher(shuru1.getText().toString()).matches()){
               		 new AlertDialog.Builder(Pkmodel.this)
               		 .setTitle("生成数大小上限不符合条件！")
               		 .setPositiveButton("确定",null)
               		 	.show();
               		 return;
               	 	}
              	if(!pat2.matcher(shuru2.getText().toString()).matches()){
          		 new AlertDialog.Builder(Pkmodel.this)
          		 .setTitle("生成数个数上线不符合条件！")
          		 .setPositiveButton("确定",null)
          		 	.show();
          		 		return;
          	 		}
              			{	
              				
              				MAXNUM=Integer.parseInt(shuru2.getText().toString());
              				MAXSHUSHANG=Integer.parseInt(shuru1.getText().toString());
              				
              				//初始化开始
              			zhuchengxu=new mainsuan(MAXNUM,MAXSHUSHANG);
              			if(chushishufu==null)zhuchengxu.firststage();
              			if(chushishufu!=null)zhuchengxu.daoru(chushishufu);
   						zhuchengxu.mainsuanrukou();
   						int a;
   					  	totali=2;
   					  	buttongroup=new textshu[MAXNUM+1];
   						viewgroup=new boardertext[MAXNUM+1];
   						jieguo2=new chuli2[MAXNUM+1][MAXNUM+1];
   					  	flag=new int[MAXNUM];
   						 for(a=0;a<MAXNUM;a++)flag[a]=0;
   						
   						chushihuatu();
   						//初始化结束
   					
   							timestop=false;
   							timer.timehandle.sendEmptyMessageDelayed(1,1); 
   						 
   						if(xianflag.isChecked())flagxianshi=1;
   						if(!xianflag.isChecked())flagxianshi=2;
   						
   						
   		    		
   		    			 
              			
              			}
              			
					b.stopshow();
				}
				
			}, new OnClickListener(){

				@Override
				public void onClick(View v) {
						b.stopshow();
				}
				
			});
			b.startshow();
	}
	
	//画图有关结束
	
	 class zongtijianting implements OnItemSelectedListener
	 	{  Spinner spinner;
	 		int weizhi;
		 public zongtijianting(Spinner a,int weizhi2){
			 spinner=a;
			 this.weizhi=weizhi2;
	 		}
	@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			  String thing=new String();
					thing="c;";
					thing=thing+spinner.getSelectedItem().toString()+";";
			
					byte buf[]=null;
					try {
						buf=thing.getBytes("ISO-8859-1");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			if(spinner.getSelectedItemPosition()!=listtag.indexOf(list.get(weizhi).fenzu))
				senddanfa(buf,serverip);

		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}  
              
    }
	
	
	void ready(int aflag)		//显示就绪,结果界面
	{	
		
	if(aflag!=4)setContentView(R.layout.activity_pkmodel_ready);
			if(aflag==1){
						adapter = new grouplistadapter(this);
						//restart=(Button)findViewById(R.id.button2);
						listview = (ListView)findViewById(R.id.group_list);
			}
			/*if(aflag==1 || aflag==4){
				restart.setVisibility(View.GONE);
	        	ready.setVisibility(View.VISIBLE);
			}else if(aflag==2){
				ready.setVisibility(View.GONE);
	        	restart.setVisibility(View.VISIBLE);
			}	
			*/
			if(aflag==4){
				//adapter = new grouplistadapter(this);
				//listview.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				if(adapter2!=null)adapter2.notifyDataSetChanged();
			}
			
		 	if(aflag==1){
		 		//adapter.setData(listzanshi);
		 		listview.setAdapter(adapter);
		 	}
		 	if(aflag==2){
		 		listview = (ListView)findViewById(R.id.group_list);
		 		adapter2 = new grouplistadapter2(this);
		 		listview.setAdapter(adapter2);
		 		//adapter.notifyDataSetChanged();
		 		}
	        
	}
	
	class grouplistadapter2 extends BaseAdapter {

		public grouplistadapter2(Context context) {
			
			
		//	super(context,0,listzanshi);
			// TODO Auto-generated constructor stub
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
         viewholder2 holder=null;
		if(convertView==null){
			if(list.get(position).name.equals(list.get(position).fenzu)) {
					convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.group_list_item_tag2, null);
               	 holder=new viewholder2();
               	 convertView.setTag(holder);
				} else {
					convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.group_list_item2, null);
            		holder=new viewholder2();
            		holder.restart=(Button)convertView.findViewById(R.id.button10);
            		convertView.setTag(holder);
				}
			}else{
				holder=(viewholder2)convertView.getTag();
			}
		if(!list.get(position).name.equals(list.get(position).fenzu))
				{
					if(holder.restart==null){
						convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.group_list_item2, null);
	            		holder=new viewholder2();
	            		holder.restart=(Button)convertView.findViewById(R.id.button10);
	            		convertView.setTag(holder);
					}
					holder.restart.setOnClickListener(null);
					holder.restart.setText("重新开始");
					holder.restart.setOnClickListener(new OnClickListener(){
		        		@Override
		        			public void onClick(View v) {
		        			stage=2;
		        			ready(1);
		         			byte bufluyou[];
		        			bufluyou=new byte[BUFMAX];
		        			try {
		        				bufluyou=new String("d;").getBytes("ISO-8859-1");
		        			} catch (UnsupportedEncodingException e) {
		        				// TODO Auto-generated catch block
		        				e.printStackTrace();
		        			}	
		        			senddanfa(bufluyou,serverip);
		        			byte bufluyou13[];
		        			bufluyou13=new byte[BUFMAX];
		        			try {
		        				String thing=new String();
		        				thing="p;";
		        				bufluyou13=thing.getBytes("ISO-8859-1");
		        			} catch (UnsupportedEncodingException e) {
		        				// TODO Auto-generated catch block
		        				e.printStackTrace();
		        			}	
		        			senddanfa(bufluyou13,serverip);
		        		}});
					if(!bendiip.toString().equals(list.get(position).ip.toString())){
	      				holder.restart.setEnabled(false);
	      			}else{
	      				holder.restart.setEnabled(true);
	      			}	
		        	

				}else{
					if(holder.restart!=null){
						convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.group_list_item_tag2, null);
		               	 holder=new viewholder2();
		               	 convertView.setTag(holder);
					}
				}
		
            TextView textView1 = (TextView)convertView.findViewById(R.id.textView1);
            textView1.setText(timezhuan(list.get(position).time));
            TextView textView = (TextView)convertView.findViewById(R.id.group_list_item_text);
            textView.setText(list.get(position).name);
            
			return convertView;
		}
		
		class viewholder2{
			public Button restart=null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
	}	
	class grouplistadapter extends BaseAdapter{
	
		public grouplistadapter(Context context) {
	
			// TODO Auto-generated constructor stub
			
		}
		 public View getView(int position, View convertView, ViewGroup parent) {
	    
	      viewholder holder=null;
	        if(convertView==null){
	            if(list.get(position).name.equals(list.get(position).fenzu)) {
	            		convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.group_list_item_tag, null);
	            		holder=new viewholder();
	            		holder.textView=(TextView)convertView.findViewById(R.id.group_list_item_text);
	            		convertView.setTag(holder);
	            } else {
	                	convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.group_list_item, null);
	                  holder=new viewholder();
	                  holder.textView=(TextView)convertView.findViewById(R.id.group_list_item_text);
	                  holder.spinner=(Spinner)convertView.findViewById(R.id.spinner1);  
	              	holder.spinneradapter=new ArrayAdapter<String>(Pkmodel.this,android.R.layout.simple_spinner_item,listtag);
	                  holder.spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	                  holder.spinner.setAdapter(holder.spinneradapter);
	                  
	                 /* holder.spinner.setOnItemSelectedListener(new OnItemSelectedListener() {  
	                
	                	  public void onItemSelected(AdapterView<?> parent,  
	                        View view,int posit,long id) {  
	                        Spinner spinner = (Spinner) parent;  
	                        //tv.setText(spinner.getSelectedItem().toString());  
	                        //Log.v("Test", "id = " + id + "("  
	                        //+ spinner.getSelectedItem().toString() + ")");  
	                        String thing=new String();
	        						thing="c;";
	        						thing=thing+spinner.getSelectedItem().toString()+";";
	        				
	        						byte buf[]=null;
	        						try {
	        							buf=thing.getBytes("ISO-8859-1");
	        						} catch (UnsupportedEncodingException e) {
	        							// TODO Auto-generated catch block
	        							e.printStackTrace();
	        						}
	        				if(spinner.getSelectedItemPosition()!=listtag.indexOf(list.get(listviewposition).fenzu))senddanfa(buf,serverip);
	                    }  
	                    public void onNothingSelected(AdapterView<?> parent) {  
	                    }  
	                          
	                });*/
	                  holder.readystate=(Button)convertView.findViewById(R.id.button1);
	                  convertView.setTag(holder);
	            }}else{
	            		holder=(viewholder)convertView.getTag();
	            }
	           
	        if(!list.get(position).name.equals(list.get(position).fenzu)) {
	        	if(holder.spinner==null){
	        		convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.group_list_item, null);
	                  holder=new viewholder();
	                  holder.textView=(TextView)convertView.findViewById(R.id.group_list_item_text);
	                  holder.spinner=(Spinner)convertView.findViewById(R.id.spinner1);  
	              	holder.spinneradapter=new ArrayAdapter<String>(Pkmodel.this,android.R.layout.simple_spinner_item,listtag);
	                  holder.spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	                  holder.spinner.setAdapter(holder.spinneradapter);  
	               //   zongti=new zongtijianting(holder.spinner,position);
	                //  holder.spinner.setOnItemSelectedListener(zongti);
	                  holder.readystate =(Button)convertView.findViewById(R.id.button1);
	                  convertView.setTag(holder);
	        		
	        	}
	        	
	        	if(!bendiip.toString().equals(list.get(position).ip.toString())){
      				holder.spinner.setEnabled(false);
      			}else{
      				holder.spinner.setEnabled(true);
      			}
	        		holder.textView.setText(list.get(position).name);
	        	
	        	holder.spinner.setOnItemSelectedListener(null);
	        	zongti=new zongtijianting(holder.spinner,position);
                holder.spinner.setOnItemSelectedListener(zongti);
	           
	        		holder.spinner.setSelection(listtag.indexOf(list.get(position).fenzu));
	        	
	        		
	        		if(list.get(position).ready==1)holder.readystate.setText("已准备");
	        		if(list.get(position).ready==0)holder.readystate.setText("准备");
	        		 if(list.get(position).ip.toString().equals(serverip.toString()))holder.readystate.setText("开始");
	    	       if(list.get(position).ip.toString().equals(bendiip.toString())){
	    	    	   
	    	        	holder.readystate.setEnabled(true);
	    	        	holder.readystate.setOnClickListener(null);
	    	        if(!list.get(position).ip.toString().equals(serverip.toString()))
	    	        				holder.readystate.setOnClickListener(new OnClickListener(){

	    		        		@Override
	    		        			public void onClick(View v) {
	    		        					// TODO Auto-generated method stub
	    		        			byte bufluyou[];
	    		        			bufluyou=new byte[BUFMAX];
	    		        			try {
	    		        				bufluyou=new String("b;").getBytes("ISO-8859-1");
	    		        			} catch (UnsupportedEncodingException e) {
	    		        				// TODO Auto-generated catch block
	    		        				e.printStackTrace();
	    		        			}	
	    		        			senddanfa(bufluyou,serverip);
	    		        
	    		        	
	    						
	    		        			}});
	    	        	
	    	        if(list.get(position).ip.toString().equals(serverip.toString())){
	    	      //  		holder.readystate.setText("开始");
	    	        		
	    	        		holder.readystate.setOnClickListener(new OnClickListener(){

	    	        		@Override
	    	        			public void onClick(View v) {
	    	        					// TODO Auto-generated method stub
	    	        			int flagnostart=0;
	    	        			for(totalthing every:list){
	    	    					if(every.name!=every.fenzu)if(!every.ip.toString().equals(bendiip.toString()))if(every.ready==0)flagnostart=1;
	    	        			}
	    	        			if(daijiaru!=0){
	    	        				 new AlertDialog.Builder(Pkmodel.this)
	                            	 .setTitle("有正在加入的人，请再等一下！")
	                        		 .setPositiveButton("确定",null)
	                        		 	.show();
	    	        			}
	    	        			if(daijiaru==0)if(flagnostart==0)
	    	        			{stage=3;
	    	        				for(totalthing every:list){
	    		    					if(every.name==every.fenzu)totalplayers=totalplayers+every.ready;
	    		    				}
	    	        				byte bufluyou[];
	    	        			bufluyou=new byte[BUFMAX];
	    	        			chongxinshuaxin=0;
	    	        			chushicanshu(null);
	    	        			String thing=new String();
	    	        			thing="4;"; 
	    	        		thing=thing+zhuchengxu.daochu()+";";
	    	        			try {
	    	        				bufluyou=thing.getBytes("ISO-8859-1");
	    	        			} catch (UnsupportedEncodingException e) {
	    	        				// TODO Auto-generated catch block
	    	        				e.printStackTrace();
	    	        			}	
	    	        			sendguangfa(bufluyou);
	    	        			}else{
	    	        				 new AlertDialog.Builder(Pkmodel.this)
	                            	 .setTitle("所有人准备了才可以开始")
	                        		 .setPositiveButton("确定",null)
	                        		 	.show();
	    		            
	    	        			}
	    					
	    	        			}});
	    	        		
	    	        }	

	    	       }else{
	    	    	   holder.readystate.setEnabled(false);
	    	       }
	        		
	        }else{
	        	if(holder.spinner!=null){
	        		convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.group_list_item_tag, null);
            		holder=new viewholder();
            		holder.textView=(TextView)convertView.findViewById(R.id.group_list_item_text);
            		convertView.setTag(holder);
	        	}
	        		holder.textView.setText(list.get(position).name);
	         }           
	            return convertView;
	       }

		 public class viewholder{
		        public TextView textView=null;
		        public Button readystate=null;
		        public Spinner spinner=null;
		        public ArrayAdapter<String> spinneradapter=null;
		 }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	void senddanfa(byte buf[],InetAddress a){
		 DatagramPacket packetser=null;
		packetser = new DatagramPacket(new byte[0],0,a,DUANKOUDAN);
		 
		 		packetser.setData(buf);
 		try {
			danfaserver.send(packetser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
	}
	void sendguangfa(byte bufluyou[]){
		/*InetAddress guangbofa=null;
		try {
			guangbofa=InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
		DatagramPacket packetguangbo=new DatagramPacket(new byte[0],0,guangbofa,DUANKOUDAN);
		packetguangbo.setData(bufluyou);
		try {
			danfaserver.setBroadcast(true);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			danfaserver.send(packetguangbo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
void addjiaru(){
	
	 TableLayout jiaru=new TableLayout(Pkmodel.this);
	 TableRow jiarufrow=new TableRow(Pkmodel.this);
	 TableRow jiarusrow=new TableRow(Pkmodel.this);
	
	 TextView xingming=new TextView(Pkmodel.this);
	 xingming.setText("姓名：");
	  shuru1=new EditText(Pkmodel.this);
	 shuru1.setWidth(200);
	 shuru1.setGravity(Gravity.CENTER_HORIZONTAL);
	 shuru1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
	 TextView fenzu=new TextView(Pkmodel.this);
	 fenzu.setText("想要加入的分组：（1-99）");
	 shuru2=new EditText(Pkmodel.this);
	 shuru2.setWidth(200);
	 shuru2.setGravity(Gravity.CENTER_HORIZONTAL);
	 shuru2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
	 jiarufrow.addView(xingming);
	 jiarufrow.addView(shuru1);
	 xingming.setGravity(Gravity.CENTER_HORIZONTAL);
	 jiarusrow.addView(fenzu);
	 jiarusrow.addView(shuru2);
	 fenzu.setGravity(Gravity.CENTER_HORIZONTAL);
	 jiaru.addView(jiarufrow);
	 jiaru.addView(jiarusrow);
	 final dingzhidialog a=new dingzhidialog(this);
	 a.setview(jiaru);
		a.setTitle("一块来吧？");
		if(serverip.equals(bendiip)){a.removejujuebutton();}
		a.setshijian(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				//Pattern pat1 = Pattern.compile("[1-9]|[1-4][0-9]");
              	 Pattern pat2 = Pattern.compile("[1-9]|[1-9][0-9]");
          
              	 /* 	//调试代码开始
              	 TextView tiaoshi=new TextView(First.this);
          	     tiaoshi.setText(shuru1.getText().toString());
            	 new AlertDialog.Builder(First.this)
              	 .setTitle("生成数个数不符合条件！")
          		 	.setView(tiaoshi)
          		 .setPositiveButton("确定",null)
          		 	.show();
              	 //调试代码结束*/               	  
              	  
             /*	 if(!pat1.matcher(shuru1.getText().toString()).matches()){
              		 new AlertDialog.Builder(Pkmodel.this)
              		 .setTitle("生成数大小上限不符合条件！")
              		 .setPositiveButton("确定",null)
              		 	.show();
              		 return;
              	 	}*/
             if(shuru1.getText().toString().equals(shuru2.getText().toString())){
            	 new AlertDialog.Builder(Pkmodel.this)
         		 .setTitle("用户名密码不能一样")
         		 .setPositiveButton("确定",null)
         		 	.show();
         		 		return;
              	 }
             int isexist=0;
             for(totalthing every:list){
					if(!every.name.equals(every.fenzu))
						if(every.name.equals(shuru1.getText().toString())){
							isexist=1;
					}
				}
             if(isexist==1){
            	 new AlertDialog.Builder(Pkmodel.this)
         		 .setTitle("已存在该用户名，请换一个吧")
         		 .setPositiveButton("确定",null)
         		 	.show();
         		 		return;
              	 }
             	if(!pat2.matcher(shuru2.getText().toString()).matches()){
         		 new AlertDialog.Builder(Pkmodel.this)
         		 .setTitle("输入分组数不符合条件")
         		 .setPositiveButton("确定",null)
         		 	.show();
         		 		return;
         	 		}
           
				
				String thing=new String();
				thing="a;";
				thing=thing+shuru1.getText().toString()+";"+shuru2.getText().toString()+";";
				
				byte buf[]=null;
				try {
					buf=thing.getBytes("ISO-8859-1");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 		/*buf=new byte[BUFMAX];
		 		buf[0]=2;*/
				/* new AlertDialog.Builder(Pkmodel.this)
             	 .setTitle(duifangdanip.toString())
         		 .setPositiveButton("确定",null)
         		 	.show();*/
				
				stage=2;
				senddanfa(buf,serverip);
				
				
					a.stopshow();
				
			}
			
		}, new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					
					if(readyflag!=0)readyflag=0;
					addpkbutton(1);
					byte bufluyou6[];
					bufluyou6=new byte[BUFMAX];
					try {
						bufluyou6=new String("n;").getBytes("ISO-8859-1");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					senddanfa(bufluyou6,serverip);
					//stage=1;
					/*Intent openfanhui=new Intent();
					openfanhui.setClass(Pkmodel.this,Welcome.class);
					startActivity(openfanhui);
*/			
					list.clear();
					listtag.clear();
					serverip=null;
					a.stopshow();
				//	finish();
			}
			
		});
		a.startshow();
}	
Handler zhutimehandle=new Handler(){
	public void handleMessage(android.os.Message msg){
		xianshitime.setText(timezhuan(msg.what));
	}
};	
	

Handler zhudanfa = new Handler(){
	       public void handleMessage(android.os.Message msg) {		
	    	   			while(duilie.size()!=0){	
	    	   				msg=duilie.get(0).message;
		    	   			xiaoxiduiyingip=duilie.get(0).ip;
		    	   			duilie.remove(0);
		    	   			danfacase(msg);
		    	   			
	    	   			}
	       			}
	       };	
void shuaxinqita(InetAddress zan){
					
	byte bufluyou[];
	bufluyou=new byte[BUFMAX];
	try {
		 String thing=new String();
			thing="6;";
			thing=thing+listzhuanhuan(list)+";"+listtagzhuanhuan(listtag)+";";
		bufluyou=thing.getBytes("ISO-8859-1");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	senddanfa(bufluyou,zan);

}


void otherxianshifinish(){
	byte bufluyou[];
	bufluyou=new byte[BUFMAX];
	try {
		 String thing=new String();
			thing="8;";
			thing=thing+listzhuanhuan(list)+";";
		bufluyou=thing.getBytes("ISO-8859-1");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	sendguangfa(bufluyou);
}

String listtagzhuanhuan(List<String> a){
	String b=null;
	b=a.size()+"-";
	for(String every:a){
		b=b+every+"-";
	}
	return b;
}
List<String> listtagzhuanhuan(String b){
	List<String> a=new ArrayList();
	int i;
	String[] fen=b.split("-");
	for(i=1;i<=Integer.parseInt(fen[0]);i++){
		a.add(fen[i]);
	}
	return a;
}
String listzhuanhuan(List<totalthing> a){
	String b=null;
	b=a.size()+"-";
		for(totalthing c:a){	
			b=b+c.name+"-"+c.fenzu+"-"+c.time+"-"+c.paiming+"-"+c.ready+"-"+c.ip+"-";
		}
	return b;
	
}
InetAddress ipzhuanhuan(String a){
	String	tempip[]=a.split("\\.");
	InetAddress fanhui=null;
	tempip[0]=tempip[0].substring(tempip[0].indexOf("/")+1,tempip[0].length());
	try {
		fanhui=InetAddress.getByName(Integer.parseInt(tempip[0])+"."+Integer.parseInt(tempip[1])+"."+Integer.parseInt(tempip[2])+"."+Integer.parseInt(tempip[3]));
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return(fanhui);
	
}
List<totalthing> listzhuanhuan(String b){
	List<totalthing> a=new ArrayList();
			int i;
			String[] fen = b.split("-");
			for(i=1;i<=Integer.parseInt(fen[0]);i++){
				totalthing temp=new totalthing();
				temp.name=fen[1+(i-1)*6];
				temp.fenzu=fen[2+(i-1)*6];
				temp.time=Integer.parseInt(fen[3+(i-1)*6]);
				temp.paiming=Integer.parseInt(fen[4+(i-1)*6]);
				temp.ready=Integer.parseInt(fen[5+(i-1)*6]);
				if(!fen[6+(i-1)*6].equals("null")){
					temp.ip=ipzhuanhuan(fen[6+(i-1)*6]);
				}
				a.add(temp);
			}
	return a;
}
void danfacase(Message a){					//单播或者广播接受的消息
	//消息1 按下let‘s pk 的按钮    action：弹出对话框提示输入姓名（确定，取消），确定了然后，
    //   显示，ready按钮（发送回应消息2）。消息2 服务器接受姓名，ip，游戏分组，进行记录。
	//消息3 服务器发送一个场随机的局，接受，并开始计时。
	//直到找到了最大值，并进行，回发服务器 消息4，所用时间  
	//消息5 服务器发送比赛结果，接受 比赛结果数据。
	Bundle b=new Bundle();
	b=a.getData();
	byte buf[];
	buf=b.getByteArray("xiaoxi");
	String line=null;
	try {
		line = new String(buf,"ISO-8859-1");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String[] splitted = line.split(";");
	switch(splitted[0].charAt(0))
		{
		case '0':		//作为192.168.43.1代理服务器					
			if(stage==1)if(isserver==0)benjiserverqidong();
			
			return;//单播，接收：服务器
		case '1':		//弹出对话框提示是否加入，内容：（输入姓名，分组）（只能按接受，拒绝消失）
		if(serverip==null)if(stage==1){
			serverip=xiaoxiduiyingip;
			if(isserver==0){
				byte bufluyou6[];
				bufluyou6=new byte[BUFMAX];
				try {
					bufluyou6=new String("o;").getBytes("ISO-8859-1");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				senddanfa(bufluyou6,serverip);
			}
			addjiaru();}
			/*if(isserver==1)if(serverip.equals(xiaoxiduiyingip)){
				addjiaru();
			}*/
		/*	AlertDialog.Builder isjoing=new AlertDialog.Builder(Pkmodel.this)
		 	.setTitle("有人建议想一块玩，是否加入？")
		 	.setView(jiaru)
		    .setPositiveButton("确定",
             new DialogInterface.OnClickListener(){
                     public void onClick(DialogInterface dialoginterface, int i){
                     }
                     });
			*/
			return;//广播，接受：服务器，非服务器
		case '2':	//接收非服务器的计算机的用户名，分组,ip
	if(serverip!=null)if(serverip.equals(xiaoxiduiyingip))if(stage==2 || stage==1){
				zanshiip=ipzhuanhuan(splitted[3]);
				/*if(zanshiip.equals(bendiip)){
					byte bufluyou6[];
					bufluyou6=new byte[BUFMAX];
					try {
						bufluyou6=new String("n;").getBytes("ISO-8859-1");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					senddanfa(bufluyou6,serverip);
				}*/
				if(isserver==1)if(!zanshiip.equals(serverip))if(daijiaru>0)daijiaru--;
			if(!listtag.contains(splitted[2])){
				listtag.add(splitted[2]);
				totalthing tempjiben=new totalthing();
				tempjiben.name=splitted[2];
				tempjiben.fenzu=splitted[2];
				tempjiben.ip=null;
				tempjiben.ready=1;
				list.add(tempjiben);
				totalthing tempjiben1=new totalthing();
				tempjiben1.name=splitted[1];
				tempjiben1.fenzu=splitted[2];
			//	splitted[3].replace("/","");
			tempjiben1.ip=zanshiip;
			list.add(tempjiben1);
			}else{
				int tempposition=0,tempnumber=0;
				for(totalthing every:list){
					if(every.name.equals(every.fenzu) && every.name.equals(splitted[2])){
						tempposition=list.indexOf(every);
						tempnumber=every.ready;
						every.ready++;
						break;
					}
				}
				totalthing tempjiben=new totalthing();
				tempjiben.name=splitted[1];
				tempjiben.fenzu=splitted[2];
			tempjiben.ip=zanshiip;
			list.add(tempposition+tempnumber+1,tempjiben);
			}
			if(readyflag==0)
			{	
				ready(1);
				readyflag++;
				}else{
				ready(4);
			}
			//服务器刷新非服务器发送用户名，分组消息。
			//shuaxinqita();
			}
			return;//单播，接收：服务器
		case '3':	//告诉服务器，其一台电脑已经准备就绪
		if(serverip!=null)if(serverip.equals(xiaoxiduiyingip))if(stage==2){	zanshiip=ipzhuanhuan(splitted[1]);
			int tempposition=0;
			for(totalthing every:list){
				if(!every.name.equals(every.fenzu))	
				if(every.ip.equals(zanshiip)){
						tempposition=list.indexOf(every);
						break;
					}
				}
			list.get(tempposition).ready=1;
			if(readyflag!=0)ready(4);//服务器刷新非服务器发送已准备消息
			//shuaxinqita();
		}
			return;//单播，接收：服务器
		case '4':	//非服务器计算机开始游戏
		if(serverip!=null)if(serverip.equals(xiaoxiduiyingip))if(stage==2){	if(isserver==0){
				int flag1=0;
			for(totalthing every:list){
				if(!every.name.equals(every.fenzu))
				if(every.ip.equals(bendiip)){
					if(every.ready==1)flag1=1;
				}
			}
			if(isserver==0)if(flag1==1){
				stage=3;
			MAXNUM=Integer.parseInt(splitted[1]);
			MAXSHUSHANG=Integer.parseInt(splitted[2]);
			chushicanshu(splitted[3]);	
			}
			}
			}
			return;//广播，接收：非服务器
		case '5':	//服务器修改非服务器计算机的分组
			if(serverip!=null)if(serverip.equals(xiaoxiduiyingip))if(stage==2){	zanshiip=ipzhuanhuan(splitted[2]);
			int tempposition1=0;
			int tempposition9=0;
			totalthing tempevery=null,tempevery2=null;
			for(totalthing every:list){
					if(every.ip!=null)
					if(every.ip.toString().equals(zanshiip.toString())){
						tempposition1=list.indexOf(every);
						tempevery=list.get(tempposition1);
						break;
					}
				}
			list.remove(tempposition1);
			for(totalthing every2:list){
				if(every2.name.equals(every2.fenzu) && every2.name.equals(tempevery.fenzu)){
					tempposition9=list.indexOf(every2);
					every2.ready=every2.ready-1;
				}
			}
			if(list.get(tempposition9).ready==0)list.remove(tempposition9);
			for(totalthing every1:list){
				if(every1.name.equals(every1.fenzu) && every1.name.equals(splitted[1])){
					tempevery2=every1;
				}
			}
			if(tempevery2==null){
				tempevery2=new totalthing();
				tempevery2.name=splitted[1];
				tempevery2.fenzu=splitted[1];
				tempevery2.ip=null;
				tempevery2.ready=0;
				list.add(tempevery2);
			}
			tempevery.fenzu=splitted[1];
			list.add(list.indexOf(tempevery2)+tempevery2.ready+1,tempevery);
			tempevery2.ready++;
			if(readyflag!=0)ready(4);//服务器刷新非服务器计算机修改分组的消息
			//shuaxinqita();
		}
			return;//单播，接收：服务器
		case '6':	//非服务器计算机显示准备页面
		
			if(isserver==0){
			if(readyflag==0){
				listtag.clear();
				list.clear();
			list=listzhuanhuan(splitted[1]);
			listtag=listtagzhuanhuan(splitted[2]);
			
			ready(1);
			readyflag++;
			serverip=xiaoxiduiyingip;
			addjiaru();
			}else{
				listtag.clear();
				list.clear();
				list=listzhuanhuan(splitted[1]);
				listtag=listtagzhuanhuan(splitted[2]);
				ready(4);
			}
			}
			return;//单播，接收：非服务器 针对stage=1阶段的发出请求的计算机。
		case '7':
			finishtotalplayers++;
			int tempposition2=0;
			for(totalthing every:list){
				if(!every.name.equals(every.fenzu))	
				if(every.ip.equals(xiaoxiduiyingip)){
						tempposition2=list.indexOf(every);
						break;
					}
				}
			list.get(tempposition2).time=Integer.parseInt(splitted[1]);
			if(totalplayers==finishtotalplayers){
				//开始计算结果
				listtidai=new ArrayList<totalthing>();
				for(totalthing every:list){
					if(every.name.equals(every.fenzu)){
						int i,temp=0;
						for(i=1;i<=every.ready;i++){
							temp=temp+list.get(list.indexOf(every)+i).time;
						}
						every.time=temp/every.ready;
					}
				}
				int i,totalgeshu=0;
				for(totalthing every:list){
					if(every.name.equals(every.fenzu))totalgeshu++;
				}
				for(i=1;i<=totalgeshu;i++){
					int fenzutemp=Integer.MAX_VALUE;
					int fenzuposition=0;
					for(totalthing every:list){
						if(every.name.equals(every.fenzu))
							if(every.time<fenzutemp){fenzutemp=every.time;fenzuposition=list.indexOf(every);}
					}
					listtidai.add(list.get(fenzuposition));
					
					int j;
					for(j=1;j<=list.get(fenzuposition).ready;j++){
						int fenzuchengyuan=Integer.MAX_VALUE;
						int chengyuanposition=0;
						int g;
						for(g=1;g<=list.get(fenzuposition).ready-j+1;g++){
							if(list.get(fenzuposition+g).time<fenzuchengyuan){fenzuchengyuan=list.get(fenzuposition+g).time;chengyuanposition=fenzuposition+g;}
						}
						listtidai.add(list.get(chengyuanposition));
						list.remove(chengyuanposition);
					}
					list.remove(fenzuposition);
				}
					list=listtidai;
				if(stage==4)ready(2);//本机显示结果
					stage=5;
					otherxianshifinish();//其他机子显示结果
			}
		
	
			return;//单播，接收：非服务器
		case '8':		//收到通知，非服务计算机显示结果
		if(serverip!=null)if(serverip.equals(xiaoxiduiyingip))if(stage==4){	int flag2=0;
			for(totalthing every:list){
				if(!every.name.equals(every.fenzu))
				if(every.ip.equals(bendiip)){
					if(every.ready==1)flag2=1;
				}
			}
			if(isserver==0)if(flag2==1){
			//dengdai.stopshow();
			list=listzhuanhuan(splitted[1]);
		
			ready(2);
			stage=5;
			}
		}
			return;	//广播，接收：非服务器
		case 'a':
			
			byte bufluyou[];
			bufluyou=new byte[BUFMAX];
			try {
				String thing=new String();
				thing="2;";
				thing=thing+splitted[1]+";"+splitted[2]+";"+xiaoxiduiyingip+";";
				bufluyou=thing.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
			sendguangfa(bufluyou);
			return;
		case 'b':
			byte bufluyou1[];
			bufluyou1=new byte[BUFMAX];
			try {
				String thing=new String();
				thing="3;";
				thing=thing+xiaoxiduiyingip+";";
				bufluyou1=thing.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			sendguangfa(bufluyou1);
			return;
		case 'c':
			byte bufluyou2[];
			bufluyou2=new byte[BUFMAX];
			try {
				String thing=new String();
				thing="5;";
				thing=thing+splitted[1]+";"+xiaoxiduiyingip+";";
				bufluyou2=thing.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			sendguangfa(bufluyou2);
			return;
		case 'd':
			byte bufluyou3[];
			bufluyou3=new byte[BUFMAX];
			try {
				String thing=new String();
				thing="e;";
				thing=thing+xiaoxiduiyingip+";";
				bufluyou3=thing.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			sendguangfa(bufluyou3);
			return;
		case 'e':
		if(serverip!=null)if(serverip.equals(xiaoxiduiyingip))if(stage!=1){	zanshiip=ipzhuanhuan(splitted[1]);
			int tempposition10=0;
			for(totalthing every:list){
				if(!every.name.equals(every.fenzu))	
				if(every.ip.equals(zanshiip)){
						tempposition10=list.indexOf(every);
						break;
					}
				}
			list.get(tempposition10).ready=0;
			if(readyflag!=0)ready(4);
		}
		return;
		case 'f':
			if(serverip!=null)if(serverip.equals(xiaoxiduiyingip)){
				final dingzhidialog zuihou1=new dingzhidialog(this);
				zuihou1.setTitle("楼主退出，该游戏已被解散！");
				zuihou1.setshijian(new OnClickListener(){
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						zuihou1.stopshow();
						finish();
					}
					
				}, new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
							zuihou1.stopshow();
							finish();
					}
					
				});
				zuihou1.startshow();			
				}
			return;
		case 'g':
			byte bufluyou4[];
			bufluyou4=new byte[BUFMAX];
			try {
				String thing=new String();
				thing="h;";
				thing=thing+xiaoxiduiyingip+";";
				bufluyou4=thing.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			sendguangfa(bufluyou4);
			return;
		case 'h':
			if(serverip!=null)if(serverip.equals(xiaoxiduiyingip))if(stage==2 ||stage==5){
				zanshiip=ipzhuanhuan(splitted[1]);
				int tempposition1=0,tempposition9=0;
			totalthing tempevery=null,tempevery2=null;
			for(totalthing every:list){
					if(every.ip!=null)
					if(every.ip.toString().equals(zanshiip.toString())){
						tempposition1=list.indexOf(every);
						tempevery=list.get(tempposition1);
						break;
					}
				}
			list.remove(tempposition1);
			for(totalthing every2:list){
				if(every2.name.equals(every2.fenzu) && every2.name.equals(tempevery.fenzu)){
					tempposition9=list.indexOf(every2);
					every2.ready=every2.ready-1;
				}
			}
			if(list.get(tempposition9).ready==0)list.remove(tempposition9);
			if(readyflag!=0)ready(4);
			}
			return;
		case 'i':
			if(stage==2){daijiaru++;
			shuaxinqita(xiaoxiduiyingip);}
			if(stage!=2){
				byte bufluyou5[];
				bufluyou5=new byte[BUFMAX];
				try {
					bufluyou5=new String("j;").getBytes("ISO-8859-1");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				senddanfa(bufluyou5,xiaoxiduiyingip);	
			}
			return;//单播，非服务器计算机准备加入服务器名单，进行状态确认！
		case 'j':
			new AlertDialog.Builder(Pkmodel.this)
		 	.setTitle("您加入晚了啊！请重新加入吧")
		    .show();
			return;//单播，非服务器计算机加入服务器名单
		case 'k':
			if(isserver==1)if(stage==2){
				zanshiip=ipzhuanhuan(splitted[1]);
				byte bufluyou5[];
				bufluyou5=new byte[BUFMAX];
				
				int tempposition7=0;
				for(totalthing every:list){
					if(!every.name.equals(every.fenzu))	
					if(every.ip.equals(bendiip)){
							tempposition7=list.indexOf(every);
							break;
						}
					}
				
				try {
					String thing=new String();
					thing="l;";
					
					thing=thing+list.get(tempposition7).name+";";
					bufluyou5=thing.getBytes("ISO-8859-1");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				senddanfa(bufluyou5,zanshiip);
			}
			return;//寻找可用主机，可用主机回复
		case 'l':
			totalthing tempjiben9=new totalthing();
			tempjiben9.name=splitted[1];
			tempjiben9.ip=xiaoxiduiyingip;
			listzhuji.add(tempjiben9);
			adapter3.notifyDataSetChanged();
			return;//单播，寻找可用主机的主机将可用结果加入
		case 'm':
			zanshiip=ipzhuanhuan(splitted[1]);
			byte bufluyou8[];
			bufluyou8=new byte[BUFMAX];
			try {
				String thing=new String();
				thing="k;"+zanshiip+";";
				bufluyou8=thing.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			sendguangfa(bufluyou8);
			return;//代理进行转发
		case 'n':
			if(isserver==1)if(serverip.equals(bendiip))daijiaru--;
			//shuaxinqita(xiaoxiduiyingip);
			return;
		case 'o':
			if(isserver==1)if(serverip.equals(bendiip))daijiaru++;
			return;
		case 'p':
			if(isserver==1)if(serverip.equals(bendiip))if(chongxinshuaxin==0){	byte bufluyou13[];
			bufluyou13=new byte[BUFMAX];
			try {
				String thing=new String();
				thing="q;";
				bufluyou13=thing.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			sendguangfa(bufluyou13);
			chongxinshuaxin=1;}
			return;
		case 'q':
			if(serverip!=null)if(serverip.equals(xiaoxiduiyingip)){
				for(totalthing every:list){
					if(!every.name.equals(every.fenzu))	
						every.ready=0;
					}
			}
			return;
		default:
			return;		
		}
		
	}
	
	
	void chushibianliang(){	
				try {
					danfa=new InetSocketAddress(DUANKOUDAN);
					danfaserver=new DatagramSocket(danfa);
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
    private boolean checkWifi() {  
        boolean isWifiConnect = true;  
        ConnectivityManager cm = (ConnectivityManager)Pkmodel.this.getSystemService(Context.CONNECTIVITY_SERVICE);  
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();  
        for (int i = 0; i<networkInfos.length; i++) {  
            if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {  
               if(networkInfos[i].getType() == cm.TYPE_MOBILE) {  
                   isWifiConnect = false;  
               }  
               if(networkInfos[i].getType() == cm.TYPE_WIFI) {  
                   isWifiConnect = true;  
               }  
            }         
    }
        return isWifiConnect;
    }
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	//	main=new RelativeLayout(this);
		if(checkWifi()==false){
			final dingzhidialog a=new dingzhidialog(this);
				a.setTitle("没有打开wifi,打开后重启");
				a.removejujuebutton();
				a.setshijian(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						a.stopshow();
						finish();
						
					}
					
				}, new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
							
						
					}
					
				});
				a.startshow();
		}
		list=new ArrayList<totalthing>();
		
		listtag=new ArrayList<String>();
		listzhuji=new ArrayList<totalthing>();
		stage=1;
		duilie=new ArrayList<duiliejiegou>();
		gestureDetector=new GestureDetector(this,new MyGestureListener(this));
		timer=new timethread();
		timer.start();
		
		huoqubendiip();
		if(bendiip!=null)huoquguangboip();
		addpkbutton(0);
		
		//后台开始侦听
		
			chushibianliang();
	
		
		danfaserverxian=new serverdanfa();
		danfaserverxian.start();
		
		
	
		
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
	
	
		
	
	
	
	}
	
    
    class serverdanfa extends Thread
  	{
    	private Looper neilooper;
  		public void run(){
  			Looper.prepare();
  			neilooper=Looper.myLooper();  			
  			 Handler serverdanfa = new Handler(){  				
 		        public void handleMessage(android.os.Message msg) {  
 		        	switch(msg.what)
 		        	{
 		        	case 1:
 		        		while(true){
 		        			try{
 		        					byte buf[]=null;
 		        					buf=new byte[BUFMAX];
 		        					DatagramPacket packet=new DatagramPacket(buf,buf.length);	
 		        					danfaserver.receive(packet);
 		        					duifangdanip=packet.getAddress();
 		        					buf=packet.getData();
 		        					Message a=Message.obtain(zhudanfa);
 		        					Bundle b= new Bundle();
 		        					b.putByteArray("xiaoxi",buf);
 		        					a.setData(b);
 		        					duiliejiegou zanshi=new duiliejiegou();
 		        					zanshi.message=a;
 		        					zanshi.ip=duifangdanip;
 		        					duilie.add(zanshi);
 		        					a.sendToTarget();
 		        					//zhudanfa.sendEmptyMessage(buf[0]);
 		        				}catch(IOException h){
 		        					break;
 		        				}
 		        		}
 		        	}
 		        }
  			 };
  			serverdanfa.sendEmptyMessage(1);	
			Looper.loop();
  			}
  		void exit()
  		{
  			neilooper.quit();
  		}
  	}
    
    
    void huoqubendiip()
    {
		
		 try {  
		       for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {  
		           NetworkInterface intf = en.nextElement();  
		           for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {  
		               InetAddress inetAddress = enumIpAddr.nextElement();  
		               if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {  
		          try {
						bendiip=InetAddress.getByName(inetAddress.getHostAddress());
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		               }  
		  
		           }  
		       }  
		   } catch (SocketException ex) {  
		   }
		if(bendiip==null){
			new AlertDialog.Builder(Pkmodel.this)
	    	 .setTitle("请确保您和您的小伙伴加入了一个无线网！确认后请重新启动！")
			 .setPositiveButton("确定",null)
			 	.show();
			return;
		}
		Pattern pat3 = Pattern.compile("^/(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                +"1$");//bendiip.toString().equals("/192.168.43.1")
		if(bendiip!=null)if(pat3.matcher(bendiip.toString()).matches()){
			isap=1;
			/*new AlertDialog.Builder(Pkmodel.this)
    		 .setTitle("输入分组数不符合条件")
    		 .setPositiveButton("确定",null)
    		 	.show();*/
    		 		//return;
    	 		}else{isap=0;}
		 if(isap==1){
		 String line = null;
			BufferedReader br=null;
			try {
				 br = new BufferedReader(new FileReader("/proc/net/arp"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				try {
					line = br.readLine();
					line=br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String[] splitted = line.split(" +");
				/*new AlertDialog.Builder(Pkmodel.this)
          	 .setTitle(splitted[0].toString())
      		 	.show();*/
				
				if (splitted != null && splitted.length >= 4) {
					try {
						dailiip=InetAddress.getByName(splitted[0]);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
		 }
    }
    
    void huoquguangboip()
    {
    	 int prefix=0;  
         int[] ipSplit=new int[4];  
          int[] bendiipshuzi=new int[4];
          int[] jieguo=new int[4];
        /* InetAddress localMachine=null;  
         try {  
             localMachine=InetAddress.getLocalHost();  
         } catch (UnknownHostException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }  */
         NetworkInterface netCard=null;  
         try {  
             netCard=NetworkInterface.getByInetAddress(bendiip);  
         } catch (SocketException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }  
          List<InterfaceAddress> localInterface=null;  
          localInterface=netCard.getInterfaceAddresses();  
          Iterator<InterfaceAddress> iterator=null;  
          iterator=localInterface.iterator();  
          while(iterator.hasNext()){  
              InterfaceAddress temp=null;  
              temp=iterator.next();  
              prefix=temp.getNetworkPrefixLength();  
          }  
          
         String tempip[]=bendiip.toString().split("\\.");
      	tempip[0]=tempip[0].substring(tempip[0].indexOf("/")+1,tempip[0].length());
          int i=0;
          for(i=0;i<=3;i++)bendiipshuzi[i]=Integer.parseInt(tempip[i]);
          
          int index=0;  
          int split=0;  
          int remainder=0;  
          split=prefix/8;  
          remainder=prefix%8;  
          while(index<split){  
              ipSplit[index]=255;  
              
              jieguo[index]=bendiipshuzi[index];
              index++;  
          }  
          if(remainder!=0){
          if(remainder==1)  
              {ipSplit[index]=128;
          		jieguo[index]=bendiipshuzi[index] & ipSplit[index];
          		jieguo[index]=jieguo[index] | 127;}
          if(remainder==2)  
              {ipSplit[index]=192; 
              jieguo[index]=bendiipshuzi[index] & ipSplit[index];
              jieguo[index]=jieguo[index] | 63;
              	} 
          if(remainder==3)  
              {ipSplit[index]=224;
              jieguo[index]=bendiipshuzi[index] & ipSplit[index];
              jieguo[index]=jieguo[index] | 31;
              	}  
          if(remainder==4)  
              {ipSplit[index]=240;
              jieguo[index]=bendiipshuzi[index] & ipSplit[index];
              jieguo[index]=jieguo[index] | 15;
              	}  
          if(remainder==5)  
              {ipSplit[index]=248;
              jieguo[index]=bendiipshuzi[index] & ipSplit[index];
              jieguo[index]=jieguo[index] | 7;
              	}  
          if(remainder==6)  
              {ipSplit[index]=252;
              jieguo[index]=bendiipshuzi[index] & ipSplit[index];
              jieguo[index]=jieguo[index] | 3;
              }  
          if(remainder==7) { 
              ipSplit[index]=254; 
              jieguo[index]=bendiipshuzi[index] & ipSplit[index];
              jieguo[index]=jieguo[index] | 1;
          } 
          index++;  }
          while(index<=3){  
              ipSplit[index]=0;  
              
              jieguo[index]=255;
              index++;  
          }  
       
    	try {
			guangbofa=InetAddress.getByName(jieguo[0]+"."+jieguo[1]+"."+jieguo[2]+"."+jieguo[3]);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
     
     
    	
    }
	void addpkbutton(int flag)
	{setContentView(R.layout.activity_pkmodel_oncreate);
		//TableRow tiaozhanfrow=new TableRow(this);
		//Button pk=new Button(this);
		Button pk=(Button)findViewById(R.id.button11);
		pk.setText("Let's pk!");
		pk.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(bendiip.toString().equals("/192.168.43.1")){
				/*	new AlertDialog.Builder(Pkmodel.this)
	             	 .setTitle(bendiip.toString())
	         		 	.show();*/
					String line;
					BufferedReader br=null;
					try {
						 br = new BufferedReader(new FileReader("/proc/net/arp"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						line = br.readLine();
						line=br.readLine();
						String[] splitted = line.split(" +");
						/*new AlertDialog.Builder(Pkmodel.this)
		             	 .setTitle(splitted[0].toString())
		         		 	.show();*/
						
						if (splitted != null && splitted.length >= 4) {
							dailiip=InetAddress.getByName(splitted[0]);
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					byte buf[];
			 		buf=new byte[BUFMAX];
			 		try {
						buf=new String("0;").getBytes("ISO-8859-1");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					senddanfa(buf,dailiip);	
				}else{
					benjiserverqidong();
				}
				// TODO Auto-generated method stub
						
			}});
		
		if(bendiip!=null)if(isap==1){pk.setEnabled(false);}
		if(bendiip==null){
			
		pk.setEnabled(false);
		}
		oncreatelist=(ListView)findViewById(R.id.group_list11);
		adapter3=new grouplistadapter3(this);
		oncreatelist.setAdapter(adapter3);
		oncreatelist.setOnItemClickListener(new itemclick());
	if(flag==0){	huoquzhu=new huoquzhujiip();
		huoquzhu.start();
		huoquzhu.huoquzhuji.sendEmptyMessageDelayed(1,1);}
	//	tiaozhanfrow.addView(pk);
	//	TableLayout tiaozhan=new TableLayout(this);
	//	tiaozhan.addView(tiaozhanfrow);
	//	RelativeLayout.LayoutParams centerparam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
	//	centerparam.addRule(RelativeLayout.CENTER_IN_PARENT);
	//	main.addView(tiaozhan,centerparam);
	}
	class itemclick implements OnItemClickListener {
		public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
			byte bufluyou[];
			bufluyou=new byte[BUFMAX];
			try {
				bufluyou=new String("i;").getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			senddanfa(bufluyou,listzhuji.get(position).ip);
		}
	}
	class grouplistadapter3 extends BaseAdapter{
		grouplistadapter3(Context context){
			
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listzhuji.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView==null){
				convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.group_list_item3, null);
			}
			TextView zhuji=(TextView)convertView.findViewById(R.id.group_list_item_text11);
			zhuji.setText(listzhuji.get(position).name);
			return convertView;
		}
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
		  
		if (keyCode == KeyEvent.KEYCODE_BACK) {  

			if(stage==1){
				final dingzhidialog zuihou=new dingzhidialog(this);
				zuihou.setTitle("还没开始就要退出？");
				zuihou.setshijian(new OnClickListener(){
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						zuihou.stopshow();
						finish();
					}
					
				}, new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
							zuihou.stopshow();
						
					}
					
				});
				zuihou.startshow();

			}
			if(stage==2 || stage ==5){
				if(isserver==1){
					
					final dingzhidialog zuihou=new dingzhidialog(this);
						zuihou.setTitle("您退出会解散游戏，继续？");
						zuihou.setshijian(new OnClickListener(){
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								String thing=new String();
								thing="f;";
					
								
								byte buf[]=null;
								try {
									buf=thing.getBytes("ISO-8859-1");
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						 		/*buf=new byte[BUFMAX];
						 		buf[0]=2;*/
								/* new AlertDialog.Builder(Pkmodel.this)
				             	 .setTitle(duifangdanip.toString())
				         		 .setPositiveButton("确定",null)
				         		 	.show();*/
								sendguangfa(buf);
								zuihou.stopshow();
							}
							
						}, new OnClickListener(){

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
									zuihou.stopshow();
								
							}
							
						});
						zuihou.startshow();

					
				}else if(isserver==0){

					
					final dingzhidialog zuihou=new dingzhidialog(this);
					zuihou.setTitle("继续退出？");
					zuihou.setshijian(new OnClickListener(){
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							String thing=new String();
							thing="g;";
							
							
							byte buf[]=null;
							try {
								buf=thing.getBytes("ISO-8859-1");
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 		
							senddanfa(buf,serverip);
							zuihou.stopshow();
							finish();
						}
						
					}, new OnClickListener(){

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
								zuihou.stopshow();
							
						}
						
					});
					zuihou.startshow();

					
					
				}
			}
			  if(stage==3 || stage==4){  
				new AlertDialog.Builder(Pkmodel.this)
		          	 .setTitle("您正在游戏中，不能退出！")
		      		 	.show();
				return true;}// 返回true,表示当前事件事件不再被传递，false继续传递  
		  }  
		  
		  return true;
		 }  
	protected void onDestroy(){
		super.onDestroy();
		timer.exit();
		huoquzhu.exit();
		danfaserverxian.exit();
		danfaserver.close();	
	}

}
