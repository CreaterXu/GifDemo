package widght;

import android.content.Context;
import android.content.res.TypedArray;
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

    public Line(Context context) {
        this(context,null);
    }
    public Line(Context context,AttributeSet attrs) {
        this(context,attrs,0);
    }
    public Line(Context context, AttributeSet attrs,int defStytle){
        super(context,attrs,defStytle);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.Line,defStytle,0);
        int n=typedArray.getIndexCount();
        for (int i=0;i<n;i++){
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.Line_line_higth:
                    mHigth=(int)typedArray.getDimension(attr, TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    Log.e("xv","mHigth="+mHigth);
                    break;
                case R.styleable.Line_line_length:
                    mLength=(int)typedArray.getDimension(attr, TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    Log.e("xv","mLength="+mLength);
                    break;
            }
        }
        typedArray.recycle();
    }
}
