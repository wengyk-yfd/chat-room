/**
 * @(#)CommunicateTest.java, 3月 08, 2020.
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
public class CommunicateTest {

    private static final int CARRIAGE_RETURN = 13;

    public static void main(String[] args) throws IOException {

        // 创建一个服务器对象
        ServerSocket server = new ServerSocket(9000);
        System.out.println("服务器创建成功，端口号为：" + server.getLocalPort());
        while (true) {
            // 创建一个 socket 对象用于连接
            Socket socket = server.accept();
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            // test
            String s = "Hello! Welcome to my ServerSocket\n";
            byte[] dataOut = s.getBytes();
            output.write(dataOut);
            output.flush();

            // 接收来自客户端的字符
            int ascii = input.read();
            // 如果接收到回车字符就结束循环
            while (ascii != CARRIAGE_RETURN) {
                char accept = (char) ascii;
                System.out.println(accept);
                ascii = input.read();
            }

            // 关闭连接
            socket.close();
        }
    }

}