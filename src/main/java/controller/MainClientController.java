package controller;


import View.Frame;
import View.mainpanel.SearchFileFrame;
import controller.Server.ClientMain;
import controller.Server.Connectivity;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class MainClientController {
    private SearchFileFrame view;
    private ClientMain model;
    private Connectivity con;
    private Frame previousView;
    private int ukk;

    public MainClientController(ClientMain model, SearchFileFrame view, Frame previousView, int ukk, Connectivity con) {
        this.view = view;
        this.model = model;
        this.con = con;
        this.previousView = previousView;
        this.ukk = ukk;
        setSearchMultiagencyMenuListener();
    }

    public MainClientController(ClientMain model, SearchFileFrame view, Frame previousView, int ukk) {
        this.view = view;
        this.model = model;
        this.previousView = previousView;
        this.ukk = ukk;
        setSearchMultiagencyMenuListener();
    }






//    public void addColumnsToPolicyTable(){
//        List<Policy> policyList = model.getPolicies();
//        Iterator it = policyList.iterator();
//        int i = 0;
//        while(it.hasNext()){
//            i++;
//            Policy policy = (Policy) it.next();
//            view.addColumnToPolicyTable(policy.infoForTable(i));
//        }
//    }

    private void setSearchMultiagencyMenuListener()
    {
        view.setSearchMultiagencyMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                System.out.println("1");
//                new SearchFileController(new SearchMultiagency(),
//                        new SearchMultiagencyFrame("Wyszukaj multiagencje"),view, con);
//                view.setVisible(false);
            }

            public void menuDeselected(MenuEvent e) {
                System.out.println("2");

            }

            public void menuCanceled(MenuEvent e) {
                System.out.println("3");

            }
        });
    }
}
