import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Controller {
    private IceReportGui gui;
    private IceDB db;

    public static void main(String[] args){
        new Controller().startApp();
    }

    private void startApp() {
        db = new IceDB();
        gui = new IceReportGui(this);

    }

    String addICEToDatabase(IceSheet ice){
        return db.addToIceDb(ice);
    }

}
