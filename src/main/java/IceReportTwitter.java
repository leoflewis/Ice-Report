import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * this class works with the twitter api to create an instance of twitter to send tweets
 * Author Leo Lewis
 */
public class IceReportTwitter {
    /**
     * twitter dev account authentication credentials
     */
    private final static String OAUTH_CONSUMER_KEY = "";
    private final static String OAUTH_CONSUMER_SECRET = "";
    private final static String OAUTH_ACCESS_TOKEN = "";
    private final static String OAUTH_ACCESS_TOKEN_SECRET = "";

    /**
     * method to build an instance of twitter and tweet a given string
     */
     static void tweet(String s) throws TwitterException {
         ConfigurationBuilder cb = new ConfigurationBuilder();
         //configure authentication credentials
         cb.setDebugEnabled(true)
                 .setOAuthConsumerKey(OAUTH_CONSUMER_KEY)
                 .setOAuthConsumerSecret(OAUTH_CONSUMER_SECRET)
                 .setOAuthAccessToken(OAUTH_ACCESS_TOKEN)
                 .setOAuthAccessTokenSecret(OAUTH_ACCESS_TOKEN_SECRET);
         //create twitter instance with set credentials
         TwitterFactory tf = new TwitterFactory(cb.build());
         Twitter twitter = tf.getInstance();
         //pass string through twitter4j's update status method
         twitter.updateStatus(s);
     }

}
