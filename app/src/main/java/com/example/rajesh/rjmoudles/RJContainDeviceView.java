package com.example.rajesh.rjmoudles;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by acer-pc on 21/7/2017.
 */

public class RJContainDeviceView extends View
{
	protected   RJContainDevice     m_containDevice;

	public RJContainDeviceView(Context context)
	{
		super(context);
		m_containDevice = new RJContainDevice();
	}

	public void mInitialize()
	{
		m_containDevice.m_dWidth  = getWidth();
		m_containDevice.m_dHeigth = getHeight();
		m_containDevice.m_ptOrigin.set((float)(m_containDevice.m_dWidth / 2f), (float)(m_containDevice.m_dHeigth / 2f));
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		m_containDevice.m_canvas = canvas;
		m_containDevice.mDraw();
	}
}
