package widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.skystudio.gifdemo.R;

/**描线类
 * Created by Creater Xu on 2016/6/30.
 */
public class Line extends View{
    private int mLength;
    private int mHigth;
    private Paint mPaint;//画笔

    public Line(Context context) {
        this(context,null,0);
    }

    public Line(Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }

    public Line(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);

        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.widght_Line,defStyle,0);
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
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // mPaint.setTextSize(mHigth);
        canvas.drawLine(0,0,getWidth(),mHigth,mPaint);
    }
}
