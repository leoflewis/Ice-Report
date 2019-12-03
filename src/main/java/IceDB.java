import java.sql.*;

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
    static final int SQLITE_DUPLICATE_PRIMARY_KEY_CODE = 19;

    IceDB(){
        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL); Statement statement = conn.createStatement()){
            String createTableSQL = "CREATE TABLE IF NOT EXISTS ice" + "(name TEXT NOT NULL, house BOOLEAN, net TEXT, water TEXT, " +
                    "quality INTEGER NOT NULL CHECK(quality >= 1 AND quality <=10), address TEXT NOT NULL, hours TEXT, date TEXT, additional TEXT)";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException sqle) {
            throw new  RuntimeException(sqle);
        }
    }

    public String addToIceDb(IceSheet ice){
        String addIceSQL = "INSERT INTO ice VALUES(?,?,?,?,?,?,?,?,?)";
        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL); PreparedStatement addICE = conn.prepareStatement(addIceSQL)){
            addICE.setString(1, ice.getName());
            addICE.setBoolean(2, ice.getWarmingHouse());
            addICE.setString(3, ice.getNetStatus());
            addICE.setString(4, ice.getWaterSource());
            addICE.setDouble(5, ice.getIceQuality());
            addICE.setString(6, ice.getAddress());
            addICE.setString(7, ice.getHours());
            addICE.setDate(8, (Date) ice.getReportDate());
            addICE.setString(9, ice.getAdditionalInfo());
            addICE.execute();
            return OK;
        } catch (SQLException sqle){
            if (sqle.getErrorCode() == SQLITE_DUPLICATE_PRIMARY_KEY_CODE){
                return DUPLICATE;
            } else {
                throw new RuntimeException(sqle);
            }
        }
    }
}
