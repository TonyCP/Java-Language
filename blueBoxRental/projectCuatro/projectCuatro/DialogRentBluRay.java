package projectCuatro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/********************************************************************************
 * Project 3 - "RedBox-like" program
 * Create a program that simulates a DVD rental store.
 * @author patterto
 *******************************************************************************/
public class DialogRentBluRay extends JDialog implements ActionListener {
    /** Textfield for title **/
    private JTextField titleTxt;
    /** Textfield for renter name **/
    private JTextField renterTxt;
    /** JTextfield for rented date **/
    private JTextField rentedOnTxt;
    /** JTextfield for due date **/
    private JTextField DueBackTxt;
    /** Drop down box for bluRay type **/
    private JComboBox layerType;
    /** JButtons **/
    private JButton okButton;
    private JButton cancelButton;
    private boolean closeStatus;
    /** String of bluRay type **/
    private String layer;
    /** Instance of DVD **/
    private DVD unit;

    /********************************************************************************
     * Instantiate a Custom Dialog as 'modal' and wait for the
     * user to provide data and click on a button.
     * @param parent reference to the JFrame application
     * @param d an instantiated object to be filled with data
     *******************************************************************************/
    public DialogRentBluRay(JFrame parent, DVD d) {
        // call parent and create a 'modal' dialog
        super(parent, true);
        setTitle("Rent a BluRay:");
        closeStatus = false;
        setSize(400,200);

        unit = d;
        // prevent user from closing window
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // instantiate and display text fields
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(6,2));

        textPanel.add(new JLabel("Your Name:"));
        renterTxt = new JTextField("John Doe",30);
        textPanel.add(renterTxt);

        textPanel.add(new JLabel("Title of BluRay:"));
        titleTxt = new JTextField("Avengers",30);
        textPanel.add(titleTxt);

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");

        textPanel.add(new JLabel("Rented on Date: "));
        rentedOnTxt = new JTextField(df.format(date),30);
        textPanel.add(rentedOnTxt);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);  // number of days to add
        date = c.getTime();

        textPanel.add(new JLabel("Due Back: "));
        DueBackTxt = new JTextField(df.format(date),15);
        textPanel.add(DueBackTxt);

        textPanel.add(new JLabel("Double layered: "));
        String[] layered = {"Yes", "No"};
        layerType = new JComboBox(layered);
        layerType.setFocusable(true);
        textPanel.add(layerType);

        getContentPane().add(textPanel, BorderLayout.CENTER);

        // Instantiate and display two buttons
        layer = "No";
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setSize(300,300);
        setVisible (true);
    }


    /********************************************************************************
     * Action performed of the GUI fires actions.
     * @param e - action event.
     *******************************************************************************/
    public void actionPerformed(ActionEvent e) {
//        JButton button = (JButton) e.getSource();

        // if OK clicked the fill the object
        if (e.getSource() == okButton) {
            // save the information in the object
//            closeStatus = true;
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
            Date daterentedOn, dateDue;
            df.setLenient(false);
            try {
                daterentedOn = df.parse(rentedOnTxt.getText());
                dateDue = df.parse(DueBackTxt.getText());
                // Throw error message for invalid date entry
                if(daterentedOn.after(dateDue) || dateDue.before(daterentedOn)
                        || rentedOnTxt.getText().equals("") || DueBackTxt.getText().equals("")){
                    closeStatus = false;
                    JOptionPane.showMessageDialog(null,
                            "Rental date must be before due date" + "\n" +
                                    "& due date must be after rental date, try again.");
                }
                // Throw error message for invalid renter entry
                if(renterTxt.getText().equals("")){
                    closeStatus = false;
                    JOptionPane.showMessageDialog(null,
                            "Rental name invalid or, try again.");
                }
                // Throw error message for invalid title entry
                if(titleTxt.getText().equals("")){
                    closeStatus = false;
                    JOptionPane.showMessageDialog(null,
                            "Title name invalid or, try again.");
                }

                // Input valid data
                GregorianCalendar rentedOn = new GregorianCalendar();
                GregorianCalendar dueBackOn = new GregorianCalendar();
                if(daterentedOn.before(dateDue) && dateDue.after(daterentedOn)
                        && !renterTxt.getText().equals("") && !titleTxt.getText().equals("")) {
                    rentedOn.setTime(daterentedOn);
                    dueBackOn.setTime(dateDue);
                    unit.setRentedOn(rentedOn);
                    unit.setDueBack(dueBackOn);
                    unit.setNameOfRenter(renterTxt.getText());
                    unit.setTitle(titleTxt.getText());
                    closeStatus = true;
                }
                layer = (String)layerType.getSelectedItem();
                if (layer.equals("Yes"))
                    ((BluRay) unit).setDoubleLayer(true);
                else
                    ((BluRay) unit).setDoubleLayer(false);

            } catch (Exception e1) {
                closeStatus = false;
                JOptionPane.showMessageDialog(null,"Invalid input, Try again");
            }
        }
        if(e.getSource() == cancelButton){
            dispose();
        }
        // make the dialog disappear
        if(closeStatus){
            dispose();
        }
    }

    /********************************************************************************
     * Method returns boolean if OK to close dialog.
     * @return - true/false.
     *******************************************************************************/
    public boolean closeOK(){
        return closeStatus;
    }

}
