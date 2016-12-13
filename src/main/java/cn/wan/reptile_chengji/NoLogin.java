package cn.wan.reptile_chengji;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万洪基 on 2016/12/8.
 */
public class NoLogin {
    private CloseableHttpClient httpClient;
    public void byHttpClient() throws IOException {
        httpClient = HttpClients.createDefault();
        HttpPost httpPostSubmit = new HttpPost("http://210.44.176.116/cjcx/zcjcx_list.php");

        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("post_xuehao","15110506056"));
        httpPostSubmit.setEntity(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));

        httpPostSubmit.setHeader("Host","210.44.176.116");
        httpPostSubmit.setHeader("Referer", "http://210.44.176.116/cjcx/left.html");
        httpPostSubmit.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E)");
        CloseableHttpResponse response=httpClient.execute(httpPostSubmit);

        HttpEntity httpEntity=response.getEntity();
        String result= EntityUtils.toString(httpEntity,"utf-8");
        System.out.println(result);
        httpClient.close();
        response.close();
    }
}
