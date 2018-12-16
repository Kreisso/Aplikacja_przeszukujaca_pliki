/**
 * Created by kreisso on 24.10.2018.
 */

import Poszukiwacz.PoszukiwaczSciezek;
import Poszukiwacz.PrzeszukiwaczPliku;
import View.loginpanel.LoginFrame;

import java.awt.*;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class main {

    final static private File FILE = new File(System.getProperty("user.dir"));
    final static private String SAMPLE = new String("od");

    public static void main(String[] args ) {


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame("Logowanie");
            }
        });

        BlockingQueue<File> arrayBlockingQueue = new ArrayBlockingQueue<File>(5);



        new Thread(new PoszukiwaczSciezek(arrayBlockingQueue, FILE)).start();;

        for (int i = 0; i < 50; i++)
            new Thread(new PrzeszukiwaczPliku(arrayBlockingQueue, SAMPLE)).start();

    }
}




