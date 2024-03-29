import twitter4j.TwitterException;
import java.util.List;

/**
 * this class operates the whole app
 * Author Leo Lewis
 */
public class Controller {
    private IceDB db;

    public static void main(String[] args){
        new Controller().startApp();
    }

    /**
     * method begins application operation
     */
    private void startApp() {
        db = new IceDB();
        List<IceSheet> list = getAllData();
        IceReportGui gui = new IceReportGui(this);
        gui.setListData(list);
    }

    /**
     * method to control additions to database
     */
    String addICEToDatabase(IceSheet ice){
        return db.addToIceDb(ice);
    }

    /**
     * calls get data method from database class, produces list of ice sheets
     */
    List<IceSheet> getAllData(){
        return db.fetchAllRecords();
    }


    /**
     * calls delete from database method and passes appropriate ice sheet through
     */
    String deleteIceFromDb(IceSheet ice){
        return db.deleteFromDB(ice);
    }

    /**
     * method to send tweet from controller
     */
    void tweetIceSheet(IceSheet ice) throws TwitterException {
        IceReportTwitter.tweet(ice.toTwitter());
    }
}
