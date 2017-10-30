package com.example.rajesh.rjmoudles;

import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by acer-pc on 8/7/2017.
 */

class RJCoordinateDefualtParams
{
    static RJCSSeriaType        seriaType               = RJCSSeriaType.Point;
    static RJGraphicsType       pointGraphics           = RJGraphicsType.Circle;
    static double               dAxiasLength            =  600;
    static double               dAxiaCorss              = 0;
    static double               dAxiaLineWidth          = 3;
    static RJColor              colorAxiaLine           = new RJColor(0, 0, 0);
    static double               dAxiaScaleLength        = 15;
    static double               dAixaScaleWidth         = 3;
    static RJColor              colorScale              = new RJColor(0, 0, 0);
    static boolean              bScaleTextCenter        = false;

    static double               dSeriaPointRadius       = 10;
    static double               dSeriaBarWidth          = 24;

    static double dSeriaLineWidth = 4;
    static double dTextInterval   = 9;
    static String strDoubleFormat = "%.2f";

    static RJColor              colorAxiaExtendLine     = new RJColor(96, 96, 96, 75);
    static double               dLineAxiaExtendWidth    = 2;
}

class RJVariant extends RJObjectBase
{
    enum ValueType
    {
        None,
        VTDouble,
        VTInt,
        VTDate,
        Text,
        VTColor,
    }

    public double    m_dValue;
    public String    m_strValue;
    public RJDate    m_dateValue;
    public int       m_nValue;
    public RJColor   m_colorValue;
    public ValueType m_vtType;

    public RJVariant()
    {
        m_dValue        = 0;
        m_strValue      = null;
        m_dateValue     = null;
        m_colorValue    = null;
        m_nValue        = 0;
        m_vtType        = ValueType.None;
    }

    public void mSetValue(double dValue)
    {
        m_dValue = dValue;
        m_vtType = ValueType.VTDouble;
    }

    public void mSetValue(int nValue)
    {
        m_nValue = nValue;
        m_vtType = ValueType.VTInt;
    }

    public void mSetValue(RJDate date)
    {
        m_dateValue = date;
        m_vtType    = ValueType.VTDate;
    }

    public void mSetValue(RJColor color)
    {
        m_colorValue    = color;
        m_vtType        = ValueType.VTColor;
    }

    public void mSetValue(String strValue)
    {
        m_strValue  = strValue;
        m_vtType    = ValueType.Text;
    }

    public RJVariant mDiv(RJVariant variant)
    {
        RJVariant   var;
        double      dValue;

        var = new RJVariant();
        switch (m_vtType)
        {
            case VTDouble:
                if(variant.m_vtType == ValueType.VTDouble)
                {
                    dValue = m_dValue / variant.m_dValue;
                    var.mSetValue(dValue);
                }
                break;
        }
        return var;
    }

    public int mCompare(RJVariant variant)
    {
        int         nValue;

        nValue = -2;
        switch (m_vtType)
        {
            case VTDouble:
                if(variant.m_vtType == ValueType.VTDouble)
                {
                    nValue = (m_dValue == variant.m_dValue) ? 0 : (m_dValue < variant.m_dValue ? -1 : 1);
                }
                break;

            case Text:
                if(variant.m_vtType == ValueType.Text)
                {
                    nValue = m_strValue.compareTo(variant.m_strValue);
                }
        }
        return nValue;
    }

    public RJVariant mAdd(RJVariant variant)
    {
        RJVariant   var;
        double      dValue;

        var = new RJVariant();
        switch (m_vtType)
        {
            case VTDouble:
                if(variant.m_vtType == ValueType.VTDouble)
                {
                    dValue = m_dValue + variant.m_dValue;
                    var.mSetValue(dValue);
                }
                break;
        }
        return var;
    }
}

class RJCSPoint extends RJObjectBase
{
    public RJVariant    m_variantX;
    public RJVariant    m_variantY;
    public RJGrahpics   m_graphics;
    public RJText       m_textLabel;
    public boolean      m_bShowLabel;

