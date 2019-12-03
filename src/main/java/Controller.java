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
}
