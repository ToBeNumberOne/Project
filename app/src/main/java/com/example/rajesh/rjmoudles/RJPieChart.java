package com.example.rajesh.rjmoudles;

import android.graphics.PointF;

/**
 * Created by Administrator on 2017/7/15.
 */

class RJPieChartDefualtParams
{
    static double  dAngleStart      = 45;
    static double  dMarkAfterOffset = 10;
    static double  dRadius          = 20;
    static String  strFomat         = "(%.2f%%)";
    static boolean bPercentMode     = false;
}

public class RJPieChart extends RJObject
{
    public RJGroup m_groupPiechartItems;
    public double  m_dAngleStart;
    public double  m_dMarkAfterOffset;
    public double  m_dRadius;
    public boolean m_bPercentMode;
    public PointF  m_ptCircleCenter;

    public void mInitialize()
    {
        RJPieChartItem  item;
        RJPieChartItem  itemLast;
        long            nIndex;
        long            nCount;
        double          dAngle;
        double          dTotal;

        m_dHeigth = m_dWidth = m_dRadius * 2;

        super.mInitialize();

        mTranslatePoint(RJBasePoint.Center);
        m_ptCircleCenter.set(m_ptOrigin.x, m_ptOrigin.y);

        nCount = m_groupPiechartItems.mSize();
        if(!m_bPercentMode)
        {
            for (dTotal = 0, nIndex = 0; nIndex < nCount; nIndex++) {
                item = (RJPieChartItem) m_groupPiechartItems.mGet(nIndex);
                dTotal += item.m_dPercent;
            }

            for (nIndex = 0; nIndex < nCount; nIndex++) {
                item = (RJPieChartItem) m_groupPiechartItems.mGet(nIndex);
                item.m_dPercent /= dTotal;
            }
        }

        s_colorScheme.mResetScheme();
        dAngle = 0;
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            item = (RJPieChartItem) m_groupPiechartItems.mGet(nIndex);
            if(nIndex == 0) dAngle = m_dAngleStart;
            else
            {
                itemLast = (RJPieChartItem) m_groupPiechartItems.mGet(nIndex - 1);
                dAngle = itemLast.m_sector.m_dAngleStart + itemLast.m_sector.m_dAngle;
            }
            item.m_sector.m_dAngleStart = dAngle;
            item.m_sector.m_colorFill = s_colorScheme.mGetNextColor();
            item.m_sector.m_ptOrigin.set(m_ptCircleCenter.x, m_ptCircleCenter.y);
            item.m_sector.m_dRadius = m_dRadius;
            item.m_sector.m_dAngle = item.m_dPercent * (double)360;
            item.m_text.m_basePoint = RJBasePoint.Center;
            item.m_text.m_strContext = item.m_text.m_strContext + String.format(RJPieChartDefualtParams.strFomat, item.m_dPercent * 100);
            item.m_text.m_ptOrigin = RJMath.sCalLinePoint(item.mGetAngleCenter(), m_ptCircleCenter, m_dRadius / 2);
            item.m_text.mInitialize();
        }
    }

    public void mAddData(String strText, double dPercent)
    {
        m_groupPiechartItems.mAddObject(new RJPieChartItem(strText, dPercent));
    }

    public RJPieChart()
    {
        m_groupPiechartItems    = new RJGroup();
        m_dAngleStart           = RJPieChartDefualtParams.dAngleStart;
        m_dMarkAfterOffset      = RJPieChartDefualtParams.dMarkAfterOffset;
        m_dRadius               = RJPieChartDefualtParams.dRadius;
        m_bPercentMode          = RJPieChartDefualtParams.bPercentMode;
        m_ptCircleCenter        = new PointF();
    }

    public void mDraw()
    {
        long            nIndex;
        long            nCount;
        RJPieChartItem  item;

        nCount = m_groupPiechartItems.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            item = (RJPieChartItem) m_groupPiechartItems.mGet(nIndex);
            item.m_text.m_canvas = m_canvas;
            item.m_sector.m_canvas = m_canvas;
            item.m_sector.mDraw();
            item.m_text.mDraw();
        }
    }
}

class RJPieChartItem extends RJObjectBase
{
    protected       RJGraphicsSector    m_sector;
    public          double              m_dPercent;
    public          RJText              m_text;

    public RJPieChartItem()
    {
        m_sector = new RJGraphicsSector();
        m_dPercent = 0;
        m_text = new RJText();
    }

    public RJPieChartItem(String strText, double dPercent)
    {
        m_sector = new RJGraphicsSector();
        m_dPercent = dPercent;
        m_text = new RJText();
        m_text.m_strContext = strText;
    }

    public double mGetAngleCenter()
    {
        return m_sector.m_dAngleStart + m_sector.m_dAngle / (double) 2;
    }
}