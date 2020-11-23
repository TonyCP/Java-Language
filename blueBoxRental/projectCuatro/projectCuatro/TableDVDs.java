package projectCuatro;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/********************************************************************************
 * Project 3 - "RedBox-like" program
 * Create a program that simulates a DVD rental store.
 * @author patterto
 *******************************************************************************/
public class TableDVDs extends AbstractTableModel {
    private MyDoubleWithTailLinkedList<DVD> listDVDs;
    private ArrayList<Integer> indexLinks;
    private MyDoubleWithTailLinkedList<TableDVDAction> tableDVDActions;

    private Boolean saved = false;

    /********************************************************************************
     * Default constructor
     *******************************************************************************/
    public TableDVDs() {
        super();
        listDVDs = new MyDoubleWithTailLinkedList<>();
        indexLinks = new ArrayList<Integer>();
        tableDVDActions = new MyDoubleWithTailLinkedList<>();
        createList();
    }

    /********************************************************************************
     * Method to get the column size.
     * @return - number of columns.
     *******************************************************************************/
    public int getColumnCount() {
        return 5;
    }

    @Override
    /********************************************************************************
     * Method to get the row size.
     * @return - size of DVD list.
     *******************************************************************************/
    public int getRowCount() {
        return listDVDs.size();
    }

    @Override
    /********************************************************************************
     * Switch statement to get specified values by row & col.
     * @row - specified row.
     * @col - specified column.
     * @returns (alot just look lol)
     *******************************************************************************/
    public Object getValueAt(int row, int col) {
        switch (col) {

            case 0:
                return (listDVDs.get(row).getNameOfRenter());
            case 1:
                return (listDVDs.get(row).getTitle());
            case 2:
                return (DateFormat.getDateInstance(DateFormat.SHORT)
                        .format(listDVDs.get(row).getRentedOn().getTime()));
            case 3:
                return (DateFormat.getDateInstance(DateFormat.SHORT)
                        .format(listDVDs.get(row).getDueBack().getTime()));
            case 4:
                if (listDVDs.get(row) instanceof BluRay) {
                    BluRay br = (BluRay) listDVDs.get(row);
                    return "BluRay - Double Layed: " + br.isDoubleLayer();
                }
                if (listDVDs.get(row) instanceof Game) {
                    Game ga = (Game) listDVDs.get(row);
                    return "Game - Required Player: " + ga.getPlayer();
                }
            default:
                return null;
        }
    }
    /***********************************************************************
     * Creates an linked list of indexes for a linked list of DVDs
     * @param size The size of the linked list of DVDs.
     * @return The filled linked list of Indexes.
     **********************************************************************/
    private MySingleWithTailLinkedList<Integer> recreateIndexLinks(int size) {
        MySingleWithTailLinkedList<Integer> indexLinks = new MySingleWithTailLinkedList<>();
        for (int i = 0; i < size; i++) {
            indexLinks.add(i);
        }
        return indexLinks;
    }


    /********************************************************************************
     * Method to remove specified DVD by index.
     * @param i - index to be removed.
     * @return - DVD being removed.
     *******************************************************************************/
    public DVD remove(int i) {
        int realIndex = indexLinks.get(i);
        DVD unit = listDVDs.remove(realIndex);
        indexLinks.remove(i);

        for (int a = 0; a < indexLinks.size(); a++) {
            int indexLink = indexLinks.get(a);
            if (indexLink > realIndex) {
                indexLinks.set(a, indexLink - 1);
            }
        }

        TableDVDAction tAction =
                new TableDVDAction(realIndex, Action.DELETE, unit);
        tableDVDActions.add(tAction);
        fireTableDataChanged();
        return unit;
    }

    /********************************************************************************
     * Method to add a DVD to the linked list.
     * @param a - DVD to be added.
     *******************************************************************************/
    public void add(DVD a) {
        listDVDs.add(a);
        int index = listDVDs.size()-1;
        indexLinks.add(index);
        TableDVDAction tAction =
                new TableDVDAction(index, Action.INSERT, a);
        tableDVDActions.add(tAction);
        //resort??
        fireTableDataChanged();
    }

    /********************************************************************************
     * Method to get DVD of the linked list by index.
     * @param a - DVD index to be returned.
     * @return specified DVD.
     *******************************************************************************/
    public DVD getDVD(int a) {
        DVD unit = listDVDs.get(a);
        fireTableDataChanged();
        return unit;
    }

