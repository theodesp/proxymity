package com.tools.proxymity.collectors;

import com.tools.proxymity.ProxyCollector;
import com.tools.proxymity.datatypes.CollectorParameters;
import com.tools.proxymity.datatypes.ProxyInfo;
import com.toortools.Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProxyroxComCollector extends ProxyCollector
{
    public ProxyroxComCollector(CollectorParameters collectorParameters)
    {
        super(collectorParameters);
    }

    public Vector<ProxyInfo> collectProxies()
    {
        try
        {
            for (int i = 1; i<200; i++)
            {
                String page ="";
                try
                {
                    while (true)
                    {
                        try
                        {
                            page = anonReadUrl("http://proxyrox.com/?p="+i);
                        }
                        catch (IOException e)
                        {
                            //System.out.println("ProxyRox.com fail");
                            continue;
                        }
                        break;
                    }
                    //System.out.println("ProxyRox.com Success!");
                }

                catch (FileNotFoundException e)
                {
                    //e.printStackTrace();
                    return getProxies();
                }

                Pattern p = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+");
                Matcher m = p.matcher(page);
                while (m.find())
                {
                    try
                    {
                        String line = m.group();
                        //System.out.println(line);
                        StringTokenizer st = new StringTokenizer(line, ":");
                        String ip = st.nextToken();
                        String port = st.nextToken();
                        Integer.parseInt(port);

                        ProxyInfo proxyInfo = new ProxyInfo();
                        proxyInfo.setHost(ip);
                        proxyInfo.setPort(port);
                        proxyInfo.setType(ProxyInfo.PROXY_TYPES_HTTP);
                        addProxy(proxyInfo);
                    }

                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return getProxies();
    }
}
