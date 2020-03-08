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

            // 关闭连接
            socket.close();
        }
    }

}