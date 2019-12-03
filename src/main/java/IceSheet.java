import java.util.Date;

/**
 * class modeling and outdoor ice sheet
 */
public class IceSheet {
    /**
     * name of ice sheet
     */
    private String name;
    /**
     * an outdoor ice sheet either has a warming house or it doesnt
     */
    private boolean warmingHouse;
    /**
     * status of nets
     */
    private String netStatus;
    /**
     * where the ice comes from
     */
    private String waterSource;

    /**
     * number 1-10 representing quality of the ice
     */
    private int iceQuality;
    /**
     * address
     */
    private String address;
    /**
     * hours lights are on and warming house
     */
    private String hours;
    /**
     * date user writes report
     */
    private Date reportDate;
    /**
     * space for additional info
     */
    private String additionalInfo;

    /**
     * The minimum needed for an Ice Sheet
     * @param name
     * @param iceQuality
     * @param address
     */
    public IceSheet(String name, int iceQuality, String address){
        this.name = name;
        this.iceQuality = iceQuality;
        this.address = address;
    }

    /**
     * Overloaded constructor with all option to add all other fields
     * @param name
     * @param iceQuality
     * @param address
     * @param netStatus
     * @param waterSource
     * @param warmingHouse
     * @param hours
     * @param additionalInfo
     * @param date
     */
    public IceSheet(String name, int iceQuality, String address, String netStatus, String waterSource, boolean warmingHouse, String hours, String additionalInfo, Date date){
        this.name = name;
        this.iceQuality = iceQuality;
        this.netStatus = netStatus;
        this.waterSource = waterSource;
        this.warmingHouse = warmingHouse;
        this.hours = hours;
        this.additionalInfo = additionalInfo;
        this.reportDate = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getWarmingHouse() {
        return warmingHouse;
    }

    public void setWarmingHouse(boolean warmingHouse) {
        this.warmingHouse = warmingHouse;
    }

    public String getNetStatus() {
        return netStatus;
    }

    public void setNetStatus(String netStatus) {
        this.netStatus = netStatus;
    }

    public String getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }

    public int getIceQuality() {
        return iceQuality;
    }

    public void setIceQuality(int iceQuality) {
        this.iceQuality = iceQuality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String toString(){
        return this.name + " " + this.address + " " + this.iceQuality + " " + this.reportDate;
    }
}
