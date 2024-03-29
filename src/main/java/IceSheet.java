
/**
 * class modeling and outdoor ice sheet
 * Author Leo Lewis
 */
public class IceSheet{
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
    String getName() {
        return name;
    }
    
    boolean getWarmingHouse() {
        return warmingHouse;
    }
    
    boolean getNetStatus() {
        return netStatus;
    }
    
    String getWaterSource() {
        return waterSource;
    }
    
    int getIceQuality() {
        return iceQuality;
    }
    
    String getAddress() {
        return address;
    }
    
    String getHours() {
        return hours;
    }
    
    String getReportDate() {
        return reportDate;
    }
    
    String getAdditionalInfo() {
        return additionalInfo;
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
    
}
