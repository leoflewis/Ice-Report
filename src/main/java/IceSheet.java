import java.util.Date;

/**
 * class modeling and outdoor ice sheet
 */
public class IceSheet implements Comparable<IceSheet>{
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
    private boolean netStatus;
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
    private String reportDate;
    /**
     * space for additional info
     */
    private String additionalInfo;

    /**
     * constructor for minimum required aspects of IceSheet, only used if full IceSheet constructor is not able to be created
     */
    public IceSheet(String name, int iceQuality, String address, String reportDate){
        this.name = name;
        this.iceQuality = iceQuality;
        this.address = address;
        this.reportDate = reportDate;
    }

    /**
     * IceSheet constructor
     */
    public IceSheet(String name, int iceQuality, String address, boolean netStatus, String waterSource, boolean warmingHouse, String hours, String additionalInfo, String date){
        this.name = name;
        this.iceQuality = iceQuality;
        this.netStatus = netStatus;
        this.waterSource = waterSource;
        this.warmingHouse = warmingHouse;
        this.hours = hours;
        this.additionalInfo = additionalInfo;
        this.reportDate = date;
        this.address = address;
    }

    /**
     * methods to get and set al instance variables of an ice sheet
     */
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

    public boolean getNetStatus() {
        return netStatus;
    }

    public void setNetStatus(boolean netStatus) {
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

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    /**
     * toString method for IceSheet class, only displays aspects required in gui form
     */
    public String toString(){
        return this.name + ", " + this.address + ", " + this.iceQuality + ", " + this.reportDate;
    }

    /**
     * format for twitter
     */
    public String toTwitter(){
        return "On " + this.reportDate + " " + this.name +  " was given a score of " + this.iceQuality + ". " + this.name + " is at " + this.address + ". Hours are " + this.getHours() + ". " + this.name+ " is a "  + this.getWaterSource() + ". " + this.getAdditionalInfo() + " " + netString(this.netStatus) + " " + warminghouseString(this.warmingHouse);
    }

    /**
     * returns a string about the warming house for the twitter method
     */
    public String warminghouseString(boolean house){
        if(house){
            return "There is a warming house.";
        }
        return "There is not a warming house.";
    }

    /**
     *  returns a string about the hockey nets for the twitter method
     */
    public String netString(boolean net){
        if(net){
            return "There are hockey nets.";
        }
        return  "There are not hockey nets.";
    }

    /**
     * compares quality score of one ice sheet to another
     */
    @Override
    public int compareTo(IceSheet o) {
        if(o instanceof IceSheet){
            IceSheet I = (IceSheet) o;
            return o.getIceQuality() - this.iceQuality;
        }
        return -1;
    }
}