    /********************************************************************************
     * Method to get the linked list of DVDS.
     * @return linked list of DVDs.
     *******************************************************************************/
    public MyDoubleWithTailLinkedList<DVD> getList() {
        return listDVDs;

    }

    /********************************************************************************
     * Method to undo last action of linked list.
     *******************************************************************************/
    public void undo(int size) {
        int actionIndex = (tableDVDActions.size()) - 1;
//        System.out.println(tableDVDActions.size()-1);
        if(actionIndex < 0) return;
        TableDVDAction tAction = tableDVDActions.get(actionIndex);
        DVD dvd = tAction.getDvd();
        int DVDIndex = tAction.getIndex();

        switch (tAction.getAction()){
            case DELETE:
                System.out.println("hey");
                listDVDs.insertAfter(dvd, DVDIndex -1);

                for (int i = 0; i < indexLinks.size(); ++i) {
                    int indexLink = indexLinks.get(i);
                    if (indexLink >= DVDIndex) {
                        indexLinks.set(i, indexLink + 1);
                    }
                }
                indexLinks.add(DVDIndex);
                break;

            case INSERT:
                listDVDs.remove(DVDIndex);

                int lastIndexLink = -1;
                for (int i = 0; i < indexLinks.size(); ++i) {
                    int indexLink = indexLinks.get(i);
                    if (indexLink == DVDIndex) {
                        lastIndexLink = i;
                    }
                }
                indexLinks.remove(lastIndexLink);
                break;

        }

    }

    /********************************************************************************
     * Method to get the past edits of the red box program.
     * @return - linked list of past edits.
     *******************************************************************************/
//    public MySingleWithTailLinkedList<DVD> getPastEdits() {
//        return pastEdits;
//    }

