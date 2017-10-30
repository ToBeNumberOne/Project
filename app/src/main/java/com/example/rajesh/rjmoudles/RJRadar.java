package com.example.rajesh.rjmoudles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Administrator on 2017/7/16.
 */

class RJRadarDefualtParams
{
    static double   dAngleAvg = 90;

    static double   dTopAngleStart = 45;
    static double   dTopAngleEnd = 45 + dAngleAvg;

    static double   dLeftAngleStart = dTopAngleEnd;
    static double   dLeftAngleEnd = dLeftAngleStart + dAngleAvg;

    static double   dBottomAngleStart = dLeftAngleEnd;
    static double   dBottomAngleEnd = dBottomAngleStart + dAngleAvg;

    static double   dRightAngleStart = dBottomAngleEnd;
    static double   dRightAngleEnd = dRightAngleStart + dAngleAvg;

    static RJColor  colorLine = new RJColor(0, 0, 0, 90);
    static int      nLevelCount = 3;
    static double   dTextInterval = 20;
    static double   dAngleStart = 90;
    static double   dRadius = 300;
    static double   dLineWidth = 2;

    static RJGraphicsType graphicsBoxType = RJGraphicsType.RegularPolygon;
}

public class RJRadar extends RJObject
{
    public      RJTarget                     m_tartget;
    public      RJTargetData                 m_targetData;
    public      RJColor[]                    m_arrColors;
    public      int                          m_nLevelCount;
    protected RJGroup        m_groupGraphicsBox;
    public    double         m_dRadius;
    public    double         m_dTextInterval;
    public    double         m_dAngleStart;
    public    RJColor        m_colorLine;
    public    double         m_dLineWidth;
    public    RJGroup        m_groupTargetData;
    public    boolean        m_bShowCenterLine;
    public    RJGraphicsType m_graphicsBoxType;
    public    PointF         m_ptCircleCenter;

    protected   double   m_dAngleAvg;
    protected   PointF[] m_arrPoints;


    public RJRadar()
    {
        m_tartget               = new RJTarget();
        m_targetData            = new RJTargetData();
        m_targetData.m_target   = m_tartget;
        m_targetData.m_radar    = this;
        m_arrColors             = null;
        m_nLevelCount           = RJRadarDefualtParams.nLevelCount;
        m_dAngleAvg             = 0;
        m_dTextInterval         = RJRadarDefualtParams.dTextInterval;
        m_dAngleStart           = RJRadarDefualtParams.dAngleStart;
        m_colorLine             = RJRadarDefualtParams.colorLine;
        m_dLineWidth            = RJRadarDefualtParams.dLineWidth;
        m_dRadius               = RJRadarDefualtParams.dRadius;
        m_groupGraphicsBox      = new RJGroup();
        m_groupTargetData       = new RJGroup();
        m_bShowCenterLine       = true;
        m_graphicsBoxType       = RJRadarDefualtParams.graphicsBoxType;
        m_arrPoints             = null;
        m_ptCircleCenter        = new PointF();
    }

