package xie.last.dbx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.TextView;

public class textshu extends TextView{

	public textshu(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setTextColor(android.graphics.Color.rgb(255, 0, 255));
		
		getPaint().setFakeBoldText(true); 
		setTextSize(20);
	}
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);  
		Paint paint2 = new Paint(); 
	        paint2.setStyle(Paint.Style.STROKE);
	        paint2.setColor(android.graphics.Color.RED);  
		//canvas.drawRect(0,0,First.ANDIS,First.ANDIS,paint2);
       // canvas.drawCircle(First.ANDIS/2,First.ANDIS/2,First.ANDIS/2,paint2);
	}

}
