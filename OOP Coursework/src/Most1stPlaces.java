import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collections;

public class Most1stPlaces extends JFrame implements ActionListener, Serializable {  //class to show drivers according to the number of 1st positions they've won

    Formula1ChampionshipManager driverObj=new Formula1ChampionshipManager();
    JTable firstPositions;
    JButton backBtn;

    Most1stPlaces(){
        driverObj.readFile();
        JPanel panel=new JPanel();
        panel.setBounds(0,0,700,500);
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());
        firstPositions = firstPositions();
        firstPositions.setBounds(0,0,700,500);  //setting bounds of the table

        //Back Button properties
        backBtn=new JButton("Back");
        backBtn.setBounds(10,280,100,30);
        backBtn.addActionListener(this);
        this.add(backBtn);
        backBtn.setFocusable(false);

        JScrollPane pane=new JScrollPane(firstPositions);
        panel.add(pane);

        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(700,500);
        this.setResizable(false);
    }

    JTable firstPositions(){    //method to add table

        driverObj.driversList.sort((a,b) -> (a.getFirstPositions()- b.getFirstPositions()));
        String[][] driverData = new String[driverObj.driversList.size()][7];    //initializing the 2D array for the table
        String[] columns = {"Driver Name", "Driver Team", "Total Races","Wins","2nd","3rd","Points"}; //columns of the table
        Collections.sort(driverObj.driversList);

        for (int i = 0; i < driverObj.driversList.size(); i++) {        //getting values of driverList
            String driverName = driverObj.driversList.get(i).getDriverName();
            String driverTeam = driverObj.driversList.get(i).getDriverTeam();
            String totalRaces = String.valueOf(driverObj.driversList.get(i).getTotalRaces());
            String wins=String.valueOf(driverObj.driversList.get(i).getFirstPositions());
            String second=String.valueOf(driverObj.driversList.get(i).getSecondPositions());
            String third=String.valueOf(driverObj.driversList.get(i).getThirdPositions());
            String points=String.valueOf(driverObj.driversList.get(i).getTotalPoints());
            String[] drivers = {driverName, driverTeam,totalRaces,wins,second,third,points};
            driverData[i] = drivers;
        }
        return firstPositions =new JTable(driverData,columns);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backBtn){         //added actionListener to backBtn to go back to the main gui
            F1ManagerGUI homeMenu=new F1ManagerGUI();
            this.dispose();
        }

    }
}
