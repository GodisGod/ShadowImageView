package study.com.shadowimageview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by  HONGDA on 2019/1/16.
 */
public class ShadowImageView extends AppCompatImageView {

    private Bitmap bitmap;
    private Paint mPaint;
    private int width, height;

    public ShadowImageView(Context context) {
        this(context, null);
    }

    public ShadowImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        width = 100;
        height = 100;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas cv) {
        super.onDraw(cv);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            //步骤1：先生成一个bitmap，在bitmap上绘制原图
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, getWidth(), getHeight());
            drawable.draw(bitmapCanvas);

            //步骤2：生成圆形图片蒙版
            Bitmap mask = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mask);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLACK);
            canvas.drawOval(new RectF(0.0f, 0.0f, width, height), paint);

            //步骤3：使用DST_IN的方式在原图的bitmap上绘制蒙版图
            mPaint.reset();
            mPaint.setFilterBitmap(false);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            bitmapCanvas.drawBitmap(mask, 0.0f, 0.0f, mPaint);

            //步骤4：将最后生成的这个bitmap绘制到View的canvas上
            if (bitmap != null) {
                mPaint.setXfermode(null);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
            }
        }
    }

}
