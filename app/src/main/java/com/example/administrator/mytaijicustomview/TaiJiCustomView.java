package com.example.administrator.mytaijicustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class TaiJiCustomView extends View {
    Paint whitePaint,blackPaint;

    public TaiJiCustomView(Context context) {
        super(context);
        init();
    }

    public TaiJiCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TaiJiCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        whitePaint=new Paint();
        blackPaint=new Paint();

        whitePaint.setColor(Color.WHITE);
        whitePaint.setAntiAlias(true);

        blackPaint.setColor(Color.BLACK);
        blackPaint.setAntiAlias(true);
    }

    float angle;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height=getHeight();
        int width=getWidth();
        int bigRadio=Math.min(height,width);
        //画背景颜色
        canvas.drawColor(Color.GRAY);
        //高度移动到中心
        canvas.translate(0,(height-bigRadio)/2);
        //旋转画布
        canvas.rotate(angle,bigRadio/2,bigRadio/2);                   //因为画布旋转对之前的绘画没有影响，所以得放在前面，如果放在最后，没有效果
        angle++;
        //画圆狐区域
        RectF rectF=new RectF(0,0,bigRadio,bigRadio);
        canvas.drawArc(rectF,90,180,true,blackPaint);
        canvas.drawArc(rectF,-90,180,true,whitePaint);

        //画半圆，（只是被遮挡，实际是一个圆）
        canvas.drawCircle(bigRadio/2,bigRadio/2+bigRadio/4,bigRadio/4,whitePaint);
        canvas.drawCircle(bigRadio/2,bigRadio/2-bigRadio/4,bigRadio/4,blackPaint);
        //画最后两个小圆
        canvas.drawCircle(bigRadio/2-bigRadio/8,bigRadio/2+bigRadio/4,bigRadio/16,blackPaint);
        canvas.drawCircle(bigRadio/2+bigRadio/8,bigRadio/2-bigRadio/4,bigRadio/16,whitePaint);


        postInvalidateDelayed(1);
    }


}
