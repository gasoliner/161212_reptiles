package cn.wan.reptile_demo.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.*;

/**
 * Created by 万洪基 on 2016/12/6.
 */
public class MainRepTest {
    public static void main(String[] args) throws IOException {
        MainRepTest mainRepTest=new MainRepTest();
//        mainRepTest.udp();
//        mainRepTest.tcp();
        mainRepTest.byHttpClient();
    }
    public void byHttpClient() throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpPost httpPost=new HttpPost("http://www.baidu.com");
        HttpGet httpGet=new HttpGet("http://127.0.0.1:8080/all/");
        CloseableHttpResponse response=httpClient.execute(httpGet);
        HttpEntity httpEntity=response.getEntity();
        String result=readresponse(httpEntity,"utf-8");

        System.out.println(result);
    }
    public String readresponse(HttpEntity httpEntity,String charset) throws IOException {
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

            }
        }
        return res.toString();
    }


    public void tcp() throws IOException {
        Socket socket=new Socket("127.0.0.1",8080);
        OutputStream outputStream=socket.getOutputStream();
        outputStream.write("GET /all/ HTTP/1.1\n".getBytes());
        outputStream.write("Host: 211.64.20.216:8080\n".getBytes());
        outputStream.write("Connection: keep-alive\n".getBytes());
        outputStream.write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n".getBytes());
        outputStream.write("Upgrade-Insecure-Requests: 1\n".getBytes());
        outputStream.write("User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36\n".getBytes());
        outputStream.write("Accept-Encoding: gzip, deflate, sdch\n".getBytes());
        outputStream.write("Accept-Language: zh-CN,zh;q=0.8\n".getBytes());
        outputStream.write("\n".getBytes());
        InputStream inputStream=socket.getInputStream();
        byte [] bytes=new byte[10240];
        inputStream.read(bytes,0,1024);
        System.out.println(new String(bytes,0,bytes.length));

    }

    public void udp() throws IOException {
        DatagramSocket datagramSocket=new DatagramSocket();
        String string="udp传输数据的演示";
        byte[] bytes=string.getBytes();
        DatagramPacket datagramPacket=
                new DatagramPacket(bytes,bytes.length, InetAddress.getByName("My-PC"),10000);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
