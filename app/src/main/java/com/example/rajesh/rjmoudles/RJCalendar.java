package com.example.rajesh.rjmoudles;

import android.graphics.Color;

/**
 * Created by Administrator on 2017/7/15.
 */

class RJCalendarHeaderDefualtParams
{
	static double   dXInterVal                = 40;
	static double   dYInterVal                = 5;
	static double   dYearAndMonthCDWidth      = 200;
	static double   dWidth                    = 120 * 7;
	static double   dHeight                   = 60;
	static RJColor  colorText                 = new RJColor(255, 0, 0, 200);
	static RJColor  colorBackGround           = new RJColor(96, 96, 96, 13);
	static float    fTextSize                 = 40;
	static RJColor  colorPageArrow            = new RJColor(255, 0, 0, 120);
	static float    fArrowLength              = 20;
	static float    fArrowWidth               = 4;
	static double   dArrowXMonthInterVal      = 30;
	static double   dArrowXYearInterVal       = 10;
	static float    fAngleArrow               = 90;
}

class RJCalendarHeader extends RJContainDevice
{
	protected   RJContainDevice     m_containDeviceYear;
	protected   RJGrahpics          m_graphicsYearLeft;
	protected   RJGrahpics          m_graphicsYearRight;
	protected   RJText              m_textYear;

	protected   RJContainDevice     m_containDeviceMonth;
	protected   RJGrahpics          m_graphicsMonthLeft;
	protected   RJGrahpics          m_graphicsMonthRight;
	protected   RJText              m_textMonth;

	public RJCalendarHeader()
	{
		m_containDeviceYear             = new RJContainDevice();
		m_containDeviceMonth            = new RJContainDevice();
		m_textMonth                     = new RJText();
		m_textYear                      = new RJText();
		m_dWidth                        = RJCalendarHeaderDefualtParams.dWidth;
		m_dHeigth                       = RJCalendarHeaderDefualtParams.dHeight;
		m_containDeviceYear.m_dWidth    = RJCalendarHeaderDefualtParams.dYearAndMonthCDWidth;
		m_containDeviceMonth.m_dWidth   = RJCalendarHeaderDefualtParams.dYearAndMonthCDWidth;
		mInitializeDefualtGraphics();
	}



	public void mSetDate(RJDate date)
	{
		m_textYear.m_strContext = String.format("%d", date.m_nYear);
		m_textMonth.m_strContext = String.format("%d", date.m_nMonth);
	}

	public void mInitializeDefualtGraphics()
	{
		RJGraphicsArrow arrowYearLeft;
		RJGraphicsArrow arrowYearRight;
		RJGraphicsArrow arrowMonthLeft;
		RJGraphicsArrow arrowMonthRight;

		m_graphicsYearLeft                  = arrowYearLeft                     = new RJGraphicsArrow();
		m_graphicsYearRight                 = arrowYearRight                    = new RJGraphicsArrow();
		m_graphicsMonthLeft                 = arrowMonthLeft                    = new RJGraphicsArrow();
		m_graphicsMonthRight                = arrowMonthRight                   = new RJGraphicsArrow();

		arrowMonthLeft.m_fKeycapAngle       = arrowYearRight.m_fKeycapAngle     = arrowMonthRight.m_fKeycapAngle        = arrowYearLeft.m_fKeycapAngle      = RJCalendarHeaderDefualtParams.fAngleArrow;
		arrowMonthLeft.m_dKeyHandleLength   = arrowYearRight.m_dKeyHandleLength = arrowMonthRight.m_dKeyHandleLength    = arrowYearLeft.m_dKeyHandleLength  = 0;
		arrowMonthLeft.m_colorArrow         = arrowYearRight.m_colorArrow       = arrowMonthRight.m_colorArrow          = arrowYearLeft.m_colorArrow        = RJCalendarHeaderDefualtParams.colorPageArrow;
		arrowMonthLeft.m_dKeycapLength      = arrowYearRight.m_dKeycapLength    = arrowMonthRight.m_dKeycapLength       = arrowYearLeft.m_dKeycapLength     = RJCalendarHeaderDefualtParams.fArrowLength;
		arrowMonthLeft.m_dKeycapWidth       = arrowYearRight.m_dKeycapWidth     = arrowMonthRight.m_dKeycapWidth        = arrowYearLeft.m_dKeycapWidth      = RJCalendarHeaderDefualtParams.fArrowWidth;

		arrowYearLeft.m_directionType       = RJDirectionType.Left;
		arrowYearRight.m_directionType      = RJDirectionType.Right;
		arrowMonthLeft.m_directionType      = RJDirectionType.Left;
		arrowMonthRight.m_directionType     = RJDirectionType.Right;
	}

