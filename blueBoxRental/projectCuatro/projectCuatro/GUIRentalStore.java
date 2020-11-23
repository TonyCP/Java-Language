package projectCuatro;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


/********************************************************************************
 * Project 3 - "RedBox-like" program
 * This class maintains the GUI1024 for the red box rental store.
 * Create a program that simulates a DVD rental store.
 * @author patterto
 *******************************************************************************/
public class GUIRentalStore extends JFrame implements ActionListener, MouseListener{
    /** Holds menu bar */
    private JMenuBar menus;
    /** menus in the menu bar */
    private JMenu fileMenu;
    private JMenu actionMenu;
    /** menu items in each of the menus */
    private JMenuItem openSerItem;
    private JMenuItem exitItem;
    private JMenuItem saveSerItem;
    private JMenuItem openTextItem;
    private JMenuItem saveTextItem;
    private JMenuItem rentBluRay;
    private JMenuItem rentGame;
    private JMenuItem returnItem;
    private JMenuItem dayLate;
    private JMenuItem undo;
    private JCheckBoxMenuItem filterTog;
    /** Holds the list engine */
    private TableDVDs tableDVDs;
    /** Holds jTable */
    private JTable jTable;
    private boolean closeStatus;

    /********************************************************************************
     * Default Constructor for the GUI
     *******************************************************************************/
    public GUIRentalStore(){
        closeStatus = false;
        // adding menu bar and menu items
        menus = new JMenuBar();
        fileMenu = new JMenu("File");
        actionMenu = new JMenu("Action");
        openSerItem = new JMenuItem("Open File");
        exitItem = new JMenuItem("Exit");
        saveSerItem = new JMenuItem("Save File");
        openTextItem = new JMenuItem("Open Text");
        saveTextItem = new JMenuItem("Save Text");
        rentBluRay = new JMenuItem("Rent BluRay");
        rentGame = new JMenuItem("Rent Game");
        returnItem = new JMenuItem("Return");
        dayLate = new JMenuItem("Days Late");
        undo = new JMenuItem("Undo");
        filterTog = new JCheckBoxMenuItem("Filter Toggle");

//        MyMouseAdapter mouse = new MyMouseAdapter();

        // adding items to bar
        fileMenu.add(openSerItem);
        fileMenu.add(saveSerItem);
        fileMenu.addSeparator();
        fileMenu.add(openTextItem);
        fileMenu.add(saveTextItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        actionMenu.add(rentBluRay);
        actionMenu.add(rentGame);
        actionMenu.addSeparator();
        actionMenu.add(returnItem);
        actionMenu.addSeparator();
        actionMenu.add(dayLate);
        actionMenu.add(filterTog);
        actionMenu.addSeparator();
        actionMenu.add(undo);

        menus.add(fileMenu);
        menus.add(actionMenu);

        // adding actionListener
        openSerItem.addActionListener(this);
        saveSerItem.addActionListener(this);
        openTextItem.addActionListener(this);
        saveTextItem.addActionListener(this);
        exitItem.addActionListener(this);
        rentBluRay.addActionListener(this);
        rentGame.addActionListener(this);
        returnItem.addActionListener(this);
        dayLate.addActionListener(this);
        undo.addActionListener(this);
        fileMenu.addActionListener(this);
        filterTog.addActionListener(this);

        filterTog.setState(false);
        // frame.setJMenuBar(menus);
        setJMenuBar(menus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // adding list to the GUI1024
        tableDVDs = new TableDVDs();
        jTable = new JTable(tableDVDs);
        jTable.getTableHeader().addMouseListener(this);
//        jTable.getTableHeader().

        add(new JScrollPane(jTable));
        setHead();

        setVisible(true);
        setSize(1000,400);
    }

    /********************************************************************************
     * Method to set JTable column headers.
     *******************************************************************************/
    public void setHead(){
        String header[] = {"Customer Name", "Title", "Rented Date", "Due Back Date", "Information"};
        for(int i = 0; i < jTable.getColumnCount(); i++){
            TableColumn col = jTable.getTableHeader().getColumnModel().getColumn(i);
            col.setHeaderValue(header[i]);
            jTable.setColumnSelectionAllowed(true);
        }
    }

    /*****************************************************************
     * This method handles event-handling code for the GUI1024.
     * @param e - Holds the action event parameter
     *****************************************************************/
    public void actionPerformed(ActionEvent e) {
        MyDoubleWithTailLinkedList<DVD> dvdList = new MyDoubleWithTailLinkedList<>();
        MyDoubleWithTailLinkedList<DVD> dvdList2 = new MyDoubleWithTailLinkedList<>();

        Object comp = e.getSource();

        if (openSerItem == comp || openTextItem == comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();

                if (openSerItem == comp)
                    tableDVDs.loadDatabase(filename);

                if(openTextItem == comp)
                    tableDVDs.loadFromText(filename);
            }
        }

        if (saveSerItem == comp || saveTextItem == comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showSaveDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                if (saveSerItem == comp)
                    tableDVDs.saveDatabase(filename);

                if(saveTextItem == comp)
                    tableDVDs.saveAsText(filename);
            }
        }

        //MenuBar options
        if(e.getSource() == exitItem){
            System.exit(1);
        }
        if(e.getSource() == rentBluRay){
            DVD bluRay = new BluRay();
            DialogRentBluRay dialog = new DialogRentBluRay(this, bluRay);
            if(dialog.closeOK()){
                tableDVDs.add(bluRay);
                tableDVDs.specialSortTypeDate(tableDVDs.getList().size());
                tableDVDs.sortByRentDate(tableDVDs.getList().size());

                // Saves past action
//                tableDVDs.getPastEdits().add(bluRay);
            }
            jTable.revalidate();
            jTable.repaint();
        }
        if(e.getSource() == rentGame){
            Game game = new Game();
            DialogRentGame dialog = new DialogRentGame(this, game);
            if(dialog.closeOK()){
                tableDVDs.add(game);
                tableDVDs.specialSortTypeDate(tableDVDs.getList().size());
                tableDVDs.sortByRentDate(tableDVDs.getList().size());

                // Saves past edit
//                tableDVDs.getPastEdits().add(game);
            }
            jTable.revalidate();
            jTable.repaint();
        }

        if (returnItem == e.getSource()) {
            // Get selected index
            int index = jTable.getSelectedRow();

            // Check for out of bounds indexes
            if(index < 0)
                // Alert the user of an out of bounds index
                JOptionPane.showMessageDialog(null,
                        "Please select an item from the list");
            else {
                returnDialog ret = new returnDialog(this, index, tableDVDs.getList());
                if(ret.closeOK()) {
                    tableDVDs.remove(index);
                }
            }
            jTable.revalidate();
            jTable.repaint();
        }
        if(e.getSource() == dayLate){
//            GregorianCalendar dat = new GregorianCalendar();
            DialogDaysLate dialog = new DialogDaysLate(this,
                    tableDVDs.getList());
        }
        if(e.getSource() == filterTog){

            if(filterTog.isSelected()) {
                dvdList = tableDVDs.getList();
                String fDay =JOptionPane.showInputDialog(null,
                        "Enter toggle date: MM/DD/YY");
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "MM/dd/yy");
                GregorianCalendar cal = new GregorianCalendar();

                if(fDay == null){
                    closeStatus = false;
                    System.out.println("Toggle Canceled");
                }
                try {
                    cal.setTime(dateFormat.parse(fDay));
                    closeStatus = true;
                } catch (ParseException e1) {
                    closeStatus = false;
                    JOptionPane.showMessageDialog(null,
                            "Return date invalid, try again.");
                }

                if(closeStatus && !(fDay == null)) {
                    for (int i = 0; i < tableDVDs.getList().size(); i++) {
                        if (tableDVDs.getDVD(i).getDueBack().before(cal)) {
                            dvdList2.add(tableDVDs.getDVD(i));
                        }
                    }
                    tableDVDs.getList().clear();
                    for (int a = 0; a < dvdList2.size(); a++) {
                        tableDVDs.add(dvdList2.get(a));
                    }
                }
            }
            if(!filterTog.isSelected()) {
                if(closeStatus) {
                    System.out.println("Hey");
//                    tableDVDs.getList().clear();
                    for (int i = 0; i < dvdList.size(); i++)
                        tableDVDs.add(dvdList.get(i));
                }
            }
            jTable.revalidate();
            jTable.repaint();
        }

