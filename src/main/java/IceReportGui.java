import twitter4j.TwitterException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


public class IceReportGui extends JFrame{
    private JCheckBox warmingHouseCheckBox;
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
    private JComboBox waterSourceBox;
    private JCheckBox netCheckBox;
    private Controller controller;
    private DefaultListModel<IceSheet> iceListModel;

    /**
     * this method constructs the gui form
     */
    IceReportGui(Controller controller){
        //set quality combo box items
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
        //set water combo box
        waterSourceBox.addItem("Select water source (optional)");
        waterSourceBox.addItem("Lake");
        waterSourceBox.addItem("Pond");
        waterSourceBox.addItem("Flooded Park");
        waterSourceBox.addItem("River");
        waterSourceBox.addItem("Other");
        //set list model
        this.controller = controller;
        iceListModel = new DefaultListModel<>();
        iceList.setModel(iceListModel);
        //add, save, right click methods for organisation
        addButtonAction();
        emptyText();
        saveAndQuitButton();
        rightClick();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    /**
     * right click pop delete menu for the ice list
     */
    private void rightClick(){
        //create pop up menu
        JPopupMenu menu = new JPopupMenu();
        JMenuItem delete = new JMenuItem("Delete?");
        menu.add(delete);
        iceList.setComponentPopupMenu(menu);
        iceList.addMouseListener(new MouseListener() {
            @Override
            //when clicked get index unless invalid
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
        //delete menu item
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //calls delete method
                delete();
            }
        });
    }

    /**
     * method to delete an item from list
     */
    private void delete() {
        IceSheet ice = (IceSheet) iceList.getSelectedValue();
        //calls delete from controller
        String s = controller.deleteIceFromDb(ice);
        if(s.equals("NullPointerException")){
            errorMessage("Select an item to delete");
        }
        //re-set list items
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
                //calls dispose method
                dispose();
            }
        });
    }

    /**
     * this method adds an ice sheet to the list
     * this method is pretty long because it checks to make sure the entered input is correct
     */
    private void addButtonAction() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create list of ice sheets to check for duplicates
                List<IceSheet> list = controller.getAllData();
                List<String> names = new ArrayList<>();
                for(IceSheet ice : list){
                    names.add(ice.getName().toLowerCase());
                }
                //get ice sheet values from gui and ensure they are appropriate
                //optional text fields have their own methods to replace empty text with appropriate text
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
                    errorMessage("Select a quality score");
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
                //crate ice sheet with values
                IceSheet iceSheetRecord;
                String water = (String) waterSourceBox.getSelectedItem();
                if(water.equals("Select water source (optional)")){  water = "Water source unknown"; }
                String hours = hoursString(hoursTextField.getText());
                String additionalInfo = addiInfoString(additionalInfoTextField.getText());
                //attempt to create full ice sheet but if that fails create basic ice sheet
                try{
                    iceSheetRecord = new IceSheet(iceName, quality, iceAddress, netCheckBox.isSelected(), water, warmingHouseCheckBox.isSelected(), hours, additionalInfo, dateTextField.getText());
                } catch (Exception ee){
                    iceSheetRecord = new IceSheet(iceName, quality,  iceAddress, date);
                }
                //add ice sheet to database and re-set text in gui form
                String result = controller.addICEToDatabase(iceSheetRecord);
                //attempt to tweet ice sheet
                try {
                    controller.tweetIceSheet(iceSheetRecord);
                } catch (TwitterException ex) {
                    ex.printStackTrace();
                }
                if (result.equals(IceDB.OK)){
                    nameTextField.setText("Name (required)");
                    addyTextField1.setText("Address (required)");
                    waterSourceBox.setSelectedIndex(0);
                    hoursTextField.setText("Hours (optional)");
                    additionalInfoTextField.setText("Additional Info (optional)");
                    dateTextField.setText("Date Skated (required)");
                    qualityComboBox.setSelectedIndex(0);
                    //update list
                    list = controller.getAllData();
                    setListData(list);
                }
            }
        });
    }

    /**
     * method to replace default text from gui form with empty string
     */
    private String addiInfoString(String text) {
        if(text.equals("Additional Info (optional)")){
            return "";
        }else {
            return text + ".";
        }
    }

    /**
     * method to replace default text from gui form with empty string
     */
    private String hoursString(String text) {
        if(text.equals("Hours (optional)")){
            return "Unknown";
        }else {
            return text;
        }
    }

    /**
     * this method fills the list in the gui with existing data
     */
    public void setListData(List<IceSheet> data){
        //updates jlist
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

    /**
     * this method empties text field when they are clicked
     */
    void emptyText(){
        nameTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameTextField.setText("");
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
        hoursTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hoursTextField.setText("");
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
        dateTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dateTextField.setText("");
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
        additionalInfoTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                additionalInfoTextField.setText("");
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
        addyTextField1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addyTextField1.setText("");
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
    }

}
