import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class IceDB {
    private static final String DB_CONNECTION_URL =  "jdbc:sqlite:ice.sqlite";

    private static final String NAME_COL = "name";
    private static final String HOUSE_COL = "house";
    private static final String NET_COL = "net";
    private static final String WATER_COL = "water";
    private static final String QUALITY_COL = "quality";
    private static final String ADDY_COL = "address";
    private static final String HOURS_COL = "hours";
    private static final String DATE_COL = "date";
    private static final String ADDI_COL = "additional";
    static final String OK = "ok";
    static final String DUPLICATE = "Duplicate ice name";

    IceDB(){
        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL); Statement statement = conn.createStatement()){
            String createTableSQL = "CREATE TABLE IF NOT EXISTS ice" + "(name TEXT NOT NULL, house BOOLEAN, net TEXT, water TEXT, " +
                    "quality INTEGER NOT NULL CHECK(quality >= 1 AND quality <=10), address TEXT NOT NULL, hours TEXT, date TEXT, additional TEXT)";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException sqle) {
            throw new  RuntimeException(sqle);
        }
    }
}
