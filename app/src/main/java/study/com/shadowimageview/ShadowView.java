package study.com.shadowimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by  HONGDA on 2019/1/17.
 */
public class ShadowView extends View {

    private Paint paint;
    private int with;
    private int height;
    private int progress = 0;
    private int color = Color.parseColor("#000000");
    public final static int TYPE_TOP = 0X1;
    public final static int TYPE_BOTTOM = 0X2;
    private int curType = TYPE_TOP;

    public ShadowView(Context context) {
        this(context, null);
    }

    public ShadowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#00aaff"));
        paint.setAntiAlias(true);
        paint.setAlpha(100);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        with = getMeasuredWidth();
        Log.i("LHD", "height = " + height + "  with = " + with);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = Math.min(with, height) / 2;//计算半径
        float x = with / 2 - r;
        float y = height / 2 - r;
        float endX = with / 2 + r;
        float endY = height / 2 + r;
        RectF oval = new RectF(x, y, endX, endY);
        float angle = 360 * progress / 100;
        float start = 90 - (angle / 2);
        float end = 90 + (angle / 2);
        Log.i("LHD", "x = " + x + "  y = " + y + "  endX = " + endX + "  endY = " + endY);
        Log.i("LHD", "start = " + start + "  end = " + end + "  angle = " + angle);
        //四个参数的意义
        //     * @param oval The bounds of oval used to define the shape and size of the arc
        //     * @param startAngle Starting angle (in degrees) where the arc begins
        //     * @param sweepAngle Sweep angle (in degrees) measured clockwise
        //     * @param useCenter If true, include the center of the oval in the arc, and close it if it is
        //     *            being stroked. This will draw a wedge
        //     * @param paint The paint used to draw the arc
        //1、圆的外切矩形，2、圆的开始角度，3、圆的总角度，4、是否连接圆形，5、画笔
        //注意第三个参数是圆的总角度，而不是圆的结束角度
        if (curType == TYPE_BOTTOM) {
            float start2 = end;
            float angle2 = 360 - angle;
            canvas.drawArc(oval, start2, angle2, false, paint);
        } else {
            canvas.drawArc(oval, start, angle, false, paint);
        }
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setCurType(int curType) {
        this.curType = curType;
    }

}
