package com.example.rajesh.rjmoudles;

import android.graphics.Paint;

/**
 * Created by acer-pc on 8/7/2017.
 */
class RJDataGridDefualtParams
{
    static double   dHeight = 700;
}

public class RJDataGrid extends RJObject
{
    protected   RJDGHeader      m_dgHeader;
    protected   RJDGBody        m_dgBody;

    public RJDataGrid()
    {
        m_dHeigth           = RJDataGridDefualtParams.dHeight;
        m_dgHeader          = new RJDGHeader();
        m_dgBody            = new RJDGBody();
        m_dgBody.m_dgHeader = m_dgHeader;
    }


    public void mAppendTextToHeader(String strText)
    {
        RJText   text;
        RJDGCell cell;

        text = new RJText();
        text.m_strContext = strText;
        cell = new RJDGHeaderCell();
        cell.m_groupObj.mAddObject(text);
        text.m_relativelySizeAndPos[0].objRel = cell;
        text.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.Center_Vertical_And_Horizontal;
        m_dgHeader.m_groupObj.mAddObject(cell);
    }

    public void mAddHeaders(String[] strTexts)
    {
        int         nIndex;
        int         nCount;
        RJText      text;
        RJDGCell    cell;

        nCount = strTexts.length;
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            text = new RJText();
            text.m_strContext = strTexts[nIndex];
            cell = new RJDGHeaderCell();
            cell.m_groupObj.mAddObject(text);
            text.m_relativelySizeAndPos[0].objRel = cell;
            text.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.Center_Vertical_And_Horizontal;
            m_dgHeader.m_groupObj.mAddObject(cell);
        }
    }

    public RJDGBodyRow mAddDatas(long[] nValues)
    {
        int         nIndex;
        int         nCount;
        RJText      text;
        RJDGCell    cell;
        RJDGBodyRow row;

        nCount = nValues.length;
        row = new RJDGBodyRow();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            text = new RJText();
            text.m_strContext = String.format("%d", (int)nValues[nIndex]);
            cell = new RJDGBodyCell();
            cell.m_groupObj.mAddObject(text);
            text.m_relativelySizeAndPos[0].objRel = cell;
            text.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.Center_Vertical_And_Horizontal;
            row.m_groupObj.mAddObject(cell);
        }
        m_dgBody.m_groupObj.mAddObject(row);
        return row;
    }

    public void mDraw()
    {
        m_dgHeader.m_canvas = m_canvas;
        m_dgHeader.mDraw();
        m_dgBody.m_canvas = m_canvas;
        m_dgBody.mDraw();
    }


    public void mInitialize()
    {
        super.mInitialize();
        mTranslatePoint(RJBasePoint.LeftCenter);
        m_dgHeader.m_ptOrigin.set(m_ptOrigin.x, (float)(m_ptOrigin.y - m_dHeigth / 2f + m_dgHeader.m_dHeigth / 2f));
        m_dgHeader.mSetRectSide(this, 0, RJObjectDefualtParams.dNotSetValue,0 , RJObjectDefualtParams.dNotSetValue);
        m_dgBody.m_dHeigth = m_dHeigth - m_dgHeader.m_dHeigth;
        m_dgBody.mSetRectSide(m_dgHeader, 0, 0, 0, RJObjectDefualtParams.dNotSetValue);
        m_dgBody.m_rectSide[2].rectSideRelObj = RectSide.Bottom;
        mSetRectSide(m_dgHeader, 0, 0, 0, RJObjectDefualtParams.dNotSetValue);
        m_rectSide[3].objRel = m_dgBody;
        m_rectSide[3].rectSideRelObj = RectSide.Bottom;
        m_rectSide[3].rectSideSelf = RectSide.Bottom;
        m_dgHeader.mInitialize();
        m_dgBody.mInitialize();
    }
}

class RJDGCell extends RJContainDevice
{
    public RJVariant m_variant;
    public String    m_strFormatMethod;

    public RJDGCell()
    {
        m_variant           = new RJVariant();
        m_strFormatMethod   = new String();
    }

    public void mInitialize()
    {
        switch(m_variant.m_vtType)
        {
            case Text:
                break;

            case VTDouble:
                break;

            case VTDate:
                break;

            case VTInt:
                break;
        }
        super.mInitialize();
    }

    public void mDraw()
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        m_canvas.drawRect(m_rectObj.m_fLeft, m_rectObj.m_fTop, m_rectObj.m_fRight, m_rectObj.m_fBottom, paint);
        super.mDraw();
    }
}

class RJDGHeaderCellDefualtParams
{
    static double   dWidth      = 120;
    static double   dMaxWidth   = 60;
    static double   dMinWidth   = 10;
    static RJColor  m_color     = new RJColor(30, 144, 255, 150);
}

class RJDGHeaderCell extends RJDGCell
{
    public double      m_dWidthMin;
    public double      m_dWidthMax;

    public RJDGHeaderCell()
    {
        m_dWidth = RJDGHeaderCellDefualtParams.dWidth ;
        mSetBackGround(RJDGHeaderCellDefualtParams.m_color);
        m_dWidthMin = RJDGHeaderCellDefualtParams.dMinWidth;
        m_dWidthMax = RJDGHeaderCellDefualtParams.dMaxWidth;
    }
}

class RJDataGridRowDefualtParams
{
    static double dHeight = 55;
}

class RJDataGridRow extends RJContainDevice
{
    public RJDataGridRow()
    {
        m_dHeigth = RJDataGridRowDefualtParams.dHeight;
    }