    public void mAddTartget(String[] strText, double[] dValues)
    {
        long                nIndex;
        long                nCount;
        RJTargetItem        item;

        RJVariant variant;
        nCount = strText.length;

        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            item = new RJTargetItem();
            item.m_variant.mSetValue(strText[(int)nIndex]);
            item.m_dValue = dValues[(int)nIndex];
            m_tartget.m_groupData.mAddObject(item);
        }
    }

    public void mAddTargetData(double[] dValues)
    {
        RJTargetData    data;

        data = new RJTargetData();
        data.m_arrData = dValues;
        m_groupTargetData.mAddObject(data);
    }

    public void mAddTargetData(double[] dValues, String strName)
    {
        RJTargetData    data;

        data = new RJTargetData();
        data.m_arrData = dValues;
        data.m_strDataName = strName;
        m_groupTargetData.mAddObject(data);
    }

    public void mInitialize()
    {
        long                     nIndex;
        double                   dValue;
        RJGraphicsRegularPolygon graphicsRegularPolygon;
        double                   dValueAvg;
        long                     nCount;
        RJText                   text;
        int                      nSizeCount;
        RJTargetData             targetData;
        double                   dAngle;
        RJGrahpics               grahpics;
        RJGraphicsCircle         graphicsCircle;
        PointF[]                 arrTextPoints;
        double[]                 arrLineAngles;

        m_dHeigth = m_dWidth = m_dRadius * 2;

        super.mInitialize();

        mTranslatePoint(RJBasePoint.Center);
        m_ptCircleCenter.set(m_ptOrigin.x, m_ptOrigin.y);

        m_tartget.mInitialize();

        dValue = m_dRadius;
        grahpics = null;

        nSizeCount = (int)m_tartget.m_groupData.mSize();
        if(m_nLevelCount <= 0) m_nLevelCount = 1;
        m_arrColors = new RJColor[m_nLevelCount];
        dValueAvg = m_dRadius / (double) m_nLevelCount;

        for(nIndex = 0; nIndex < m_nLevelCount; nIndex++)
        {
            if(m_graphicsBoxType == RJGraphicsType.RegularPolygon)
            {
                grahpics = graphicsRegularPolygon = new RJGraphicsRegularPolygon();
                graphicsRegularPolygon.m_dRadius = dValue;
                graphicsRegularPolygon.m_dAngleStart = m_dAngleStart;
                graphicsRegularPolygon.m_nSideCount = nSizeCount;
            }
            else if(m_graphicsBoxType == RJGraphicsType.Circle)
            {
                grahpics = graphicsCircle = new RJGraphicsCircle();
                graphicsCircle.m_dRadius = dValue;
            }

            if(grahpics != null)
            {
                grahpics.m_dLineWidth = m_dLineWidth;
                grahpics.m_colorLine = m_colorLine;
                grahpics.m_ptOrigin = m_ptCircleCenter;
                grahpics.m_drawType = RJGrahpics.DrawType.Stroke_Only;
                grahpics.mInitialize();
                m_groupGraphicsBox.mAddObject(grahpics);
            }
            dValue -= dValueAvg;
        }

        arrTextPoints = null;
        arrLineAngles = null;
        if(nSizeCount > 0)
        {
            m_dAngleAvg = 360 / nSizeCount;
            m_arrPoints = new PointF[nSizeCount];
            arrTextPoints = new PointF[nSizeCount];
            arrLineAngles = new double[nSizeCount];
        }

        dAngle = m_dAngleStart;
        for(nIndex = 0; nIndex < nSizeCount; nIndex++)
        {
            m_arrPoints[(int)nIndex] = RJMath.sCalLinePoint(dAngle, m_ptCircleCenter, m_dRadius);
            arrTextPoints[(int)nIndex] = RJMath.sCalLinePoint(dAngle, m_ptCircleCenter, m_dRadius + m_dTextInterval);
            arrLineAngles[(int)nIndex] = dAngle;
            dAngle += m_dAngleAvg;
        }


        nCount = m_tartget.m_groupText.mSize();
        for(nIndex =0; nIndex < nCount; nIndex++)
        {
            text = (RJText) m_tartget.m_groupText.mGet(nIndex);
            text.m_ptOrigin = arrTextPoints[(int)(nIndex)];
            dValue = arrLineAngles[(int)nIndex];
            ((RJTargetItem)(m_tartget.m_groupData.mGet(nIndex))).m_dAngle = dValue;
            dValue = dValue >= 360 ? dValue - 360 : dValue;
            if((dValue >= RJRadarDefualtParams.dTopAngleStart) && (dValue < RJRadarDefualtParams.dTopAngleEnd)) text.m_basePoint = RJBasePoint.BottomCenter;
            else if(dValue >= RJRadarDefualtParams.dLeftAngleStart && dValue < RJRadarDefualtParams.dLeftAngleEnd) text.m_basePoint = RJBasePoint.RightCenter;
            else if(dValue >= RJRadarDefualtParams.dBottomAngleStart && dValue < RJRadarDefualtParams.dBottomAngleEnd) text.m_basePoint = RJBasePoint.TopCenter;
            else text.m_basePoint = RJBasePoint.LeftCenter;
        }

        nCount = m_groupTargetData.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            targetData = (RJTargetData) m_groupTargetData.mGet(nIndex);
            targetData.m_radar = this;
            targetData.m_target = m_tartget;
            targetData.m_graphicsIP.mSetColor(s_colorScheme.mGetNextColor());
            targetData.mInitialize();
        }
    }

    public void mDraw()
    {
        long         nIndex;
        long         nCount;
        RJGrahpics   gs;
        long         nPointIndex;
        long         nPointCount;
        Paint        paintLine;
        PointF       ptPoint;
        RJText       text;
        RJTargetData targetData;

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth((float)m_dLineWidth);
        paintLine.setColor(m_colorLine.mGetColor());
        nCount = m_groupGraphicsBox.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            gs = (RJGrahpics)m_groupGraphicsBox.mGet(nIndex);
            gs.m_canvas = m_canvas;
            gs.mDraw();
            if(nIndex == 0 && m_bShowCenterLine)
            {
                nPointCount = m_arrPoints.length;
                for(nPointIndex = 0; nPointIndex < nPointCount; nPointIndex++)
                {
                    ptPoint = m_arrPoints[(int)nPointIndex];
                    m_canvas.drawLine(m_ptCircleCenter.x, m_ptCircleCenter.y, ptPoint .x, ptPoint.y, paintLine);
                }
            }
        }

        nCount = m_tartget.m_groupText.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            text = (RJText) m_tartget.m_groupText.mGet(nIndex);
            text.m_canvas = m_canvas;
            text.mDraw();
        }

        nCount = m_groupTargetData.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            targetData = (RJTargetData) m_groupTargetData.mGet(nIndex);
            targetData.m_graphicsIP.m_canvas = m_canvas;
            targetData.mDrawPoint(m_canvas);
            targetData.m_graphicsIP.mDraw();
        }
    }
}