	public void mInitializeEvent()
	{
	}

	public void mInitializeObjectsRelation()
	{
		m_groupObj.mAddObject(m_containDeviceYear);
		m_groupObj.mAddObject(m_containDeviceMonth);
		m_containDeviceYear.m_groupObj.mAddObject(m_textYear);
		m_containDeviceMonth.m_groupObj.mAddObject(m_textMonth);
		m_containDeviceYear.m_groupObj.mAddObject(m_graphicsYearLeft);
		m_containDeviceYear.m_groupObj.mAddObject(m_graphicsYearRight);
		m_containDeviceMonth.m_groupObj.mAddObject(m_graphicsMonthLeft);
		m_containDeviceMonth.m_groupObj.mAddObject(m_graphicsMonthRight);
	}

	public void mInitializeAttributes()
	{
		m_textYear.m_colorText = m_textMonth.m_colorText = RJCalendarHeaderDefualtParams.colorText;
		m_textYear.m_dTextSize = m_textMonth.m_dTextSize = RJCalendarHeaderDefualtParams.fTextSize;
		mSetBackGround(RJCalendarHeaderDefualtParams.colorBackGround);
	}

	public void mInitializePosition()
	{
		m_textYear.m_relativelySizeAndPos[0].objRel = m_containDeviceYear;
		m_textYear.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.Center_Vertical_And_Horizontal;
		m_textMonth.m_relativelySizeAndPos[0].objRel = m_containDeviceMonth;
		m_textMonth.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.Center_Vertical_And_Horizontal;
		m_containDeviceYear.mSetRectSide(this, RJCalendarHeaderDefualtParams.dXInterVal, RJObjectDefualtParams.dNotSetValue, RJCalendarHeaderDefualtParams.dYInterVal, RJCalendarHeaderDefualtParams.dYInterVal);
		m_containDeviceMonth.m_dWidth = m_containDeviceYear.m_dWidth = RJCalendarHeaderDefualtParams.dYearAndMonthCDWidth;
		m_containDeviceMonth.mSetRectSide(this, RJObjectDefualtParams.dNotSetValue, -RJCalendarHeaderDefualtParams.dXInterVal, RJCalendarHeaderDefualtParams.dYInterVal, RJCalendarHeaderDefualtParams.dYInterVal);

		m_graphicsMonthLeft.m_relativelySizeAndPos[0].objRel = m_containDeviceMonth;
		m_graphicsMonthLeft.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterVertical;

		m_graphicsMonthRight.m_relativelySizeAndPos[0].objRel = m_containDeviceMonth;
		m_graphicsMonthRight.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterVertical;

		m_graphicsYearLeft.m_relativelySizeAndPos[0].objRel = m_containDeviceYear;
		m_graphicsYearLeft.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterVertical;

		m_graphicsYearRight.m_relativelySizeAndPos[0].objRel = m_containDeviceYear;
		m_graphicsYearRight.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterVertical;

		m_graphicsYearLeft.m_rectSide[0].objRel = m_containDeviceYear;
		m_graphicsYearLeft.m_rectSide[0].rectSideRelObj = RectSide.Left;
		m_graphicsYearLeft.m_rectSide[0].rectSideSelf = RectSide.Left;
		m_graphicsYearLeft.m_rectSide[0].dSpacing = RJCalendarHeaderDefualtParams.dArrowXYearInterVal;

		m_graphicsYearRight.m_rectSide[0].objRel = m_containDeviceYear;
		m_graphicsYearRight.m_rectSide[0].rectSideRelObj = RectSide.Right;
		m_graphicsYearRight.m_rectSide[0].rectSideSelf = RectSide.Right;
		m_graphicsYearRight.m_rectSide[0].dSpacing = -RJCalendarHeaderDefualtParams.dArrowXYearInterVal;

		m_graphicsMonthRight.m_rectSide[0].objRel = m_containDeviceMonth;
		m_graphicsMonthRight.m_rectSide[0].rectSideRelObj = RectSide.Right;
		m_graphicsMonthRight.m_rectSide[0].rectSideSelf = RectSide.Right;
		m_graphicsMonthRight.m_rectSide[0].dSpacing = -RJCalendarHeaderDefualtParams.dArrowXMonthInterVal;

		m_graphicsMonthLeft.m_rectSide[0].objRel = m_containDeviceMonth;
		m_graphicsMonthLeft.m_rectSide[0].rectSideRelObj = RectSide.Left;
		m_graphicsMonthLeft.m_rectSide[0].rectSideSelf = RectSide.Left;
		m_graphicsMonthLeft.m_rectSide[0].dSpacing = RJCalendarHeaderDefualtParams.dArrowXMonthInterVal;
	}