    public RJCSPoint()
    {
        m_variantX = new RJVariant();
        m_variantY = new RJVariant();
        m_graphics = new RJGrahpics();
        m_textLabel = new RJText();
        m_bShowLabel = false;
    }

    public void mSetGraphicsColor(RJColor color)
    {
        m_graphics.m_color = color;
    }
}

enum RJCSSeriaType
{
    Point,
    Line,
    Bar,
}

class RJCSSeria extends RJObject
{
    public      RJGroup          m_groupPoints;
    public      RJCSSeriaType    m_seriaType;
    public      RJGraphicsType   m_grahpicsType;
    public      RJGrahpics       m_graphicsCustom;
    public      double           m_dLineWidth;
    public      RJColor          m_colorLine;

    protected   RJAxias          m_axiaX;
    protected   RJAxias          m_axiaY;

    public RJCSSeria(RJAxias axiaX, RJAxias axiaY)
    {
        m_groupPoints = new RJGroup();
        m_seriaType = RJCoordinateDefualtParams.seriaType;
        m_grahpicsType = RJCoordinateDefualtParams.pointGraphics;
        m_graphicsCustom = null;
        m_dLineWidth = RJCoordinateDefualtParams.dSeriaLineWidth;

        m_axiaX = axiaX;
        m_axiaY = axiaY;
        m_colorLine = new RJColor();
    }

    public void mSetAttribute(String strAttributes, RJVariant variant)
    {
        long        nIndex;
        long        nCount;
        RJCSPoint    point;

        if(s_AttributeColor.compareTo(strAttributes) == 0 && variant.m_vtType == RJVariant.ValueType.VTColor)
        {
            m_colorLine = variant.m_colorValue;
            nCount = m_groupPoints.mSize();
            for(nIndex = 0; nIndex < nCount; nIndex++)
            {
                point = (RJCSPoint) m_groupPoints.mGet(nIndex);
                point.m_graphics.mSetAttribute(strAttributes, variant);
            }
        }
    }

    public RJCSPoint mAddData(String strX, double dY)
    {
        RJCSPoint point;

        point = new RJCSPoint();
        point.m_variantX.mSetValue(strX);
        point.m_variantY.mSetValue(dY);
        m_groupPoints.mAddObject(point);
        return point;
    }

    public RJCSSeria()
    {
        m_groupPoints = new RJGroup();
        m_seriaType = RJCoordinateDefualtParams.seriaType;
        m_grahpicsType = RJCoordinateDefualtParams.pointGraphics;
        m_graphicsCustom = null;
        m_dLineWidth = RJCoordinateDefualtParams.dSeriaLineWidth;
    }

    public void mDraw()
    {
        long      nIndex;
        long      nCount;
        RJCSPoint point;
        PointF    ptStart;
        PointF    ptEnd;
        Paint     paint;

        nCount = m_groupPoints.mSize();
        for (nIndex = 0; nIndex < nCount; nIndex++)
        {
            point = (RJCSPoint) m_groupPoints.mGet(nIndex);
            if (point != null)
            {
                point.m_graphics.m_canvas = m_canvas;
                point.m_graphics.mDraw();
            }
        }

        if (m_seriaType == RJCSSeriaType.Line)
        {
            nCount -= 1;
            paint = new Paint();
            paint.setColor(m_colorLine.mGetColor());
            paint.setAntiAlias(true);
            paint.setStrokeWidth((float) m_dLineWidth);
            for (nIndex = 0; nIndex < nCount; nIndex++)
            {
                point = (RJCSPoint) m_groupPoints.mGet(nIndex);
                ptStart = point.m_graphics.m_ptOrigin;
                point = (RJCSPoint) m_groupPoints.mGet(nIndex + 1);
                ptEnd = point.m_graphics.m_ptOrigin;
                m_canvas.drawLine(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y, paint);
            }
        }
    }

