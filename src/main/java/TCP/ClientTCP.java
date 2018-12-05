package TCP;

import java.net.InetAddress;
import java.net.Socket;

public class ClientTCP {
    public static void main(String args[]) {
        if (args.length < 2)
            System.out.println("Wprowadź adres serwera TCP oraz numer portu");
        else{
            int port = 0;
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Wprowadź poprawny numer portu: " + e);
                return;
            }
            try {
                Socket socket = new Socket(InetAddress.getByName(args[0]), port);
                int in;
                while ((in = System.in.read()) >= 0)
                    socket.getOutputStream().write((char) in);
                socket.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
