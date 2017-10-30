package com.example.rajesh.rjmoudles;

/**
 * Created by acer-pc on 8/7/2017.
 */

class RJLoginDefualtParams
{
    static  double dcontainDeviceWidth          = 600;
    static  double dcontrolsInterval            = 50;
    static  double dcontrolsHeight              = 80;
    static  double dCopyRightBottomIntervalu    = 0;
}

public class RJLogin extends RJContainDevice
{
    protected   RJContainDevice m_containDevice;
    protected   RJEdit          m_editServer;
    protected   RJEdit          m_editUserName;
    protected   RJEdit          m_editPassword;
    protected   RJButton        m_buttonLogin;
    protected   RJButton        m_buttonRemenberPassword;
    protected   RJText          m_textCopyRight;

    public RJLogin()
    {
        m_editPassword              = new RJEdit();
        m_editServer                = new RJEdit();
        m_editUserName              = new RJEdit();
        m_buttonLogin               = new RJButton();
        m_containDevice             = new RJContainDevice();
        m_buttonRemenberPassword    = new RJButton();
        m_textCopyRight             = new RJText();

        m_containDevice.m_groupObj.mAddObject(m_editPassword);
        m_containDevice.m_groupObj.mAddObject(m_editServer);
        m_containDevice.m_groupObj.mAddObject(m_editUserName);
        m_containDevice.m_groupObj.mAddObject(m_buttonLogin);
        m_containDevice.m_groupObj.mAddObject(m_containDevice);
        m_containDevice.m_groupObj.mAddObject(m_buttonRemenberPassword);

        m_groupObj.mAddObject(m_containDevice);
        m_groupObj.mAddObject(m_textCopyRight);
    }

    public void mInitializeEvent()
    {
    }

    public void mInitialize()
    {
        m_editServer.m_dHeigth = m_editPassword.m_dHeigth = m_editUserName.m_dHeigth = m_buttonLogin.m_dHeigth = m_buttonRemenberPassword.m_dHeigth = RJLoginDefualtParams.dcontrolsHeight;

        m_editServer.m_rectSide[0].objRel = m_containDevice;
        m_editServer.m_rectSide[0].dSpacing = 0;
        m_editServer.m_rectSide[0].rectSideSelf = RectSide.Top;
        m_editServer.m_rectSide[0].rectSideRelObj = RectSide.Top;
        m_editServer.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
        m_editServer.m_relativelySizeAndPos[0].objRel = m_containDevice;

        m_editUserName.m_rectSide[0].objRel = m_editServer;
        m_editUserName.m_rectSide[0].dSpacing = RJLoginDefualtParams.dcontrolsInterval;
        m_editUserName.m_rectSide[0].rectSideSelf = RectSide.Top;
        m_editUserName.m_rectSide[0].rectSideRelObj = RectSide.Top;
        m_editUserName.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
        m_editUserName.m_relativelySizeAndPos[0].objRel = m_containDevice;

        m_editPassword.m_rectSide[0].objRel = m_editUserName;
        m_editPassword.m_rectSide[0].dSpacing = RJLoginDefualtParams.dcontrolsInterval;
        m_editPassword.m_rectSide[0].rectSideSelf = RectSide.Top;
        m_editPassword.m_rectSide[0].rectSideRelObj = RectSide.Top;
        m_editPassword.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
        m_editPassword.m_relativelySizeAndPos[0].objRel = m_containDevice;

        m_buttonLogin.m_rectSide[0].objRel = m_editPassword;
        m_buttonLogin.m_rectSide[0].dSpacing = RJLoginDefualtParams.dcontrolsInterval;
        m_buttonLogin.m_rectSide[0].rectSideSelf = RectSide.Top;
        m_buttonLogin.m_rectSide[0].rectSideRelObj = RectSide.Top;
        m_buttonLogin.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
        m_buttonLogin.m_relativelySizeAndPos[0].objRel = m_containDevice;

        m_buttonRemenberPassword.m_rectSide[0].objRel = m_buttonLogin;
        m_buttonRemenberPassword.m_rectSide[0].dSpacing = RJLoginDefualtParams.dcontrolsInterval;
        m_buttonRemenberPassword.m_rectSide[0].rectSideSelf = RectSide.Top;
        m_buttonRemenberPassword.m_rectSide[0].rectSideRelObj = RectSide.Top;
        m_buttonRemenberPassword.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
        m_buttonRemenberPassword.m_relativelySizeAndPos[0].objRel = m_containDevice;

        m_containDevice.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
        m_containDevice.m_relativelySizeAndPos[1].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
        m_containDevice.m_relativelySizeAndPos[1].objRel = this;
        m_containDevice.m_relativelySizeAndPos[0].objRel = this;
        m_containDevice.m_dWidth = RJLoginDefualtParams.dcontainDeviceWidth;
        m_containDevice.m_dHeigth = m_editServer.m_dHeigth + m_editUserName.m_dHeigth + m_editPassword.m_dHeigth + m_buttonLogin.m_dHeigth + m_buttonLogin.m_dHeigth + 4 * RJLoginDefualtParams.dcontrolsInterval;

        m_textCopyRight.m_relativelySizeAndPos[0].objRel = this;
        m_textCopyRight.m_relativelySizeAndPos[0].relativelySizeAndPos = RelativelySizeAndPos.CenterHorizontal;
        m_textCopyRight.m_rectSide[0].objRel = this;
        m_textCopyRight.m_rectSide[0].dSpacing = RJLoginDefualtParams.dCopyRightBottomIntervalu;
        m_textCopyRight.m_rectSide[0].rectSideSelf = RectSide.Bottom;
        m_textCopyRight.m_rectSide[0].rectSideRelObj = RectSide.Bottom;

        super.mInitialize();
    }
}