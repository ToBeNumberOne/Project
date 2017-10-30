package com.example.rajesh.rjmoudles;

/**
 * Created by acer-pc on 8/7/2017.
 */

class RJTabHeaderCell extends RJObject
{
    public RJContainDevice  m_containDeviceClickShow;

}

class RJTabHeader extends RJContainDevice
{
    public RJGroup  m_groupTabObjects;

    public RJTabHeader()
    {
        m_groupTabObjects   = new RJGroup();
    }
}

class RJTabDefualtParams
{
    static RJDirectionType  directionType = RJDirectionType.Up;
}

public class RJTab extends RJContainDevice
{
    public RJDirectionType  m_directionHeader;
    public RJTabHeader      m_tabHeader;
    public RJContainDevice  m_cdBody;

    public RJTab()
    {
    }

    public void mInitializeDefualtParams()
    {
        m_directionHeader = RJTabDefualtParams.directionType;
    }
}
