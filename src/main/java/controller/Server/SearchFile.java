package controller.Server;

import model.Multiagency;

import java.util.LinkedList;
import java.util.List;

public class SearchFile {
    private String city;
    private String [][] multiagencies2;
    private List multiagencies;

    public SearchFile(){
        multiagencies = new LinkedList<Multiagency>();
    }

    public SearchFile(String city) {
        this.city = city;
        multiagencies = new LinkedList<Multiagency>();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List getMultiagencies() {
        return multiagencies;
    }

    public void addMultiagencies(Multiagency s) {
        multiagencies.add(s);
    }
}
