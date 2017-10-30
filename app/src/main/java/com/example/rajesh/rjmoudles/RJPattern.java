package com.example.rajesh.rjmoudles;

import android.graphics.Paint;

/**
 * Created by Administrator on 2017/7/15.
 */

public class RJPattern extends RJObject
{
    public RJPattern()
    {
        m_color = new RJColor();
    }

    public void mDraw()
    {
        Paint paint;

        paint = new Paint();
        paint.setColor(m_color.m_color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        m_canvas.drawRect(m_rectObj.m_fLeft, m_rectObj.m_fTop, m_rectObj.m_fRight, m_rectObj.m_fBottom, paint);
    }
}