package cn.wan.reptile_demo.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * Created by 万洪基 on 2016/12/6.
 */
public class MainRepTest02 {
    public static void main(String[] args) throws IOException {
        MainRepTest02 mainRepTest02=new MainRepTest02();
//        mainRepTest02.udp();
        mainRepTest02.tcp();
    }

    public void tcp() throws IOException {
        ServerSocket serverSocket=new ServerSocket(8081);
        while (true){
            Socket socket=serverSocket.accept();
            InputStream inputStream=socket.getInputStream();
            byte [] bytes=new byte[10240];
            inputStream.read(bytes,0,10240);
            System.out.println(new String(bytes,0,bytes.length));
        }
    }
    public void udp() throws IOException {
        DatagramSocket datagramSocket=new DatagramSocket(10000);
        byte bytes[]=new byte[1024];
        DatagramPacket datagramPacket=new DatagramPacket(bytes,1024);
        datagramSocket.receive(datagramPacket);
        String string=new String (datagramPacket.getData(),0,datagramPacket.getData().length);
        System.out.println(string);
        datagramSocket.close();
    }
}