    public void mInitialize()
    {
        long                nIndex;
        long                nCount;
        RJCSPoint           point;
        RJGraphicsType      graphicsType;
        RJGraphicsCircle    graphicsCircle;
        RJGrahpicsRectangle grahpicsRectangle;

        //初始化每个点的图形
        graphicsType = RJGraphicsType.Circle;
        nCount = m_groupPoints.mSize();
        if (m_grahpicsType == RJGraphicsType.Custom) graphicsType = RJGraphicsType.Custom;
        else
        {
            switch (m_seriaType)
            {
                case Point:
                case Line:
                    graphicsType = m_grahpicsType;
                    break;

                case Bar:
                    graphicsType = RJGraphicsType.Rectangle;
                    break;
            }
            for (nIndex = 0; nIndex < nCount; nIndex++)
            {
                point = (RJCSPoint) m_groupPoints.mGet(nIndex);
                if (point != null)
                {
                    switch (graphicsType)
                    {
                        case Circle:
                            point.m_graphics = graphicsCircle = new RJGraphicsCircle();
                            graphicsCircle.m_dRadius = RJCoordinateDefualtParams.dSeriaPointRadius;
                            graphicsCircle.m_drawType = RJGrahpics.DrawType.Fill_Only;
                            point.m_graphics.m_ptOrigin.x = (float) m_axiaX.mGetPos(point.m_variantX);
                            point.m_graphics.m_ptOrigin.y = (float) m_axiaY.mGetPos(point.m_variantY);
                            break;

                        case Rectangle:
                            point.m_graphics = grahpicsRectangle = new RJGrahpicsRectangle();
                            grahpicsRectangle.m_dWidth = RJCoordinateDefualtParams.dSeriaBarWidth;
                            point.m_graphics.m_ptOrigin = m_axiaX.mGetPoint(point.m_variantX);
                            grahpicsRectangle.m_b2PointMode = true;
                            grahpicsRectangle.m_drawType = RJGrahpics.DrawType.Fill_Only;
                            grahpicsRectangle.m_ptEnd.x = (float) m_axiaX.mGetPos(point.m_variantX);
                            grahpicsRectangle.m_ptEnd.y = (float) m_axiaY.mGetPos(point.m_variantY);
                            break;

                        case Custom:
                            point.m_graphics = m_graphicsCustom.mCreateStyleGraphic();
                            break;
                    }
                }
            }
            mSetColor(m_color);
        }
    }

    public void mSetColor(RJColor color)
    {
        long nIndex;
        long nCount;

        nCount = m_groupPoints.mSize();
        for (nIndex = 0; nIndex < nCount; nIndex++) ((RJCSPoint) m_groupPoints.mGet(nIndex)).mSetGraphicsColor(color);
        m_colorLine = color;
    }

    public RJCSPoint mGetPoint(long nIndex)
    {
        return (RJCSPoint) m_groupPoints.mGet(nIndex);
    }
}

class RJAxias extends RJObject
{
    public RJContinueData m_continueData;
    public RJDiscreteData m_disreteData;
    public RJVariant      m_variantScaleUint;
    public boolean        m_bVertical;
    public boolean        m_bScaleTextCenter;
    public RJGroup        m_groupScaleText;
    public String         m_strFormat;

    public      double           m_dAxiaLength;
    public      double           m_dCross;

    public      double           m_dLineWidth;
    public      RJColor          m_colorLine;

    public      double           m_dScaleLength;
    public      double           m_dScaleWidth;
    public      RJColor          m_colorScale;

    private     double           m_dScaleUint;
    public      double           m_dTextInterval;
    public      RJGraphicsArrow  m_graphicsArrow;
    public      boolean          m_bShowArrow;
    public      boolean          m_bHiddenAxia;
    public      RJAxias          m_axiaRel;
    public      RJColor          m_colorSacleExtendLine;
    public      double           m_dLineSacleExtendWidth;
    public      boolean          m_bShowSacleExtendLine;

