package projectCuatro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/********************************************************************************
 * Project 3 - "RedBox-like" program
 * Create a program that simulates a DVD rental store.
 * @author patterto
 *******************************************************************************/
public class DialogDaysLate extends JDialog implements ActionListener {
    /** Textfield for date entry **/
    private JTextField daysPastTxt;
    /** Jbuttons **/
    private JButton okButton;
    private JButton cancelButton;
    /** The output area */
    private JTextArea output;
    /** Linked list of DVDs **/
    private MyDoubleWithTailLinkedList<DVD> list;
    /** Gregorian Calendar **/
    private GregorianCalendar dat;
    /** Boolean for close window status **/
    private boolean closeStatus;
    /** Boolean for opening window **/
    private boolean openStatus;
    /** The number of milliseconds in one day */
    private static final int MILLISECONDS_IN_DAY = 1000 * 3600 * 24;

    /********************************************************************************
     * Default constructor this class.
     * @param parent - Parent Frame for reference.
     * @param list - Arraylist of DVDs.
     *******************************************************************************/
    public DialogDaysLate(JFrame parent, MyDoubleWithTailLinkedList<DVD> list) {
        // call parent and create a 'modal' dialog
        super(parent, true);
        this.list = list;

        setTitle("Days Late Estimate: ");
        closeStatus = false;
        openStatus = true;
        setSize(400,200);

        dat = new GregorianCalendar();
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
        daysPastTxt = new JTextField(df.format(date),15);
        textPanel.add(daysPastTxt);

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

    public void sortList(){
        if(daysPastTxt.getText().equals("")){
            closeStatus = false;
            JOptionPane.showMessageDialog(null,
                    "Return date invalid, try again.");
        }
        // Clear the output text
        output.setText(null);
        // Create lists for the date and titles of each unit
        ArrayList<String> dates = new ArrayList<String>();
        ArrayList<String> titles = new ArrayList<String>();
        // Create calendars for the input date and the due date
        Calendar dueDate = new GregorianCalendar();
        // The amount of days the title is late
        int daysLate = 0;
        double cost = 0.0;
        double lateFee = 0.0;

        // Parse and format entered date
        if(dat != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "MM/dd/yy");
            try {
                dat.setTime(dateFormat.parse(daysPastTxt.getText()));
                openStatus = true;
            } catch (ParseException e1) {
                closeStatus = false;
                openStatus = false;
                JOptionPane.showMessageDialog(null,
                        "Return date invalid, try again.");
            }
        }

        //Store the date and title for each unit
//        for(DVD dvd : list) {
        for(int i = 0; i < list.size(); i++){
            DVD dvd = list.get(i);
            dates.add(DateFormat.getDateInstance(DateFormat.SHORT).
                    format(dvd.getDueBack().getTime()));
            titles.add("Name: " + dvd.getNameOfRenter() + ", Title: " + dvd.getTitle());

            //If the title is late
            if (dat.after(dvd.dueBack))
                lateFee = 1.0  * Math.abs(daysBetween(dat, dueDate));

        }

        for( int i = 0; i < titles.size(); i++) {
            String title = titles.get(i);
            String date = dates.get(i);
            cost = 3.0;
            lateFee = 1.0  * Math.abs(daysBetween(dat, list.get(i).getDueBack()));
            dueDate.setTime(list.get(i).getDueBack().getTime());

            //If the title is late relative to the input date
            if(dueDate.before(dat)) {
                //Set the low-level time components to zero
                dueDate.set(Calendar.HOUR_OF_DAY, 0);
                dueDate.set(Calendar.MINUTE, 0);
                dueDate.set(Calendar.SECOND, 0);
                dueDate.set(Calendar.MILLISECOND, 0);

                //Get the time in milliseconds of the due date
                long startTime = dueDate.getTimeInMillis();

                //Set the low-level time components to zero
                dat.set(Calendar.HOUR_OF_DAY, 0);
                dat.set(Calendar.MINUTE, 0);
                dat.set(Calendar.SECOND, 0);
                dat.set(Calendar.MILLISECOND, 0);

                //Get the time in milliseconds of the input date
                long endTime = dat.getTimeInMillis();

                //Calculate the amount of days that the title is late
                daysLate = (int)((endTime - startTime) /
                        MILLISECONDS_IN_DAY);

                // Update the output area with the late title data
                DecimalFormat df = new DecimalFormat("#0.00");
                double total = cost + lateFee;
                if(total > 50){
                    total = 50;
                    lateFee = 0;
                    cost = 50;
                }
                output.append(title +  ", Due Date: " + date + ", Days Late: " + daysLate +
                        ", Cost: " + df.format(total) + "\n");
            }
        }
    }

    public int daysBetween(Calendar d1, Calendar d2){
        Date date = d1.getTime();
        Date date2 = d2.getTime();
        return (int)((date.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
    }
    /********************************************************************************
     * Action performed/ the control.
     * @param e - action event just fired.
     *******************************************************************************/
    public void actionPerformed(ActionEvent e) {
        // if OK clicked the fill the object
        if (e.getSource()== okButton) {
            // save the information in the object
            sortList();
        }
        if(e.getSource() == cancelButton){
            openStatus = false;
            dispose();
        }
        // make the dialog disappear
        if(openStatus) {
            dispose();
            JOptionPane.showMessageDialog(null, new JScrollPane(output));
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