	public void mInitialize()
	{
		mInitializeAttributes();
		mInitializePosition();
		mInitializeObjectsRelation();
		super.mInitialize();
	}

	public void mDraw()
	{
		mDrawBorder();
		super.mDraw();
	}
}


class RJCalendarCellDefualtParams
{
	static double   dCDDayMargin = -5;
}


class RJCalendarCell extends RJDGBodyCell
{
	protected   RJGrahpics        m_graphics;
	protected   RJContainDevice   m_cdDay;
	protected   RJText            m_textNumber;
	protected   RJText            m_textHSEB;      //Heavenly Stems and Earthly Branches of text
	public      boolean           m_bNumberOnly;

	public RJCalendarCell()
	{
		m_graphics      = new RJGraphicsCircle();
		m_cdDay         = new RJContainDevice();
		m_textHSEB      = new RJText();
		m_textNumber    = new RJText();
		m_bNumberOnly   = true;
	}


	public void mInitialize()
	{

		if(m_bNumberOnly)
		{
			m_textNumber.m_relativelySizeAndPos[0].objRel = m_cdDay;
			m_textNumber.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.Center_Vertical_And_Horizontal;
			m_cdDay.m_groupObj.mAddObject(m_textNumber);
		}
		else
		{
			m_textNumber.m_relativelySizeAndPos[0].objRel = m_cdDay;
			m_textNumber.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
			m_textNumber.mSetRectSide(m_cdDay, RJObjectDefualtParams.dNotSetValue, RJObjectDefualtParams.dNotSetValue, 0, RJObjectDefualtParams.dNotSetValue);
			m_textHSEB.m_relativelySizeAndPos[0].objRel = m_cdDay;
			m_textHSEB.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
			m_textHSEB.mSetRectSide(m_cdDay, RJObjectDefualtParams.dNotSetValue, RJObjectDefualtParams.dNotSetValue, RJObjectDefualtParams.dNotSetValue, 0);
			m_cdDay.m_groupObj.mAddObject(m_textNumber);
			m_cdDay.m_groupObj.mAddObject(m_textHSEB);
		}
		m_cdDay.mSetRectSide(this, RJCalendarCellDefualtParams.dCDDayMargin, -RJCalendarCellDefualtParams.dCDDayMargin, RJCalendarCellDefualtParams.dCDDayMargin, -RJCalendarCellDefualtParams.dCDDayMargin);
		m_groupObj.mAddObject(m_cdDay);
		super.mInitialize();
	}