    /********************************************************************************
     * Method to save serialized file.
     * @param filename - name of the file to be saved.
     *******************************************************************************/
    public void saveDatabase(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listDVDs);
            os.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in saving db");

        }
    }

    /********************************************************************************
     * Loads (deserializes) the Account objects from the specified file.
     *  @param filename autoName of the file to load from.
     *******************************************************************************/
    public void loadDatabase(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);

            listDVDs = (MyDoubleWithTailLinkedList<DVD>) is.readObject();
            fireTableDataChanged();
            is.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error in loading db");
        }
    }

    /********************************************************************************
     * Method to save as text file.
     * @param - name of text file to be saved.
     *******************************************************************************/
    public boolean saveAsText(String filename) {
        File saveFile = new File(filename);
        FileWriter savefileWriter;

        // Attempt to create the file writer
        try {
            savefileWriter = new FileWriter(saveFile);
        } catch (IOException e) {
            return false;
        }

        String retval = "";

        // Print the unit data
//        for(DVD dvd1 : listDVDs){
        for (int i = 0; i < listDVDs.size(); i++) {
            DVD dvd1 = listDVDs.get(i);
            // For a game
            if (dvd1 instanceof Game) {
                retval += DateFormat.getDateInstance(DateFormat.SHORT).
                        format(dvd1.getRentedOn().getTime()) + ",";
                retval += DateFormat.getDateInstance(DateFormat.SHORT).
                        format(dvd1.getDueBack().getTime()) + ",";
                retval += dvd1.getTitle() + ",";
                retval += dvd1.getNameOfRenter() + ",";
                retval += ((Game) dvd1).getPlayer();
            } else if (dvd1 instanceof BluRay) {
                retval += DateFormat.getDateInstance(DateFormat.SHORT).
                        format(dvd1.getRentedOn().getTime()) + ",";
                retval += DateFormat.getDateInstance(DateFormat.SHORT).
                        format(dvd1.getDueBack().getTime()) + ",";
                retval += dvd1.getTitle() + ",";
                retval += dvd1.getNameOfRenter() + ",";
                retval += ((BluRay) dvd1).isDoubleLayer();
            }
            // Add the new line character
            retval += "\n";
        }
        //Attempt to write to the file and close it
        try {
            savefileWriter.write(retval);
            savefileWriter.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /********************************************************************************
     * Loads text file by filename.
     * @param filename - name of text file to be loaded.
     *******************************************************************************/
    public boolean loadFromText(String filename) {
        File loadFile;
        Scanner fileIn;

        // Attempt to create the scanner
        try {
            loadFile = new File(filename);
            fileIn = new Scanner(loadFile);
        } catch (Exception e) {
            return false;
        }
        String line;
        String properties[];

        // If there is another entry...
        while (fileIn.hasNextLine()) {

            ///Read the line
            line = fileIn.nextLine();
            //Delimit the line
            properties = line.split(",");

            // Parse and create
            if (properties.length == 5) {
                // Parse date for input into Gregorian Calender
                GregorianCalendar date = new GregorianCalendar();
                String[] parsedDate = properties[0].split("/");
                date.set(GregorianCalendar.MONTH, Integer.parseInt(parsedDate[0]) - 1);
                date.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(parsedDate[1]));
                date.set(GregorianCalendar.YEAR, Integer.parseInt(parsedDate[2]));

                GregorianCalendar date2 = new GregorianCalendar();
                String[] parsedDate2 = properties[1].split("/");
                date2.set(GregorianCalendar.MONTH, Integer.parseInt(parsedDate2[0]) - 1);
                date2.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(parsedDate2[1]));
                date2.set(GregorianCalendar.YEAR, Integer.parseInt(parsedDate2[2]));
                System.out.println(properties.length);

                if (properties[4].contains("true") || properties[4].contains("false")) {
                    try {
                        add(new BluRay(date, date2, properties[2], properties[3],
                                Boolean.valueOf(properties[4])));
                    } catch (Exception e) {
                        return false;
                    }

                } else
                    try {
                        add(new Game(date, date2, properties[2], properties[3],
                                PlayerType.valueOf(properties[4])));
                    } catch (Exception e) {
                        return false;
                    }
            }
        }
        //Close the file
        fileIn.close();

        //Completed successfully
        return true;
    }

    /********************************************************************************
     * Method to start with a premade list of DVDs and renters.
     *******************************************************************************/
    public void createList() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar temp1 = new GregorianCalendar();
        GregorianCalendar temp2 = new GregorianCalendar();
        GregorianCalendar temp3 = new GregorianCalendar();
        GregorianCalendar temp4 = new GregorianCalendar();
        GregorianCalendar temp5 = new GregorianCalendar();
        GregorianCalendar temp6 = new GregorianCalendar();

        try {
            Date d1 = df.parse("3/20/2010");
            temp1.setTime(d1);
            Date d2 = df.parse("4/12/2018");
            temp2.setTime(d2);
            Date d3 = df.parse("04/20/2018");
            temp3.setTime(d3);
            Date d4 = df.parse("5/20/2018");
            temp4.setTime(d4);
            Date d5 = df.parse("5/25/2018");
            temp5.setTime(d5);
            Date d6 = df.parse("1/20/2020");
            temp6.setTime(d6);


            Game game1 = new Game(temp1, temp2, "Title Game1", "Name1", PlayerType.Xbox360);
            Game game2 = new Game(temp1, temp3, "Title Game2", "Name2", PlayerType.XboxOne);
            Game game3 = new Game(temp1, temp6, "Title Game3", "Name3", PlayerType.PC);
            BluRay bluRay1 = new BluRay(temp1, temp3, "Title BluRay1", "Name4", true);
            BluRay bluRay2 = new BluRay(temp2, temp5, "Title BluRay2", "Name5", false);
//            BluRay bluRay3 = new BluRay(temp5, temp1, "Title BluRay3", "Name6" , true);

            add(game1);
            add(game2);
            add(bluRay1);
            add(game3);
//            add(bluRay2);
//            add(bluRay3);
        } catch (ParseException e) {
            throw new RuntimeException("Error in testing, creation of list");
        }

    }

