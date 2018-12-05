package TCP;

import java.net.InetAddress;
import java.net.ServerSocket;
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
            try {
                // tworzymy socket
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                    // czekamy na zgłoszenie klienta ...
                    //...
                }
                //zamknięcie strumieni i połączenia
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}