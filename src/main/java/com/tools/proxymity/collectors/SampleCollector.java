package com.tools.proxymity.collectors;

import com.tools.proxymity.ProxyCollector;
import com.tools.proxymity.ProxyInfo;

import java.sql.Connection;
import java.util.Vector;

public class SampleCollector extends ProxyCollector
{
    public SampleCollector(Connection dbConnection)
    {
        super(dbConnection);
    }

    public Vector<ProxyInfo> collectProxies()
    {
        try
        {

            for (int i = 1; i<50; i++)
            {


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return getProxies();
    }
}
