package projectCuatro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class returnDialog extends JDialog implements ActionListener {
    /** Textfield for date entry **/
    private JTextField returnDate;
    /** Jbuttons **/
    private JButton okButton;
    private JButton cancelButton;
    /** The output area */
    private JTextArea output;
    /** Linked list of DVDs **/
    private MyDoubleWithTailLinkedList<DVD> list;
    /** index of arraylist **/
    private int index;
    /** Gregorian Calendar **/
    private GregorianCalendar dateOf;
    /** Boolean for close window status **/
    private boolean closeStatus;
    /** Boolean for opening window **/
    private boolean openStatus;

    public returnDialog(JFrame parent, int index, MyDoubleWithTailLinkedList<DVD> list){
        // call parent and create a 'modal' dialog
        super(parent, true);
        this.list = list;
        this.index = index;

        setTitle("Days Late Estimate: ");
        closeStatus = false;
        openStatus = true;
        setSize(400,200);

        dateOf = new GregorianCalendar();
        // prevent user from closing window
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //Configure the output area
        output = new JTextArea();
        output.setEditable(false);
        output.setPreferredSize(new Dimension(500, 300));

        // instantiate and display text fields
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(6,2));

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);  // number of days to add
        date = c.getTime();

        // JTextField for date entry
        textPanel.add(new JLabel("Date: "));
        returnDate = new JTextField(df.format(date),15);
        textPanel.add(returnDate);
        getContentPane().add(textPanel, BorderLayout.CENTER);

        // Instantiate and display two buttons
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

    public void returnDVD(int index){
        if(returnDate.getText().equals("")){
            closeStatus = false;
            JOptionPane.showMessageDialog(null,
                    "Return date invalid, try again.");
        }
        // Create calendars for date comparison
        Calendar currDate = new GregorianCalendar();
        Calendar dueDate = new GregorianCalendar();
        dueDate.setTime(list.get(index).getDueBack().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        try {
            currDate.setTime(dateFormat.parse(returnDate.getText()));
        } catch (ParseException e) {
            closeStatus = false;
            JOptionPane.showMessageDialog(null,
                    "Return date invalid, try again.");
        }

        // Handles DVD rental charges
        double cost = 0.0;
        double lateFee = 0.0;
        if (list.get(index) instanceof BluRay) {
            cost = ((BluRay) list.get(index)).getCost(list.
                    get(index).rentedOn);
            lateFee = 0.0;

            // If the title is late
            if (currDate.after(dueDate))
                lateFee = 1.0 * Math.abs(daysBetween(currDate, dueDate));
        } else {
            cost = ((Game) list.get(index)).getCost(list.
                    get(index).rentedOn);
            lateFee = 0.0;

            // If the title is late
            if (currDate.after(dueDate))
                lateFee = 1.0 * Math.abs(daysBetween(currDate, dueDate));
        }

        // Handles return of DVD message to customer
        DecimalFormat df = new DecimalFormat("#0.00");
        double total = cost + lateFee;
        if(total > 50){
            total = 50;
            lateFee = 0;
            cost = 50;
        }

        String thankStr = "Thank you, " +
                list.get(index).getNameOfRenter()
                + ", for renting " + list.
                get(index).getTitle() + ".\n";
        String costString = "Rental cost: $" +
                df.format(cost) + "\n";
        String lateFeeString = "Late fee: $" +
                df.format(lateFee) + "\n";
        String totalCostString = "Total cost: $" +
                df.format(total) + "\n";

        if(closeStatus) {
            JOptionPane.showMessageDialog(null, thankStr +
                    costString + lateFeeString + totalCostString);
        }
    }

    public int daysBetween(Calendar d1, Calendar d2){
        Date date = d1.getTime();
        Date date2 = d2.getTime();
        return (int)((date.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == okButton){
            closeStatus = true;
            returnDVD(index);
        }
        if(e.getSource() == cancelButton){
            dispose();
        }

        if(closeStatus){
            dispose();
        }

    }

    /********************************************************************************
     * Return a String to let the caller know which button was clicked.
     *@return an int representing the option OK or CANCEL.
     *******************************************************************************/
    public boolean closeOK(){
        return closeStatus;
    }
}
