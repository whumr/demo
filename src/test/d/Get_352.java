package test.d;

import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class Get_352 {

	static String url = "http://www.352.cn/trading3/webTradingRzbList.do?cursor=1&pageSize=10";
	static String charset = "UTF-8";
	
//	<div class="rzbcollect_list">
//    <div class="title">
//        <span style="color:#cc0000; " title="���ŵ������Ȩ3��6��">���ŵ������Ȩ3��6��---ZT201400036</span></div>
//         <table width="100%" border="0" cellspacing="1" cellpadding="0">
//                                         <tr>
//                                            <td   class="tdtitle">���ʰ����ͣ�</td>
//                                            <td class="tdwidth" >��Ŀ��</td>
//                                            <td class="tdtitle">�������ڣ�</td>
//                                            <td>
//                                                    2014-02-05
//                                            </td>
//                                        </tr>
//                                        
//                                        <tr>
//                                            <td   class="tdtitle">��Ŀ���?��</td>
//                                            <td colspan="3" >���������ŵ���������޹�˾ &nbsp;&nbsp;
//                                                
//                                                <a class="buttonfwt" onclick="window.open('http://saibainuo.vip.352.cn');" href="###"><span class="allico a2"></span><span class="text">������</span></a>
//                                                
//                                            </td>
//                                        </tr>
//                                        <tr>
//                                            <td class="tdtitle">�����</td>
//                                            <td ><span class="style1">1000.00</span> ��</td>
//                                            <td class="tdtitle">�������ޣ�</td>
//                                            <td >120 ��</td>
//                                        </tr>
//                                        
//                                        
//                                        <tr>
//                                            
//                                            
//                                                <td  class="tdtitle">�Ƿ���</td>
//                                                <td ><span class="state p1">[δ���]</span></td>
//                                                <td class="tdtitle">������ڣ�</td>
//                                                <td> -- </td>
//                                            
//                                        </tr>
//                                        <tr>
//                                            
//                                            
//                                                <td class="tdtitle">�Ƿ���㣺</td>
//                                                <td > δ���� </td>
//                                                <td class="tdtitle">�������ڣ�</td>
//                                                <td> -- </td>
//                                            
//                                        </tr>
//                                    </table>
//
//    <div class="more">
//        <a class="link" target="_blank" href="http://www.352.cn/trading3/webTradingRzbDetail.do?id=1162">�鿴����</a>
//        
//        
//    </div>
//    </div>

	
	public static void main(String[] args) throws Exception {
		HttpClient client = HttpUtil.getHttpClient();
		HttpGet get = new HttpGet(url);
		get.setHeader("Host", "www.352.cn");
		get.setHeader("Connection", "keep-alive");
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
		
		HttpResponse response = client.execute(get);
		
		String s = readResponse(response, charset);
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("x.txt"), "UTF-8");
		
		writer.write(s);
		writer.close();
//		System.out.println(readResponse(response, charset));
	}

	public static String readResponse(HttpResponse response, String charset) throws Exception {
		InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), charset);
		char[] chars = new char[4096];
		StringBuilder sb = new StringBuilder();
		int length = -1;
		while ((length = reader.read(chars)) > 0)
			sb.append(chars, 0, length);
		return sb.toString();
	}
}
