package cn.wan.reptile_demo;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万洪基 on 2016/12/6.
 */
public class RepForXF {
    private CloseableHttpClient httpClient;
    private CookieStore cookieStore;
    public void byHttpClient() throws IOException {
        httpClient= HttpClients.createDefault();

        //设置cookie

        HttpPost httpPostSubmit=new HttpPost("http://210.44.176.46/default5.aspx");
        //用httpPost1访问的验证码图片能不能给httpPost2用？
        String code=getCode();
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("__VIEWSTATE","/wEPDwUKLTc2MzEwNzQ0Mw9kFgICAQ9kFgICBg8PZBYCHgdvbmNsaWNrBQ93aW5kb3cuY2xvc2UoKTtkZL4evS YObzTtZ4Tbqs4aCU5LGjA*"));
        nameValuePairs.add(new BasicNameValuePair("TextBox1","15110506056"));
        nameValuePairs.add(new BasicNameValuePair("TextBox2","**19970612"));
        nameValuePairs.add(new BasicNameValuePair("TextBox3",code));
        nameValuePairs.add(new BasicNameValuePair("RadioButtonList1","学生"));
        nameValuePairs.add(new BasicNameValuePair("Button1",""));
        nameValuePairs.add(new BasicNameValuePair("hidPdrs",""));
        nameValuePairs.add(new BasicNameValuePair("hidsc",""));

        UrlEncodedFormEntity encodedFormEntity=new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);

        httpPostSubmit.setEntity(encodedFormEntity);
        httpPostSubmit.setHeader("Cookie","ASP.NET_SessionId=5n3gimbz1fpriyiz454jlb55");
        httpPostSubmit.setHeader("Host","210.44.176.43");
        httpPostSubmit.setHeader("Origin","http://210.44.176.43");
        httpPostSubmit.setHeader("Referer","http://210.44.176.43/default5.aspx");
        httpPostSubmit.setHeader("Upgrade-Insecure-Requests","1");
        httpPostSubmit.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        CloseableHttpResponse response=httpClient.execute(httpPostSubmit);

        System.out.println("response:\n"+response);
        Header [] cookies=response.getHeaders("Set-Cookie");
        System.out.println("\n"+cookies.length);
        for (Header cookie:cookies){
            System.out.println("\n"+cookie+"\n");
        }




        HttpEntity httpEntity=response.getEntity();
//        String result=readresponse(httpEntity,"utf-8");
        String result=EntityUtils.toString(httpEntity,"utf-8");
        System.out.println(result);
        httpClient.close();
        response.close();
    }
    private String getCode() throws IOException {
        HttpGet httpGetCode =new HttpGet("http://210.44.176.46/CheckCode.aspx");
            HttpResponse response=httpClient.execute(httpGetCode);

            HttpEntity httpEntity=response.getEntity();
            InputStream inputStream=httpEntity.getContent();
            byte [] bytes=new byte[2048];
            FileOutputStream fileOutputStream=new FileOutputStream("F:\\CodeCheck\\code.png");
            int line=-1;
            try {
                while ((line=inputStream.read(bytes))!=-1){
                    fileOutputStream.write(bytes,0,bytes.length);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                inputStream.close();
                fileOutputStream.close();
            }
            System.out.println("文件：F:\\CodeCheck\\code.png，请输入验证码：");
            InputStreamReader inputStreamReader=new InputStreamReader(System.in);
            char [] chars=new char[11];
            inputStreamReader.read(chars);
            inputStreamReader.close();
            String code=new String(chars);
            return code;
    }


    private String readresponse(HttpEntity httpEntity,String charset) throws IOException {
        StringBuffer res=new StringBuffer();
        BufferedReader bufferedReader=null;
        try {
            if (httpEntity==null){
                return null;
            }
            bufferedReader=new BufferedReader(new InputStreamReader(httpEntity.getContent(),charset));
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                res.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return res.toString();
    }
}