    public void mInitialize()
    {
        long            nIndex;
        long            nCount;
        RJObject        obj;
        RJObject        objLast;

        objLast = null;
        nCount = m_groupObj.mSize();
        m_dWidth = 0;
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            obj = (RJObject) m_groupObj.mGet(nIndex);
            m_dWidth += obj.m_dWidth;
            if(nIndex == 0)
            {
                obj.m_rectSide[0].objRel = this;
                obj.m_rectSide[0].rectSideRelObj = RectSide.Left;
            }
            else
            {
                obj.m_rectSide[0].objRel = objLast;
                obj.m_rectSide[0].rectSideRelObj = RectSide.Right;
            }
            obj.m_rectSide[0].dSpacing = 0;
            obj.m_rectSide[0].rectSideSelf = RectSide.Left;

            obj.m_rectSide[1].objRel = this;
            obj.m_rectSide[1].dSpacing = 0;
            obj.m_rectSide[1].rectSideSelf = RectSide.Top;
            obj.m_rectSide[1].rectSideRelObj = RectSide.Top;

            obj.m_rectSide[2].objRel = this;
            obj.m_rectSide[2].dSpacing = 0;
            obj.m_rectSide[2].rectSideSelf = RectSide.Bottom;
            obj.m_rectSide[2].rectSideRelObj = RectSide.Bottom;
            objLast = obj;
        }
        super.mInitialize();
    }
}

class RJDGHeader extends RJDataGridRow
{
}

class RJDGBodyCellDefualtParams
{
    static int      dSpaceRow = 1;
    static int      dSpaceCol = 1;
}

class RJDGBodyCell extends RJDGCell
{
    public      int         m_nSpaceRow;
    public      int         m_nSpaceCol;
    protected   int         m_nOffset;

    public RJDGBodyCell()
    {
        m_nSpaceCol         = RJDGBodyCellDefualtParams.dSpaceCol;
        m_nSpaceRow         = RJDGBodyCellDefualtParams.dSpaceRow;
        m_nOffset           = 0;
    }
}

class RJDGBodyRow extends RJDataGridRow
{
    public RJDGHeader   m_dgHeader;
    public RJColor      m_colorCostom;

    public RJDGBodyRow()
    {
    }

    public void mAppendObject(RJObject obj)
    {
        RJDGBodyCell cell;

        cell = new RJDGBodyCell();
        cell.m_groupObj.mAddObject(obj);
        obj.m_relativelySizeAndPos[0].objRel = cell;
        obj.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.Center_Vertical_And_Horizontal;
        m_groupObj.mAddObject(cell);
    }

    public void mInitialize()
    {
        long            nIndex;
        long            nCount;
        RJObject        obj;
        RJObject        objHeaderCell;

        nCount = m_groupObj.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            obj = (RJObject) m_groupObj.mGet(nIndex);
            objHeaderCell = (RJObject) m_dgHeader.m_groupObj.mGet(nIndex);
            obj.m_dWidth = objHeaderCell.m_dWidth;
        }
        super.mInitialize();
    }
}

class RJDGBodyDefualtParams
{
    static RJColor  colorRowCF1 = new RJColor(255, 255, 255, 100);
    static RJColor  colorRowCF2 = new RJColor(96, 96, 96, 150);
}

class RJDGBody extends  RJContainDevice
{
    public      RJColor         m_colorRowCF1;
    public      RJColor         m_colorRowCF2;
    public      RJDGHeader      m_dgHeader;

    public RJDGBody()
    {
        m_colorRowCF1 = RJDGBodyDefualtParams.colorRowCF1;
        m_colorRowCF2 = RJDGBodyDefualtParams.colorRowCF2;
    }

    public void mInitialize()
    {
        long            nIndex;
        long            nCount;
        RJDGBodyRow     obj;
        RJObject        objLast;

        objLast = null;
        nCount = m_groupObj.mSize();

        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            obj = (RJDGBodyRow) m_groupObj.mGet(nIndex);
            obj.m_dgHeader = m_dgHeader;
            if(nIndex == 0)
            {
                obj.m_rectSide[0].objRel = this;
                obj.m_rectSide[0].dSpacing = 0;
                obj.m_rectSide[0].rectSideSelf = RectSide.Top;
                obj.m_rectSide[0].rectSideRelObj = RectSide.Top;
            }
            else
            {
                obj.m_rectSide[0].objRel = objLast;
                obj.m_rectSide[0].dSpacing = 0;
                obj.m_rectSide[0].rectSideSelf = RectSide.Top;
                obj.m_rectSide[0].rectSideRelObj = RectSide.Bottom;
            }

            obj.m_rectSide[1].objRel = this;
            obj.m_rectSide[1].dSpacing = 0;
            obj.m_rectSide[1].rectSideSelf = RectSide.Left;
            obj.m_rectSide[1].rectSideRelObj = RectSide.Left;

            obj.m_rectSide[2].objRel = this;
            obj.m_rectSide[2].dSpacing = 0;
            obj.m_rectSide[2].rectSideSelf = RectSide.Right;
            obj.m_rectSide[2].rectSideRelObj = RectSide.Right;
            obj.mSetBackGround(obj.m_colorCostom != null ? obj.m_colorCostom : (nIndex % 2 == 0 ? m_colorRowCF1 : m_colorRowCF2));
            objLast = obj;
        }
        super.mInitialize();
    }
}