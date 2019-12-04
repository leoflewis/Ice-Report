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
        gui = new IceReportGui(this);
        List<IceSheet> list = db.fetchAllRecords();
        gui.setListData(list);
    }

    String addICEToDatabase(IceSheet ice){
        return db.addToIceDb(ice);
    }

    List<IceSheet> getAllData(){
        return db.fetchAllRecords();
    }

    String deleteIceFromDb(IceSheet ice){
        return db.deleteFromDB(ice);
    }
}
