import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class Race implements Serializable {     //to hold details about all the races

    Date date;
    LinkedList<Formula1Driver> raceInfo =new LinkedList<>();
    LinkedList<Integer> position =new LinkedList<>();              //list to hold positions of drivers

    public Race(Date date, LinkedList<Formula1Driver> driversOrder) {          //constructor to takes only date and driver list
        this.date = date;
        this.raceInfo = driversOrder;
    }

    public Race(Date date, LinkedList<Formula1Driver> raceInfo, LinkedList<Integer> position) {
        this.date = date;
        this.raceInfo = raceInfo;
        this.position = position;
    }

    //getters and setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LinkedList<Formula1Driver> getRaceInfo() {
        return raceInfo;
    }

    public void setRaceInfo(LinkedList<Formula1Driver> raceInfo) {
        this.raceInfo = raceInfo;
    }

    public LinkedList<Integer> getPosition() {
        return position;
    }

    public void setPosition(LinkedList<Integer> position) {
        this.position = position;
    }
}
