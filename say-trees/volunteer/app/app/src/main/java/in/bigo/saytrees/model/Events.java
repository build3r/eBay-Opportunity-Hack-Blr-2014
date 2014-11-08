package in.bigo.saytrees.model;

/**
 * Created by SPARK on 08/11/14.
 */
public class Events {

    /**
     * {
     545e381ae4b0da992da912b2: {
     _id: {
     _time: 1415460890,
     _machine: -458171751,
     _inc: 766055090,
     _new: false
     },
     saplingCount: "100",
     organizer: "EBay",
     eventTime: "2014-11-08 21:02:44",
     location: "Madurai",
     timestamp: "1415460890925",
     volunteersCount: 0
     }
     }
     */


    private String saplingCount;
    private String organizer;
    private String eventTime;
    private String location;
    private String timestamp;

    public int getVolunteersCount() {
        return volunteersCount;
    }

    public void setVolunteersCount(int volunteersCount) {
        this.volunteersCount = volunteersCount;
    }

    private int volunteersCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getSaplingCount() {
        return saplingCount;
    }

    public void setSaplingCount(String saplingCount) {
        this.saplingCount = saplingCount;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
