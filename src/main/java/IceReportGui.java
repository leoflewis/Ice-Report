import javax.swing.*;

public class IceReportGui extends JFrame{
    private JCheckBox warmingHouseCheckBox;
    private JTextArea netTextArea;
    private JTextField waterTextField;
    private JTextField nameTextField;
    private JComboBox qualityComboBox;
    private JTextField addyTextField1;
    private JTextField hoursTextField;
    private JTextField dateTextField;
    private JPanel mainPanel;
    private Controller controller;
    private DefaultListModel<IceSheet> iceListModel;

    IceReportGui(Controller controller){
        this.controller = controller;
        iceListModel = new DefaultListModel<>();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

}
