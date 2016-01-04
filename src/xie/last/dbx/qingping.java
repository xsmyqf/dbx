package xie.last.dbx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.view.View;

public class qingping extends View{
	public qingping(Context context){
		super(context);
	}
	protected void Ondraw(Canvas qingping)
	{
		 Paint p = new Paint();
	        //清屏
	        p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
	        qingping.drawPaint(p);
	        p.setXfermode(new PorterDuffXfermode(Mode.SRC));
	}
}
