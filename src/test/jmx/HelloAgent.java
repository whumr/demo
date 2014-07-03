package test.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent {
	public static void main(String[] args) throws Exception {
        MBeanServer server = MBeanServerFactory.createMBeanServer();
        
        //必须是a:b=c的格式
        ObjectName helloName = new ObjectName("testjmx:aaa=hello");
        server.registerMBean(new Hello(), helloName);

        //http展示
        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        //默认端口8082
        adapter.setPort(80);
        server.registerMBean(adapter, adapterName);

        adapter.start();
        
        System.out.println("start.....");

    }
}
