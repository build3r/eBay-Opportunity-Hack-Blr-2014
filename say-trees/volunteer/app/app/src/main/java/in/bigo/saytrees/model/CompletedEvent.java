package in.bigo.saytrees.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by prasanna on 5/10/14.
 */
public class CompletedEvent implements Serializable{


    /**
     * sample json
     * landmark: "nearby ipad",
     severity: "HIGH",
     location: [
     12.9433481,
     77.6295012
     ],
     imageUrl: "http://res.cloudinary.com/bigo/image/upload/v1413570835/jfrfh2rgmt6zwdf0rymy.png",
     description: "dirty mac",
     timestamp: "1413570874988",
     userId: "10204049299689489",
     status: "OPEN",
     firstname: "Karthikeyan",
     gender: "male",
     link: "https://www.facebook.com/app_scoped_user_id/10204049299689489/",
     name: "Karthikeyan Ng",
     email: "intrepidkarthi@gmail.com",
     lastname: "Ng",
     userHandle: "Karthikeyan Ng",
     comments: [ ],
     commentsCount: 0,
     isLiked: false,
     likesCount: 0
     */

    private String id;
    private String eventId;
    private String varieties;
    private String plantedSaplings;
    private String volunteers;

    public ArrayList<String> getUrl() {
        return url;
    }

    public void setUrl(ArrayList<String> url) {
        for(int i=0;i<url.size();i++)
            this.url.add(url.get(i));
    }

    private ArrayList<String> url = new ArrayList<String>();

    private String timeStamp;
    private String samplingCount;
    private String month;
    private String organizer;
    private String eventName;
    private String landmark;
    private String day;
    private String image;
    private String eventTime;
    private String description;
    private String location;
    private String address;

    private int commentsCount;
    private int likesCount;
    private boolean isLiked;

    private ArrayList<CommentItem> commentItems;
    private ArrayList<LikeItem> likeItems;
    private ArrayList<String> comments;
    private ArrayList<String> likes;


    /*public CompletedEvent(String id, String eventId, String varieties,
                          String plantedSaplings, String volunteers, String timeStamp, String url,
                          String samplingCount, String month, String organizer,
                          String eventName, String landmark, String day, String eventTime
    ) {
        this.id = id;
        this.eventId = eventId;
        this.varieties = varieties;
        this.plantedSaplings = plantedSaplings;
        this.volunteers = volunteers;
        this.timeStamp = timeStamp;
        this.url = url;
        this.samplingCount=samplingCount;
        this.month=month;
        this.organizer=organizer;
        this.eventName=eventName;
        this.landmark=landmark;
        this.day=day;
        this.eventTime=eventTime;
    }
*/
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPlantedSaplings() {
        return plantedSaplings;
    }

    public void setPlantedSaplings(String plantedSaplings) {
        this.plantedSaplings = plantedSaplings;
    }

    public String getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(String volunteers) {
        this.volunteers = volunteers;
    }

    public String getSamplingCount() {
        return samplingCount;
    }

    public void setSamplingCount(String samplingCount) {
        this.samplingCount = samplingCount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public ArrayList<CommentItem> getCommentItems() {
        return commentItems;
    }

    public void setCommentItems(ArrayList<CommentItem> commentItems) {
        this.commentItems = commentItems;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public ArrayList<LikeItem> getLikeItems() {
        return likeItems;
    }

    public void setLikeItems(ArrayList<LikeItem> likeItems) {
        this.likeItems = likeItems;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }


}
