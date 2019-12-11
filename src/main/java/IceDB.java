import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
    static final int SQLITE_DUPLICATE_PRIMARY_KEY_CODE = 1;

    /**
     * creates the database
     */
    IceDB(){
        //attempt connection
        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL); Statement statement = conn.createStatement()){
            //create table in database, ensure required aspects contain valid input
            String createTableSQL = "CREATE TABLE IF NOT EXISTS ice" + "(name TEXT NOT NULL, house BOOLEAN, net BOOLEAN, water TEXT, " +
                    "quality INTEGER NOT NULL CHECK(quality >= 1 AND quality <=10), address TEXT NOT NULL, hours TEXT, date TEXT NOT NULL, additional TEXT)";
            statement.executeUpdate(createTableSQL);
            //throw exception if connection failed
        } catch (SQLException sqle) {
            throw new  RuntimeException(sqle);
        }
    }

    /**
     * adds an ice sheet to the data
     */
    public String addToIceDb(IceSheet ice){
        //parametrized statement to prevent injection
        String addIceSQL = "INSERT INTO ice VALUES(?,?,?,?,?,?,?,?,?)";
        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL); PreparedStatement addICE = conn.prepareStatement(addIceSQL)){
            addICE.setString(1, ice.getName());
            addICE.setBoolean(2, ice.getWarmingHouse());
            addICE.setBoolean(3, ice.getNetStatus());
            addICE.setString(4, ice.getWaterSource());
            addICE.setDouble(5, ice.getIceQuality());
            addICE.setString(6, ice.getAddress());
            addICE.setString(7, ice.getHours());
            addICE.setString(8,  ice.getReportDate());
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

    /**
     * method to delete item from db
     */
    public String deleteFromDB(IceSheet ice){
        //parameterized statement to prevent injection
        String deleteIceSQL = "DELETE FROM ice WHERE name LIKE (?)";
        //attempt connection
        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL); PreparedStatement deleteICE = conn.prepareStatement(deleteIceSQL)){
            try {
                deleteICE.setString(1, ice.getName());
                deleteICE.execute();
                return OK;
            } catch (NullPointerException p){
                return "NullPointerException";
            }
            //throw exception if connection to database unsuccessful
        } catch (SQLException e) {
            if (e.getErrorCode() == SQLITE_DUPLICATE_PRIMARY_KEY_CODE){
                return DUPLICATE;
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * this method retrieves ice sheets from the database
     */
    public List<IceSheet> fetchAllRecords() {
        //empty list
        List<IceSheet> allRecords = new ArrayList<>();
        //attempt connection
        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL); Statement statement = conn.createStatement()){
            //parameterized statement
            String selectAllSQL = "SELECT * FROM ice ORDER BY quality DESC ";
            ResultSet rsALL = statement.executeQuery(selectAllSQL);
            //get ice sheets from database
            while(rsALL.next()){
                String name = rsALL.getString(NAME_COL);
                int quality = (int) rsALL.getDouble(QUALITY_COL);
                String address = rsALL.getString(ADDY_COL);
                boolean nets = rsALL.getBoolean(NET_COL);
                String water = rsALL.getString(WATER_COL);
                boolean house = rsALL.getBoolean(HOUSE_COL);
                String hours = rsALL.getString(HOURS_COL);
                String additionalInfo = rsALL.getString(ADDI_COL);
                String date = rsALL.getString(DATE_COL);
                IceSheet iceSheetRecord = new IceSheet(name, quality, address, nets, water, house, hours, additionalInfo, date);
                allRecords.add(iceSheetRecord);
            }
            return allRecords;
        } catch (SQLException sqle){
            throw new RuntimeException(sqle);
        }
    }
}
