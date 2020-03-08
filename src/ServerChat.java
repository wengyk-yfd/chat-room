/**
 * @(#)ServerChat.java, 3月 08, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wengyk
 */
public class ServerChat {

    private static final int CARRIAGE_RETURN = 13;

    OutputStream output;    // 输出流
    InputStream input;  // 输入流
    ServerSocket server;    // 设置一个服务器对象属性

    // 定义一个建立服务器的方法
    private void setUpServer(int port) throws IOException {
        // 将输入的端口设置为服务器
        server = new ServerSocket(port);
        // 输出当前服务器的端口号
        System.out.println("服务器创建成功，端口号：" + server.getLocalPort());
        // 定义一个作为中介的接收对象Socket
        Socket socket = server.accept();

        // 为输入输出流赋值
        output = socket.getOutputStream();
        input = socket.getInputStream();

        // 开始通信
        // 传送信息给客户机
        String outS = "Hello! Welcome to my ServerSocket!\r\n";
        out(outS);

        // 发送信息给服务器
        readString();
    }

    // 定义一个输出信息到客户机的方法
    private void out(String outS) throws IOException {
        // 将字符串转化为byte数组
        byte[] dataOut = outS.getBytes();
        // 调用write()将信息发送客户机
        output.write(dataOut);
        // 强制输出到命令行的界面中
        output.flush();
    }

    // 定义一个传送字符串给服务器的方法
    public void readString() throws IOException {
        String inputS = "";
        while (!inputS.equals("bye")) {
            // 读取第一个字符
            int asciiNumber = input.read();
            while (asciiNumber != CARRIAGE_RETURN) {
                // 将ascii码转化为相应的char型字符
                inputS += (char) asciiNumber;
                // 接收下一个字符
                asciiNumber = input.read();
            }
            System.out.println(inputS);
        }
        output.close();
    }

    public static void main(String[] args) throws IOException {
        //创建一个通信类的对象
        ServerChat server = new ServerChat();
        server.setUpServer(9005);
    }

}