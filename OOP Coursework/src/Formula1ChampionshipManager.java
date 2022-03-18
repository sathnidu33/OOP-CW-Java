import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager,Serializable {

    private static String driverName = "";
    private static String driverTeam="";
    private static String driverLocation ="";
    private static int secondPositions = 0;
    private static int thirdPositions=0;
    private static int firstPositions=0;
    private static int totalPoints=0;
    private static int totalRaces=0;
    private static int position;
    private static Date date;
    Date raceDate;

    public  LinkedList <Formula1Driver> driversList =new LinkedList<>();
    public  LinkedList <Formula1Driver> driverOrder =new LinkedList<>();
    public  LinkedList <Race> raceInfo =new LinkedList<>();
    static Formula1ChampionshipManager drivers =new Formula1ChampionshipManager();


    public static void main(String[] args)  throws IOException, ParseException  {

        int menuInput;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome To Formula1 Championship \n");


        while (true) {
            //Console Menu interface
            System.out.println("""
                    Press '1' to add a new driver
                    Press '2' to Delete the driver and the team
                    Press '3' to Change the driver for an existing constructor team
                    Press '4' statistics of existing drivers
                    Press '5' Formula 1 Driver Table
                    Press '6' to add a race
                    Press '7' to save to a file
                    Press '8 to open GUI'""");


            menuInput = input.nextInt();


            if (menuInput == 1) {               //add a driver
                drivers.addDriver();

            } else if (menuInput == 2) {        //delete a driver
                drivers.removeDriver();


            } else if (menuInput == 3) {        //changing drivers team
                drivers.changeTeam();


            } else if (menuInput == 4) {        //display statistics
                drivers.driverStats();

            } else if (menuInput == 5) {        //Displaying formula1 DRIVER TABLE
                drivers.driverTable();

            } else if (menuInput == 6) {       //add Race
                drivers.addRace();


            } else if (menuInput == 7) {        //saveFile
                drivers.saveFile();

            } else if (menuInput == 8) {        //opening GUI
                new F1ManagerGUI();
            }
        }
//TODO object.check();
    }

    public LinkedList<Formula1Driver> getDriversList() {
        return driversList;
    }

    public void setDriversList(LinkedList<Formula1Driver> driversList) {
        this.driversList = driversList;
    }

    public LinkedList<Formula1Driver> getDriverOrder() {
        return driverOrder;
    }

    public void setDriverOrder(LinkedList<Formula1Driver> driverOrder) {
        this.driverOrder = driverOrder;
    }

    public LinkedList<Race> getRaceInfo() {
        return raceInfo;
    }

    public void setRaceInfo(LinkedList<Race> raceInfo) {
        this.raceInfo = raceInfo;
    }

    @Override
    public void addDriver(){  //adding driver to the championship
        readFile();
        Scanner input1 = new Scanner(System.in);
        System.out.println("ADD DRIVER");
        System.out.println("-------------------------------------");
        System.out.println("Please enter drivers first name: ");
        driverName = input1.nextLine();

        System.out.println("Please enter drivers racing team:");
        driverTeam = input1.nextLine();

        System.out.println("Please enter drivers driver location:");
        driverLocation = input1.nextLine();

        System.out.println("Please enter drivers no. of 1st positions: ");
        firstPositions = input1.nextInt();

        System.out.println("Please enter drivers no. of 2nd positions: ");
        secondPositions = input1.nextInt();

        System.out.println("Please enter drivers no. of 3rd positions: ");
        thirdPositions = input1.nextInt();

        System.out.println("Please enter drivers total points so far:");
        totalPoints = input1.nextInt();

        System.out.println("Please enter drivers total races participated so far:");
        totalRaces = input1.nextInt();

        System.out.println("Driver " + driverName + " added successfully to the TEAM " + driverTeam + "\n");

        driversList.add(new Formula1Driver(driverName, driverLocation, driverTeam, secondPositions, thirdPositions, firstPositions, totalPoints, totalRaces));
        driverOrder.add(new Formula1Driver(driverName, driverLocation, driverTeam, position));
//        raceInfo.add(new Race(date, driverOrder)); //to create races
        saveFile();
    }
    @Override
    public void removeDriver(){             //method to remove a driver from championship
        readFile();                         //loading previous records
        System.out.println("REMOVE DRIVER");
        Scanner input2 = new Scanner(System.in);
        System.out.println("Please enter drivers name: ");
        String deleteDriver = input2.nextLine();
        boolean verify = true;

        for (int i = 0; i < driversList.size(); i++) {      //check whether driver is available in championship

            if (driversList.get(i).getDriverName().equalsIgnoreCase(deleteDriver)) {
                System.out.println("Driver " + deleteDriver + " of " + driversList.get(i).getDriverTeam() + " successfully removed\n");
                driversList.remove(i);          //removing driver from the list
                verify = false;
            }

            if (verify) {
                System.out.println("Could not find the driver");
            }
        }
        saveFile(); //saving to the ser file
    }

    public void changeTeam(){       //to change existing driver team

        readFile();
        System.out.println("CHANGE DRIVER CONSTRUCTOR TEAM"+"\n");
        System.out.println("Please enter the drivers name: ");
        Scanner input3 = new Scanner(System.in);
        String tempDriver = input3.nextLine();
        boolean verify2 = true;
        boolean verify3 = true;
        String newTeam;


        for (int i = 0; i < driversList.size(); i++) { //checking whether driver available

            if (driversList.get(i).getDriverName().trim().equalsIgnoreCase(tempDriver)) {
                System.out.println("Please enter drivers new constructor team need to be added: ");
                Scanner temp=new Scanner(System.in);
                newTeam = temp.nextLine();
                verify2 = false;

                for (Formula1Driver formula1Driver : driversList) {

                    if (formula1Driver.getDriverName().equalsIgnoreCase(newTeam)) {     //checking whether the team is a existing team
                        driversList.get(i).setDriverTeam(newTeam);
                        System.out.println("CONSTRUCTOR TEAM CHANGED SUCCESSFULLY" + "\n");
                        verify3 = false;
                        break;

                    }
                }

                if (verify3) { //if verify3 false
                    System.out.println("Team " + newTeam + " not registered!");
                }
            }
        }

        if (verify2) {
            System.out.println("No driver has been found!");
        }

        saveFile();
    }

    public void driverStats(){          //to display individual driver statistics

        readFile();
        Scanner input4 = new Scanner(System.in);
        System.out.println("Please enter drivers name to see statics");
        String tempName = input4.nextLine();

        for (int i = 0; i < driversList.size(); i++) {      //checking whether the driver is available

            if (driversList.get(i).getDriverName().equalsIgnoreCase(tempName)) {

                System.out.println("\nDRIVER DETAILS\n");
                System.out.println("Driver Name: " + driversList.get(i).getDriverName());
                System.out.println("Driver Team: " + driversList.get(i).getDriverTeam());
                System.out.println("Driver Location: " + driversList.get(i).getLocation());
                System.out.println("Total Races Participated: " + driversList.get(i).getTotalRaces());
                System.out.println("No. of 1st Positions: " + driversList.get(i).getFirstPositions());
                System.out.println("No. of 2nd Positions: " + driversList.get(i).getSecondPositions());
                System.out.println("No. of 3rd Positions: " + driversList.get(i).getThirdPositions());
                System.out.println("No. of total points in season: " + driversList.get(i).getTotalPoints());

                System.out.println();

            } else {

                System.out.println("Cannot find a driver as " + tempName+"\n");
                System.out.println("Please enter driver name again"+"\n");
                break;


            }
        }
    }

    public void driverTable(){  //F1 driver table to display existing driver stats

        readFile();
        System.out.println("\n CHAMPIONSHIP LEADERBOARD \n");
        Collections.sort(driversList);  //to sort the list

        for (int i = 0; i < driversList.size(); i++) {

            System.out.println("Driver Name: " + driversList.get(i).getDriverName());
            System.out.println("Driver Team: " + driversList.get(i).getDriverTeam());
            System.out.println("Driver Location: " + driversList.get(i).getLocation());
            System.out.println("Total Races Participated: " + driversList.get(i).getTotalRaces());
            System.out.println("No. of 1st Positions: " + driversList.get(i).getFirstPositions());
            System.out.println("No. of 2nd Positions: " + driversList.get(i).getSecondPositions());
            System.out.println("No. of 3rd Positions: " + driversList.get(i).getThirdPositions());
            System.out.println("No. of total points in season: " + driversList.get(i).getTotalPoints());
            System.out.println();
        }
    }

    @Override
    public void addRace(){      //adding new race

        System.out.println("RACE DAY"+"\n");
        readFile();
        LinkedList<Integer> positions= new LinkedList<>();
        Scanner input6 = new Scanner(System.in);
        System.out.println("Please enter the race date:  ");
        System.out.println("dd-mm-yyyy");
        String date=input6.nextLine();

        try {
            raceDate=new SimpleDateFormat("dd-MM-yyyy").parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < driversList.size(); i++) {

            System.out.println("Please enter driver " + driversList.get(i).getDriverName() + " position:");
            position = input6.nextInt();
            positions.add(position);

            switch (position) {     //updating driver stats according to position

                case 1:             //case=position
                    driversList.get(i).setFirstPositions(firstPositions + 1);
                    driversList.get(i).setTotalPoints(totalPoints + 25);
                    driversList.get(i).setTotalRaces(totalRaces + 1);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;

                case 2:
                    driversList.get(i).setSecondPositions(secondPositions + 1);
                    driversList.get(i).setTotalPoints(totalPoints + 18);
                    driversList.get(i).setTotalRaces(totalRaces + 1);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;

                case 3:
                    driversList.get(i).setThirdPositions(thirdPositions + 1);
                    driversList.get(i).setTotalRaces(totalRaces + 1);
                    driversList.get(i).setTotalPoints(totalPoints + 15);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;

                case 4:
                    driversList.get(i).setTotalPoints(totalPoints + 12);
                    driversList.get(i).setTotalRaces(totalRaces + 1);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;

                case 5:

                    driversList.get(i).setTotalPoints(totalPoints + 10);
                    driversList.get(i).setTotalRaces(totalRaces + 1);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;
                case 6:

                    driversList.get(i).setTotalPoints(totalPoints + 8);
                    driversList.get(i).setTotalRaces(totalRaces + 1);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;

                case 7:

                    driversList.get(i).setTotalPoints(totalPoints + 6);
                    driversList.get(i).setTotalRaces(totalRaces + 1);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;

                case 8:

                    driversList.get(i).setTotalPoints(totalPoints + 4);
                    driversList.get(i).setTotalRaces(totalRaces + 1);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;
                case 9:

                    driversList.get(i).setTotalPoints(totalPoints + 2);
                    driversList.get(i).setTotalRaces(totalRaces + 1);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;

                case 10:
                    driversList.get(i).setTotalPoints(totalPoints + 1);
                    driversList.get(i).setTotalRaces(totalRaces + 1);

                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;
                default:

                    driversList.get(i).setTotalRaces(totalRaces + 1);
                    driverOrder.get(i).setDriverName(driverName);
                    driverOrder.get(i).setDriverTeam(driverTeam);
                    driverOrder.get(i).setLocation(driverLocation);
                    driverOrder.get(i).setPosition(position);
                    break;
            }
        }
        System.out.println("RACE DETAILS ADDED TO THE SYSTEM SUCCESSFULLY"+"\n");
        LinkedList<Formula1Driver> raceDrivers= new LinkedList<>(driversList);
        Race newRace=new Race(raceDate,raceDrivers,positions);
        raceInfo.add(newRace);
        saveFile();
    }

    public void saveFile(){     //method of saving stats of drivers

        try {
            FileOutputStream fos = new FileOutputStream("Formula1Championship.ser");    //file name
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(driversList);
            oos.writeObject(driverOrder);
            oos.writeObject(raceInfo);
            oos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("UPDATED THE FILE!"+"\n");

    }
    @SuppressWarnings("unchecked")
    public void readFile(){         //deserialization of the file

        try {

            FileInputStream fis = new FileInputStream("Formula1Championship.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object driversArrayObject = ois.readObject();
            Object orderArrayObject = ois.readObject();
            Object racesObject = ois.readObject();
//            Object racesArrayObject = ois.readObject();

//            ObjectOutputStream.writeObject
            driversList = (LinkedList<Formula1Driver>) driversArrayObject;
            driverOrder=(LinkedList<Formula1Driver>) orderArrayObject;
            raceInfo=(LinkedList<Race>) racesObject;

            fis.close();
            ois.close();

        }

        catch (FileNotFoundException | EOFException e){
           new File("Formula1Championship.ser");
       }

        catch (IOException | ClassNotFoundException ioe)

        {
            ioe.printStackTrace();
        }

    }
}
