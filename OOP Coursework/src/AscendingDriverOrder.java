import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collections;

public class AscendingDriverOrder extends JFrame implements ActionListener, Serializable {

    Formula1ChampionshipManager driverObj=new Formula1ChampionshipManager();  //class use to show according to the points won by drivers (ascending)
    JButton backBtn=new JButton("Back");
    JTable accentingStatsTable;

    AscendingDriverOrder(){ //constructor

        Collections.reverse(driverObj.driversList); //reversed to generate to ascending order
        JPanel panel2=new JPanel();
        panel2.setBounds(0,0,700,500);
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new BorderLayout());
        accentingStatsTable=ascendingDriverOrder();  //adding method to the table
        accentingStatsTable.setBounds(0,0,500,700);

        //Back Button properties
        backBtn=new JButton("Back");
        backBtn.setBounds(10,280,100,30);
        backBtn.addActionListener(this);
        this.add(backBtn);
        backBtn.setFocusable(false);

        JScrollPane pane2=new JScrollPane(accentingStatsTable);
        panel2.add(pane2);

        this.add(panel2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(700,500);
        this.setResizable(false);
    }

    JTable ascendingDriverOrder(){       //shows in ascending order for POINTS

        driverObj.readFile();
        String[][] driverAscendingData = new String[driverObj.driversList.size()][7];                   //initializing the 2D array
        String[] columns = {"Driver Name", "Driver Team", "Total Races","Wins","2nd","3rd","Points"};   //table columns

        for (int i = 0; i < driverObj.driversList.size(); i++) {                                        //getting values of driverList

            String driverName = driverObj.driversList.get(i).getDriverName();
            String driverTeam = driverObj.driversList.get(i).getDriverTeam();
            String totalRaces = String.valueOf(driverObj.driversList.get(i).getTotalRaces());
            String wins=String.valueOf(driverObj.driversList.get(i).getFirstPositions());
            String second=String.valueOf(driverObj.driversList.get(i).getSecondPositions());
            String third=String.valueOf(driverObj.driversList.get(i).getThirdPositions());
            String points=String.valueOf(driverObj.driversList.get(i).getTotalPoints());
            String[] drivers = {driverName, driverTeam,totalRaces,wins,second,third,points};
            driverAscendingData[i] = drivers;   //adding array in to the 2D array
        }
        return accentingStatsTable = new JTable(driverAscendingData, columns);  //initializing the table
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==backBtn){        //added actionListener to backBtn to go back to the main gui
            F1ManagerGUI homeMenu=new F1ManagerGUI();
            this.dispose();
        }

    }
}
