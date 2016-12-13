package cn.wan.reptile_acm;

import cn.wan.utils.FileUtil;
import cn.wan.utils.TextUtil;
import org.apache.http.util.EntityUtils;

import java.io.*;

/**
 * Created by 万洪基 on 2016/12/6.
 */
       /*
        * 思路
        * 1.发送请求并获得响应，
        * 2.把响应的内容解析，解析后有三种情况：
        *    |--获得view code界面的url，执行1,获得#include......，over
        *    |--获得下一页的url，执行1
        *    |--获得null，说明到最后一页也没发现view code界面的url，over
        * 3.如果获得了code，则用IO流导出成文件
        * */
public class Main_acm {
    public static void main(String[] args) throws IOException {
        int filename=1;
        int requesttimes=50;
        String code;
        String url;
        String cookie;
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("tell me your cookie:");
        cookie=bufferedReader.readLine();
        while (requesttimes!=0){
            int page=0;
            System.out.println("tell me url:");
            url=bufferedReader.readLine();
            if (url.trim().equals("exit")){
                System.out.println("Bye!");
                break;
            }

            GetCode getCode=new GetCode();
            getCode.setCookie(TextUtil.dealText(cookie));
            getCode.setHost("acm.sdut.edu.cn");
            getCode.setReferer("http://acm.sdut.edu.cn/onlinejudge2/index.php/Home/Contest/problemlist/cid/1821");
            getCode.setUser_agent("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E)");
            while (true){
                page++;
                String result=EntityUtils.toString(getCode.sendRequest(url).getEntity(),"utf-8");
                url=getCode.match(result);

//            test
                System.out.println("url:"+url);

                if (getCode.isURLCodeFind){
                    code= TextUtil.html2text(getCode.match(EntityUtils.toString(getCode.sendRequest(url).getEntity(),"utf-8"),"#include[\\s\\S]*\\*/"));

//                test
                    System.out.println("code:"+code);

                    break;
                }else if (url==null){
                    code=null;
                    break;
                }
            }
//            file and io about code
            if (code!=null){
                File file= FileUtil.newFile("F:\\DownLoadCode\\"+filename+".txt");

                System.out.println(file.getName());

                FileOutputStream fileOutputStream=new FileOutputStream(file);
                fileOutputStream.write(code.getBytes());
                fileOutputStream.close();
                getCode.stopAllResource();
                System.out.println("在第"+page+"页");
                filename++;
            }else {
                System.out.println("该用户没有Accept该题目！");
            }
            requesttimes--;
        }
        bufferedReader.close();
    }
}
