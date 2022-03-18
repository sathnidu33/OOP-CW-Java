import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateRace extends JFrame implements ActionListener, Serializable {      //using this class to generate races and positions

    Formula1ChampionshipManager championshipManager=new Formula1ChampionshipManager();  //Created an object using Formula1ChampionshipManager class
    LinkedList<Integer> positions =new LinkedList<>();                                  //to add positions
    Race race;

    JTable driverTable;
    JButton backBtn;
    JButton setDateBtn;
    JTextField dateTextField;
    JLabel label;
    Date raceDate;


    GenerateRace(){
        championshipManager.readFile();
        JPanel panel=new JPanel();
        panel.setBounds(0,0,700,500);
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());
        driverTable = generateRace();
        driverTable.setBounds(0,0,700,500);
        driverTable.setVisible(false);  //table visibility false till user enters date


        //Back Button properties
        backBtn=new JButton("Back");
        backBtn.setBounds(10,280,100,30);
        backBtn.addActionListener(this);
        this.add(backBtn);
        backBtn.setFocusable(false);

        //setDate button
        setDateBtn=new JButton("Set Date");
        setDateBtn.setBounds(420,320,100,30);
        setDateBtn.addActionListener(this);
        this.add(setDateBtn);
        setDateBtn.setFocusable(false);

        //JLabel properties
        label=new JLabel();
        label.setText("Please enter the race Date (DD-MM-YYYY)");
        label.setVisible(true);
        this.add(label);
        label.setText("Please enter the race Date (DD-MM-YYYY)");

        //dateTextField properties
        dateTextField=new JTextField();
        dateTextField.setBounds(350,280,250,30);
        this.add(dateTextField);


        JScrollPane pane=new JScrollPane(driverTable);
        panel.add(pane);


        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(700,500);
        this.setResizable(false);
    }

    public Race createRace(){ //method to create race

        championshipManager.readFile();     //calling deserialization from from Formula1ChampionShipManager
        LinkedList<Formula1Driver> driverOrder=new LinkedList<>(championshipManager.getDriversList());

        for(int i=0;i<driverOrder.size();i++){

            int pos=generatePositions(positions,driverOrder.size()); //generating random position using generatePositions method
            positions.add(pos);

            switch (pos) {          //updating driver statistics position,points and total races
                case 1:
                    championshipManager.driversList.get(i).setFirstPositions(championshipManager.driversList.get(i).firstPositions + 1);
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 25);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);

                    break;
                case 2:
                    championshipManager.driversList.get(i).setSecondPositions(championshipManager.driversList.get(i).secondPositions + 1);
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 18);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);

                    break;
                case 3:
                    championshipManager.driversList.get(i).setThirdPositions(championshipManager.driversList.get(i).thirdPositions + 1);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 15);

                    break;
                case 4:
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 12);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);

                    break;
                case 5:
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 10);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);

                    break;
                case 6:
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 8);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);

                    break;
                case 7:
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 6);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);

                    break;
                case 8:
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 4);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);

                    break;
                case 9:
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 2);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);

                    break;
                case 10:
                    championshipManager.driversList.get(i).setTotalPoints(championshipManager.driversList.get(i).totalPoints + 1);
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);

                    break;
                default:
                    championshipManager.driversList.get(i).setTotalRaces(championshipManager.driversList.get(i).totalRaces + 1);
                    break;
            }
        }
        Race newRace=new Race(raceDate,driverOrder,positions);
        championshipManager.saveFile();
        return newRace;
    }

    public int generatePositions(LinkedList <Integer>positions,int range){      //method to generate position randomly
        int number;

        do{
            Random random= new Random();
            number = random.nextInt(range)+1;

        }while (positions.contains(number));

        return number;
    }


        JTable generateRace(){                                                              //method to generate the table

        race= createRace();
        String[][] driverData = new String[championshipManager.driversList.size()][2];      //initializing a 2D array
        String[] columns = {"Driver Name", "Position"};
        Collections.sort(championshipManager.driversList);                                  //sorting the list in descending order

        for (int i = 0; i < race.getRaceInfo().size(); i++) {
            String driverName = race.getRaceInfo().get(i).getDriverName();
            String wins=String.valueOf(positions.get(i));
            String[] drivers = {driverName,wins};
            driverData[i] = drivers;
        }

        JTable driverTable =new JTable(driverData,columns);                                 //adding to the table

        //Sorting the table in ascending order
        TableRowSorter<TableModel> sorter;
        sorter = new TableRowSorter<TableModel>(driverTable.getModel());
        driverTable.setRowSorter(sorter);
        LinkedList<RowSorter.SortKey> sortKeys = new LinkedList<>();

        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        driverTable.setEnabled(false);
        driverTable.getTableHeader().setReorderingAllowed(false);
        driverTable.getTableHeader().setResizingAllowed(false);
        driverTable.getTableHeader().setEnabled(false);

        return driverTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backBtn){  //added actionListener to backBtn to go back to the main gui
            F1ManagerGUI homeMenu=new F1ManagerGUI();
            this.dispose();
        }

       else if (e.getSource()==setDateBtn){ //button to enter date
            driverTable.setVisible(true);

            try {
                raceDate =new SimpleDateFormat("dd-MM-yyyy").parse(dateTextField.getText());
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            race.setDate(raceDate);
            System.out.println(race.getDate());
            championshipManager.getRaceInfo().add(race);
            championshipManager.saveFile();
            dateTextField.setEnabled(false);    //cant change after date enter
            setDateBtn.setEnabled(false);
        }
    }
}
