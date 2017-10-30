package com.example.rajesh.rjmoudles;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by acer-pc on 8/7/2017.
 */

class RJGraphicsDefualtParams
{
    static double               dCircleRadius = 6;
    static double               dCircleBorderWidth = 1;

    static RJDirectionType      dArrowirectionType = RJDirectionType.Up;
    static float                fArrowKeycapAngle = 60;
    static double               dArrowKeycapLength = 20;
    static double               dArrowKeycapWidth = 4;
    static double               dArrowKeyHandleWidth = 4;
    static double               dArrowKeyHandleLength = 40;
    static RJColor              colorArrow = new RJColor(0, 0, 0);
    static RJColor              colorArrowUp = new RJColor(Color.GREEN);
    static RJColor              colorArrowDown = new RJColor(Color.RED);

    static double               dEqualLineWidth = 3;
    static double               dEqualLength = 9;
    static double               dLineInterval = 4;

    static double               dLineWidth = 1;
    static RJColor              colorLine = new RJColor(0, 0, 0);
}

enum RJGraphicsType
{
    Custom,
    Circle,
    Rectangle,
    ArrowUp,
    ArrowDown,
    ArrowLeft,
    ArrowRight,
    Equal,
    Traingle,
    RegularPolygon,
}

public class RJGrahpics extends RJObject
{
    enum DrawType
    {
        Stroke_Only,
        Fill_Only,
        Fill_And_Stroke,
    }

    public RJGrahpics       m_graphicsType;
    public double           m_dLineWidth;
    public RJColor          m_colorLine;
    public RJColor          m_colorFill;
    public DrawType         m_drawType;

    public RJGrahpics()
    {
        m_dLineWidth    = RJGraphicsDefualtParams.dLineWidth;
        m_colorLine     = RJGraphicsDefualtParams.colorLine;
        m_colorFill     = new RJColor();
        m_drawType      = DrawType.Fill_Only;
    }

    public void mSetAttribute(String strAttributes, RJVariant variant)
    {
        if(s_AttributeColor.compareTo(strAttributes) == 0 && variant.m_vtType == RJVariant.ValueType.VTColor)
        {
            m_colorFill = m_colorLine = variant.m_colorValue;
        }
    }

    public RJGrahpics mCreateStyleGraphic()
    {
        return this;
    }

    static RJGrahpics mCreateGraphics(RJGraphicsType graphicsType)
    {
        RJGrahpics      grahpics;
        RJGraphicsArrow graphicsArrow;

        grahpics = new RJGrahpics();
        switch (graphicsType)
        {
            case Circle:
                grahpics = new RJGraphicsCircle();
                break;

            case Rectangle:
                grahpics = new RJGrahpicsRectangle();
                break;

            case Equal:
                grahpics = new RJGrahpicsEqual();
                break;

            case ArrowUp:
                grahpics = graphicsArrow = new RJGraphicsArrow();
                graphicsArrow.m_directionType = RJDirectionType.Up;
                break;

            case ArrowDown:
                grahpics = graphicsArrow = new RJGraphicsArrow();
                graphicsArrow.m_directionType = RJDirectionType.Down;
                break;

            case ArrowRight:
                grahpics = graphicsArrow = new RJGraphicsArrow();
                graphicsArrow.m_directionType = RJDirectionType.Right;
                break;

            case ArrowLeft:
                grahpics = graphicsArrow = new RJGraphicsArrow();
                graphicsArrow.m_directionType = RJDirectionType.Left;
                break;

            case Traingle:
                break;
        }

        return grahpics;
    }

}

class RJGraphicsCircle extends RJGrahpics
{
    public double   m_dRadius;
    public double   m_dBorderWidth;
    public RJColor  m_colorBorder;

    public RJGraphicsCircle()
    {
        m_dRadius       = RJGraphicsDefualtParams.dCircleRadius;
        m_dBorderWidth  = RJGraphicsDefualtParams.dCircleBorderWidth;
        m_colorBorder   = new RJColor();
        m_basePoint     = RJBasePoint.Center;
    }

    public void mSetColor(RJColor color)
    {
        m_colorLine = m_colorFill = color;
    }

