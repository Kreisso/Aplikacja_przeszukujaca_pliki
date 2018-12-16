package controller;

import View.Frame;
import View.mainpanel.SearchFileFrame;
import controller.Server.Connectivity;
import controller.Server.SearchFile;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class SearchFileController {
    private SearchFile model;
    private SearchFileFrame view;
    private Connectivity con;
    private Frame previousView;

    public SearchFileController(SearchFile model, SearchFileFrame view, Frame previousView, Connectivity con) {
        this.model = model;
        this.view = view;
        this.con = con;
        this.previousView = previousView;
        setMyPoliciesMenuListener();
        setViewCity();
    }

    public SearchFileController(SearchFile model, SearchFileFrame view, Frame previousView) {
        this.model = model;
        this.view = view;
        this.previousView = previousView;
        setMyPoliciesMenuListener();
        setViewCity();
    }

    private String getModelCity(){
        return model.getCity();
    }


    private void setModelCity(String city){
        model.setCity(city);
    }



    private String getViewCity(){
        return view.getInputSearchByCity();
    }

    private void setViewCity(){
        view.setSearchByCityButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("search");
                getMultiagencies();
//                addColumnsToMultiagenciesTable();
            }
        });
    }

    private void getMultiagencies() {
        view.restartRowCount();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        setModelCity(getViewCity());
        String sql="select * from multiagency INNER join contact on multiagency.contact_id=contact.id where city=?";
        try{
            con =  new Connectivity();
            System.out.println(getModelCity());
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, getModelCity());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println("w srodku 1");

            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }



//    public void addColumnsToMultiagenciesTable(){
//        List<Multiagency> multiagencyList = model.getMultiagencies();
//        System.out.println(multiagencyList);
//        Iterator it = multiagencyList.iterator();
//        int i = 0;
//        while(it.hasNext()){
//            i++;
//            Multiagency multiagency = (Multiagency) it.next();
//            view.addColumnToMultiagencyTable(multiagency.infoForTable(i));
//        }
//    }

    private void setMyPoliciesMenuListener()
    {
        view.setMyPoliciesMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                previousView.setVisible(true);
                view.dispose();
            }

            public void menuDeselected(MenuEvent e) {

            }

            public void menuCanceled(MenuEvent e) {

            }
        });
    }
}
