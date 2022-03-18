import java.io.Serializable;

abstract public class Driver implements  Serializable {
    private String driverName;
    private String location;
    private String driverTeam;
    public abstract void driverInfo();
    public abstract void statistics();

    public Driver(String driverName, String location, String driverTeam)  {
        this.driverName = driverName;
        this.location = location;
        this.driverTeam = driverTeam;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDriverTeam() {
        return driverTeam;
    }

    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }
}