    public void mDraw()
    {
        Paint   paint;
        boolean bDrawLine;
        boolean bDrawFill;

        paint = new Paint();
        paint.setAntiAlias(true);
        bDrawFill = bDrawLine = false;
        switch (m_drawType)
        {
            case Fill_And_Stroke:
                bDrawFill = bDrawLine = true;
                break;

            case Stroke_Only:
                bDrawLine = true;
                break;

            case Fill_Only:
                bDrawFill  = true;
                break;
        }

        if(bDrawFill)
        {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(m_colorFill.m_color);
            m_canvas.drawCircle(m_ptOrigin.x, m_ptOrigin.y, (float)m_dRadius, paint);
        }

        if(bDrawLine)
        {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(m_colorLine.m_color);
            paint.setStrokeWidth((float)m_dLineWidth);
            m_canvas.drawCircle(m_ptOrigin.x, m_ptOrigin.y, (float)m_dRadius, paint);
        }
    }
}

class RJGraphicsArrow extends RJGrahpics
{
    public    RJDirectionType m_directionType;
    public    float           m_fKeycapAngle;
    public    double          m_dKeycapLength;
    public    double          m_dKeycapWidth;
    public    double          m_dKeyHandleWidth;
    public    double          m_dKeyHandleLength;
    public    RJColor         m_colorArrow;
    protected PointF          m_ptLeft;
    protected PointF          m_ptRight;
    protected PointF          m_ptEnd;

    public RJGraphicsArrow()
    {
        m_directionType     = RJGraphicsDefualtParams.dArrowirectionType;
        m_fKeycapAngle      = RJGraphicsDefualtParams.fArrowKeycapAngle;
        m_dKeycapLength     = RJGraphicsDefualtParams.dArrowKeycapLength;
        m_dKeycapWidth      = RJGraphicsDefualtParams.dArrowKeycapWidth;
        m_dKeyHandleWidth   = RJGraphicsDefualtParams.dArrowKeyHandleWidth;
        m_dKeyHandleLength  = RJGraphicsDefualtParams.dArrowKeyHandleLength;
        m_colorArrow        = RJGraphicsDefualtParams.colorArrow;
    }

    public void mInitialize()
    {
        float  fAngleLeft;
        float  fAngleRight;
        double dValue;
        double d2Pi;

        d2Pi = (m_fKeycapAngle / (float)2) / (float)360 * (float)2 * Math.PI;
        dValue = (m_dKeycapLength * Math.sin(d2Pi)) * 2;
        switch (m_directionType)
        {
            case Right:
            case Left:
                m_dHeigth = dValue;
                m_dWidth = m_dKeyHandleLength;
                break;

            case Down:
            case Up:
                m_dWidth = dValue;
                m_dHeigth = m_dKeyHandleLength;
                break;
        }
        super.mInitialize();

        fAngleLeft = 270 - (m_fKeycapAngle / 2);
        fAngleRight = fAngleLeft + m_fKeycapAngle;

        switch(m_directionType)
        {
            case Right:
                mTranslatePoint(RJBasePoint.LeftCenter);
                break;

            case Left:
                mTranslatePoint(RJBasePoint.RightCenter);
                break;

            case Up:
                mTranslatePoint(RJBasePoint.BottomCenter);
                break;

            case Down:
                mTranslatePoint(RJBasePoint.TopCenter);
                break;
        }

        m_ptEnd = new PointF(m_ptOrigin.x, m_ptOrigin.y);
        switch(m_directionType)
        {
            case Right:
                m_ptEnd.x += m_dKeyHandleLength;
                fAngleLeft -= 90;
                fAngleRight -= 90;
                break;

            case Left:
                m_ptEnd.x -= m_dKeyHandleLength;
                fAngleLeft += 90;
                fAngleRight += 90;
                break;

            case Up:
                m_ptEnd.y -= m_dKeyHandleLength;
                m_colorArrow = RJGraphicsDefualtParams.colorArrowUp;
                break;

            case Down:
                m_ptEnd.y += m_dKeyHandleLength;
                fAngleLeft += 180;
                fAngleRight += 180;
                m_colorArrow = RJGraphicsDefualtParams.colorArrowDown;
                break;
        }

        m_ptLeft = RJMath.sCalLinePoint(fAngleLeft, m_ptEnd, m_dKeycapLength);
        m_ptRight = RJMath.sCalLinePoint(fAngleRight, m_ptEnd, m_dKeycapLength);
    }

    public void mDraw()
    {
        Paint paint;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(m_colorArrow.m_color);
        paint.setStrokeWidth((float)m_dKeycapWidth);
        m_canvas.drawLine(m_ptEnd.x, m_ptEnd.y, m_ptLeft.x, m_ptLeft.y, paint);
        m_canvas.drawLine(m_ptEnd.x, m_ptEnd.y, m_ptRight.x, m_ptRight.y, paint);
        paint.setStrokeWidth((float)m_dKeyHandleWidth);
        m_canvas.drawLine(m_ptOrigin.x, m_ptOrigin.y, m_ptEnd.x, m_ptEnd.y, paint);
    }
}

