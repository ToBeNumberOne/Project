package com.example.rajesh.rjmoudles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

/**
 * Created by Rajesh on 2017/7/18.
 */


class RJDosomethingObject extends RJDoSomething
{
    public RJDosomethingObject()
    {
    }

    public void mClick()
    {
    }

    public void mSlide()
    {
    }
}

class RJObjectEvent extends RJEvent
{
    protected RJDosomethingObject   m_doSomethingObj;

    public RJObjectEvent()
    {
        m_doSomething = m_doSomethingObj = new RJDosomethingObject();
    }

    public void mClick()
    {
        m_doSomethingObj.mClick();
    }

    public void mSlide()
    {
        m_doSomethingObj.mSlide();
    }
}

class RJObjectDefualtParams
{
                static  double      dWidth                         = 10;
                static  double      dHeight                        = 8;
    final       static  double      dNotSetValue                   = Double.MIN_VALUE;
}

class RJRelativelyOffset
{
    public RJObject.RectSide    rectSideSelf;
    public RJObject             objRel;
    public RJObject.RectSide    rectSideRelObj;
    public double               dSpacing;           //为负数时表示向上或者向左的距离，否则向下或者向右

    public RJRelativelyOffset()
    {
        rectSideSelf    = RJObject.RectSide.None;
        rectSideRelObj  = RJObject.RectSide.None;
        objRel          = null;
        dSpacing        = 0;
    }
}

class RJRelativeSizeAndPos
{
    public RJObject                          objRel;
    public RJObject.RelativelySizeAndPos     relativelySizeAndPos;

    public RJRelativeSizeAndPos()
    {
        objRel                  = null;
        relativelySizeAndPos    = RJObject.RelativelySizeAndPos.None;
    }
}

public class RJObject extends RJObjectBase
{
    public enum   RectSide
    {
        None,
        Top,
        Left,
        Right,
        Bottom,
    }

    public enum   RelativelySizeAndPos
    {
        None,
        EqualWidth,
        EqualHeight,
        Equal_Width_And_Height,
        CenterVertical,
        CenterHorizontal,
        Center_Vertical_And_Horizontal,
    }

    static      String                    s_AttributeColor          = "Color";
    static      RJColorScheme             s_colorScheme             = new RJColorScheme();
    public      RJRelativelyOffset[]      m_rectSide;
    public      RJRelativeSizeAndPos[]    m_relativelySizeAndPos;
    protected   RJRectangle               m_rectObj;
    public      RJColor                   m_color;
    public      Canvas                    m_canvas;
    public      PointF                    m_ptOrigin;
    public      double                    m_dWidth;
    public      double                    m_dHeigth;
    public      RJObjectEvent             m_event;
    public      RJBasePoint               m_basePoint;
    public      boolean                   m_bPenetrateHit;
    public      boolean                   m_bHide;

    public RJObject()
    {
        m_rectSide                  = new RJRelativelyOffset[4];
        m_rectSide[0]               = new RJRelativelyOffset();
        m_rectSide[1]               = new RJRelativelyOffset();
        m_rectSide[2]               = new RJRelativelyOffset();
        m_rectSide[3]               = new RJRelativelyOffset();
        m_relativelySizeAndPos      = new RJRelativeSizeAndPos[4];
        m_relativelySizeAndPos[0]   = new RJRelativeSizeAndPos();
        m_relativelySizeAndPos[1]   = new RJRelativeSizeAndPos();
        m_relativelySizeAndPos[2]   = new RJRelativeSizeAndPos();
        m_relativelySizeAndPos[3]   = new RJRelativeSizeAndPos();
        m_dWidth                    = RJObjectDefualtParams.dWidth;
        m_dHeigth                   = RJObjectDefualtParams.dHeight;
        m_bPenetrateHit             = true;
        m_bHide                     = false;
        m_color                     = new RJColor();
        m_basePoint                 = RJBasePoint.LeftCenter;
        m_ptOrigin                  = new PointF();
        m_rectObj                   = new RJRectangle();
        m_event                     = new RJObjectEvent();
    }

    public void mPress()
    {

    }

    public void mMove()
    {

    }

    public void mBounceUp()
    {
        //if(m_rectObj.mPointIsInRect())
    }