    public RJAxias()
    {
        m_continueData          = null;
        m_disreteData           = null;
        m_strFormat             = new String();
        m_dAxiaLength           = RJCoordinateDefualtParams.dAxiasLength;
        m_dCross                = RJCoordinateDefualtParams.dAxiaCorss;
        m_variantScaleUint      = new RJVariant();
        m_groupScaleText        = new RJGroup();
        m_bVertical             = true;
        m_dLineWidth            = RJCoordinateDefualtParams.dAxiaLineWidth;
        m_colorLine             = RJCoordinateDefualtParams.colorAxiaLine;

        m_dScaleLength          = RJCoordinateDefualtParams.dAxiaScaleLength;
        m_dScaleWidth           = RJCoordinateDefualtParams.dAixaScaleWidth;
        m_colorScale            = RJCoordinateDefualtParams.colorScale;
        m_bScaleTextCenter      = RJCoordinateDefualtParams.bScaleTextCenter;

        m_dTextInterval         = RJCoordinateDefualtParams.dTextInterval;
        m_graphicsArrow         = new RJGraphicsArrow();
        m_bShowArrow            = true;
        m_bHiddenAxia           = false;
        m_colorSacleExtendLine  = RJCoordinateDefualtParams.colorAxiaExtendLine;
        m_dLineSacleExtendWidth = RJCoordinateDefualtParams.dLineAxiaExtendWidth;
        m_bShowSacleExtendLine  = true;
    }

    public void mSetCross(RJVariant variant)
    {
        m_dCross = mGetRelativelyPos(variant);
    }

    public boolean mSetRange(double dMin, double dMax, double dScaleuint)
    {
        m_continueData = new RJContinueData();
        m_continueData.m_variantMin.mSetValue(dMin);
        m_continueData.m_variantMax.mSetValue(dMax);
        m_continueData.m_vt = RJVariant.ValueType.VTDouble;
        m_variantScaleUint.mSetValue(dScaleuint);
        return true;
    }

    public void mAddDiscreteData(String strText)
    {
        if(m_disreteData == null) m_disreteData = new RJDiscreteData();
        m_disreteData.mAddData(strText);
    }

    public double mGetPos(RJVariant variant)
    {
        double dValue;

        dValue = mGetRelativelyPos(variant);
        if(m_bVertical) dValue = m_ptOrigin.y - dValue;
        else dValue += m_ptOrigin.x;
        return dValue;
    }

    public PointF mGetPoint(RJVariant variant)
    {
        PointF point;
        double dValue;

        point = new PointF();
        dValue = mGetPos(variant);
        if(m_bVertical) point.set(m_ptOrigin.x, (float)dValue);
        else point.set((float)dValue, m_ptOrigin.y);
        return point;
    }

    public double mGetRelativelyPos(RJVariant variant)
    {
        long    nCount;
        long    nIndex;
        double  dValue;

        dValue = 0;
        if(m_disreteData != null)
        {
            nCount = m_disreteData.mSize();
            nIndex = m_disreteData.mGetSameIndex(variant);
            if(nIndex != -1)
            {
                dValue = ((nIndex + 1) / (double) nCount) * m_dAxiaLength;
                if(m_bScaleTextCenter) dValue -= (m_dScaleUint / 2);
            }
        }
        else if(m_continueData != null)
        {
            dValue = m_continueData.mGetPercent(variant);
            dValue = m_dAxiaLength * dValue;
        }
        return dValue;
    }

