import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collections;

public class SeasonRaces extends JFrame implements Serializable, ActionListener { //shows races according to the date in ascending order
    Formula1ChampionshipManager driverObj=new Formula1ChampionshipManager();
    JButton backBtn;
    JTable raceTable;

    SeasonRaces(){
        driverObj.readFile();
        JPanel panel=new JPanel();
        panel.setBounds(0,0,700,500);
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());
        raceTable =raceTable();
        raceTable.setEnabled(false);
        raceTable.getTableHeader().setReorderingAllowed(false);
        raceTable.getTableHeader().setResizingAllowed(false);
        raceTable.getTableHeader().setEnabled(false);
        raceTable.setBounds(0,0,700,500);


        //Back Button properties
        backBtn=new JButton("Back");
        backBtn.setBounds(10,280,100,30);
        backBtn.addActionListener(this);
        this.add(backBtn);
        backBtn.setFocusable(false);


        JScrollPane pane=new JScrollPane(raceTable);
        panel.add(pane);

        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(700,500);
        this.setResizable(false);

    }

    JTable raceTable(){     //adding values to the table
        String[][] driverData = new String[driverObj.driversList.size()][5];
        String[] columns = {"Race Date", "Driver Name", "Driver Team","Position","Points"}; //column names
        Collections.sort(driverObj.driversList);
        String driverName=null;


        for (int i = 0; i < driverObj.raceInfo.size(); i++) {
            String raceDate=String.valueOf(driverObj.raceInfo.get(i).getDate());
            driverName = driverObj.raceInfo.get(i).getRaceInfo().get(i).getDriverName();
            String driverTeam = driverObj.driversList.get(i).getDriverTeam();
            String position=String.valueOf(driverObj.raceInfo.get(i).getRaceInfo().get(i).getPosition());
            String points=String.valueOf(driverObj.driversList.get(i).getTotalPoints());
            String[] drivers = {raceDate, driverTeam,points};
            driverData[i] = drivers;
        }
        return raceTable =new JTable(driverData,columns);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backBtn){                    //added actionListener to backBtn to go back to the main gui
            F1ManagerGUI homeMenu=new F1ManagerGUI();
            this.dispose();
        }

    }
}
