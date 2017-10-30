package com.example.rajesh.rjmoudles;

import android.graphics.Color;

/**
 * Created by acer-pc on 8/7/2017.
 */

class RJEditCell extends RJObject
{
}

class RJExitTextCell extends RJEditCell
{
	public RJColor  m_colorText;
	public float    m_fSizeText;

	public RJExitTextCell()
	{
	}
}

class RJEmoticonCell extends RJEditCell
{
}

class RJImageCell extends RJEditCell
{
}

class RJEditRowDefualtParams
{
	static double dHeight = 80;
}

class RJEditRow extends RJContainDevice
{
	public RJEditRow()
	{
	}

	public void mInitializeDefualtParams()
	{
		m_dHeigth = RJEditDefualtParams.dHeight;
	}

	public void mInitializePosition()
	{
	}
}

class RJCursorDefualtParams
{
	static float    fFlashingTime       = 200;
	static float    fCursorLineWidtn    = 3;
	static RJColor  colorLine           = new RJColor(Color.BLACK);
}

class RJCursor extends RJObject
{
	public float        m_fFlashingTime;        //millisecond unit
	public RJGrahpics   m_graphics;

	public RJCursor()
	{
		mInitializeDefualtParams();
	}

	public void mInitializeDefualtParams()
	{
		m_graphics = new RJGraphicsLine();
		m_fFlashingTime = RJCursorDefualtParams.fFlashingTime;
		m_graphics.m_dLineWidth = RJCursorDefualtParams.fCursorLineWidtn;
		m_graphics.m_colorLine = RJCursorDefualtParams.colorLine;
	}
}

class RJEditDefualtParams
{
	static String   strPasswordReplaceSymbol    = "●";
	static RJColor  colorBottomText             = new RJColor(96, 96, 96, 80);
	static RJColor  colorBackGround             = new RJColor(Color.WHITE);
	static double   dHeight                     = 80;
}

public class RJEdit extends RJContainDevice
{
	protected   RJCursor    m_cursor;
	public      RJText      m_textBottom;
	public      RJObject    m_objLeft;
	public      RJObject    m_objRight;
	public      boolean     m_bPasswordMode;
	public      String      m_strPasswordReplaceSymbol;

	public RJEdit()
	{
		mInitializeDefualtParams();
	}

	public void mInitializeDefualtParams()
	{
		m_cursor = new RJCursor();
		m_textBottom = new RJText();
		m_textBottom.m_colorText = RJEditDefualtParams.colorBottomText;
		m_objLeft = m_objRight = null;
		m_bPasswordMode = false;
		m_strPasswordReplaceSymbol = RJEditDefualtParams.strPasswordReplaceSymbol;
		m_dHeigth = RJEditDefualtParams.dHeight;
		mSetBackGround(RJEditDefualtParams.colorBackGround);
	}
}