import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kreisso on 24.10.2018.
 */
public class PrzeszukiwaczPliku implements Runnable{


    BlockingQueue<File> queue;
    String sample;

    public PrzeszukiwaczPliku(BlockingQueue<File> queue, String sample){

        this.queue = queue;
        this.sample = sample;


    }

    public void FindWord(File file) throws FileNotFoundException {

        Scanner reader = new Scanner(new BufferedReader(new FileReader(file)));

        int lineNumber = 0;

        while(reader.hasNext()) {
            lineNumber++;

            if(reader.nextLine().contains(sample))
                System.out.println("Szukane słowo znajduje się w pliku: "+file.getPath()+" w lini "+lineNumber);

        }

        reader.close();

    }
    public void run() {
        boolean interrupt = false;

        while(!interrupt){

            try {
                File currentFile;
                currentFile = queue.take();
                if(currentFile.equals(new File("Empty"))) {
                    interrupt = true;
                    queue.put(currentFile);
                }
                else {
                    FindWord(currentFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }


}
