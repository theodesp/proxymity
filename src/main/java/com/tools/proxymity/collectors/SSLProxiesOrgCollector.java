package com.tools.proxymity.collectors;

import com.tools.proxymity.ProxyCollector;
import com.tools.proxymity.ProxyInfo;
import com.toortools.Utilities;

import java.sql.Connection;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SSLProxiesOrgCollector extends ProxyCollector
{


    public SSLProxiesOrgCollector(Connection dbConnection)
    {
        super(dbConnection);
    }


    public Vector<ProxyInfo> collectProxies()
    {
        Vector<ProxyInfo> proxies = new Vector<ProxyInfo>();
        try
        {
            String page = Utilities.readUrl("http://www.sslproxies.org/");
            Pattern p = Pattern.compile("<tr><td>.*?</td></tr>");
            Matcher m = p.matcher(page);
            while (m.find())
            {
                try
                {
                    String line = m.group();
                    Pattern pp = Pattern.compile("<tr><td>.*</td><td>\\d*</td>");
                    Matcher mm = pp.matcher(line);
                    if (mm.find()) {
                        line = mm.group().trim();
                        String ip = Utilities.cut("<tr><td>", "<", line);
                        String port = Utilities.cut("</td><td>", "<", line);
                        Integer.parseInt(port);
                        /*System.out.println("Ip: " + ip);
                        System.out.println("Port: " + port);*/
                        ProxyInfo proxyInfo = new ProxyInfo();
                        proxyInfo.setHost(ip);
                        proxyInfo.setPort(port);
                        proxyInfo.setType(ProxyInfo.PROXY_TYPES_HTTPS);
                        proxies.add(proxyInfo);
                    }
                }
                catch (Exception ee)
                {
                    ee.printStackTrace();
                }
                //System.exit(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return proxies;
    }
}