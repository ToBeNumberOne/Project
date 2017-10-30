package com.example.rajesh.rjmoudles;

import java.util.Calendar;

/**
 * Created by Rajesh on 2017/7/31.
 */

class RJDateDefualtParams
{
	final static RJDate         dateRef             = new RJDate(1900, 1, 1);
	final static RJDate.Week    weekRef             = RJDate.Week.Monday;
	final static int[]          arrBigMonth         = new int[]{1, 3, 5, 7, 8, 10, 12};
	final static int[]          arrSmallMonth       = new int[]{4, 6, 9, 11};
	final static int            nBigMonthDays       = 31;
	final static int            nSmallMonthDays     = 30;
	final static int            nLeapYear2Month     = 29;
	final static int            nCommonYear2Month   = 28;
	final static int            nLeapYearDays       = arrBigMonth.length * nBigMonthDays + arrSmallMonth.length * nSmallMonthDays + nLeapYear2Month;
	final static int            nCommonYearDays     = arrBigMonth.length * nBigMonthDays + arrSmallMonth.length * nSmallMonthDays + nCommonYear2Month;
}

public class RJDate extends RJObjectBase
{
	enum Week
	{
		Monday,
		Tuesday,
		Wednesday,
		Thursday,
		Friday,
		Saturday,
		Sunday,
	}

	public int          m_nYear;
	public int          m_nMonth;
	public int          m_nDay;
	public int          m_nHour;
	public int          m_nMinute;
	public int          m_nSecond;
	public int          m_nMicroSecond;

	public RJDate()
	{
		m_nYear           = 0;
		m_nMonth          = 0;
		m_nDay            = 0;
		m_nHour           = 0;
		m_nMinute         = 0;
		m_nSecond         = 0;
		m_nMicroSecond    = 0;
	}

	public RJDate(int nYear, int nMonth, int nDay)
	{
		m_nYear           = nYear;
		m_nMonth          = nMonth;
		m_nDay            = nDay;
		m_nHour           = 0;
		m_nMinute         = 0;
		m_nSecond         = 0;
		m_nMicroSecond    = 0;
	}

	public RJDate(int nYear, int nMonth, int nDay, int nHour, int nMinute, int nSecond)
	{
		m_nYear             = nYear;
		m_nMonth            = nMonth;
		m_nDay              = nDay;
		m_nHour             = nHour;
		m_nMinute           = nMinute;
		m_nSecond           = nSecond;
		m_nMicroSecond      = 0;
	}

	static RJCompare mCompareDate(RJDate d1, RJDate d2)
	{
		RJCompare compareRet;

		compareRet = RJCompare.EqualTo;
		if(d1.m_nYear == d2.m_nYear)
		{
			if(d1.m_nMonth == d2.m_nMonth)
			{
				if(d1.m_nDay == d2.m_nDay) compareRet = RJCompare.EqualTo;
				else if(d1.m_nDay > d2.m_nDay) compareRet = RJCompare.BigThan;
				else compareRet = RJCompare.LessThan;
			}
			else if(d1.m_nMonth > d2.m_nMonth) compareRet = RJCompare.BigThan;
			else  compareRet = RJCompare.LessThan;
		}
		else if(d1.m_nYear > d2.m_nYear) compareRet = RJCompare.BigThan;
		else compareRet = RJCompare.LessThan;

		return compareRet;
	}

	public void mAddYears(int nYears)
	{
		m_nYear += nYears;
		mTrimDay();
	}

	public void mAdd1Day()
	{
		int nDaysCurrentMonth;

		nDaysCurrentMonth = (int)mGetDaysCurrentMonth();
		if(nDaysCurrentMonth == m_nDay)
		{
			m_nDay = 1;
			mAddMonths(1);
		}
		else m_nDay++;
	}

	public void mSub1Day()
	{
		if(m_nDay > 1) m_nDay--;
		else
		{
			mAddMonths(-1);
			m_nDay = (int)mGetDaysCurrentMonth();
		}
	}

	public void mAddMonths(int nMonth)
	{
		int nY;
		int nM;
		int nMM;

		if(nMonth > 0)
		{
			m_nYear += ((m_nMonth + nMonth) / 12);
			m_nMonth = ((m_nMonth + nMonth) % 12);
		}
		else if(nMonth < 0)
		{
			nY = nMonth / 12;
			nM = nMonth % 12;
			m_nYear -= nY;
			nMM = nM + m_nMonth;
			if(nMM < 0)
			{
				m_nMonth = 12 + nMM;
				m_nYear--;
			}
			else if(nMM > 0) m_nMonth = nMM;
		}
		if(m_nMonth == 0)
		{
			m_nMonth = 12;
			m_nYear -= 1;
		}
		mTrimDay();
	}

	public void mTrimDay()
	{
		int nDays;

		nDays = (int)mGetDaysCurrentMonth();
		if(m_nDay > nDays) m_nDay = nDays;
	}

	public void mAddDays(long nDays)
	{
		long  nIndex;

		if(nDays > 0)
		{
			for(nIndex = 0; nIndex < nDays; nIndex++) mAdd1Day();
		}
		else if(nDays < 0)
		{
			for(nIndex = 0; nIndex < nDays; nIndex++) mSub1Day();
		}
	}