class RJTargetItem extends RJObjectBase
{
    public      RJVariant       m_variant;
    public      double          m_dValue;
    protected   double          m_dAngle;

    public RJTargetItem()
    {
        m_variant = new RJVariant();
        m_dValue = 1;
    }
}

class RJTargetDefualtParams
{
    static RJColor    colorText = new RJColor(0, 0, 0, 90);
}

class RJTarget extends RJObjectBase
{
    public RJGroup              m_groupData;
    public RJGroup              m_groupText;
    public RJVariant.ValueType  m_vt;

    public RJTarget()
    {
        m_groupData = new RJGroup();
        m_groupText = new RJGroup();
        m_vt = RJVariant.ValueType.Text;
    }

    public double mGetTargetValue(long nIndex)
    {
        return ((RJTargetItem)m_groupData.mGet(nIndex)).m_dValue;
    }

    public double mGetTargetAngle(long nIndex)
    {
        return ((RJTargetItem)m_groupData.mGet(nIndex)).m_dAngle;
    }

    public void mInitialize()
    {
        long            nIndex;
        long            nCount;
        RJText          text;
        RJTargetItem    item;

        switch (m_vt)
        {
            case Text:
                nCount = m_groupData.mSize();
                for(nIndex = 0; nIndex < nCount; nIndex++)
                {
                    text = new RJText();
                    item = (RJTargetItem) m_groupData.mGet(nIndex);
                    text.m_strContext = item.m_variant.m_strValue;
                    text.m_color = RJTargetDefualtParams.colorText;
                    text.mInitialize();
                    m_groupText.mAddObject(text);
                }
                break;
        }
    }
}

