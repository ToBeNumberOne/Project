package com.example.rajesh.rjmoudles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TestView(this));
        //setContentView(R.layout.activity_main);
    }
}


class TestView extends View
{
    RJCoordinateSystem  coordinateSystem;
    RJObject            m_objUI;
    RJObject            m_objUI2;
    RJObject            m_objUI3;
    RJObject            m_objUI4;
    RJObject            m_objUI5;
    RJObject            m_objUI6;

    public TestView(Context context)
    {
        super(context);
        mInit();
    }

    public void mInit()
    {
        RJCalendar calendar;

        m_objUI = calendar = new RJCalendar();
        calendar.m_ptOrigin.set(0, 240);
        calendar.m_dgBody.m_dHeigth = 400;
        calendar.mInitialize();

        /*m_objUI2 = calendar = new RJCalendar();
        calendar.m_dateCurrent.m_nMonth = 8;
        calendar.m_dgBody.m_dHeigth = 400;
        calendar.mSetrelativelayPosition(m_objUI, RJObject.RelativelySizeAndPos.Equal_Width_And_Height);
        calendar.mSetRectSide(m_objUI,0, RJObjectDefualtParams.dNotSetValue, 0, RJObjectDefualtParams.dNotSetValue);
        calendar.m_rectSide[0].rectSideRelObj = RJObject.RectSide.Right;
        calendar.mInitialize();

        m_objUI3 = calendar = new RJCalendar();
        calendar.m_dateCurrent.m_nMonth = 9;
        calendar.m_dgBody.m_dHeigth = 400;
        calendar.mSetrelativelayPosition(m_objUI2, RJObject.RelativelySizeAndPos.Equal_Width_And_Height);
        calendar.mSetRectSide(m_objUI2,0, RJObjectDefualtParams.dNotSetValue, 0, RJObjectDefualtParams.dNotSetValue);
        calendar.m_rectSide[0].rectSideRelObj = RJObject.RectSide.Right;
        calendar.mInitialize();*/



        /*RJDataGrid      dataGrid;
        RJDGBodyRow     row;
        RJGraphicsArrow arrow;

        m_objUI = dataGrid = new RJDataGrid();
        dataGrid.mAddHeaders(new String[]{"月份", "苹果", "小米", "三星", "联想", "诺基亚", "趋势"});
        row = dataGrid.mAddDatas(new long[]{201701, 2323, 3412, 1234, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Down;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201702, 582, 321, 542, 32, 254});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Up;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);
        row.m_dHeigth += 50;
        row.m_colorCostom = new RJColor(Color.RED);


        row = dataGrid.mAddDatas(new long[]{201703, 3323, 3412, 1534, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Up;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201704, 2523, 1212, 1234, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Up;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201705, 2323, 3512, 1234, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Up;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201706, 2323, 3412, 1234, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Down;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201707, 2323, 3412, 1234, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Up;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201708, 2323, 3412, 1234, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Down;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201709, 3323, 3412, 1534, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Up;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201710, 2523, 1212, 1234, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Up;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201711, 2323, 3512, 1234, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Up;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        row = dataGrid.mAddDatas(new long[]{201712, 2323, 3412, 1234, 5213, 1123});
        arrow = new RJGraphicsArrow();
        arrow.m_directionType = RJDirectionType.Down;
        arrow.m_dKeyHandleLength = row.m_dHeigth;
        arrow.m_dKeyHandleLength -= 20;
        row.mAppendObject(arrow);

        m_objUI.m_ptOrigin.set(0, 350);
        m_objUI.m_basePoint = RJBasePoint.LeftCenter;
        m_objUI.mInitialize();

        RJRadar radar;

        m_objUI2 = radar = new RJRadar();
        radar.mAddTartget(new String[] { "击杀", "金钱", "防御", "魔法", "物理", "助攻", "生存", "打野"}, new double[] {100, 100, 100, 100, 100, 1, 100, 50});
        radar.mAddTargetData(new double[] {85, 99, 32, 51,66, 0.54, 88, 34});
        radar.mAddTargetData(new double[] {55, 72, 88, 41,88, 0.32, 32, 50});
        radar.mAddTargetData(new double[] {99, 52, 81, 79,92, 0.99, 77, 12});
        m_objUI2.m_ptOrigin.set(90, 1130);
        radar.mInitialize();*/

        /*RJPieChart pie;

        m_objUI2 = pie = new RJPieChart();
        pie.mAddData("苹果", 1927);
        pie.mAddData("小米", 1856);
        pie.mAddData("三星", 1226);
        pie.mAddData("联想", 1011);
        pie.mAddData("华为", 1777);
        pie.m_dRadius = 400;
        m_objUI2.mSetrelativelayPosition(m_objUI, RJObject.RelativelySizeAndPos.Equal_Width_And_Height);
        m_objUI2.mSetRectSide(m_objUI, 0, RJObjectDefualtParams.dNotSetValue, 0, RJObjectDefualtParams.dNotSetValue);
	    m_objUI2.m_rectSide[0].rectSideRelObj = RJObject.RectSide.Right;
        pie.mInitialize();*/


        /*RJCSSeria seria;

        m_objUI2 = coordinateSystem = new RJCoordinateSystem();
        coordinateSystem.m_aixaX.mAddDiscreteData("星期一");
        coordinateSystem.m_aixaX.mAddDiscreteData("星期二");
        coordinateSystem.m_aixaX.mAddDiscreteData("星期三");
        coordinateSystem.m_aixaX.mAddDiscreteData("星期四");
        coordinateSystem.m_aixaX.mAddDiscreteData("星期五");
        coordinateSystem.m_aixaX.mAddDiscreteData("星期六");
        coordinateSystem.m_aixaX.mAddDiscreteData("星期七");
        coordinateSystem.m_aixaX.m_dAxiaLength = 700;
        coordinateSystem.m_aixaX.m_bScaleTextCenter = true;
        //coordinateSystem.m_aixaY.m_dAxiaLength = 700;
        //coordinateSystem.m_aixaY.m_bHiddenAxia = true;
        //coordinateSystem.m_ptOrigin.x = 200;
        //coordinateSystem.m_ptOrigin.y = 800;
        //coordinateSystem.m_aixaY.mSetRange(-100, 200, 20);
        //coordinateSystem.m_aixaX.m_bShowSacleExtendLine = false;
        //RJVariant vx = new RJVariant();
        //RJVariant vy = new RJVariant();
        //vx.mSetValue("   ");
        //vy.mSetValue(0f);
        //coordinateSystem.m_aixaY.mSetCross(vy);
        //coordinateSystem.m_aixaX.mSetCross(vx);
        //coordinateSystem.mSetCross(vx, vy);
        //coordinateSystem.mSetRectSide(pie, 0 ,0 , RJObjectDefualtParams.dNotSetValue, RJObjectDefualtParams.dNotSetValue);
        coordinateSystem.m_rectSide[0].objRel = m_objUI;
        coordinateSystem.m_rectSide[0].rectSideSelf = RJObject.RectSide.Top;
        coordinateSystem.m_rectSide[0].rectSideRelObj = RJObject.RectSide.Bottom;
        coordinateSystem.m_rectSide[0].dSpacing = 0;
        coordinateSystem.m_rectSide[1].objRel = m_objUI;
        coordinateSystem.m_rectSide[1].rectSideSelf = RJObject.RectSide.Right;
        coordinateSystem.m_rectSide[1].rectSideRelObj = RJObject.RectSide.Right;
        coordinateSystem.m_rectSide[1].dSpacing = 0;
        /*coordinateSystem.m_rectSide[2].objRel = pie;
        coordinateSystem.m_rectSide[2].rectSideSelf = RJObject.RectSide.Bottom;
        coordinateSystem.m_rectSide[2].rectSideRelObj = RJObject.RectSide.Bottom;
        coordinateSystem.m_rectSide[2].dSpacing = 0;*/
        /*coordinateSystem.m_rectSide[3].objRel = pie;
        coordinateSystem.m_rectSide[3].rectSideSelf = RJObject.RectSide.Right;
        coordinateSystem.m_rectSide[3].rectSideRelObj = RJObject.RectSide.Right;
        coordinateSystem.m_rectSide[3].dSpacing = 0;*/
       // coordinateSystem.m_relativelySizeAndPos[0].objRel = pie;
        //coordinateSystem.m_relativelySizeAndPos[0].relativelySizeAndPos = RJObject.RelativelySizeAndPos.Equal_Width_And_Height;
        /*coordinateSystem.m_relativelySizeAndPos[1].objRel = pie;
        coordinateSystem.m_relativelySizeAndPos[1].relativelySizeAndPos = RJObject.RelativelySizeAndPos.Center_Vertical_And_Horizontal;*/

        /*coordinateSystem.m_relativelySizeAndPos[1].objRel = pie;
        coordinateSystem.m_relativelySizeAndPos[1].relativelySizeAndPos = RJObject.RelativelySizeAndPos.Center_Vertical_And_Horizontal;*/


        /*seria = coordinateSystem.mCreateSeria();
        seria.mAddData("星期一", 195);
        seria.mAddData("星期二", 7);
        seria.mAddData("星期三", 89);
        seria.mAddData("星期四", 194);
        seria.mAddData("星期五", 166);
        seria.mAddData("星期六", 121);
        seria.mAddData("星期七", 166);
        seria.m_seriaType = RJCSSeriaType.Point;

        seria = coordinateSystem.mCreateSeria();
        seria.mAddData("星期一", 175);
        seria.mAddData("星期二", -84);
        seria.mAddData("星期三", 152);
        seria.mAddData("星期四", 106);
        seria.mAddData("星期五", 143);
        seria.mAddData("星期六", -58);
        seria.mAddData("星期七", 97);
        seria.m_seriaType = RJCSSeriaType.Line;

        seria = coordinateSystem.mCreateSeria();
        seria.mAddData("星期一", 44);
        seria.mAddData("星期二", -50);
        seria.mAddData("星期三", 32);
        seria.mAddData("星期四", 44);
        seria.mAddData("星期五", 20);
        seria.mAddData("星期六", -10);
        seria.mAddData("星期七", 22);
        seria.m_seriaType = RJCSSeriaType.Bar;

        coordinateSystem.mInitialize();*/
        /*RJGraphicsArrow arrow;

        m_objUI = arrow = new RJGraphicsArrow();
        arrow.m_ptOrigin.set(800, 700);
        arrow.m_directionType = RJDirectionType.Down;
        arrow.mInitialize();

        m_objUI2 = arrow = new RJGraphicsArrow();
        m_objUI2.mSetRectSide(m_objUI, 0, RJObjectDefualtParams.dNotSetValue, 0, RJObjectDefualtParams.dNotSetValue);
        m_objUI2.m_rectSide[0].rectSideRelObj = RJObject.RectSide.Right;
        arrow.m_directionType = RJDirectionType.Up;
        arrow.mInitialize();

        m_objUI3 = arrow = new RJGraphicsArrow();
        m_objUI3.mSetRectSide(m_objUI2, 0, RJObjectDefualtParams.dNotSetValue, 0, RJObjectDefualtParams.dNotSetValue);
        m_objUI3.m_rectSide[0].rectSideRelObj = RJObject.RectSide.Right;
        arrow.m_directionType = RJDirectionType.Down;
        arrow.mInitialize();

        m_objUI4 = arrow = new RJGraphicsArrow();
        m_objUI4.mSetRectSide(m_objUI3, RJObjectDefualtParams.dNotSetValue, 0, 0, RJObjectDefualtParams.dNotSetValue);
        m_objUI4.m_rectSide[2].rectSideRelObj = RJObject.RectSide.Bottom;
        arrow.m_directionType = RJDirectionType.Right;
        arrow.m_dKeyHandleLength = m_objUI.m_dWidth + m_objUI2.m_dWidth + m_objUI3.m_dWidth;
        arrow.mInitialize();*/
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        m_objUI.m_canvas = canvas;
        m_objUI.mDraw();
        //m_objUI2.m_canvas = canvas;
        //m_objUI2.mDraw();
        //m_objUI3.m_canvas = canvas;
        //m_objUI3.mDraw();
    }
}
