package cn.wan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 万洪基 on 2016/12/13.
 */
public class TextUtil {
    public static String html2text(String html){

        Pattern pattern=Pattern.compile("&[\\s\\S]*?;");
        Matcher matcher=pattern.matcher(html);
        while (matcher.find()){
            switch (matcher.group()){
                case "&gt;":html=html.replaceAll(matcher.group(),">");break;
                case "&lt;":html=html.replaceAll(matcher.group(),"<");break;
                case "&amp;":html=html.replaceAll(matcher.group(),"&"); break;
                case "&quot;":html=html.replaceAll(matcher.group(),"\""); break;
                case "&apos;":html=html.replaceAll(matcher.group(),"'"); break;
            }
        }
        return html;
    }
//    public static String text2html(String text){
//        Pattern pattern=Pattern.compile("&.*;");
//        Matcher matcher=pattern.matcher(text);
//        while (matcher.find()){
//            switch (matcher.group()){
//                case "&gt;":text.replaceAll(matcher.group(),">"); break;
//                case "&lt;":text.replaceAll(matcher.group(),"<"); break;
//                case "&amp;":text.replaceAll(matcher.group(),"&"); break;
//                case "&quot;":text.replaceAll(matcher.group(),"\""); break;
//                case "&apos;":text.replaceAll(matcher.group(),"'"); break;
//            }
//        }
//        return text;
//    }
    public static String dealText(String text){
        return text.trim();
    }
}
