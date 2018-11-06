/**
 * Created by kreisso on 24.10.2018.
 */

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class main {

    final static private File FILE = new File(System.getProperty("user.dir"));
    final static private String SAMPLE = new String("od");

    public static void main(String[] args ) {
        BlockingQueue<File> arrayBlockingQueue = new ArrayBlockingQueue<File>(5);



        new Thread(new PoszukiwaczSciezek(arrayBlockingQueue, FILE)).start();;

        for (int i = 0; i < 50; i++)
            new Thread(new PrzeszukiwaczPliku(arrayBlockingQueue, SAMPLE)).start();

    }
}




