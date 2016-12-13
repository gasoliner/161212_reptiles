package cn.wan.reptile_acm;

import cn.wan.utils.TextUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 万洪基 on 2016/12/8.
 */
public class GetCode {
    private CloseableHttpClient httpClient;
    private String cookie;
    private String host;
    private String referer;
    private String user_agent;
    private CloseableHttpResponse response;
    public boolean isURLCodeFind=false;

    private String urlcode;
    private String urlnext;
    public GetCode() {
        httpClient =HttpClients.createDefault();
    }

    public CloseableHttpResponse sendRequest(String url) throws IOException {

        HttpGet httpGet=new HttpGet(TextUtil.dealText(url));
        if (cookie!=null){
            httpGet.setHeader("Cookie",cookie);
        }
        if (host!=null){
            httpGet.setHeader("Host",host);
        }
        if (referer!=null){
            httpGet.setHeader("Referer",referer);
        }
        if (user_agent!=null){
            httpGet.setHeader("User-Agent",user_agent);
        }
        response=httpClient.execute(httpGet);
        return response;
    }

    public String  match(String txt){
//        将正则规则进行对象的封装
        Pattern pattern=Pattern.compile("/onlinejudge2/index\\.php/Home/Viewcode/view/sid/\\d*");
//        通过matcher方法和字符串关联。获取对字符串操作的匹配器对象Matcher
        Matcher matcher=pattern.matcher(txt);
        if (matcher.find()){
            urlcode=matcher.group();
            isURLCodeFind=true;
            return "http://"+host+"/"+urlcode;
        }else{
            pattern=Pattern.compile("/onlinejudge2/index\\.php/Contest/conteststatus/cid/\\d*/pid/\\d*/result/1/p/\\d*\\.html\">下一页");
            matcher=pattern.matcher(txt);
            if (matcher.find()){
                txt=matcher.group();
                pattern=Pattern.compile("/onlinejudge2/index\\.php/Contest/conteststatus/cid/\\d*/pid/\\d*/result/1/p/\\d*\\.html");
                matcher=pattern.matcher(txt);
                if (matcher.find()){
                    urlnext=matcher.group();
                }
                return "http://"+host+"/"+urlnext;
            }else {
//                最后一页的情况
                return null;
            }
        }
    }
    public String  match(String txt,String regx){
        Pattern pattern=Pattern.compile(regx);
        Matcher matcher=pattern.matcher(txt);
        if (matcher.find()){
            return matcher.group();
        }else {
            return null;
        }
    }

    public void stopAllResource() throws IOException {
        httpClient.close();
        response.close();
    }




//    Getter and Setter
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }
}
