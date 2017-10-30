package com.example.rajesh.rjmoudles;

/**
 * Created by Administrator on 2017/7/15.
 */

public class RJContainDevice extends RJObject
{
    public RJGroup      m_groupObj;
    public RJObject     m_objBackGround;

    public RJContainDevice()
    {
        m_groupObj      = new RJGroup();
        m_objBackGround = null;
    }

    public void mDraw()
    {
        long        nIndex;
        long        nCount;
        RJObject    obj;

        nCount = m_groupObj.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            obj = (RJObject)m_groupObj.mGet(nIndex);
            obj.m_canvas = m_canvas;
            obj.mDraw();
        }
    }

    public void mSetBackGround(RJColor color)
    {
        if(m_objBackGround == null) m_objBackGround = new RJPattern();
        m_objBackGround.m_color = color;
    }

    public void mSetBackGround(RJImage image)
    {
    }

    public void mInitialize()
    {
        long            nIndex;
        long            nCount;
        RJObject        obj;

        super.mInitialize();
        if(m_objBackGround != null)
        {
            m_groupObj.mAddObjectAtHead(m_objBackGround);
            m_objBackGround.mSetRectSide(this, 0, 0, 0, 0);
        }
        nCount = m_groupObj.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            obj = (RJObject) m_groupObj.mGet(nIndex);
            obj.mInitialize();
        }
    }
}