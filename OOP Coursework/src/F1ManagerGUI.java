import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class F1ManagerGUI extends JFrame implements ActionListener, Serializable {  //This class use to show GUI Main Interface

   JButton button1; //leaderboard Button
   JButton button2; //leaderboard (ascending) Button
   JButton button3; //most 1st positions Button
   JButton button4; //new race Button
   JButton button5; //previous Button
   JButton button6; //search driver Button
   JButton button7; //display probability of winning of drivers
   JLabel label;
   JLabel borderLabel;

   ImageIcon f1Image=new ImageIcon("check.png");    //Main Image appears in the menu
   Border border= BorderFactory.createLineBorder(Color.red,3);


F1ManagerGUI() {

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setVisible(true);
    this.setSize(500,700);
    this.setResizable(false);
    this.setBounds(0,0,500,750);


    //Initializing buttons
    button1=new JButton("LEADERBOARD");             //button1= leaderboard Button
    button2=new JButton("LEADERBOARD (ASCENDING)"); //button2= leaderboard (ascending) Button
    button3=new JButton("MOST 1ST POSITIONS");      //button3= most 1st positions Button
    button4=new JButton("NEW RACE");                //button4= NEW RACE Button
    button5=new JButton("PREVIOUS RACES");          //button5= PREVIOUS RACES Button
    button6=new JButton("SEARCH DRIVER");           //button6= SEARCH DRIVER Button
    button7=new JButton("PROBABILITY OF DRIVERS");           //button6= SEARCH DRIVER Button

    label=new JLabel();
    borderLabel=new JLabel();



    label.setIcon(f1Image);
    label.setBounds(100,10,490,300);
    label.setSize(490,250);
    this.add(label);


//Button 1 properties (leaderboard Button)
    button1.setBounds(130,280,230,50);
    button1.addActionListener(this);
    this.add(button1);
    button1.setFocusable(false);

//Button 2 properties (leaderboard (ascending) Button)
    button2.setBounds(130,340,230,50);
    button2.addActionListener(this);
    this.add(button2);
    button2.setFocusable(false);

//Button 3 properties (leaderboard (ascending) Button)
    button3.setBounds(130,400,230,50);
    button3.addActionListener(this);
    this.add(button3);
    button3.setFocusable(false);

//Button 4 properties ( NEW RACE Button)
    button4.setBounds(130,460,230,50);
    button4.addActionListener(this);
    this.add(button4);
    button4.setFocusable(false);

//Button 5 properties (PREVIOUS RACES Button)
    button5.setBounds(130,520,230,50);
    button5.addActionListener(this);
    this.add(button5);
    button5.setFocusable(false);

//Button 6 properties (SEARCH DRIVER Button)
    button6.setBounds(130,580,230,50);
    button6.addActionListener(this);
    this.add(button6);
    button6.setFocusable(false);

//Button 7 properties (SEARCH DRIVER Button)
    button7.setBounds(130,640,230,50);
    button7.addActionListener(this);
    this.add(button7);
    button7.setFocusable(false);
}


    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource()==button1){            //to show drivers list in f1 championship
        DriversStats descendingOrder=new DriversStats();
        this.dispose();
    }
    else if(e.getSource()==button2){       //to show drivers according the the points (ASCENDING)
        AscendingDriverOrder ascendingOrder= new AscendingDriverOrder();
        this.dispose();
    }
    else if(e.getSource()==button3){        //to display according the no. 1st positions
        Most1stPlaces most1stPlaces=new Most1stPlaces();
        this.dispose();
    }
    else if(e.getSource()==button4){        //to generate a race randomly
       GenerateRace generateRace=new GenerateRace();
        this.dispose();
    }
    else if(e.getSource()==button5){        //to display races accoring to date in ascending
        SeasonRaces seasonRaces=new SeasonRaces();
        this.dispose();
  }
    else if(e.getSource()==button6){        //to search statistics of an existing driver
        SearchDriver searchDriver=new SearchDriver();
        this.dispose();
    }
    else if(e.getSource()==button7){        //to display drivers winning probability
        RaceProbability searchDriver=new RaceProbability();
        this.dispose();
    }
}
}
