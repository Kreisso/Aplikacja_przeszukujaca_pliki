package controller;

import Poszukiwacz.PoszukiwaczSciezek;
import Poszukiwacz.PrzeszukiwaczPliku;
import View.Frame;
import View.loginpanel.SearchFileFrame;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import controller.Server.Connectivity;
import controller.Server.SearchFile;
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

    private String getModelCity(){
        return model.getSample();
    }


    private void setModelCity(String city){
        model.setSample(city);
    }



    private String getViewCity(){
        return view.getInputSearchBySample();
    }

    private void setViewCity(){
        view.setSearchByCityButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                view.restartRowCount();
                try {
                    multimap.clear();
                }catch (ConcurrentModificationException e1)
                {
                    System.out.println("test");
                }finally {

                }
                System.out.println("search");
                getFiles();

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                finally {
                    while (true) {
                        try {
                            addColumnsToTable();
                            break;
                        } catch (ConcurrentModificationException e1) {
                            System.out.println("test");

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

private void getFiles()
{
    file = new File(System.getProperty("user.dir"));
    sample = new String(view.getInputSearchBySample());

    BlockingQueue<File> arrayBlockingQueue = new ArrayBlockingQueue<File>(5);
    new Thread(new PoszukiwaczSciezek(arrayBlockingQueue, file)).start();;

    for (int i = 0; i < 50; i++)
        new Thread(new PrzeszukiwaczPliku(arrayBlockingQueue, sample, view, multimap)).start();



}



    public void addColumnsToTable(){

        String[] result = new String[3];
        int i = 0;
        for (String key : multimap.keys()) {
            result[0] = String.valueOf(i++);
            result[1] = String.valueOf(key);
            result[2] = String.valueOf(multimap.get(key));
            System.out.println(multimap.get(key));
            view.addColumnToMultiagencyTable(result);

        }



    }



}
