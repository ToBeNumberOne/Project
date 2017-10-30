package com.example.rajesh.rjmoudles;

import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by acer-pc on 8/7/2017.
 */

class RJTextDefualtParams
{
    static String  strContext = new String("");
    static RJColor colorText  = new RJColor(0, 0, 0);
    static double  dTextSize  = 27;
    static String  strFont    = new String("");
}

public class RJText extends RJObject
{
    public String  m_strContext;
    public RJColor m_colorText;
    public double  m_dTextSize;
    public String  m_strFont;

    public RJText()
    {
        m_strContext    = RJTextDefualtParams.strContext;
        m_colorText     = RJTextDefualtParams.colorText;
        m_dTextSize     = RJTextDefualtParams.dTextSize;
        m_strFont       = RJTextDefualtParams.strFont;
    }

    public void mInitialize()
    {
        m_dWidth    = mGetDrawWidth();
        m_dHeigth   = mGetDrawHeigth();

        super.mInitialize();
    }

    public void mDraw()
    {
        mTranslatePoint(RJBasePoint.LeftCenter);
        mDrawByLeftCenter(m_ptOrigin);
    }

    public void mDrawByLeftCenter(PointF point)
    {
        Paint             paint;
        Paint.FontMetrics fm;
        double            dHeigth;
        PointF            ptDraw;

        paint = new Paint();
        paint.setTextSize((float)m_dTextSize);
        fm = paint.getFontMetrics();
        dHeigth = fm.bottom + (-fm.top);
        ptDraw = new PointF(point.x, point.y + (-fm.top - (float)(dHeigth / 2)));
        paint.setAntiAlias(true);
        paint.setColor(m_colorText.m_color);
        m_canvas.drawText(m_strContext, ptDraw.x, ptDraw.y, paint);
    }

    public double mGetDrawWidth()
    {
        Paint  paint;
        double dValue;

        paint = new Paint();
        paint.setTextSize((float)m_dTextSize);
        dValue = paint.measureText(m_strContext);
        return dValue;
    }

    public double mGetDrawHeigth()
    {
        Paint             paint;
        Paint.FontMetrics fm;
        double            dValue;

        paint = new Paint();
        paint.setTextSize((float)m_dTextSize);
        fm = paint.getFontMetrics();
        dValue = fm.bottom + (-fm.top);
        return dValue;
    }
}