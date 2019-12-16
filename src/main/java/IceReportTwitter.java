import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class IceReportTwitter {
    /**
     * twitter dev account authentication credentials
     */
    public final static String OAUTH_CONSUMER_KEY = "9LWxvJr4mQCr9eX8WKUVdE3AF";
    public final static String OAUTH_CONSUMER_SECRET = "HDcFSTxLMjaxswjyUmtLI75fVDUGjltS45ssxcNEEBRVrFa3Eb";
    public final static String OAUTH_ACCESS_TOKEN = "1204591886750629889-1J34DkFtw3NrNQpchaKV0CyevCO3LD";
    public final static String OAUTH_ACCESS_TOKEN_SECRET = "CHvpI8d10E9W9j8snl8Kco9PlWXCme48Sq8HHEOW7MF9J";

    /**
     * method to build an instance of twitter and tweet a given string
     */
     public static void tweet(String s) throws TwitterException {
         ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
                 .setOAuthConsumerKey(OAUTH_CONSUMER_KEY)
                 .setOAuthConsumerSecret(OAUTH_CONSUMER_SECRET)
                 .setOAuthAccessToken(OAUTH_ACCESS_TOKEN)
                 .setOAuthAccessTokenSecret(OAUTH_ACCESS_TOKEN_SECRET);
         TwitterFactory tf = new TwitterFactory(cb.build());
         Twitter twitter = tf.getInstance();
         twitter.updateStatus(s);
     }

}