//    /******************************************************************
//     * Method to sort DVDs by name alphabetical order
//     *****************************************************************/
//    public void sortByName(int size){
//        Boolean isSwapped = false;
//        do{
//            isSwapped = false;
//            for(int i=0;i < size-1;i++){
//                if( listDVDs.get(i).getNameOfRenter().compareToIgnoreCase( listDVDs.get(i + 1).getNameOfRenter())>0){
//                    String temp =  listDVDs.get(i +1).getNameOfRenter();
//                    listDVDs.get(i + 1).setNameOfRenter(listDVDs.get(i).getNameOfRenter());
//                    listDVDs.get(i).setNameOfRenter(temp);
//                    isSwapped = true;
//                }
//            }
//        }while((isSwapped));
//        this.fireTableDataChanged();
//
//    }
//
//    /******************************************************************
//     * Method to sort by DVD title.
//     *****************************************************************/
//    public void sortByTitle(int size){
//        Boolean isSwapped = false;
//        do{
//            isSwapped = false;
//            for(int i=0;i < size-1;i++){
//                if( listDVDs.get(i).getTitle().compareToIgnoreCase( listDVDs.get(i + 1).getTitle())>0){
//                    String temp =  listDVDs.get(i +1).getTitle();
//                    listDVDs.get(i + 1).setTitle(listDVDs.get(i).getTitle());
//                    listDVDs.get(i).setTitle(temp);
//                    isSwapped = true;
//                }
//            }
//        }while((isSwapped));
//        this.fireTableDataChanged();
//    }
//
    /******************************************************************
     * Method to sort by date of rental.
     *****************************************************************/
    public void sortByRentDate(int size){
        // Sort first
        specialSortTypeDate(size);
        // Count instances of games
        int gCount = 0;
        for(int i = 0; i < size; i++){
            if(listDVDs.get(i) instanceof Game){
                gCount++;
            }
        }
        // Count instances of bluRay
        int bCount = 0;
        for(int i =0; i < size; i++){
            if(listDVDs.get(i) instanceof BluRay){
                bCount++;
            }
        }

       Boolean isSwapped = false;
                do{
                    isSwapped = false;

                    System.out.println("g: " + gCount);
                    System.out.println("b: " + bCount);
                    // sort game by date.
                    for(int i = 0; i < gCount-2; i++) {
                        if (listDVDs.get(i) instanceof Game && listDVDs.get(i + 1) instanceof Game) {
                            if (listDVDs.get(i).getRentedOn().compareTo(listDVDs.get(i + 1).getRentedOn()) > 0) {
                                DVD temp = listDVDs.get(i);
                                listDVDs.remove(i);
                                listDVDs.insertAfter(temp, i + 1);
                                isSwapped = true;

                            }
                        }
                    }

//                    // sort bluRay by date.
                    for(int i = gCount; i < size-2; i++) {
                            if (listDVDs.get(i) instanceof BluRay && listDVDs.get(i + 1) instanceof BluRay) {
                                if (listDVDs.get(i).getRentedOn().compareTo(listDVDs.get(i + 1).getRentedOn()) > 0) {
                                    DVD temp = listDVDs.get(i);
                                    listDVDs.remove(i);
                                    listDVDs.insertAfter(temp, i + 1);
                                    isSwapped = true;

                                }
                            }
                        }
////                        if(i == size-1){
////                            if(listDVDs.get(i) instanceof BluRay){
////                                listDVDs.insertAfter(listDVDs.get(i), gCount-1);
////                            }
////                        }
//                    }
                    this.fireTableDataChanged();
                }while((isSwapped));
        this.fireTableDataChanged();
    }

//    /******************************************************************
//     * Method to sort by date DVD is due back.
//     *****************************************************************/
//    public void sortByDueDate(int size){
//        Boolean isSwapped = false;
//        do{
//            isSwapped = false;
//            for(int i=0;i < size-1;i++){
//                if( listDVDs.get(i).getDueBack().compareTo( listDVDs.get(i + 1).getDueBack())>0){
//                    GregorianCalendar temp =  listDVDs.get(i +1).getDueBack();
//                    listDVDs.get(i + 1).setDueBack(listDVDs.get(i).getDueBack());
//                    listDVDs.get(i).setDueBack(temp);
//                    isSwapped = true;
//                }
//            }
//        }while((isSwapped));
//        this.fireTableDataChanged();
//    }

    /******************************************************************
     * Method to sort DVDs by type and rental date.
     *****************************************************************/
    public void specialSortTypeDate(int size) {

        for (int i = 0; i < size; i++) {

            if (listDVDs.get(i) instanceof Game) {
                DVD dvd = listDVDs.get(i);
                listDVDs.remove(i);
                listDVDs.addTop(dvd);
            }
            this.fireTableDataChanged();
        }
    }

}