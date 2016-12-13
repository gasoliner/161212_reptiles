package cn.wan.reptile_demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by 万洪基 on 2016/12/8.
 */
public class NoLogin {
    private CloseableHttpClient httpClient;
    public void byHttpClient() throws IOException {
        httpClient = HttpClients.createDefault();
        HttpPost httpPostSubmit = new HttpPost("http://210.44.176.45/xskbcx.aspx?xh=15110506056&xm=%E4%B8%87%E6%B4%AA%E5%9F%BA&gnmkdm=N121603");
        httpPostSubmit.setHeader("Cookie", " - ASP.NET_SessionId: osefzn55bqordl55t4mbnv45");
        httpPostSubmit.setHeader("Host","210.44.176.43");
        httpPostSubmit.setHeader("Origin","http://210.44.176.43");
        httpPostSubmit.setHeader("Referer","http://210.44.176.43/default5.aspx");
        httpPostSubmit.setHeader("Upgrade-Insecure-Requests","1");
        httpPostSubmit.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        CloseableHttpResponse response=httpClient.execute(httpPostSubmit);

        HttpEntity httpEntity=response.getEntity();
        String result= EntityUtils.toString(httpEntity,"utf-8");
        System.out.println(result);
        httpClient.close();
        response.close();
    }
}