        if(e.getSource() == undo){
            tableDVDs.undo(tableDVDs.getList().size());
            jTable.revalidate();
            jTable.repaint();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = jTable.columnAtPoint(e.getPoint());
//
//        if(jTable.getTableHeader().getColumnModel().getColumn(col).
//                getHeaderValue() == "Customer Name"){
//            tableDVDs.sortByName(tableDVDs.getList().size());
//        }
//
//        if(jTable.getTableHeader().getColumnModel().getColumn(col).
//                getHeaderValue() == "Title"){
//            tableDVDs.sortByTitle(tableDVDs.getList().size());
//        }0    !qqqqqQQQQQQQ

        if(jTable.getTableHeader().getColumnModel().getColumn(col).
                getHeaderValue() == "Rented Date"){
//            tableDVDs.sortByRentDate(tableDVDs.getList().size());
        }
//
//        if(jTable.getTableHeader().getColumnModel().getColumn(col).
//                getHeaderValue() == "Due Back Date"){
//            tableDVDs.sortByDueDate(tableDVDs.getList().size());
//        }

//        if(jTable.getTableHeader().getColumnModel().getColumn(col).
//                getHeaderValue() == "Information"){
//            tableDVDs.specialSortTypeDate(tableDVDs.getList().size());
//        }

        jTable.revalidate();
        jTable.repaint();
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


    /********************************************************************************
     * Main Method to run the program.
     *******************************************************************************/
    public static void main(String[] args) {
        new GUIRentalStore();
    }
}