    public double mGetTextMaxWidth()
    {
        long    nIndex;
        long    nCount;
        RJText  text;
        double  dWidth;
        double  dMaxWidth;

        dMaxWidth = 0;
        nCount = m_groupScaleText.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            text = (RJText) m_groupScaleText.mGet(nIndex);
            if(text != null)
            {
                dWidth = text.mGetDrawWidth();
                if(nIndex == 0) dMaxWidth = dWidth;
                else if(dMaxWidth < dWidth) dMaxWidth = dWidth;
            }
        }
        return dMaxWidth;
    }

    public double mGetTextMaxHeight()
    {
        long    nIndex;
        long    nCount;
        RJText  text;
        double  dHeight;
        double  dMaxHeigth;

        dMaxHeigth = 0;
        nCount = m_groupScaleText.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            text = (RJText) m_groupScaleText.mGet(nIndex);
            if(text != null)
            {
                dHeight = text.mGetDrawHeigth();
                if(nIndex == 0) dMaxHeigth = dHeight;
                else if(dMaxHeigth < dHeight) dMaxHeigth = dHeight;
            }
        }
        return dMaxHeigth;
    }


    public String mGetFormatText(String strFomat, RJVariant variant)
    {
        String strValue;

        strValue = new String();
        if(variant.m_vtType == RJVariant.ValueType.VTDouble) strValue = String.format(m_strFormat.isEmpty() ? RJCoordinateDefualtParams.strDoubleFormat : m_strFormat, variant.m_dValue);
        else if(variant.m_vtType == RJVariant.ValueType.Text) strValue = variant.m_strValue;
        return strValue;
    }

    public void mInitializeBefore()
    {
        long      nCount;
        long      nIndex;
        RJText    text;
        String    strFormatText;
        RJVariant variant;

        if(m_disreteData != null)
        {
            m_groupScaleText.mAddObject(new RJText());
            nCount = m_disreteData.mSize();
            for(nIndex = 0; nIndex < nCount; nIndex++)
            {
                variant = m_disreteData.mGet(nIndex);
                strFormatText = mGetFormatText(m_strFormat, variant);
                text = new RJText();
                text.m_strContext = strFormatText;
                m_groupScaleText.mAddObject(text);
                text.mInitialize();
            }
        }
        else if(m_continueData != null)
        {
            for(variant = m_continueData.m_variantMin; !(variant.mCompare(m_continueData.m_variantMax) == 1); variant = variant.mAdd(m_variantScaleUint))
            {
                strFormatText = mGetFormatText(m_strFormat, variant);
                text = new RJText();
                text.m_strContext = strFormatText;
                m_groupScaleText.mAddObject(text);
                text.mInitialize();
            }
        }

    }

    public void mInitialize()
    {
        long        nCount;

        nCount = m_groupScaleText.mSize();
        nCount -= 1;
        if(nCount > 0) m_dScaleUint = m_dAxiaLength / nCount;

        if(m_graphicsArrow != null)
        {
            m_graphicsArrow.mInitialize();
            m_graphicsArrow.m_directionType = m_bVertical ? RJDirectionType.Up : RJDirectionType.Right;
            m_graphicsArrow.mInitialize();
            m_graphicsArrow.m_colorArrow = m_colorLine;
            m_graphicsArrow.m_dKeyHandleWidth = m_dLineWidth;
            m_graphicsArrow.m_dKeycapWidth = m_dLineWidth;
        }
    }

    public void mDraw()
    {
        PointF   ptEnd;
        PointF   ptAxiaLineEnd;
        PointF   ptNext;
        Paint    paint;
        long     nIndex;
        long     nCount;
        Paint    paintExtendLine;
        RJObject obj;
        double   dValue;
        double   dRelPos;

        paint = new Paint();
        ptEnd = new PointF();
        ptAxiaLineEnd = new PointF();

        ptAxiaLineEnd.set(m_ptOrigin.x, m_ptOrigin.y);
        if(m_bVertical) ptAxiaLineEnd.y -= m_dAxiaLength;
        else ptAxiaLineEnd.x += m_dAxiaLength;
        paint.setStrokeWidth((float)m_dLineWidth);
        paint.setColor(m_colorLine.mGetColor());
        paint.setAntiAlias(true);
        if(!m_bHiddenAxia) m_canvas.drawLine(m_ptOrigin.x, m_ptOrigin.y, ptAxiaLineEnd.x, ptAxiaLineEnd.y, paint);

        ptNext = new PointF(m_ptOrigin.x, m_ptOrigin.y);
        nCount = m_groupScaleText.mSize();
        paint = new Paint();
        paint.setColor(m_colorScale.mGetColor());
        paint.setStrokeWidth((float)m_dScaleWidth);
        paint.setAntiAlias(true);
        dValue = m_axiaRel == null ? 0 : m_axiaRel.m_dAxiaLength;
        dRelPos = m_bVertical ? m_axiaRel.m_ptOrigin.x : m_axiaRel.m_ptOrigin.y;
        paintExtendLine = new Paint();
        paintExtendLine.setColor(m_colorSacleExtendLine.mGetColor());
        paintExtendLine.setStrokeWidth((float)m_dLineSacleExtendWidth);
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            ptEnd = new PointF(ptNext.x, ptNext.y);
            if(m_bVertical) ptEnd.x -= m_dScaleLength;
            else ptEnd.y += m_dScaleLength;

            if(m_bShowSacleExtendLine)
            {
                if (m_bVertical) m_canvas.drawLine((float)dRelPos, ptNext.y, (float) (dRelPos + dValue), ptNext.y, paintExtendLine);
                else m_canvas.drawLine(ptNext.x, (float)dRelPos, ptNext.x, (float) (dRelPos - dValue), paintExtendLine);
            }

            if(!m_bHiddenAxia) m_canvas.drawLine(ptNext.x, ptNext.y, ptEnd.x, ptEnd.y, paint);
            obj = (RJObject) m_groupScaleText.mGet(nIndex);
            if(obj != null)
            {
                if(m_bVertical)
                {
                    ptEnd.x -= m_dTextInterval;
                    if(m_bScaleTextCenter)ptEnd.y -= m_dScaleUint / 2;
                }
                else
                {
                    ptEnd.y += m_dTextInterval;
                    if(m_bScaleTextCenter)ptEnd.x -= m_dScaleUint / 2;
                }
                if(m_bVertical) obj.m_basePoint = RJBasePoint.RightCenter;
                else obj.m_basePoint = RJBasePoint.TopCenter;

                obj.m_ptOrigin.set(ptEnd.x, ptEnd.y);
                obj.m_canvas = m_canvas;
                obj.mDraw();
            }

            if(m_bVertical) ptNext.y -= m_dScaleUint;
            else ptNext.x += m_dScaleUint;
        }

        if(m_bShowArrow && !m_bHiddenAxia)
        {
            m_graphicsArrow.m_ptOrigin = ptAxiaLineEnd;
            m_graphicsArrow.m_canvas = m_canvas;
            m_graphicsArrow.mDraw();
        }
    }
}

