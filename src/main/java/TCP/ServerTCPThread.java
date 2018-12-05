package TCP;

import java.net.Socket;

public class ServerTCPThread extends Thread {
    Socket mySocket;

    public ServerTCPThread(Socket socket)
    {
        super(); // konstruktor klasy Thread
        mySocket = socket;
    }

    public void run() // program wątku
    {
        try {
            /* odbieramy i drukujemy ... */
            //...
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}