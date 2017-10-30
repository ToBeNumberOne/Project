package com.example.rajesh.rjmoudles;

import android.graphics.Color;

/**
 * Created by acer-pc on 21/7/2017.
 */

public class RJColorScheme
{
    protected   long        m_nIndex;

    static String s_strCSGeneral = "General";
    protected   int[]        m_setScheme;

    static      int[]       s_arrGeneralColors =
            {
                    Color.argb(180, 254, 67, 101),
                    Color.argb(180, 252, 157,154),
                    Color.argb(180, 249,205,173),
                    Color.argb(180, 200,200,169),
                    Color.argb(180, 131, 175, 155),
                    Color.argb(187, 254, 67, 101),
                    Color.argb(187, 252, 157, 154),
                    Color.rgb(249,205,173),
                    Color.rgb(200,200,169),
                    Color.rgb(131, 175, 155),
                    Color.GREEN,
                    Color.BLUE,
                    Color.GRAY,
                    Color.BLACK,
                    Color.MAGENTA,
                    Color.CYAN,
                    0x00112233,
                    0x00223344,
                    0x00334455,
                    0x00445566,
                    0x00556677,
                    0x00667788,
                    0x00778899,
                    0x008899aa,
                    0x0099aabb,
                    0x00aabbcc,
                    0x00bbccdd,
                    0x00ccddee,
                    0x00ddee00,
                    0x00ee0011,
            };

    public RJColorScheme()
    {
        mSetScheme(s_strCSGeneral);
    }

    public void mSetScheme(String strSchemeName)
    {
        if(strSchemeName.compareTo(s_strCSGeneral) == 0) m_setScheme = s_arrGeneralColors;
        m_nIndex = -1;
    }

    public RJColor mGetNextColor()
    {
        RJColor color;

        color = new RJColor();
        if(m_nIndex > (m_setScheme.length - 1)) mResetScheme();
        color.m_color = s_arrGeneralColors[(int)++m_nIndex];
        return color;
    }

    public  void mSetColor(RJGroup groupObj)
    {
        RJObject    obj;
        long        nIndex;
        long        nCount;
        RJVariant    variant;

        mResetScheme();
        nCount = groupObj.mSize();
        for(nIndex = 0; nIndex < nCount; nIndex++)
        {
            obj = (RJObject) groupObj.mGet(nIndex);
            if(obj != null)
            {
                variant = new RJVariant();
                variant.mSetValue(mGetNextColor());
                obj.mSetAttribute(RJObject.s_AttributeColor, variant);
            }
        }
    }

    public void mResetScheme()
    {
        m_nIndex = -1;
    }
}