class RJGrahpicsRectangle extends RJGrahpics
{
    public double  m_dWidth;
    public double  m_dHeigth;
    public boolean m_b2PointMode;
    public PointF  m_ptEnd;

    public RJGrahpicsRectangle()
    {
        m_dHeigth       = 0;
        m_dWidth        = 0;
        m_b2PointMode   = false;
        m_ptEnd         = new PointF();
    }

    public void mDraw()
    {
        PointF      ptOrigin;
        RJBasePoint basePoint;

        basePoint = m_basePoint;
        if(m_b2PointMode)
        {
            m_dHeigth = m_ptOrigin.y - m_ptEnd.y;
            basePoint = RJBasePoint.BottomCenter;
        }
        ptOrigin = new PointF();
        switch (basePoint)
        {
            case Center:
                ptOrigin = new PointF(m_ptOrigin.x - (float)(m_dWidth / 2), m_ptOrigin.y);
                break;

            case LeftCenter:
                ptOrigin = new PointF(m_ptOrigin.x, m_ptOrigin.y);
                break;

            case RightCenter:
                ptOrigin = new PointF(m_ptOrigin.x - (float)m_dWidth, m_ptOrigin.y);
                break;

            case TopCenter:
                ptOrigin = new PointF(m_ptOrigin.x - (float)(m_dWidth / 2), m_ptOrigin.y + (float)(m_dHeigth / 2));
                break;

            case BottomCenter:
                ptOrigin = new PointF(m_ptOrigin.x - (float)(m_dWidth / 2), m_ptOrigin.y - (float)(m_dHeigth / 2));
                break;
        }
        m_dHeigth = Math.abs(m_dHeigth);
        mDrawByLeftCenter(ptOrigin);
    }

    public void mDrawByLeftCenter(PointF ptOrigin)
    {
        PointF ptStart;
        PointF ptEnd;
        Paint  paint;

        paint = new Paint();
        ptStart = new PointF(ptOrigin.x, ptOrigin.y - (float)m_dHeigth / 2);
        ptEnd = new PointF(ptStart.x + (float)m_dWidth, ptStart.y + (float)m_dHeigth);
        paint.setColor(m_colorFill.mGetColor());
        paint.setStrokeWidth((float)m_dLineWidth);
        m_canvas.drawRect(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y, paint);
    }

    public void mSetAttribute(String strAttributes, RJVariant variant)
    {
        if(s_AttributeColor.compareTo(strAttributes) == 0 && variant.m_vtType == RJVariant.ValueType.VTColor)
        {
            m_colorFill = m_colorLine = variant.m_colorValue;
        }
    }
}

class RJGrahpicsEqual extends RJGrahpics
{
    public double   m_dEqualLineWidth;
    public double   m_dLength;
    public double   m_dLineInterval;

    public RJGrahpicsEqual()
    {
        m_dEqualLineWidth = RJGraphicsDefualtParams.dEqualLineWidth;
        m_dLength = RJGraphicsDefualtParams.dEqualLength;
        m_dLineInterval = RJGraphicsDefualtParams.dLineInterval;
    }

    public void mDraw()
    {
        PointF ptStart;
        PointF ptEnd;
        Paint  paint;

        paint = new Paint();
        paint.setColor(m_color.mGetColor());
        paint.setStrokeWidth((float)m_dEqualLineWidth);
        ptStart = new PointF(m_ptOrigin.x - ((float)m_dLength / 2), m_ptOrigin.y - ((float)m_dLineInterval / 2));
        ptEnd = new PointF(ptStart.x + (float)m_dLength, ptStart.y);
        m_canvas.drawLine(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y, paint);
        ptStart.x += m_dLineInterval;
        ptEnd.y += m_dLineInterval;
        m_canvas.drawLine(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y, paint);
    }
}

class RJGraphicsSector extends RJGrahpics
{
    public double    m_dAngle;
    public double    m_dAngleStart;
    public double    m_dRadius;

    public RJGraphicsSector()
    {
        m_dAngle = 60;
        m_dAngleStart = 0;
        m_dRadius = 10;
    }