class RJTargetDataDefualtParams
{
    static double   dLineWidth          = 1;
    static double   dCircleWidth        = 4;
    static double   dCircleLineRadius   = 7;
}

class RJTargetData extends RJObjectBase
{
    static String s_AttributeColor = "Color";

    public double[]                   m_arrData;
    public RJGraphicsIrregularPolygon m_graphicsIP;
    public RJGraphicsCircle[]         m_arrGrahphicsPoint;
    public String                     m_strDataName;
    public RJTarget                   m_target;
    public RJRadar                    m_radar;
    public double                     m_LineWidth;

    public RJTargetData()
    {
        m_arrData           = null;
        m_graphicsIP        = new RJGraphicsIrregularPolygon();
        m_arrGrahphicsPoint = null;
        m_strDataName       = new String();
        m_LineWidth         = RJTargetDataDefualtParams.dLineWidth;
    }

    public void mSetAttribute(String strAttributes, RJVariant variant)
    {
        if(s_AttributeColor.compareTo(strAttributes) == 0)
        {
            m_graphicsIP.mSetAttribute(strAttributes, variant);
        }
    }

    public void mDrawPoint(Canvas canvas)
    {
        long nIndex;
        long nCount;

        for(nIndex = 0, nCount = m_arrGrahphicsPoint.length; nIndex < nCount; nIndex++)
        {
            m_arrGrahphicsPoint[(int) nIndex].m_canvas = canvas;
            m_arrGrahphicsPoint[(int) nIndex].mDraw();
        }
    }

    public void mInitialize()
    {
        long            nIndex;
        long            nCount;
        RJColor         color;

        m_graphicsIP.m_ptPoints = new PointF[m_arrData.length];
        m_arrGrahphicsPoint = new RJGraphicsCircle[m_arrData.length];
        nCount = m_graphicsIP.m_ptPoints.length;
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            m_graphicsIP.m_ptPoints[(int) nIndex] = new PointF();
            m_graphicsIP.m_drawType = RJGrahpics.DrawType.Fill_And_Stroke;
            m_graphicsIP.m_dLineWidth = m_LineWidth;
            mCalcDataPoint(nIndex);
        }

        nCount = m_arrGrahphicsPoint.length;
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
             color = (m_graphicsIP.m_drawType == RJGrahpics.DrawType.Stroke_Only ) ? m_graphicsIP.m_colorLine : ((m_graphicsIP.m_drawType == RJGrahpics.DrawType.Fill_Only) ? m_graphicsIP.m_colorFill : m_graphicsIP.m_colorFill );
             m_arrGrahphicsPoint[(int)nIndex] = new RJGraphicsCircle();
             m_arrGrahphicsPoint[(int)nIndex].mSetColor(color);
             m_arrGrahphicsPoint[(int)nIndex].m_drawType = RJGrahpics.DrawType.Stroke_Only;
             m_arrGrahphicsPoint[(int)nIndex].m_dLineWidth = RJTargetDataDefualtParams.dCircleWidth;
             m_arrGrahphicsPoint[(int)nIndex].m_dRadius = RJTargetDataDefualtParams.dCircleLineRadius;
             m_arrGrahphicsPoint[(int)nIndex].m_ptOrigin = m_graphicsIP.m_ptPoints[(int)nIndex];
        }
    }

    public void mCalcDataPoint(long nIndex)
    {
        double dTargetItemValue;
        double dTargetDataItemValue;
        double dRadius;
        double dAngle;
        PointF ptOrgin;

        dTargetItemValue = m_target.mGetTargetValue(nIndex);
        dAngle = m_target.mGetTargetAngle(nIndex);
        dTargetDataItemValue = m_arrData[(int)nIndex];
        dRadius = m_radar.m_dRadius;
        ptOrgin = m_radar.m_ptOrigin;
        m_graphicsIP.m_ptPoints[(int)nIndex] = RJMath.sCalLinePoint(dAngle, ptOrgin, dTargetDataItemValue / dTargetItemValue * dRadius);
    }
}