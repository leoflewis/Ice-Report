import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        List<IceSheet> list = db.fetchAllRecords();
        gui = new IceReportGui(this);
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
     * calls delete from database method and passes appropriate ice sheet through
     */
    String deleteIceFromDb(IceSheet ice){
        return db.deleteFromDB(ice);
    }
}