public class RJCoordinateSystem extends RJObject
{
    public RJAxias m_aixaX;
    public RJAxias m_aixaY;
    public RJGroup m_groupSerias;

    public RJCoordinateSystem()
    {
        m_aixaX = new RJAxias();
        m_aixaY = new RJAxias();
        m_aixaX.m_bVertical = false;
        m_groupSerias = new RJGroup();
    }

    public double mGetWidth()
    {
        double dValue;

        dValue = 0;
        dValue = m_aixaY.mGetTextMaxWidth();
        dValue += m_aixaY.m_dTextInterval;
        dValue += m_aixaY.m_dScaleLength;
        dValue += m_aixaX.m_dAxiaLength;
        dValue += m_aixaX.m_graphicsArrow.m_dKeyHandleLength;
        return dValue;
    }

    public double mGetHeight()
    {
        double dValue;

        dValue = 0;
        dValue = m_aixaX.mGetTextMaxHeight();
        dValue += m_aixaX.m_dTextInterval;
        dValue += m_aixaX.m_dScaleLength;
        dValue += m_aixaY.m_dAxiaLength;
        dValue += m_aixaY.m_graphicsArrow.m_dKeyHandleLength;
        return dValue;
    }

    public void mDraw()
    {
        RJCSSeria seria;
        long nIndex;
        long nCount;

        m_aixaX.m_canvas = m_canvas;
        m_aixaX.mDraw();
        m_aixaY.m_canvas = m_canvas;
        m_aixaY.mDraw();

        nCount = m_groupSerias.mSize();
        for (nIndex = 0; nIndex < nCount; nIndex++)
        {
            seria = (RJCSSeria) m_groupSerias.mGet(nIndex);
            if (seria != null)
            {
                seria.m_canvas = m_canvas;
                seria.mDraw();
            }
        }
    }