	public void mSetText(int nNumber, String strHSEB)
	{
		m_textNumber.m_strContext = String.format("%d", nNumber);
		m_textHSEB.m_strContext = strHSEB;
	}

	public void mSetText(int nNumber, String strHSEB, RJColor colorText)
	{
		m_textNumber.m_strContext = String.format("%d", nNumber);
		m_textHSEB.m_strContext = strHSEB;
		m_textNumber.m_colorText = m_textHSEB.m_colorText = colorText;
	}
}

class RJCalendarDefualtParams
{
	static String[]         strWeekForChinese       = new String[]{"一", "二", "三", "四", "五","六", "日"};
	static String[]         strWeekForChinese1      = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五","星期六", "星期日"};
	static String[]         strWeekForChinese2      = new String[]{"周一", "周二", "周三", "周四", "周五","周六", "周日"};
	static String[]         strWeekForEnglish       = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
	static RJDate.Week[]    weekSequence            = new RJDate.Week[]{RJDate.Week.Monday, RJDate.Week.Tuesday, RJDate.Week.Wednesday, RJDate.Week.Thursday, RJDate.Week.Friday, RJDate.Week.Saturday, RJDate.Week.Sunday};
	static RJColor          colorSelect             = new RJColor(0, 255, 0, 100);
	static RJColor          colorToday              = new RJColor(255, 0, 0, 50);
	static RJColor          colorWeekend            = new RJColor(Color.GRAY);
	static double           dNotSolarOnlyRowHeight  = 80;
}

public class RJCalendar extends RJContainDevice
{
	protected   RJDataGrid          m_dgBody;
	public      String[]            m_strWeek;
	protected   RJDate.Week[]       m_weekDays;
	public      RJColor             m_colorSelect;
	public      RJColor             m_colorToday;
	public      RJDate              m_dateCurrent;
	public      RJCalendarHeader    m_header;
	public      boolean             m_bSolarOnly;
	public      RJDate              m_dateSelect;

	public RJCalendar()
	{
		m_dgBody        = new RJDataGrid();
		m_strWeek       = RJCalendarDefualtParams.strWeekForChinese;
		m_weekDays      = RJCalendarDefualtParams.weekSequence;
		m_colorSelect   = RJCalendarDefualtParams.colorSelect;
		m_colorToday    = RJCalendarDefualtParams.colorToday;
		m_header        = new RJCalendarHeader();
		m_dateCurrent   = RJDate.mGetNow();
		m_bSolarOnly    = true;
		m_dateSelect    = new RJDate();
	}

	public void mDraw()
	{
		m_header.m_canvas = m_canvas;
		m_dgBody.m_canvas = m_canvas;
		m_header.mDraw();
		m_dgBody.mDraw();
	}

	public int mGetWeekIndex(RJDate.Week week)
	{
		int nIndex;

		for(nIndex = 0; nIndex < m_weekDays.length; nIndex++)
		{
			if(m_weekDays[nIndex] == week) break;
		}
		return nIndex;
	}

	public int mGetDisplacementDays(RJDate.Week w1, RJDate.Week w2)
	{
		int nW1;
		int nW2;

		nW1 = mGetWeekIndex(w1);
		nW2 = mGetWeekIndex(w2);

		return Math.abs(nW1 - nW2) + 1;
	}