    public void mDrawBorder()
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        m_canvas.drawRect(m_rectObj.m_fLeft, m_rectObj.m_fTop, m_rectObj.m_fRight, m_rectObj.m_fBottom, paint);
    }

    public void mCalculationSize()
    {
    }

    public boolean mHitTestPoint(PointF ptPoint)
    {
        if(!m_bPenetrateHit) return false;
        return m_rectObj.mPointIsInRect(ptPoint);
    }

    public void mTranslatePoint(RJBasePoint basePointTranslate)
    {
        m_basePoint = mTranslatePoint(m_ptOrigin, m_basePoint, basePointTranslate, m_rectObj);
    }

    public RJBasePoint mTranslatePoint(PointF ptOringin, RJBasePoint basePointCurr, RJBasePoint basePointTranlate, RJRectangle rectangle)
    {
        if(basePointCurr == basePointTranlate) return basePointTranlate;
        switch (basePointCurr)
        {
            case Center:
                ptOringin.set(ptOringin.x - rectangle.mGetWidth() / 2f, ptOringin.y);
                break;

            case LeftCenter:
                break;

            case RightCenter:
                ptOringin.set(ptOringin.x - rectangle.mGetWidth(), ptOringin.y);
                break;

            case TopCenter:
                ptOringin.set(ptOringin.x - rectangle.mGetWidth() / 2f, ptOringin.y + rectangle.mGetHeight() / 2f);
                break;

            case BottomCenter:
                ptOringin.set(ptOringin.x - rectangle.mGetWidth() / 2f, ptOringin.y - rectangle.mGetHeight() / 2f);
                break;
        }
        mTranslatePointByLeftPoint(ptOringin, basePointTranlate, rectangle);
        return basePointTranlate;
    }

    public void mTranslatePointByLeftPoint(PointF ptOringin, RJBasePoint basePointTranlate, RJRectangle rectangle)
    {
        switch (basePointTranlate)
        {
            case Center:
                ptOringin.set(ptOringin.x + rectangle.mGetWidth() / 2f, ptOringin.y);
                break;

            case LeftCenter:
                break;

            case RightCenter:
                ptOringin.set(ptOringin.x + rectangle.mGetWidth(), ptOringin.y);
                break;

            case TopCenter:
                ptOringin.set(ptOringin.x + rectangle.mGetWidth() / 2f, ptOringin.y - rectangle.mGetHeight() / 2f);
                break;

            case BottomCenter:
                ptOringin.set(ptOringin.x + rectangle.mGetWidth() / 2f, ptOringin.y + rectangle.mGetHeight() / 2f);
                break;
        }
    }

    public void mTranslateRect()
    {
        mTranslateRect(m_basePoint, m_ptOrigin, m_dWidth, m_dHeigth, m_rectObj);
    }

    public void mTranslateRect(RJBasePoint basePointCurr, PointF ptOringin, double dWidth, double dHeight, RJRectangle rectangle)
    {
        float  fWidth;
        float  fHeight;
        PointF ptPoint;

        fWidth = (float)dWidth;
        fHeight = (float)dHeight;

        ptPoint = new PointF(ptOringin.x, ptOringin.y);
        switch (basePointCurr)
        {
            case Center:
                ptPoint.set(ptPoint.x - fWidth / 2f, ptPoint.y);
                break;

            case LeftCenter:
                break;

            case RightCenter:
                ptPoint.set(ptPoint.x - fWidth, ptPoint.y);
                break;

            case TopCenter:
                ptPoint.set(ptPoint.x - fWidth / 2f, ptPoint.y + fHeight / 2f);
                break;

            case BottomCenter:
                ptPoint.set(ptPoint.x - fWidth / 2f, ptPoint.y - fHeight / 2f);
                break;
        }
        mTranslateRectByLeftPoint(ptPoint, dWidth, dHeight, rectangle);
    }

    public void mTranslateRectByLeftPoint(PointF ptOrigin, double dWidth, double dHeight, RJRectangle rectangle)
    {
        rectangle.mSet(ptOrigin.x, (float)(ptOrigin.x + dWidth), (float)(ptOrigin.y - dHeight / (float)2), (float)(ptOrigin.y + dHeight / (float)2));
    }

    public void mReCalculatePointByCurrRect()
    {
        switch (m_basePoint)
        {
            case Center:
                m_ptOrigin.set(m_rectObj.m_fLeft + m_rectObj.mGetWidth() / 2f, m_rectObj.m_fTop + m_rectObj.mGetHeight() / 2f);
                break;

            case LeftCenter:
                m_ptOrigin.set(m_rectObj.m_fLeft, m_rectObj.m_fTop + m_rectObj.mGetHeight() / 2f);
                break;

            case RightCenter:
                m_ptOrigin.set(m_rectObj.m_fRight, m_rectObj.m_fTop + m_rectObj.mGetHeight() / 2f);
                break;

            case TopCenter:
                m_ptOrigin.set(m_rectObj.m_fLeft + m_rectObj.mGetWidth() / 2f, m_rectObj.m_fTop);
                break;

            case BottomCenter:
                m_ptOrigin.set(m_rectObj.m_fLeft + m_rectObj.mGetWidth() / 2f, m_rectObj.m_fBottom);
                break;
        }
    }

    public void mSetrelativelayPosition(RJObject obj, RelativelySizeAndPos relativeSizeAndPos)
    {
        m_relativelySizeAndPos[0].objRel = obj;
        m_relativelySizeAndPos[0].relativelySizeAndPos = relativeSizeAndPos;
    }

    public void mSetRectSide(RJObject obj, double dLeft, double dRight, double dTop, double dBottom)
    {
        if(dLeft != RJObjectDefualtParams.dNotSetValue)
        {
            m_rectSide[0].objRel = obj;
            m_rectSide[0].rectSideSelf = RectSide.Left;
            m_rectSide[0].rectSideRelObj = RectSide.Left;
            m_rectSide[0].dSpacing = dLeft;
        }

        if(dRight != RJObjectDefualtParams.dNotSetValue)
        {
            m_rectSide[1].objRel = obj;
            m_rectSide[1].rectSideSelf = RectSide.Right;
            m_rectSide[1].rectSideRelObj = RectSide.Right;
            m_rectSide[1].dSpacing = dRight;
        }

        if(dTop != RJObjectDefualtParams.dNotSetValue)
        {
            m_rectSide[2].objRel = obj;
            m_rectSide[2].rectSideSelf = RectSide.Top;
            m_rectSide[2].rectSideRelObj = RectSide.Top;
            m_rectSide[2].dSpacing = dTop;
        }

        if(dBottom != RJObjectDefualtParams.dNotSetValue)
        {
            m_rectSide[3].objRel = obj;
            m_rectSide[3].rectSideSelf = RectSide.Bottom;
            m_rectSide[3].rectSideRelObj = RectSide.Bottom;
            m_rectSide[3].dSpacing = dBottom;
        }
    }

    public void mInitialize()
    {
        int                         nIndex;
        int                         nCount;
        RJRelativelyOffset          relativelayPosition;
        boolean                     bTopRef;
        boolean                     bBottomRef;
        boolean                     bLeftRef;
        boolean                     bRightRef;
        boolean                     bChange;
        double                      dValue;

        mCalculationSize();
        mTranslateRect();

        bChange = false;
        for(nIndex = 0; nIndex < 4; nIndex++)
        {
            switch (m_relativelySizeAndPos[nIndex].relativelySizeAndPos)
            {
                case EqualWidth:
                    m_dWidth = m_relativelySizeAndPos[nIndex].objRel.m_dWidth;
                    bChange = true;
                    break;

                case EqualHeight:
                    m_dHeigth = m_relativelySizeAndPos[nIndex].objRel.m_dHeigth;
                    bChange = true;
                    break;

                case CenterHorizontal:
                    mTranslatePoint(RJBasePoint.Center);
                    m_relativelySizeAndPos[nIndex].objRel.mTranslatePoint(RJBasePoint.Center);
                    m_ptOrigin.x = m_relativelySizeAndPos[nIndex].objRel.m_ptOrigin.x;
                    bChange = true;
                    break;

                case CenterVertical:
                    mTranslatePoint(RJBasePoint.Center);
                    m_relativelySizeAndPos[nIndex].objRel.mTranslatePoint(RJBasePoint.Center);
                    m_ptOrigin.y = m_relativelySizeAndPos[nIndex].objRel.m_ptOrigin.y;
                    bChange = true;
                    break;

                case Equal_Width_And_Height:
                    m_dWidth = m_relativelySizeAndPos[nIndex].objRel.m_dWidth;
                    m_dHeigth = m_relativelySizeAndPos[nIndex].objRel.m_dHeigth;
                    bChange = true;
                    break;

                case Center_Vertical_And_Horizontal:
                    mTranslatePoint(RJBasePoint.Center);
                    m_relativelySizeAndPos[nIndex].objRel.mTranslatePoint(RJBasePoint.Center);
                    m_ptOrigin.set(m_relativelySizeAndPos[nIndex].objRel.m_ptOrigin.x, m_relativelySizeAndPos[nIndex].objRel.m_ptOrigin.y);
                    bChange = true;
                    break;

                case None:
                    bChange = false;
                    break;
            }
            if (bChange) mTranslateRect();
        }

        bTopRef = bBottomRef = bLeftRef = bRightRef = false;
        nCount = 4;
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            relativelayPosition = m_rectSide[nIndex];
            switch (m_rectSide[nIndex].rectSideSelf)
            {
                case Top:
                    dValue = m_rectObj.mGetHeight();
                    if(relativelayPosition.rectSideRelObj == RectSide.Top) m_rectObj.m_fTop = (float)(relativelayPosition.objRel.m_rectObj.m_fTop - relativelayPosition.dSpacing);
                    else if(relativelayPosition.rectSideRelObj == RectSide.Bottom) m_rectObj.m_fTop = (float)(relativelayPosition.objRel.m_rectObj.m_fBottom - relativelayPosition.dSpacing);
                    bTopRef = true;
                    if(!bBottomRef) m_rectObj.m_fBottom = (float)(m_rectObj.m_fTop + dValue);
                    break;

                case Bottom:
                    dValue = m_rectObj.mGetHeight();
                    if(relativelayPosition.rectSideRelObj == RectSide.Top) m_rectObj.m_fBottom = (float)(relativelayPosition.objRel.m_rectObj.m_fTop - relativelayPosition.dSpacing);
                    else if(relativelayPosition.rectSideRelObj == RectSide.Bottom) m_rectObj.m_fBottom = (float)(relativelayPosition.objRel.m_rectObj.m_fBottom - relativelayPosition.dSpacing);
                    bBottomRef = true;
                    if(!bTopRef) m_rectObj.m_fTop = (float)(m_rectObj.m_fBottom - dValue);
                    break;

                case Left:
                    dValue = m_rectObj.mGetWidth();
                    if(relativelayPosition.rectSideRelObj == RectSide.Left) m_rectObj.m_fLeft = (float)(relativelayPosition.objRel.m_rectObj.m_fLeft + relativelayPosition.dSpacing);
                    else if(relativelayPosition.rectSideRelObj == RectSide.Right) m_rectObj.m_fLeft = (float)(relativelayPosition.objRel.m_rectObj.m_fRight + relativelayPosition.dSpacing);
                    bLeftRef = true;
                    if(!bRightRef) m_rectObj.m_fRight = (float)(m_rectObj.m_fLeft + dValue);
                    break;

                case Right:
                    dValue = m_rectObj.mGetWidth();
                    if(relativelayPosition.rectSideRelObj == RectSide.Left) m_rectObj.m_fRight = (float)(relativelayPosition.objRel.m_rectObj.m_fLeft + relativelayPosition.dSpacing);
                    else if(relativelayPosition.rectSideRelObj == RectSide.Right) m_rectObj.m_fRight = (float)(relativelayPosition.objRel.m_rectObj.m_fRight + relativelayPosition.dSpacing);
                    bRightRef = true;
                    if(!bLeftRef) m_rectObj.m_fLeft = (float)(m_rectObj.m_fRight - dValue);
                    break;

                case None:
                    break;
            }
        }
        if(bTopRef && bBottomRef) m_dHeigth= m_rectObj.mGetHeight();
        if(bLeftRef && bRightRef) m_dWidth  = m_rectObj.mGetWidth();
        if(bLeftRef || bBottomRef || bTopRef || bRightRef) mReCalculatePointByCurrRect();
    }

    public void mSetColor(RJColor color)
    {
        m_color = m_color;
    }

    public double mGetWidth()
    {
        return m_dWidth;
    }

    public double mGetHeight()
    {
        return m_dHeigth;
    }

    public void mDraw()
    {

    }
}