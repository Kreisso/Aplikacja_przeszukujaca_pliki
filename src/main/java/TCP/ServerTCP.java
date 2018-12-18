package TCP;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerTCP {
    public static void main(String args[]) throws UnknownHostException{
        System.out.println(InetAddress.getLocalHost().getHostAddress());

        if (args.length == 0)
            System.out.println("Wprowadź numer portu, na którym serwer będzie oczekiwał na klientów");
        else {
            int port = 0;
            try {

                port = Integer.parseInt(args[0]);

            } catch (NumberFormatException e) {
                System.err.println("Wprowadź poprawny numer portu: " + e);
                return;
            }

            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
                while (true) {

                    Socket socket = serverSocket.accept();
                    (new ServerTCPThread(socket)).start();
                }
            } catch (Exception e) {
                System.err.println(e);
            }finally{
                if(serverSocket != null)
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}