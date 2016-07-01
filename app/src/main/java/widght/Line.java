package widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**描线类
 * Created by Creater Xu on 2016/6/30.
 */
public class Line extends View{
    private int mLength;
    private int mHigth;
    private Paint mPaint;//画笔

    public Line(Context context) {
        super(context);
        Log.e("xv","in f");
    }

    public Line(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("xv","in s");
    }

    public Line(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("xv","in t");
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
    }
/* TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.widght_Line,defStytle,0);
        int n=typedArray.getIndexCount();
        for (int i=0;i<n;i++){
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.widght_Line_line_higth:
                    mHigth=(int)typedArray.getDimension(attr, TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    Log.e("xv","mHigth="+mHigth);
                    break;
                case R.styleable.widght_Line_line_length:
                    mLength=(int)typedArray.getDimension(attr, TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    Log.e("xv","mLength="+mLength);
                    break;
            }
        }
        typedArray.recycle();*/


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // mPaint.setTextSize(mHigth);
        canvas.drawLine(0,0,10,0,mPaint);
    }
}