    public RJCSSeria mCreateSeria()
    {
        RJCSSeria seria;

        seria = new RJCSSeria();
        m_groupSerias.mAddObject(seria);
        return seria;
    }

    public void mInitializeAxiasData()
    {
    }

    public void mSetCross(RJVariant varX, RJVariant varY)
    {
        m_aixaX.mSetCross(varX);
        m_aixaY.mSetCross(varY);
    }

    public void mInitialize()
    {
        RJCSSeria seria;
        long      nIndex;
        long      nCount;
        long      nPointCount;
        RJCSPoint csPoint;
        PointF    ptPoint;
        double    dOriginWidth;
        double    dOriginHeight;


        m_aixaX.mInitializeBefore();
        m_aixaY.mInitializeBefore();

        dOriginWidth = m_dWidth = mGetWidth();
        dOriginHeight = m_dHeigth = mGetHeight();

        super.mInitialize();

        if(dOriginWidth != m_dWidth) m_aixaX.m_dAxiaLength += (m_dWidth - dOriginWidth);
        if(dOriginHeight != m_dWidth) m_aixaY.m_dAxiaLength += (m_dHeigth - dOriginHeight);

        m_aixaX.mInitialize();
        m_aixaY.mInitialize();

        ptPoint = new PointF();
        mTranslatePoint(RJBasePoint.LeftCenter);
        ptPoint.set(m_ptOrigin.x, (float)(m_ptOrigin.y));

        m_aixaX.m_ptOrigin.x = (float)(m_rectObj.m_fRight - (m_aixaX.m_dAxiaLength + m_aixaX.m_graphicsArrow.m_dKeyHandleLength));
        m_aixaX.m_ptOrigin.y = (float)(m_rectObj.m_fTop + ((m_aixaY.m_dAxiaLength + m_aixaY.m_graphicsArrow.m_dKeyHandleLength) - m_aixaY.m_dCross ));

        m_aixaY.m_ptOrigin.x = (float)(m_rectObj.m_fRight - ((m_aixaX.m_dAxiaLength + m_aixaX.m_graphicsArrow.m_dKeyHandleLength) - m_aixaX.m_dCross));
        m_aixaY.m_ptOrigin.y = (float)(m_rectObj.m_fTop + m_aixaY.m_dAxiaLength + m_aixaY.m_graphicsArrow.m_dKeyHandleLength);

        m_aixaY.m_axiaRel = m_aixaX;
        m_aixaX.m_axiaRel = m_aixaY;

        nCount = m_groupSerias.mSize();

        for (nIndex = 0; nIndex < nCount; nIndex++)
        {
            seria = (RJCSSeria) m_groupSerias.mGet(nIndex);
            if (seria != null)
            {
                seria.m_axiaX = m_aixaX;
                seria.m_axiaY = m_aixaY;
                seria.mInitialize();
            }
        }

        if (nCount > 1) s_colorScheme.mSetColor(m_groupSerias);
        else if (nCount == 1)
        {
            seria = (RJCSSeria) m_groupSerias.mGet(0);
            nPointCount = seria.m_groupPoints.mSize();
            s_colorScheme.mResetScheme();
            for (nIndex = 0; nIndex < nPointCount; nIndex++)
            {
                csPoint = (RJCSPoint) seria.m_groupPoints.mGet(nIndex);
                csPoint.mSetGraphicsColor(s_colorScheme.mGetNextColor());
            }
            seria.m_colorLine = s_colorScheme.mGetNextColor();
        }
    }
}