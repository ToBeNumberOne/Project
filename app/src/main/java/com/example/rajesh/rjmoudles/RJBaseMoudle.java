package com.example.rajesh.rjmoudles;
import android.graphics.Color;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer-pc on 8/7/2017.
 */

enum RJBasePoint
{
    Center,
    RightCenter,
    LeftCenter,
    BottomCenter,
    TopCenter,
}

enum RJDirectionType
{
    Up,
    Down,
    Left,
    Right,
}

enum RJCompare
{
    BigThan,
    EqualTo,
    LessThan,
}

public class RJBaseMoudle
{
}

class RJContinueData
{
    public RJVariant               m_variantMin;
    public RJVariant               m_variantMax;

    public RJVariant.ValueType     m_vt;

    public RJContinueData()
    {

        m_variantMin    = new RJVariant();
        m_variantMax    = new RJVariant();
        m_vt            = RJVariant.ValueType.None;
    }

    public double mGetPercent(RJVariant variant)
    {
        double dValue;
        double dMin;
        double dMax;

        dValue = 0;
        switch(m_vt)
        {
            case VTDouble:
                if(variant.m_vtType == RJVariant.ValueType.VTDouble)
                {
                    dValue = variant.m_dValue;
                    dMin = m_variantMin.m_dValue;
                    dMax = m_variantMax.m_dValue;
                    dValue = (dValue - dMin) / (dMax - dMin);
                }
                break;
        }
        return dValue;
    }
}

class RJDiscreteData
{
    public RJGroup              m_groupDatas;
    public RJVariant.ValueType  m_vt;

    public RJDiscreteData()
    {
        m_groupDatas = new RJGroup();
        m_vt = RJVariant.ValueType.None;
    }

    public boolean mAddData(String strText)
    {
        RJVariant variant;

        variant = new RJVariant();
        variant.mSetValue(strText);
        return m_groupDatas.mAddObject(variant);
    }

    public long mSize()
    {
        return m_groupDatas.mSize();
    }

    public RJVariant mGet(long nIndex)
    {
        return (RJVariant) m_groupDatas.mGet(nIndex);
    }

    public long mGetSameIndex(RJVariant variant)
    {
        long nCount;
        long nIndex;

        nCount = mSize();
        nIndex = -1;
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            if(variant.mCompare((RJVariant) m_groupDatas.mGet(nIndex)) == 0) break;
        }
        if(nIndex >= nCount) nIndex = -1;
        return nIndex;
    }
}

class RJObjectBase
{
    public void mSetAttribute(String strAttributes, RJVariant variant)
    {
    }
}

class RJColor
{
    public int m_color;

    public RJColor()
    {
        m_color = 0;
    }

    public RJColor(int color)
    {
        m_color = color;
    }

    public RJColor(int R, int G, int B)
    {
        m_color = Color.rgb(R, G, B);
    }

    public RJColor(int R, int G, int B, int a)
    {
        m_color = Color.argb(a, R, G, B);
    }

    public int mGetColor()
    {
        return m_color;
    }

    public void mSetAlpha(int nAlpha)
    {
    }
}

class RJRectangle
{
    public  float   m_fLeft;
    public  float   m_fRight;
    public  float   m_fTop;
    public  float   m_fBottom;

    public RJRectangle(float fLeft, float fRight, float fTop,float fBottom)
    {
        m_fTop = fLeft;
        m_fBottom = fRight;
        m_fLeft = fTop;
        m_fRight = fBottom;
    }

    public boolean mPointIsInRect(PointF ptPoint)
    {
        return false;
    }

    public void mAddWidth(float fWidth)
    {
        m_fRight += fWidth;
    }

    public void mAddHeight(float fHeight)
    {
        m_fBottom += fHeight;
    }

    public void mSet(RJRectangle rectangle)
    {
        m_fTop      = rectangle.m_fTop;
        m_fBottom   = rectangle.m_fBottom;
        m_fLeft     = rectangle.m_fLeft;
        m_fRight    = rectangle.m_fRight;
    }

    public void mSet(float fLeft, float fRight, float fTop,float fBottom)
    {
        m_fTop      = fTop;
        m_fBottom   = fBottom;
        m_fLeft     = fLeft;
        m_fRight    = fRight;
    }

    public RJRectangle()
    {
        m_fTop = m_fBottom = m_fLeft = m_fRight = 0;
    }

    public float mGetHeight()
    {
        return m_fBottom - m_fTop;
    }

    public float mGetWidth()
    {
        return m_fRight - m_fLeft;
    }
}

class RJGroup
{
    enum StorageType
    {
        Array,
        LinkedList,
    }

    protected List<RJObjectBase> m_listObj;
    public    RJEvent            m_evnet;

    public RJGroup()
    {
        m_listObj = new ArrayList<RJObjectBase>();
    }

    public boolean mAddObject(RJObjectBase obj)
    {
        return m_listObj.add(obj);
    }

    public void mAddObjectAtHead(RJObjectBase obj)
    {
        m_listObj.add(0, obj);
    }

    public RJObjectBase mGet(long nIndex)
    {
        return m_listObj.get((int)nIndex);
    }

    public long mSize()
    {
        return m_listObj.size();
    }

    public void mAddObject(RJObjectBase[] arrObjs, int nStart, int nEnd)
    {
        int nIndex;

        for(nIndex = nStart; nIndex <= nEnd; nIndex++)
        {
            mAddObject(arrObjs[nIndex]);
        }
    }
}

class RJMath
{
    static PointF sCalLinePoint(double dAngle, PointF ptPoint, double dLength)
    {
        double dAngle2Pi;
        double dY;
        double dX;
        PointF ptResult;

        dAngle2Pi = dAngle / 360 * 2 * Math.PI;
        dY = dLength * Math.sin(dAngle2Pi);
        dX = dLength * Math.cos(dAngle2Pi);
        ptResult = new PointF();
        ptResult.set((float)(ptPoint.x + dX), (float)(ptPoint.y - dY));
        return ptResult;
    }
}

class RJTreeNode extends RJObjectBase
{
    protected  RJGroup   m_treeNodeChilds;
}

class RJImage extends RJObjectBase
{
}


class RJStatus extends RJObjectBase
{

}