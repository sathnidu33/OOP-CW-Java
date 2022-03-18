import java.io.Serializable;

public class Formula1Driver extends Driver implements Comparable<Formula1Driver>, Serializable {

    public int secondPositions;
    public int thirdPositions;
    public int firstPositions;
    public int totalPoints;
    public int totalRaces;
    public int position;

//constructors
    public Formula1Driver(String driverName, String location, String driverTeam, int secondPositions, int thirdPositions, int firstPositions, int totalPoints, int totalRaces) {
        super(driverName, location, driverTeam);
        this.secondPositions = secondPositions;
        this.thirdPositions = thirdPositions;
        this.firstPositions = firstPositions;
        this.totalPoints = totalPoints;
        this.totalRaces = totalRaces;
    }

    public Formula1Driver(String driverName, String location, String driverTeam, int position) { //for driversOrders in championsShip manager
        super(driverName, location, driverTeam);
        this.position = position;
    }



    public int getSecondPositions() {           //getters and setter for the second position
        return secondPositions;
    }

    public void setSecondPositions(int secondPositions) {
        this.secondPositions = secondPositions;
    }

    public int getThirdPositions() {            //getters and setter for the third position
        return thirdPositions;
    }

    public void setThirdPositions(int thirdPositions) {
        this.thirdPositions = thirdPositions;
    }

    public int getFirstPositions() {            //getters and setter for the first position
        return firstPositions;
    }

    public void setFirstPositions(int firstPositions) {
        this.firstPositions = firstPositions;
    }

    public int getTotalPoints() {               //getters and setter for the first position
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalRaces() {                //getters and setter for the first position
        return totalRaces;
    }

    public void setTotalRaces(int totalRaces) {
        this.totalRaces = totalRaces;
    }

    @Override
    public void driverInfo() {              //method not in use
        Formula1Driver f1=new Formula1Driver("Lewis","london","Merc",2,1,3,2,2);
        System.out.println(f1);

    }

    @Override
    public void statistics() {

    }

    @Override
    public int compareTo(Formula1Driver o) {  //method use to sorting
        int result= o.getTotalPoints()-this.getTotalPoints();
        if (result==0){
            return o.getFirstPositions()-this.getFirstPositions();
        }
        return result;
    }
    


    public int getPosition() {      //getters and setters of position array
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
