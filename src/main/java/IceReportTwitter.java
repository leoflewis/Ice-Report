import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


public class IceReportTwitter {
    Twitter twit = TwitterFactory.getSingleton();
    private String latestStatus = "";
    Status status = twit.updateStatus(latestStatus);
    public IceReportTwitter() throws TwitterException {
        System.out.println("Successfully updated the status to [" + status.getText() + "].");
    }
}