    public void mDraw()
    {
        Paint paint;
        RectF rect;

        paint = new Paint();
        paint.setColor(m_colorFill.mGetColor());
        paint.setStrokeWidth((float)m_dLineWidth);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        rect = new RectF((float)(m_ptOrigin.x - m_dRadius), (float)(m_ptOrigin.y - m_dRadius), (float)(m_ptOrigin.x + m_dRadius), (float)(m_ptOrigin.y + m_dRadius));
        m_canvas.drawArc(rect, (float)360 - (float)(m_dAngleStart + m_dAngle), (float)m_dAngle, true, paint);
    }
}

class RJGraphicsLine extends RJGrahpics
{
    public PointF m_ptEnd;

    public RJGraphicsLine()
    {
        m_ptEnd = new PointF();
    }

    public void mDraw()
    {
        Paint paint;

        paint = new Paint();
        paint.setColor(m_colorLine.mGetColor());
        paint.setStrokeWidth((float)m_dLineWidth);
        m_canvas.drawLine(m_ptOrigin.x, m_ptOrigin.y, m_ptEnd.x, m_ptEnd.y, paint);
    }
}

class RJGraphicsIrregularPolygon extends RJGrahpics
{
    public PointF[] m_ptPoints;

    public RJGraphicsIrregularPolygon()
    {
    }

    public void mSetColor(RJColor color)
    {
        m_colorFill = color;
    }

    public void mSetAttribute(String strAttributes, RJVariant variant)
    {
        if(s_AttributeColor.compareTo(strAttributes) == 0 && variant.m_vtType == RJVariant.ValueType.VTColor)
        {
            m_colorLine = m_colorFill = variant.m_colorValue;
        }
    }

    public void mDraw()
    {
        Path        path;
        long        nIndex;
        long        nCount;
        PointF      point;
        Paint       paint;
        boolean     bDrawFill;
        boolean     bDrawLine;
        Paint.Style paintStyle;

        path = new Path();
        paint = new Paint();
        nCount = m_ptPoints.length;
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            point = m_ptPoints[(int)nIndex];
            if(nIndex == 0) path.moveTo(point.x, point.y);
            else path.lineTo(point.x, point.y);
        }
        path.close();
        paint.setAntiAlias(true);
        paint.setColor(m_colorLine.m_color);
        bDrawFill = bDrawLine = false;
        switch (m_drawType)
        {
            case Fill_And_Stroke:
                bDrawFill = bDrawLine = true;
                break;

            case Stroke_Only:
                bDrawLine = true;
                break;

            case Fill_Only:
                bDrawLine = true;
                break;
        }

        if(bDrawFill)
        {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(m_colorFill.m_color);
            m_canvas.drawPath(path, paint);
        }

        if(bDrawLine)
        {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(m_colorLine.m_color);
            paint.setStrokeWidth((float)m_dLineWidth);
            m_canvas.drawPath(path, paint);
        }


    }
}

class RJGraphicsRegularPolygon extends RJGraphicsIrregularPolygon
{
    public      int             m_nSideCount;
    public      double          m_dAngleStart;
    public      double          m_dRadius;
    protected   double          m_AngleAvg;

    public RJGraphicsRegularPolygon()
    {
        m_nSideCount = 3;
        m_dAngleStart = 90;
        m_dRadius = 100;
    }

    public PointF mGetPointExtendsLinePoint(long nIndex, double dExtendsLength)
    {
        return RJMath.sCalLinePoint(m_dAngleStart + nIndex * m_AngleAvg, m_ptOrigin, m_dRadius + dExtendsLength);
    }

    public double mGetPointAngle(long nIndex)
    {
        return m_dAngleStart + nIndex * m_AngleAvg;
    }

    public void mInitialize()
    {
        int         nIndex;
        double      dAngle;

        dAngle = 0;
        if(m_nSideCount != 0)
        {
            m_AngleAvg = 360 / m_nSideCount;
            m_ptPoints = new PointF[m_nSideCount];
        }
        for(nIndex = 0; nIndex < m_nSideCount; nIndex++)
        {
            if(nIndex == 0) dAngle = m_dAngleStart;
            else dAngle +=  m_AngleAvg;
            m_ptPoints[nIndex] = RJMath.sCalLinePoint(dAngle, m_ptOrigin, m_dRadius);
        }
    }

    public PointF[] mGetPoints()
    {
        return m_ptPoints;
    }
}

class RJGraphicsTraingle extends RJGraphicsIrregularPolygon
{
    public float    m_fObjAngle;
    public float    m_fTraingleAngle;
    public double   m_dSideLength;

    public RJGraphicsTraingle()
    {

    }

    public void mInitialize()
    {
        super.mInitialize();
    }
}