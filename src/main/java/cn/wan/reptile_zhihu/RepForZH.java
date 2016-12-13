package cn.wan.reptile_zhihu;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万洪基 on 2016/12/6.
 */
public class RepForZH {
    private CloseableHttpClient httpClient;
    public void byHttpClient() throws IOException {
        httpClient= HttpClients.createDefault();

        HttpPost httpPostSubmit=new HttpPost("https://www.zhihu.com/search");
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("type","content"));
        nameValuePairs.add(new BasicNameValuePair("q","福特野马"));

        httpPostSubmit.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        httpPostSubmit.setHeader("Cookie","d_c0=\'AGACxvIH9QqPToAIAcTxNrZ_B9vaG0tKHdo=|1481022447\'; q_c1=f4d73bd3e6a9405e9ff9fb340e328a72|1481022447000|1481022447000; _xsrf=ef9d02dd838ccd602a430d99c6e1d108; l_cap_id=\'NWY4Yjc5YWFlNTZiNDk1ODhmZGYxNjNjNzA0NjEyZjU=|1481161779|6cedef67bd22b42ef2847af6040b7ba444fc5e05\'; cap_id=\'OGNlZmFjNTljYzUxNGZmZGFmZDUxMGUwNWRhMWViNzM=|1481161779|c284f610ca028e86f97160a5b010cbca22011800\'; _zap=e675d4b5-d90f-4c20-acbe-d5c30df95e51; __utmt=1; r_cap_id=\'N2UxOTk0NmEwMDVjNDFhZmI2MzkwZTE4MWU2MDQ4Nzk=|1481161781|fa1ef78198763640674b029876f796bbb17c569e\'; login=\'NGM1NDBhNzkzNjgyNDljOGFmM2JlYzI1YWNjZTRjNzA=|1481161842|d41d96e70b1354aaad7248740c09a5a721ec6385\'; unlock_ticket=\'QUVBQThDX01yZ2tYQUFBQVlRSlZUWlhEU0ZnXzFsMm1lSU9YSUxfMi1qYVMyRlZHQ1JLUUtRPT0=|1481161869|c149030fbc86fa239085573cf407ed8940f4c04c\'; n_c=1; __utma=51854390.1741465888.1481022448.1481022448.1481161783.2; __utmb=51854390.6.10.1481161783; __utmc=51854390; __utmz=51854390.1481161783.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=51854390.100--|2=registration_date=20160328=1^3=entry_date=20160328=1; z_c0=Mi4wQUVBQThDX01yZ2tBWUFMRzhnZjFDaGNBQUFCaEFsVk5qVWx3V0FBaHRtSWVPRlk5NUpxOHkteEk0bGFrSkhUWWp3|1481162202|e274daab4f47dfcf366f4d116d96888e5e12f382");
        httpPostSubmit.setHeader("Host","www.zhihu.com");
        httpPostSubmit.setHeader("Origin","http://210.44.176.43");
        httpPostSubmit.setHeader("Referer","https://www.zhihu.com/search?type=content&q=%E5%A6%B9%E5%AD%90");
        httpPostSubmit.setHeader("Upgrade-Insecure-Requests","1");
        httpPostSubmit.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        CloseableHttpResponse response=httpClient.execute(httpPostSubmit);


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