	public void mInitialize()
	{
		int                 nIndex;
		int                 nEndIndex;
		int                 nNumber;
		String[]            strList;
		RJDate.Week         weekFirstDay;
		RJDate.Week         weekLastDay;
		int                 nDaysCurrent;
		int                 nTotalCells;
		RJCalendarCell[]    cells;
		int                 nRows;
		RJDGBodyRow[]       rows;
		RJDate.Week         week;
		RJDate              dateNow;

		m_dWidth = m_header.m_dWidth;
		m_dHeigth = m_header.m_dHeigth + m_dgBody.m_dHeigth;
		super.mInitialize();

		m_dgBody.m_dgBody.m_colorRowCF2 = m_dgBody.m_dgBody.m_colorRowCF1;

		mTranslatePoint(RJBasePoint.LeftCenter);
		m_header.m_ptOrigin.set(m_ptOrigin.x, (float)(m_ptOrigin.y - m_dHeigth / 2f + m_header.m_dHeigth / 2f));

		//Initialize calendar header text
		m_header.mSetDate(m_dateCurrent);

		//Initialize calendar week text
		strList = new String[7];
		for(nIndex = 0; nIndex < 7; nIndex++)
		{
			switch(m_weekDays[nIndex])
			{
				case Monday:
					strList[nIndex] = m_strWeek[0];
					break;

				case Tuesday:
					strList[nIndex] = m_strWeek[1];
					break;

				case Wednesday:
					strList[nIndex] = m_strWeek[2];

					break;

				case Thursday:
					strList[nIndex] = m_strWeek[3];
					break;

				case Friday:
					strList[nIndex] = m_strWeek[4];
					break;

				case Saturday:
					strList[nIndex] = m_strWeek[5];
					break;

				case Sunday:
					strList[nIndex] = m_strWeek[6];
					break;

			}
		}
		m_dgBody.mAddHeaders(strList);

		//Intialize calendar day number text
		nTotalCells = nDaysCurrent = (int)m_dateCurrent.mGetDaysCurrentMonth();
		m_dateCurrent.m_nDay = 1;
		weekFirstDay = m_dateCurrent.mGetDayOfWeek();
		m_dateCurrent.m_nDay = nDaysCurrent;
		weekLastDay = m_dateCurrent.mGetDayOfWeek();

		nTotalCells += mGetDisplacementDays(m_weekDays[0], weekFirstDay) - 1;
		nTotalCells += mGetDisplacementDays(m_weekDays[6], weekLastDay) - 1;

		cells = new RJCalendarCell[nTotalCells];
		for(nIndex = 0; nIndex < nTotalCells; nIndex++) cells[nIndex] = new RJCalendarCell();

		nIndex = mGetWeekIndex(weekFirstDay);
		nEndIndex = nIndex + nDaysCurrent - 1;
		nNumber = 1;
		dateNow = RJDate.mGetNow();
		for(; nIndex <= nEndIndex; nIndex++)
		{
			m_dateCurrent.m_nDay = nNumber;
			week = m_dateCurrent.mGetDayOfWeek();
			if(week == RJDate.Week.Sunday || week == RJDate.Week.Saturday) cells[nIndex].mSetText(nNumber++, "初一", RJCalendarDefualtParams.colorWeekend);
			else cells[nIndex].mSetText(nNumber++, "初一");
			if(RJDate.mCompareDate(dateNow, m_dateCurrent) == RJCompare.EqualTo) cells[nIndex].mSetBackGround(RJCalendarDefualtParams.colorToday);
			if(RJDate.mCompareDate(m_dateSelect, m_dateCurrent) == RJCompare.EqualTo) cells[nIndex].mSetBackGround(RJCalendarDefualtParams.colorSelect);
			cells[nIndex].m_bNumberOnly = m_bSolarOnly;
		}

		nRows = nTotalCells / m_weekDays.length;
		rows = new RJDGBodyRow[nRows];
		for(nIndex = 0; nIndex < nRows; nIndex++)
		{
			rows[nIndex] = new RJDGBodyRow();
			if(!m_bSolarOnly)rows[nIndex].m_dHeigth = RJCalendarDefualtParams.dNotSolarOnlyRowHeight;
			rows[nIndex].m_groupObj.mAddObject(cells, nIndex * 7, nIndex * 7 + 6);
			m_dgBody.m_dgBody.m_groupObj.mAddObject(rows[nIndex]);
		}

		//Initialize calendar size and body position
		m_dgBody.mSetRectSide(m_header, 0, 0, 0, RJObjectDefualtParams.dNotSetValue);
		m_dgBody.m_rectSide[2].rectSideRelObj = RectSide.Bottom;

		m_header.mInitialize();
		m_dgBody.mInitialize();
	}
}