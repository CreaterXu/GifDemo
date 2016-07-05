package widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.MainThread;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.skystudio.gifdemo.R;

/**描线类  可实现画不同颜色的直线
 * Created by Creater Xu on 2016/6/30.
 */
public class Line extends View{
    private int mLength;
    private int mHigth;
    private int mColor;
    private int mCycle;//一个周期的宽度
    private int mLineType=LINE_TYPE_STRAIGHT;
    private Paint mPaint;//画笔
    private Canvas canvas;//画布
    private int mStart;//起始的标志

    public static final int LINE_TYPE_WAVE=1;
    public static  final int LINE_TYPE_STRAIGHT=2;
    public Line(Context context) {
        this(context,null,0);
    }

    public Line(Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }

    public Line(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
                case R.styleable.widght_Line_line_color:
                    mColor=typedArray.getColor(attr,Color.GRAY);
                    break;
                case R.styleable.widght_Line_line_type:
                    boolean type=true;
                    type=typedArray.getBoolean(attr,true);
                    if (type)mLineType=LINE_TYPE_STRAIGHT;
                    else {
                        mLineType=LINE_TYPE_WAVE;
                        moveSin.start();//启动曲线运动线程
                    }
                    break;
            }
        }
        typedArray.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int width,height;
        if (widthMode==MeasureSpec.EXACTLY){
            //设置了明确值或是match_parent
            width=widthSize;
        }else{
            width=widthSize;
        }
        if (heightMode==MeasureSpec.EXACTLY){
            //设置了明确值或是match_parent
            height=heightSize;
        }else{
            height=1;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        this.canvas=canvas;
        mCycle=2*getHeight();
        if(mLineType==LINE_TYPE_STRAIGHT){
            canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        }else {
            drawSin(getWidth(),getHeight(),mStart*90,canvas);
        }

    }



    /**
     * 绘制正弦曲线
     * @param width,height,canvas
     * */
    private void drawSin(int width,int height,int firstRadians,Canvas canvas){
        int oldX=0,oldY=0;
        for (int i=oldX+1;i<width+1;i++){
            double y= Math.sin(Math.toRadians((i%mCycle*360/mCycle)+firstRadians));
            canvas.drawLine(oldX,oldY,i,(int)((y+1)*height/2),mPaint);
            oldX=i;
            oldY=(int)((y+1)*height/2);
        }
    }

    /**将sin曲线运动起来的线程***********************/
    Thread moveSin=new Thread(){
        @Override
        public void run() {
            super.run();
            while (true){
                for (int i=0;i<4;i++){
                    mStart=i;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }
    };

}
