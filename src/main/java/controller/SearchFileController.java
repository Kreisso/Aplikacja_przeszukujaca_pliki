package controller;

import Poszukiwacz.PoszukiwaczSciezek;
import Poszukiwacz.PrzeszukiwaczPliku;
import TCP.ClientTCP;
import View.Frame;
import View.loginpanel.SearchFileFrame;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import controller.Server.Connectivity;
import controller.Server.SearchFile;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public class SearchFileController {
    private File file;
    private  Multimap<String,String> multimap;
    private String sample;
    private SearchFile model;
    private SearchFileFrame view;
    private Connectivity con;
    private Frame previousView;

    public SearchFileController(SearchFile model, SearchFileFrame view, Frame previousView, Connectivity con) {
        this.model = model;
        this.view = view;
        this.con = con;
        this.previousView = previousView;
        multimap = ArrayListMultimap.create();
        setViewCity();
    }

    public SearchFileController(SearchFile model, SearchFileFrame view, Frame previousView) {
        this.model = model;
        this.view = view;
        this.previousView = previousView;
        multimap = ArrayListMultimap.create();

        setViewCity();
    }

    private void setViewCity(){
        view.setSearchByCityButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                view.restartRowCount();
                try {
                    System.out.println(multimap);
                    multimap.clear();
                }catch (ConcurrentModificationException e1)
                {
                    System.out.println("ConcurrentModificationException 1");
                }finally {

                }
                System.out.println("search");
//                getFiles();
                sample = new String(view.getInputSearchBySample());
                ClientTCP clientTCP = new ClientTCP(sample, multimap);

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                finally {
                    while (true) {
                        try {
//                            System.out.println("dodaje do tbeli");

                            addColumnsToTable(clientTCP.getMultimap());
                            break;
                        } catch (ConcurrentModificationException e1) {
                            System.out.println("ConcurrentModificationException 2");

                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e2) {
                                e1.printStackTrace();
                            }

                        } finally {

                        }
                    }
                }


            }
        });
    }

//private void getFiles()
//{
////    String path = "/Users/kreisso/Desktop/";
//    String path = System.getProperty("user.home");
//    file = new File(path);
//    sample = new String(view.getInputSearchBySample());
//
//    BlockingQueue<File> arrayBlockingQueue = new ArrayBlockingQueue<File>(5);
//    new Thread(new PoszukiwaczSciezek(arrayBlockingQueue, file)).start();;
//
//    for (int i = 0; i < 50; i++)
//        new Thread(new PrzeszukiwaczPliku(arrayBlockingQueue, sample, multimap)).start();
//
//
//
//}



    public void addColumnsToTable(Multimap<String,String> m){

        String[] result = new String[3];
        int i = 0;
        System.out.println("addColumns" + m);
        for (String key : m.keys()) {
            result[0] = String.valueOf(i++);
            result[1] = String.valueOf(key);
            result[2] = String.valueOf(m.get(key));
            System.out.println(m.get(key));
            view.addColumnToMultiagencyTable(result);

        }



    }



}
