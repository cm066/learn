package com.cm.socket;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {
//    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);
    public void start(int port){
        //创建ServerSocket 对象并且绑定一个端口
        try (ServerSocket server = new ServerSocket(port);){
            Socket socket;
            while ((socket = server.accept()) != null){
                System.err.print("client connected");
                System.out.println();
                try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream()))   {
                    Message message = (Message)objectInputStream.readObject();
                    System.out.println("receive client message content:"+message.getContent());
                    message.setContent("new content");
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloServer helloServer = new HelloServer();
        helloServer.start(6666);
    }
}
