package xie.last.dbx;






import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class First extends Activity {
	//变量定义开始
//	  private SurfaceView surfview;  
//	  private SurfaceHolder surfholder; 
		 static int MAXNUM=5;
		final static int ANDIS=100;
		final static int ANNIUDIS=60;
		static int flagxianshi=1;
		static int MAXSHUSHANG=100;
		static int JILUMAX=0;
		final static double PI=3.1415926;
		static double centerx;
		static double centery;
		static double chushir;
		int width;
		int height;
	    
		textshu buttongroup[];
		boardertext viewgroup[];
		mainsuan  zhuchengxu;
		RelativeLayout mainbuju;
	
		chuli2 jieguo2[][];
		meicihua meici;
		int flag[];
		 private GestureDetector gestureDetector=null;
		RelativeLayout.LayoutParams mainparam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams textparam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

		
		TableLayout jubuanniu;
		int totali=2;
		
		EditText shuru1;
		EditText shuru2;
		EditText nicheng;
		CheckBox xianflag;
		//qingping qingping;
		String timestring;int inttime=0,firstdeal,seconddeal,firstdealyu,seconddealyu;TextView xianshitime;//秒表相关变量
		Boolean timestop=false;
		int temptime;
		
		File cunchu;
		File cunchujuti;
		
		Boolean isrecord=true;
		int isxiankaifajianjie=1;
		int dijige;
		int stoptime;
	//变量定义结束
		
		
		//自定义秒表开始	相关变量：String timestring=new String();int inttime=0,firstdeal,seconddeal,firstdealyu,seconddealyu;TextView xianshitime=new TextView(this);
		 private Handler uiHandle = new Handler(){  
		        public void handleMessage(android.os.Message msg) {  
		        	
		        	/*TextView xia=new TextView(First.this);
		        	xia.setText(String.valueOf(timestop));
		        	if(inttime==0)new AlertDialog.Builder(First.this)
                  	 .setTitle("你好")
                  	 	.setView(xia)
              		 .setPositiveButton("确定",null)
              		 	.show();*/
		        	
		            switch(msg.what){  
		            case 1:  
		            	if(!timestop){
		            	if(inttime<12000){
		                	inttime++;
		                	firstdeal=inttime/10;
		                	firstdealyu=inttime%10;
		                	seconddeal=firstdeal/60;
		                	seconddealyu=firstdeal%60;
		                	timestring=String.valueOf(seconddeal)+"分:     "+String.valueOf(seconddealyu)+"."+String.valueOf(firstdealyu)+"秒";
		                	xianshitime.setText(timestring);
		              
		                uiHandle.sendEmptyMessageDelayed(1, 100);  
		                break;
		            	}else{
		            		inttime=0;
		            		 new AlertDialog.Builder(First.this)
                        	 .setTitle("打盹了吧，请重新开始吧！")
                    		 .setPositiveButton("确定",null)
                    		 	.show();
		            		
		            		 mainbuju.removeAllViews();
		   				  int a;
		   			  	totali=2;
		   				  for(a=0;a<MAXNUM;a++)flag[a]=0;
		   				  huatunext(1);
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
		
		//自定义秒表结束
		
		
		    //自定义文件初始化开始
		    void initfile() throws IOException{
		    	 Context cont = this.getApplicationContext();
		    	cunchu= new File(cont.getFilesDir()+"/xietemp.txt");
		    	cunchujuti= new File(cont.getFilesDir()+"/xietempjuti.txt");
		    	
		    	/*TextView xianshi=new TextView(this);
		    	xianshi.setText(cont.getFilesDir().toString());
		    	 new AlertDialog.Builder(First.this)
            	 .setTitle("文件目录")
            	 .setView(xianshi)
        		 .setPositiveButton("确定",null)
        		 	.show();*/
        		
		    		if(!cunchu.exists()){
		    			/*new AlertDialog.Builder(First.this)
	                   	 .setTitle("文件不存在！")
	               		 .setPositiveButton("确定",null)
	               		 	.show();*/
		    			cunchu.createNewFile(); 
		    			
		    			FileWriter output;
		    			output=new FileWriter(cunchu,false);
		    			BufferedWriter bo=new BufferedWriter(output);
		    			bo.write(String.valueOf(0)+";"+String.valueOf(MAXNUM)+";"+String.valueOf(MAXSHUSHANG)+";"+String.valueOf(flagxianshi)+"\n");
		    			bo.close();
		    			output.close();
		    		}else{
		    			/*new AlertDialog.Builder(First.this)
	                   	 .setTitle("文件已存在")
	               		 .setPositiveButton("确定",null)
	               		 	.show();*/
		    			FileReader input;
		    			input=new FileReader(cunchu);
		    			BufferedReader br=new BufferedReader(input);
		    			String a=new String();
		    			a=br.readLine();
		    				String[] array;
		    				array=a.split(";"); 
		    				JILUMAX=Integer.valueOf(array[0]);
		    				MAXNUM=Integer.valueOf(array[1]);
		    				MAXSHUSHANG=Integer.valueOf(array[2]);
		    				flagxianshi=Integer.valueOf(array[3]);
		    				/*for(String b :array){ 
		    					System.out.println(b); 
		    					} */
		    			br.close();
		    			input.close();
		    		}
		    }
		    //自定义文件初始化结束
		    
		//自定义函数开始
		void getcenter(){
			timestring=new String();
			
			
				DisplayMetrics metric = new DisplayMetrics();
			 	getWindowManager().getDefaultDisplay().getMetrics(metric);
		        width = metric.widthPixels;  // 屏幕宽度（像素）
		        height = metric.heightPixels;  // 屏幕高度（像素）
		     //   float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
		       // int densityDpi = metric.densityDpi;  
		        mainbuju=new RelativeLayout(this);
		     //   mainbuju=(RelativeLayout)LayoutInflater.from(First.this).inflate(R.layout.activity_first,mainbuju);
		      
		        mainbuju.setBackgroundColor(Color.rgb(224,255,255));
		      
		        //mainparam   ANDIS,ANDIS);
		        centerx=width/2;
		        centery=height/2;
		      
		       
		        
		        //jubuanniu.addRule(RelativeLayout.CENTER_VERTICAL);
		 /*       ViewTreeObserver viewObserver =nextone.getViewTreeObserver();
		        
		        viewObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
		        	public boolean onPreDraw()
		        	{
		        		int hei,wid;//nextone高度宽度
		        		  hei=nextone.getMeasuredHeight();
		        		  wid=nextone.getMeasuredWidth();
		        		 
	//	        		  nextone.setX((float)centerx-wid/2);
		//   		        nextone.setY((float)centery-hei/2);
		        		return true;
		        	}
		        
		       });*/
		        
		    //    surfview =new SurfaceView(this);  
		     //   surfholder = surfview.getHolder();  
		      //  mainbuju.addView(surfview);
		    
		      chushir=centery*0.5;
		        		//centerx-2*ANDIS;
		        
		       
		 
		}
		void xinyiju()
		{
			zhuchengxu=new mainsuan(MAXNUM,MAXSHUSHANG);
			zhuchengxu.firststage();
			zhuchengxu.mainsuanrukou();
			int a;
		  	totali=2;
			  for(a=0;a<MAXNUM;a++)flag[a]=0;
			mainbuju.removeView(xianshitime);
			huatunext(1);
			isrecord=true;
			 isxiankaifajianjie=1;
		}
		void zhanshi(){
			int i;
		//mainbuju.removeAllViews();
			//qingping=new qingping(this);
		//	mainbuju.addView(qingping);
		//	setContentView(mainbuju);
			huatunext(1);
			for(i=2;i<=MAXNUM;i++)huatunext(i);
			
		}
		
		 /* class nextonelink implements OnClickListener
	        {
	        	public void onClick(View v){
	        		//mainbuju.removeAllViews();
	        	
	        		
	        		
	        		
	        	}
	        	
	        }*/
		public boolean onTouchEvent(MotionEvent event) {  
	          
	        // 按下时清理之前的记录  
	      /*  if (event.getAction() == MotionEvent.ACTION_DOWN) {  
	            mRecordMap.clear();  
	        }  */
	          
	        return gestureDetector.onTouchEvent(event);  
	    }  
		  
		  public class MyGestureListener extends SimpleOnGestureListener{
			  public MyGestureListener(Context context) {
				  
				  // TODO Auto-generated constructor stub
				  //mContext=context;
				 }
			  public void onLongPress(MotionEvent e)
			  {isxiankaifajianjie=1;
				  uiHandle.sendEmptyMessageDelayed(2,1);
				  xianshitime.setText("");
				  mainbuju.removeAllViews();
				  zhanshi();
			  }
			  public boolean onDoubleTap(MotionEvent e)
			  {
				  uiHandle.sendEmptyMessageDelayed(2,1);
				  xianshitime.setText("");
				  mainbuju.removeAllViews();
				  int a;
			  	totali=2;
				  for(a=0;a<MAXNUM;a++)flag[a]=0;
				  huatunext(1);
				  isxiankaifajianjie=1;
				 
				  
				return true;
				  
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
		point zhuanxi(double jiaodu,double r,int anniudis)
		{
			
			point a=new point();
			a.x=centerx+r*Math.cos(jiaodu);
			a.x=a.x-anniudis/2;
			a.y=centery+r*Math.sin(jiaodu);
			a.y=a.y-anniudis/2;
			return a;
		}
		
		void huatunext(int i)//第i圈
		{int j;
		if(i==2)flag[1]=1;
		double r;
		 point linshi=new point();
		 
		 
		 
		 //mainbuju.removeAllViews();
		 if(i==1){
			 meici=new meicihua(this);  	
			
			 mainbuju.addView(meici);
			 
			
			//backview.setBackgroundColor(Color.TRANSPARENT);
			 meici.setcan(i,(float)(zhuchengxu.jieguo[i][1].shutheata/(2*PI)*360),(float)((zhuchengxu.jieguo[i][First.MAXNUM-i+1].shutheata-zhuchengxu.jieguo[i][1].shutheata)/(2*PI)*360),1);
			 		/*if(i!=1)
			 		{
			 			meici.hebing1=(float)(zhuchengxu.jieguo[i-1][zhuchengxu.hebing[i-1]].shutheata/(2*PI)*360);
			 			meici.hebing2=(float)(zhuchengxu.jieguo[i-1][zhuchengxu.hebing[i-1]+1].shutheata/(2*PI)*360);
			 		}*/
			 //meici.invalidate();
			
			
				
			 //setContentView(backview);
			
			 // mainbuju
			 
			//按钮插入开始
	//		 TableLayout.LayoutParams jubuparam=new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);
		        mainparam.addRule(RelativeLayout.CENTER_HORIZONTAL); 
		        mainparam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		        textparam.addRule(RelativeLayout.CENTER_HORIZONTAL); 
		        textparam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		        Button nextone=new Button(this);
		        Button shezhi=new Button(this);
		         Button jilu=new Button(this);
		         Button kaifajianjie=new Button(this);
		        nextone.setText("新一局");
		        shezhi.setText("设置参数");
		         jilu.setText("记录");
		         kaifajianjie.setText("开发者简介");
		        nextone.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						xinyiju();
						
					}});
		        shezhi.setOnClickListener(new OnClickListener(){
		        	
					@Override
					public void onClick(View v) {
						
						// TODO Auto-generated method stub
			//内容生成开始
					   TableRow duihuafrow=new TableRow(First.this);
					   TableRow duihuasrow=new TableRow(First.this);
					   TableRow duihuatrow=new TableRow(First.this);
				      TableLayout duihua=new TableLayout(First.this);
						TextView diyi=new TextView(First.this);
						diyi.setText("生成数大小上限(1-49)：");
						TextView dier=new TextView(First.this);
						dier.setText("生成数个数上限(3-12)：");
						TextView disan=new TextView(First.this);
						disan.setText("生成数显示方式是否用叠加圆：");
						
						shuru1=new EditText(First.this);
						shuru2=new EditText(First.this);
						xianflag=new CheckBox(First.this);
						
						shuru1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)}); 
						shuru2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
						shuru1.setWidth(100);
						shuru2.setWidth(100);
						
						shuru1.setText(Integer.toString(MAXSHUSHANG));
						shuru2.setText(Integer.toString(MAXNUM));
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
		   //内容生成结束
					 new AlertDialog.Builder(First.this)
					 	.setTitle("请输入相关参数")
					 	.setView(duihua)
					    //.setView()
					    .setPositiveButton("确定",
                         new DialogInterface.OnClickListener(){
                                 public void onClick(DialogInterface dialoginterface, int i){
                                     //按钮事件
                                	 Pattern pat1 = Pattern.compile("[1-9]|[1-4][0-9]");
                                	 Pattern pat2 = Pattern.compile("[3-9]|[1][0-2]");
                               	/*//调试代码开始
                                	 TextView tiaoshi=new TextView(First.this);
                            	     tiaoshi.setText(shuru1.getText().toString());
                              	 new AlertDialog.Builder(First.this)
                                	 .setTitle("生成数个数不符合条件！")
                            		 	.setView(tiaoshi)
                            		 .setPositiveButton("确定",null)
                            		 	.show();
                                	 //调试代码结束
                                */	  
                                	  
                               	 if(!pat1.matcher(shuru1.getText().toString()).matches()){
                                		 new AlertDialog.Builder(First.this)
                                		 .setTitle("生成数大小上限不符合条件！")
                                		 .setPositiveButton("确定",null)
                                		 	.show();
                                		 return;
                                	 	}
                               	if(!pat2.matcher(shuru2.getText().toString()).matches()){
                           		 new AlertDialog.Builder(First.this)
                           		 .setTitle("生成数个数上线不符合条件！")
                           		 .setPositiveButton("确定",null)
                           		 	.show();
                           		 		return;
                           	 		}
                               			{	
                               				
                               				MAXNUM=Integer.parseInt(shuru2.getText().toString());
                               				MAXSHUSHANG=Integer.parseInt(shuru1.getText().toString());
                               				isrecord=true;
                               				//初始化开始
                               			zhuchengxu=new mainsuan(MAXNUM,MAXSHUSHANG);
                               			zhuchengxu.firststage();
                    						zhuchengxu.mainsuanrukou();
                    						int a;
                    					  	totali=2;
                    					  	buttongroup=new textshu[MAXNUM+1];
                    						viewgroup=new boardertext[MAXNUM+1];
                    						jieguo2=new chuli2[MAXNUM+1][MAXNUM+1];
                    					  	flag=new int[MAXNUM];
                    						  for(a=0;a<MAXNUM;a++)flag[a]=0;
                    						  mainbuju.removeView(xianshitime);
                    						huatunext(1);
                    						//初始化结束
                    						
                    						if(xianflag.isChecked())flagxianshi=1;
                    						if(!xianflag.isChecked())flagxianshi=2;
                    						
                    						
                    		    			try {
                    		    				FileWriter output;
												output=new FileWriter(cunchu,false);
												output.write(String.valueOf(JILUMAX)+";"+String.valueOf(MAXNUM)+";"+String.valueOf(MAXSHUSHANG)+";"+String.valueOf(flagxianshi)+"\n");
	                    		    			output.close();
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
                    		    			
                    		    			 isxiankaifajianjie=1;
                               				new AlertDialog.Builder(First.this)
                                      		 .setTitle("设置成功")
                                      		 .setPositiveButton("确定",null)
                                      		 .show();
                                      		 return;
                               			}
                                 }
                         })
                      .setNegativeButton("取消", null)
					    .show();  
					}
		        	
		        		});
		        
		        	jilu.setOnClickListener(new OnClickListener(){

		        		@Override
		        			public void onClick(View v) {
		        					// TODO Auto-generated method stub
		        				Intent dakaijilu = new Intent();
		        					dakaijilu.setClass(First.this,jilu.class);
		        				startActivityForResult(dakaijilu,1);
		        	
						
		        			}});
		        
		        	kaifajianjie.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							TextView kaifa=new TextView(First.this);
							kaifa.setText("游戏介绍：点击符号会进行合并，最后会和平成一个数，请你找出一种合并方式使得这个数最大\n 可进行操作：单击红色符号进行合并 双击屏幕清空 长按屏幕显示结果\n开发者：谢帅  喜欢研究开源软件，Linux kernel，算法 \n联系我请email       544017782@qq.com 手机：15297300312\n欢迎学术交流，特别想和志同道合的朋友一块做点东西");
							
							RelativeLayout.LayoutParams kaifaparam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
							kaifaparam.addRule(RelativeLayout.CENTER_IN_PARENT);
							if(isxiankaifajianjie==1){mainbuju.addView(kaifa,kaifaparam);
														 isxiankaifajianjie=2;
														 setContentView(mainbuju);
															}
					
							/*	new AlertDialog.Builder(First.this)
                     		 .setTitle("1")
                     		 .setPositiveButton("确定",null)
                     		 .show();
							
													}
							if(isxiankaifajianjie%2==0){mainbuju.removeView(kaifa);
							
							new AlertDialog.Builder(First.this)
                     		 .setTitle("2")
                     		 .setPositiveButton("确定",null)
                     		 .show();
							}
							isxiankaifajianjie++;*/
							
							
						}
		        		
		        	});
		        TableRow diyirow=new TableRow(this);
		      //  TableLayout jubuanniu=new TableLayout(this);
		        jubuanniu=new TableLayout(this);
		        
		        diyirow.addView(nextone);
		        diyirow.addView(shezhi);
		         diyirow.addView(jilu);
		         diyirow.addView(kaifajianjie);
		        jubuanniu.addView(diyirow);
		        //jubuanniu.addView(shezhi);
		        mainbuju.addView(jubuanniu,mainparam);
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
			
			 
			if(i!=1){
			 meici=new meicihua(this);  	
			 mainbuju.addView(meici);
			//backview.setBackgroundColor(Color.TRANSPARENT);
			 meici.setcan(i,(float)(zhuchengxu.jieguo[i][1].shutheata/(2*PI)*360),(float)((zhuchengxu.jieguo[i][First.MAXNUM-i+1].shutheata-zhuchengxu.jieguo[i][1].shutheata)/(2*PI)*360),1);
			 		//if(i!=1)
			 		//{
			 			meici.hebing1=(float)(zhuchengxu.jieguo[i-1][zhuchengxu.hebing[i-1]].shutheata/(2*PI)*360);
			 			meici.hebing2=(float)(zhuchengxu.jieguo[i-1][zhuchengxu.hebing[i-1]+1].shutheata/(2*PI)*360);
			 	//	}
			 //meici.invalidate();
			
			
				
			 //setContentView(backview);
			
			 // mainbuju
			 setContentView(mainbuju);
			}
			//setContentView(meici); 
		}
		void usernextf(int i,int weizhi)
		{
			int j,m=1,n=weizhi;
			
		//	System.out.printf("%d", n);
			
//进行处理jieguo2开始
			if(i==2){
				timestop=false;
				 uiHandle.sendEmptyMessageDelayed(1,1); 
			 }
			 if(i==MAXNUM){
				 uiHandle.sendEmptyMessageDelayed(2,1); 
				
			 }
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
		new AlertDialog.Builder(First.this)
		.setTitle("您没有得到最大值，再接再励吧")
		.setPositiveButton("确定",null)
		.show();	
	}
	if(jieguo2[i][1].shu==zhuchengxu.jieguo[i][1].shu && !isrecord){
		
		//Log.i("inttime",String.valueOf(inttime));
		//Log.i("temptime",String.valueOf(temptime));
		if(inttime<=temptime){
			new AlertDialog.Builder(First.this)
			.setTitle("您用的时间比上一次短了")
			.setPositiveButton("确定",null)
			.show();
		}else{
			
			new AlertDialog.Builder(First.this)
			.setTitle("您用的时间比上一次长了")
			.setPositiveButton("确定",null)
			.show();
		}
		temptime=inttime;
	}
	
				if(jieguo2[i][1].shu==zhuchengxu.jieguo[i][1].shu && isrecord){
					isrecord=false;
					new AlertDialog.Builder(First.this)
				 	.setTitle("恭喜您！您得到了最大值，是否要保存一下？")//.setView()
				    .setPositiveButton("确定",
                     new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
							
							nicheng=new EditText(First.this);
							 new AlertDialog.Builder(First.this)
						 	.setTitle("请输入一个昵称：！")//
						 	.setView(nicheng)
						    .setPositiveButton("确定",
		                     new DialogInterface.OnClickListener(){
						    				
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
									/*	
										
						    			try {
						    				FileReader input;
											input=new FileReader(cunchu);
											BufferedReader br=new BufferedReader(input);
							    			String a=new String();
							    			a=br.readLine();
							    			a=br.readLine();
							    			while(a!=null){
							    			
							    				String[] array;
							    				array=a.split(";"); 
							    			if(nicheng.getText().toString().equals(array[1])){
							    					new AlertDialog.Builder(First.this)
							    					.setTitle("已存在该用户，对不起，请重新输入")
							    					.setPositiveButton("确定",null)
							    					.show();
							    					
							    				return;
							    				}
							    			a=br.readLine();
							    			}
							    			
							    			br.close();
							    			input.close();
											
										} catch (FileNotFoundException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
						    		*/
									
									try {int i;
										String hang=new String();
										JILUMAX=JILUMAX+1;
            		    				FileWriter output;
										output=new FileWriter(cunchu,false);
										output.write(String.valueOf(JILUMAX)+";"+String.valueOf(MAXNUM)+";"+String.valueOf(MAXSHUSHANG)+";"+String.valueOf(flagxianshi)+"\n");
                		    			output.close();
                		    		
                		    			SimpleDateFormat formatter=new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss     ");     
                		    			 Date curDate   =   new Date(System.currentTimeMillis());//获取当前时间     
                		    			String str=formatter.format(curDate);   
                		    			
                		    			FileWriter outputtianjia;
                		    			outputtianjia=new FileWriter(cunchujuti,true);
                		    			hang=str+";"+String.valueOf(MAXNUM)+";"+nicheng.getText().toString()+";"+String.valueOf(timestring)+";"+String.valueOf(stoptime)+";";
										for(i=1;i<=MAXNUM;i++)hang=hang+zhuchengxu.firstdealshu[i]+";";
										for(i=1;i<MAXNUM;i++)hang=hang+zhuchengxu.firstdealfu[i]+";";
										hang=hang+"\n";
                		    			outputtianjia.write(hang);
                		    			outputtianjia.close();
                		    			new AlertDialog.Builder(First.this)
				    					.setTitle("保存成功")
				    					.setPositiveButton("确定",null)
				    					.show();
                		    			xinyiju();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
						    	
						    })
						    .setNegativeButton("取消", null)
						    .show();
							
						}
				    	
				    })
				    .setNegativeButton("取消", null)
				    .show();
					
				temptime=inttime;
				}
				
				
}
//结束条件结束
			            
			            
//开始画图
			        double r;
					 point linshi=new point();
				
					 if(i==2){
						 mainbuju.removeView(jubuanniu);
					 }
					 if(flagxianshi==2){
						 mainbuju.removeAllViews();
						 
						 				 
						 
						 meici=new meicihua(this);  	
						 mainbuju.addView(meici);
						 meici.setcan(i,(float)(jieguo2[i][1].shutheata/(2*PI)*360),(float)((jieguo2[i][First.MAXNUM-i+1].shutheata-jieguo2[i][1].shutheata)/(2*PI)*360),flagxianshi);
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
						 meici=new meicihua(this);  	
						 mainbuju.addView(meici);
						 meici.setcan(i,(float)(jieguo2[i][1].shutheata/(2*PI)*360),(float)((jieguo2[i][First.MAXNUM-i+1].shutheata-jieguo2[i][1].shutheata)/(2*PI)*360),flagxianshi);
						 		if(i!=1)
						 		{
						 			meici.hebing1=(float)(jieguo2[i-1][n].shutheata/(2*PI)*360);
						 			meici.hebing2=(float)(jieguo2[i-1][n+1].shutheata/(2*PI)*360);
						 		}
						 setContentView(mainbuju);	
					}
