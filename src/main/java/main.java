/**
 * Created by kreisso on 24.10.2018.
 */

import Poszukiwacz.PoszukiwaczSciezek;
import Poszukiwacz.PrzeszukiwaczPliku;
import View.loginpanel.LoginFrame;
import controller.LoginController;
import controller.Server.Login;

import java.awt.*;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class main {



    public static void main(String[] args ) {


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginController(new Login(), new LoginFrame("Logowanie"));
            }
        });

//        BlockingQueue<File> arrayBlockingQueue = new ArrayBlockingQueue<File>(5);
//
//
//
//        new Thread(new PoszukiwaczSciezek(arrayBlockingQueue, FILE)).start();;
//
//        for (int i = 0; i < 50; i++)
//            new Thread(new PrzeszukiwaczPliku(arrayBlockingQueue, SAMPLE)).start();

    }
}




