package cn.wan.reptile_acm;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万洪基 on 2016/12/14.
 */
public class Login {
    private String cookie;
    private String host;
    private String referer;
    private String user_agent;
    private String connection;


    public CloseableHttpResponse signin(String username,String password) throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpPost httpPost=new HttpPost("http://acm.sdut.edu.cn/onlinejudge2/index.php/Home/Login/login");
        if (cookie!=null){
            httpPost.setHeader("Cookie",cookie);
        }
        if (host!=null){
            httpPost.setHeader("Host",host);
        }
        if (referer!=null){
            httpPost.setHeader("Referer",referer);
        }
        if (user_agent!=null){
            httpPost.setHeader("User-Agent",user_agent);
        }
        if (connection!=null){
            httpPost.setHeader("Connection",connection);
        }
        List<NameValuePair> nameValuePairs=new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("user_name",username));
        nameValuePairs.add(new BasicNameValuePair("password",password));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));
        return httpClient.execute(httpPost);
    }

//    getter and setter

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

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
