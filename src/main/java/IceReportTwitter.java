import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.io.PrintStream;


public class IceReportTwitter {


     public static void main(String[] args) {
         Twitter twitter;
         try {
             twitter = getTwitterinstance();

             Status status = twitter.updateStatus("tweets");
             twitter.tweets().updateStatus("tweet");
         } catch (IllegalStateException | TwitterException e) {
             e.printStackTrace();
         }

     }
    public static Twitter getTwitterinstance() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("9LWxvJr4mQCr9eX8WKUVdE3AF")
                .setOAuthConsumerSecret("HDcFSTxLMjaxswjyUmtLI75fVDUGjltS45ssxcNEEBRVrFa3Eb ")
                .setOAuthAccessToken("1204591886750629889-1J34DkFtw3NrNQpchaKV0CyevCO3LD")
                .setOAuthAccessTokenSecret("CHvpI8d10E9W9j8snl8Kco9PlWXCme48Sq8HHEOW7MF9J");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getSingleton();
        return twitter;
    }
}
