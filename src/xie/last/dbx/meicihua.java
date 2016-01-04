package xie.last.dbx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.view.View;

public class meicihua extends View {
float kai,zhuan,hebing1,hebing2;
double r,hebingr;
int i;
int flagxianshi;
Paint drawspace=new Paint();  
RectF quyu=new RectF();
public meicihua(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setBackgroundColor(Color.TRANSPARENT);
	}
void setcan(int i,float kai,float zhuan,int flagxian)
{	this.i=i;
	this.kai=kai;
	this.zhuan=zhuan;
	flagxianshi=flagxian;
	//this.invalidate();
	}
protected void onDraw(Canvas huaxian)
	{	
	if(i==1 || flagxianshi==2)
	{//huaxian.drawColor(Color.TRANSPARENT,Mode.CLEAR);
		 Paint p = new Paint();
	        //清屏
	        p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
	        huaxian.drawPaint(p);
	        p.setXfermode(new PorterDuffXfermode(Mode.SRC));
	}
	if(flagxianshi==2)r=First.chushir;
	if(flagxianshi==1)r=First.chushir-First.chushir/(First.MAXNUM-1)*(i-1);
	// new Rect(0,0,width-100,height-100));
	
	quyu.set((float)(First.centerx-r),(float)(First.centery-r),(float)(First.centerx+r),(float)(First.centery+r));
 drawspace.setColor(Color.GREEN);// 画笔为绿色   
 drawspace.setStrokeWidth(2);
 drawspace.setStyle(Paint.Style.STROKE);
 //huaxian.clipRect(quyu);
 huaxian.drawArc(quyu,kai,zhuan,false,drawspace);
 	if(flagxianshi==1)if(i!=1){
 		hebingr=r+First.chushir/(First.MAXNUM-1);
 		quyu.set((float)(First.centerx-hebingr),(float)(First.centery-hebingr),(float)(First.centerx+hebingr),(float)(First.centery+hebingr));
 		 drawspace.setColor(Color.YELLOW);//    
 		 drawspace.setStrokeWidth(4);
 		 drawspace.setStyle(Paint.Style.STROKE);
 		 huaxian.drawArc(quyu,hebing1,hebing2-hebing1,false,drawspace);
 			}
 	if(flagxianshi==2)if(i!=1){
 		drawspace.setColor(Color.YELLOW);
 		float hebingzhong=hebing1+(hebing2-hebing1)/2;
 		huaxian.drawCircle((float)(First.centerx+r*Math.cos(hebingzhong/360*(2*First.PI))),(float)(First.centery+r*Math.sin(hebingzhong/360*(2*First.PI))),First.ANDIS/2,drawspace);
 	}
	}

}
