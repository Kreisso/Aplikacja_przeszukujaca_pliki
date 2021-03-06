package View.loginpanel;
import View.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

    public class SearchFileFrame extends Frame {
        private int frameWidth = 1000;
        private int frameHeight = 600;

        private JScrollPane scrollPane;
        private JTable resultTable;
        private JLabel searchByCityLabel;
        private JTextField inputSearchBySample;

        private JButton searchButton;

        private DefaultTableModel model;

        public JTable getResultTable() {
            return resultTable;
        }

        public SearchFileFrame(String name) throws HeadlessException {
            super(name);

            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
            this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

            this.setSize(frameWidth, frameHeight);
            this.setLayout(null);


            searchByCityLabel = new JLabel("Wpisz szukane słowo lub wyrażenie");
            searchByCityLabel.setSize(searchByCityLabel.getPreferredSize());
            searchByCityLabel.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05)-20);
            this.add(searchByCityLabel);

            inputSearchBySample = new JTextField();
            inputSearchBySample.setSize((int) (frameWidth * 0.7), 40);
            inputSearchBySample.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05));
            this.add(inputSearchBySample);

            searchButton = new JButton("Szukaj");
            searchButton.setSize((int) (frameHeight*0.25), 40);
            searchButton.setLocation((int) (frameWidth*0.8),  (int) (frameHeight*0.05));

            this.getRootPane().setDefaultButton(searchButton);
            this.add(searchButton);

            resultTable = new JTable();
            model = new DefaultTableModel();
            model.addColumn("LP");
            model.addColumn("ścieżka do pliku");
            model.addColumn("Numer lini");
            resultTable = new JTable(model);
            scrollPane = new JScrollPane(resultTable);
            scrollPane.setSize((int) (frameWidth * 0.7), (int) (frameHeight * 0.7));
            scrollPane.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.15));
            this.add(scrollPane);



        }

        public String getInputSearchBySample() {
            return inputSearchBySample.getText();
        }

        public void setSearchByCityButton(ActionListener actionListener){

            searchButton.addActionListener(actionListener);

        }

        public void addColumnToMultiagencyTable(Object[] multiagencies) {

            model.addRow(multiagencies);
        }

        public void restartRowCount()
        {
            model.setRowCount(0);
        }

    }


