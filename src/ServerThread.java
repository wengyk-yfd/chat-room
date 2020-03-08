/**
 * @(#)ServerTread.java, 3月 08, 2020.
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
public class ServerThread extends Thread {

    private static final int CARRIAGE_RETURN = 13;

    OutputStream output;
    InputStream input;
    Socket socket;

    public void run() {
        try {
            System.out.println("start a new thread");

            output = socket.getOutputStream();
            input = socket.getInputStream();

            String outS = "Hello! Welcome to my ServerSocket!\r\n";
            out(outS);

            readString();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setUpServer(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("服务器创建成功，端口号：" + server.getLocalPort());
        while (true) {
            ServerThread ts = new ServerThread();
            ts.socket = server.accept();
            ts.start();
        }
    }

    private void out(String outS) throws IOException {
        byte[] dataOut = outS.getBytes();
        output.write(dataOut);
        output.flush();
    }

    public void readString() throws IOException {
        String inputS = "";
        while (!inputS.equals("bye")) {
            inputS = "";
            int asciiNumber = input.read();
            while (asciiNumber != CARRIAGE_RETURN) {
                inputS += (char) asciiNumber;
                asciiNumber = input.read();
            }
            System.out.println(inputS);
        }
        output.close();
    }

    public static void main(String[] args) throws IOException {
        ServerThread server = new ServerThread();
        server.setUpServer(9010);
    }

}