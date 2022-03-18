import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class SearchDriver extends JFrame implements ActionListener, Serializable {
    Formula1ChampionshipManager driverObj=new Formula1ChampionshipManager();
    JButton backBtn;
    JTextArea  textArea;
    JTextField searchTxtField;
    JButton searchBtn;


    SearchDriver(){
        driverObj.readFile();
        JPanel panel=new JPanel();
        panel.setBounds(0,0,700,500);
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());
        textArea =new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(50,20,600,250);
        textArea.setFont(new Font("Calibre",Font.BOLD,13));
        this.add(textArea);

        //Back Button properties
        backBtn=new JButton("Back");
        backBtn.setBounds(10,280,100,30);
        backBtn.addActionListener(this);
        this.add(backBtn);
        backBtn.setFocusable(false);

        //setDate button
        searchBtn=new JButton("Search");
        searchBtn.setBounds(420,320,100,30);
        searchBtn.addActionListener(this);
        this.add(searchBtn);
        searchBtn.setFocusable(false);

        //dateTextField properties
        searchTxtField=new JTextField();
        searchTxtField.setBounds(350,280,250,30);
        this.add(searchTxtField);


        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(700,500);
        this.setResizable(false);

    }

    public void setData(){
        textArea.setText("");

        driverObj.readFile();
        System.out.println(driverObj.getRaceInfo().size());
        String text;
        int i=0;

        String tempDriverName=searchTxtField.getText();
        for(Race race:driverObj.getRaceInfo()){

            for (Formula1Driver driver: race.getRaceInfo()){
                if(tempDriverName.equalsIgnoreCase(driver.getDriverName())){
                    String pos=String.valueOf(race.getPosition().get(i));
                    String name=driver.getDriverName();
                    System.out.println(race.getDate());
                    text="Name:  "+name+"   Race Date: "+race.getDate().toString()+"   Position: "+pos+ "\n";
                    textArea.setText(textArea.getText()+text);
                    i++;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backBtn){
            F1ManagerGUI homeMenu=new F1ManagerGUI();
            this.dispose();

        }else if (e.getSource()==searchBtn){
            setData();
    }
    }
}
