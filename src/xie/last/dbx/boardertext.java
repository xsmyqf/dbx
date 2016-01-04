package xie.last.dbx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.widget.TextView;

public class boardertext extends TextView{

	public boardertext(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setTextColor(android.graphics.Color.RED);
		getPaint().setFakeBoldText(true);
		setTextSize(25);
	}
	  	
	protected void onDraw(Canvas canvas) {  
	        super.onDraw(canvas);  
	        Paint paint = new Paint(); 
	        paint.setStyle(Paint.Style.STROKE);
	        paint.setColor(android.graphics.Color.RED);  		
	     //	canvas.drawRect(0,0,First.ANNIUDIS,First.ANNIUDIS,paint);
	        canvas.drawCircle(First.ANNIUDIS/2,First.ANNIUDIS/2,First.ANNIUDIS/2,paint);
	    }  
}
