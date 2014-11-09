package in.bigo.saytrees.model;

/**
 * Created by SPARK on 08/11/14.
 */
public class Events {

    /**
     * {
     saplingCount: "15000",
     month: "Jan",
     organizer: "Sachin",
     eventTime: "2014-09-28 09:25:07",
     eventName: "Mumbai Sapling drive",
     location: "19.07509724212452,72.83729553222656",
     landmark: "Bandra",
     day: 28,
     timestamp: "1415515664423",
     volunteersCount: 0,
     completed: false,
     address: "South Avenue, Santacruz West, Mumbai, Maharashtra 400052, India, ",
     volunteeredCount: 0
     }
     }
     */


    private String saplingCount;
    private String organizer;
    private String eventTime;
    private String location;
    private String timestamp;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    private String eventName;

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
