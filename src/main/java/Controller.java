import java.util.Collections;
import java.util.List;

/**
 * this class operates the whole app
 */
public class Controller {
    private IceReportGui gui;
    private IceDB db;

    public static void main(String[] args){
        new Controller().startApp();
    }

    private void startApp() {
        db = new IceDB();
        List<IceSheet> list = sortIceByScore();
        gui = new IceReportGui(this);
        sortIceByScore();
        gui.setListData(list);
    }

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
     * sorts ice sheets by their quality score
     */
    List<IceSheet> sortIceByScore(){
        List<IceSheet> iceSheets = db.fetchAllRecords();
        Collections.sort(iceSheets);
        return iceSheets;
    }

    /**
     * calls delete from database method and passes appropriate ice sheet through
     */
    String deleteIceFromDb(IceSheet ice){
        return db.deleteFromDB(ice);
    }
}
