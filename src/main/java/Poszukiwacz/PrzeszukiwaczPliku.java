package Poszukiwacz;

import View.Frame;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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


    private BlockingQueue<File> queue;
    private String sample;
    private Multimap<String,String> multimap;
    private Frame view;
    private String[] result = new String[3];



    public PrzeszukiwaczPliku(BlockingQueue<File> queue, String sample, Frame view, Multimap<String, String> multimap){

        this.queue = queue;
        this.sample = sample;
        this.view = view;
        this.multimap = multimap;

    }

    public Multimap<String, String> getMultimap() {
        return multimap;
    }

    public void FindWord(File file) throws FileNotFoundException {

        Scanner reader = new Scanner(new BufferedReader(new FileReader(file)));

        int lineNumber = 0;

        while(reader.hasNext()) {
            lineNumber++;

            if(reader.nextLine().contains(sample))
                //System.out.println("Szukane słowo znajduje się w pliku: "+file.getPath()+" w lini "+lineNumber);
                multimap.put(file.getPath(), String.valueOf(lineNumber));
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
