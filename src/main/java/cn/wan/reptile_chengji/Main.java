package cn.wan.reptile_chengji;

import java.io.IOException;

/**
 * Created by 万洪基 on 2016/12/6.
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        RepForXF repForXF=new RepForXF();
//        repForXF.byHttpClient();
        cn.wan.reptile_chengji.NoLogin noLogin=new cn.wan.reptile_chengji.NoLogin();
        noLogin.byHttpClient();
    }
}
