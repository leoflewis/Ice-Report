import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    private JList iceList;
    private JButton addButton;
    private JButton saveAndQuitButton;
    private JTextField additionalInfoTextField;
    private JTextField netTextField;
    private Controller controller;
    private DefaultListModel<IceSheet> iceListModel;

    /**
     * this method constructs the gui form
     */
    IceReportGui(Controller controller){
        qualityComboBox.addItem("Choose Ice Quality Score");
        qualityComboBox.addItem(1);
        qualityComboBox.addItem(2);
        qualityComboBox.addItem(3);
        qualityComboBox.addItem(4);
        qualityComboBox.addItem(5);
        qualityComboBox.addItem(6);
        qualityComboBox.addItem(7);
        qualityComboBox.addItem(8);
        qualityComboBox.addItem(9);
        qualityComboBox.addItem(10);
        this.controller = controller;
        iceListModel = new DefaultListModel<>();
        iceList.setModel(iceListModel);
        addButtonAction();
        saveAndQuitButton();
        rightClick();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    /**
     * right lick pop delete menu for the ice list
     */
    private void rightClick(){
        JPopupMenu menu = new JPopupMenu();
        JMenuItem delete = new JMenuItem("Delete?");
        menu.add(delete);
        iceList.setComponentPopupMenu(menu);
        iceList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selection = iceList.locationToIndex(e.getPoint());
                if(selection < 0){
                    errorMessage("Select an Ice Sheet");
                }
                iceList.setSelectedIndex(selection);
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
    }

    /**
     * method to delete an item from list
     */
    private void delete() {
        IceSheet ice = (IceSheet) iceList.getSelectedValue();
        String s = controller.deleteIceFromDb(ice);
        if(s.equals("NullPointerException")){
            errorMessage("Select an item to delete");
        }
        List<IceSheet> list = controller.getAllData();
        setListData(list);
    }

    /**
     * save and quit button action listener
     */
    private void saveAndQuitButton(){
        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * this method adds an icesheet to the list
     */
    private void addButtonAction() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<IceSheet> list = controller.getAllData();
                List<String> names = new ArrayList<>();
                for(IceSheet ice : list){
                    names.add(ice.getName().toLowerCase());
                }
                String iceName = nameTextField.getText();
                if(names.contains(iceName.toLowerCase())){
                    errorMessage("Name already exists in Ice Report, delete and try again");
                    return;
                }
                if(iceName.isEmpty() || iceName.equals("Name (required)")){
                    errorMessage("Enter a name for ice sheet");
                    return;
                }
                int quality;
                try {
                    quality = Integer.parseInt(String.valueOf(qualityComboBox.getSelectedItem()));
                } catch (NumberFormatException nfe){
                    errorMessage("Select a number");
                    return;
                }
                String iceAddress = addyTextField1.getText();
                if(iceAddress.isEmpty() || iceAddress.equals("Address (required)")){
                    errorMessage("Enter an address for ice sheet");
                    return;
                }
                String date = dateTextField.getText();
                if(date.isEmpty() || date.equals("Date Skated (required)")){
                    errorMessage("Enter date ice was skated");
                    return;
                }
                IceSheet iceSheetRecord;
                String nets = netString(netTextField.getText());
                String water = waterString(waterTextField.getText());
                String hours = hoursString(hoursTextField.getText());
                String additionalInfo = addiInfoString(additionalInfoTextField.getText());
                try{
                    iceSheetRecord = new IceSheet(iceName, quality, iceAddress, nets, water, warmingHouseCheckBox.isSelected(), hours, additionalInfo, dateTextField.getText());
                } catch (Exception ee){
                    iceSheetRecord = new IceSheet(iceName, quality,  iceAddress);
                }
                String result = controller.addICEToDatabase(iceSheetRecord);
                if (result.equals(IceDB.OK)){
                    nameTextField.setText("Name (required)");
                    addyTextField1.setText("Address (required)");
                    netTextField.setText("What kind of nets and how many are available? (optional)");
                    waterTextField.setText("Water Source (optional)");
                    hoursTextField.setText("Hours lights are on and warming house is open (optional)");
                    additionalInfoTextField.setText("Additional Info (optional)");
                    dateTextField.setText("Date Skated (required)");
                    qualityComboBox.setSelectedIndex(0);
                    list = controller.getAllData();
                    setListData(list);
                }
            }
        });
    }

    /**
     * method to replace default text from gui form with empty string
     * @param text
     * @return
     */
    private String addiInfoString(String text) {
        if(text.equals("Additional Info (optional)")){
            return "";
        }else {
            return text;
        }
    }

    /**
     * method to replace default text from gui form with empty string
     * @param text
     * @return
     */
    private String hoursString(String text) {
        if(text.equals("Hours (optional)")){
            return "";
        }else {
            return text;
        }
    }

    /**
     * method to replace default text from gui form with empty string
     * @param text
     * @return
     */
    private String waterString(String text) {
        if(text.equals("Water Source (optional)")){
            return "";
        }else {
            return text;
        }
    }

    /**
     * method to replace default text from gui form with empty string
     * @param text
     * @return
     */
    private String netString(String text) {
        if(text.equals("Net info (optional)")){
            return "";
        }else {
            return text;
        }
    }

    /**
     * this method fills the list in the gui with existing data
     */
    public void setListData(List<IceSheet> data){
        iceListModel.clear();
        if(data != null){
            for(IceSheet ice : data){
                iceListModel.addElement(ice);
            }
        }
    }

    /**
     * this method produces an error dialog
     */
    private void errorMessage(String s) {
        JOptionPane.showMessageDialog(IceReportGui.this, s, "error", JOptionPane.ERROR_MESSAGE);
    }

}