//画图结束
		}
		
		//自定义函数结束

	
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				//自定义开始
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				
				try {
					initfile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				
				buttongroup=new textshu[MAXNUM+1];
				viewgroup=new boardertext[MAXNUM+1];
				jieguo2=new chuli2[MAXNUM+1][MAXNUM+1];
			
				
				flag=new int[MAXNUM+1];
				int a;for(a=1;a<=MAXNUM;a++)flag[a]=0;
				
				zhuchengxu=new mainsuan(MAXNUM,MAXSHUSHANG);
				zhuchengxu.firststage();
				zhuchengxu.mainsuanrukou();
				
				gestureDetector=new GestureDetector(this,new MyGestureListener(this));
				
				getcenter();
				huatunext(1);
				
				 
				//自定义结束


		
				}
		
		 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		      
			  switch(resultCode){
              case 1:
              // ResultActivity的返回数据
            
            		int i;
            		String[] array=null;
            		
			 	dijige= data.getExtras().getInt("diji");//得到新Activity 关闭后返回的数据
		   
			 	isrecord=false;
			 			
			 	try {
    				FileReader input;
					input=new FileReader(cunchujuti);
					BufferedReader br=new BufferedReader(input);
	    			String a=new String();
	    		
	    			for(i=1;i<=dijige+1;i++)a=br.readLine();
	 
	    				
	    			array=a.split(";"); 
	    				
	    				MAXNUM=Integer.parseInt(array[1]);
	    				temptime=Integer.parseInt(array[4]);;
	    			
	    				
	    			
	    			br.close();
	    			input.close();
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	 isxiankaifajianjie=1;
			 	
			 		//初始化开始
       			zhuchengxu=new mainsuan(MAXNUM,MAXSHUSHANG);
       				for(i=1;i<=MAXNUM;i++)zhuchengxu.firstdealshu[i]=Integer.parseInt(array[i+4]);
       				for(i=1;i<=MAXNUM-1;i++)zhuchengxu.firstdealfu[i]=array[i+4+MAXNUM].charAt(0);
					zhuchengxu.mainsuanrukou();
					int a;
				  	totali=2;
				  	buttongroup=new textshu[MAXNUM+1];
					viewgroup=new boardertext[MAXNUM+1];
					jieguo2=new chuli2[MAXNUM+1][MAXNUM+1];
				  	flag=new int[MAXNUM];
					  for(a=0;a<MAXNUM;a++)flag[a]=0;
					  mainbuju.removeView(xianshitime);
					  huatunext(1);
					
					//初始化结束
			 	
			 	
			 	
			  	break;
			  				default:
			  				Log.i("hello","nihao ");
			  				break;
			  		
			  	}
			
		    }
		/*public void onConfigurationChanged(Configuration newConfig) { 

	        try { 

	            super.onConfigurationChanged(newConfig); 

	            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { 

	               // Log.v("Himi", "onConfigurationChanged_ORIENTATION_LANDSCAPE");
	            	return;

	            } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { 

	               // Log.v("Himi", "onConfigurationChanged_ORIENTATION_PORTRAIT"); 
	            	return;
	            } 

	        } catch (Exception ex) { 

	        } 

	    } */
	
}
	

