package cn.wan.test;

import cn.wan.utils.TextUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by 万洪基 on 2016/12/12.
 */
public class TestAll {
    @Test
    public void match(){
        String txt1="#include &lt;iostream&gt;\n" +
                "\n" +
                "using namespace std;\n" +
                "\n" +
                "class Time\n" +
                "{\n" +
                "    public:\n" +
                "        int hour;\n" +
                "        int min;\n" +
                "        int sec;\n" +
                "};\n" +
                "\n" +
                "int main()\n" +
                "{\n" +
                "    Time time;\n" +
                "    cin &gt;&gt;time.hour&gt;&gt;time.min&gt;&gt;time.sec;\n" +
                "    cout &lt;&lt;time.hour&lt;&lt;&quot;:&quot;&lt;&lt;time.min&lt;&lt;&quot;:&quot;&lt;&lt;time.sec&lt;&lt;endl;\n" +
                "\n" +
                "    return 0;\n" +
                "}\n" +
                "\n" +
                "/***************************************************\n" +
                "User name: rchg150616万洪基\n" +
                "Result: Accepted\n" +
                "Take time: 0ms\n" +
                "Take Memory: 164KB\n" +
                "Submit time: 2016-11-10 08:34:29\n" +
                "****************************************************/";

//        String string="kjdasjfkjsd&gt;hdfjk&lt;dsf&gt;fsdfdsfs&gt;dfagg";
//        System.out.println(txt1.replaceAll("&gt;",">"));
//        System.out.println("----------------");
//        System.out.println(txt1);


        System.out.println(TextUtil.html2text(txt1));




//        String s="da jia hao,ming tian bu fang jia !";
//        String reg="\\b[a-z]{3}\\b";
//        Pattern p = Pattern.compile(reg);
//        Matcher m = p.matcher(s);
//        while(m.find()){
//            System.out.println(m.group());
//        }
    }

    @Test
    public void aboutHttpClient() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet HttpGetSubmit = new HttpGet("http://210.44.176.116/cjcx/zcjcx_list.php");
        HttpGet HttpGetSubmit = new HttpGet("http://acm.sdut.edu.cn/");

//
//        HttpGetSubmit.setHeader("Host","210.44.176.116");
//        HttpGetSubmit.setHeader("Referer", "http://210.44.176.116/cjcx/left.html");
//        HttpGetSubmit.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E)");
        CloseableHttpResponse response=httpClient.execute(HttpGetSubmit);

        HttpEntity httpEntity=response.getEntity();
        String result= EntityUtils.toString(httpEntity,"utf-8");
        System.out.println(result);
        httpClient.close();
        response.close();
    }

    @Test
    public void fileTest() throws IOException {
        File file=new File("F:\\11212112121212.txt");
        file.createNewFile();
        System.out.println(file.exists());
    }

}
