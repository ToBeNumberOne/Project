package com.example.rajesh.rjmoudles;

/**
 * Created by acer-pc on 10/7/2017.
 */

class RJTipDefualtParams
{
    static RJGrahpics grahpics      = new RJGraphicsCircle();
    static String     strText       = new String("");
    static int        sNumber       = 0;
    static String     strEllipses   = new String("...");
    static RJColor    colorGraphics = new RJColor(255, 0, 0);
}

public class RJTip extends RJObject
{
    public RJGrahpics m_graphics;
    public RJText     m_text;
    public String     m_strText;
    public int        m_sNumber;
    public boolean    m_bNumberMode;

    public RJTip()
    {
        m_graphics = RJTipDefualtParams.grahpics;
        m_graphics.m_color = RJTipDefualtParams.colorGraphics;
        m_text = new RJText();
        m_strText = RJTipDefualtParams.strText;
        m_sNumber = RJTipDefualtParams.sNumber;
        m_bNumberMode = true;
    }



    public void mInitialize()
    {
        m_text.m_strContext = m_bNumberMode ? m_strText : String.format("%d", m_sNumber);
        //如果超过图形边界则用省略号代替//m_text.m_strContext = RJTipDefualtParams.strEllipses
    }

    public void mDraw()
    {
        m_graphics.mDraw();
        m_text.mDraw();
    }
}