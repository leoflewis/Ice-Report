import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private Controller controller;
    private DefaultListModel<IceSheet> iceListModel;

    /**
     * this method contructs the gui form
     */
    IceReportGui(Controller controller){
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

    private void rightClick(){
        JPopupMenu menu = new JPopupMenu();
        JMenuItem delete = new JMenuItem("Delete?");
        menu.add(delete);
        iceList.setComponentPopupMenu(menu);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        iceList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selection = iceList.locationToIndex(e.getPoint());
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
    }

    /**
     * method to delete an item from list
     */
    private void delete() {
        IceDB.deleteFromDB();
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
                String iceName = nameTextField.getText();
                if(iceName.isEmpty() || iceName.equals("Name (required)")){
                    errorMessage("Enter a name for ice sheet");
                    return;
                }
                int quality;
                try {
                    quality = Integer.parseInt(String.valueOf(qualityComboBox.getSelectedItem()));
                } catch (NumberFormatException nfe){
                    errorMessage("number format exception");
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
                try{
                    iceSheetRecord = new IceSheet(iceName, quality, iceAddress, netTextArea.getText(), waterTextField.getText(), warmingHouseCheckBox.isSelected(), hoursTextField.getText(), additionalInfoTextField.getText(), dateTextField.getText());
                } catch (Exception ee){
                    iceSheetRecord = new IceSheet(iceName, quality,  iceAddress);
                }
                String result = controller.addICEToDatabase(iceSheetRecord);
                if (result.equals(IceDB.OK)){
                    nameTextField.setText("");
                    addyTextField1.setText("");
                    netTextArea.setText("");
                    waterTextField.setText("");
                    hoursTextField.setText("");
                    additionalInfoTextField.setText("");
                    dateTextField.setText("");
                }
            }
        });
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