	static RJDate mGetNow()
	{
		RJDate   date;
		Calendar calendar;

		calendar = Calendar.getInstance();
		date = new RJDate(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
		return date;
	}

	public long mGetThisYearSurplusDays()
	{
		int     nNextMonth;
		long    nDays;

		nDays = mGetDaysCurrentMonth(m_nYear, m_nMonth);
		nDays -= m_nDay;
		nNextMonth = m_nMonth;
		nNextMonth += 1;
		for(; nNextMonth <= 12; nNextMonth++) nDays += mGetDaysCurrentMonth(m_nYear, nNextMonth);
		return nDays;
	}

	public long mGetThisYearGoDays()
	{
		int nDays;
		int nIndex;
		int nEndMonth;

		nDays = 0;
		nEndMonth = m_nMonth - 1;
		for(nIndex = 1; nIndex <= nEndMonth; nIndex++) nDays += mGetDaysCurrentMonth(m_nYear, nIndex);
		nDays += m_nDay;
		return nDays;
	}

	static Week mGetPostponeWeek(Week week, int nPostponeDays)
	{
		int nDays;

		nDays = nPostponeDays + mGetNumberByWeek(week);
		nDays %= 7;
		if(nDays == 0) nDays = 7;
		return mGetWeekByNumber(nDays);
	}

	static Week mGetWeekByNumber(int nWeek)
	{
		Week week;

		week = Week.Friday;
		if(nWeek > 7)
		{
			nWeek %= 7;
			if(nWeek == 0) nWeek = 7;
		}
		switch(nWeek)
		{
			case 1:
				week = Week.Monday;
				break;

			case 2:
				week = Week.Tuesday;
				break;

			case 3:
				week = Week.Wednesday;
				break;

			case 4:
				week = Week.Thursday;
				break;

			case 5:
				week = Week.Friday;
				break;

			case 6:
				week = Week.Saturday;
				break;

			case 7:
				week = Week.Sunday;
				break;
		}
		return week;
	}

	static int mGetNumberByWeek(Week week)
	{
		int nNumber;

		nNumber = -1;
		switch(week)
		{
			case Monday:
				nNumber = 1;
				break;


			case Tuesday:
				nNumber = 2;
				break;

			case Wednesday:
				nNumber = 3;
				break;

			case Thursday:
				nNumber = 4;
				break;

			case Friday:
				nNumber = 5;
				break;

			case Saturday:
				nNumber = 6;
				break;

			case Sunday:
				nNumber = 7;
				break;
		}

		return nNumber;
	}

	public Week mGetDayOfWeek()
	{
		long    nDiffDays;

		nDiffDays = mGetDiffDays(RJDateDefualtParams.dateRef, this);
		return mGetPostponeWeek(RJDateDefualtParams.weekRef, (int)nDiffDays);
	}

	static long mGetDiffDaysStandard(RJDate date1, RJDate date2) //Necessary condition : date1 <= date2
	{
		long        nDays;
		long        nStart;
		long        nEnd;

		nDays = 0;
		if(date1.m_nYear == date2.m_nYear) nDays = date2.mGetThisYearGoDays() - date1.mGetThisYearGoDays();
		else
		{
			nDays = date1.mGetThisYearSurplusDays() + date2.mGetThisYearGoDays();
			nStart = date1.m_nYear;
			nEnd = date2.m_nYear;
			nStart += 1;
			nEnd -= 1;
			for(; nStart <= nEnd; nStart++) nDays += mGetYearDays((int)nStart);
		}
		return nDays;
	}

	static long mGetDiffDays(RJDate date1, RJDate date2)
	{
		long        nDays;
		RJCompare   compare;

		compare = mCompareDate(date1, date2);
		if(compare == RJCompare.EqualTo) nDays = 0;
		else if(compare == RJCompare.BigThan) nDays = -mGetDiffDaysStandard(date2, date1);
		else nDays = mGetDiffDaysStandard(date1, date2);
		return nDays;
	}

	static long mGetYearDays(int nYear)
	{
		return mIsLeapYear(nYear) ?  RJDateDefualtParams.nLeapYearDays : RJDateDefualtParams.nCommonYearDays;
	}

	public long mGetDaysCurrentMonth()
	{
		return mGetDaysCurrentMonth(m_nYear, m_nMonth);
	}

	static long mGetDaysCurrentMonth(int nYear, int nMonth)
	{
		int nDays;

		nDays = -1;
		switch(nMonth)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				nDays = RJDateDefualtParams.nBigMonthDays;
				break;

			case 4:
			case 6:
			case 9:
			case 11:
				nDays = RJDateDefualtParams.nSmallMonthDays;
				break;

			case 2:
				nDays = mIsLeapYear(nYear) ? RJDateDefualtParams.nLeapYear2Month : RJDateDefualtParams.nCommonYear2Month;
				break;
		}

		return nDays;
	}

	static boolean mIsLeapYear(int nYear)
	{
		return ((nYear % 4 == 0) && (nYear % 100 != 0)) || (nYear % 400 == 0 && nYear % 3200 != 0);
	}
}