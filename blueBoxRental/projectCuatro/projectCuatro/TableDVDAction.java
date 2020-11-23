package projectCuatro;
import java.io.Serializable;

/***********************************************************************
 * Project 4 - "Red-Box" like program.
 * This class helps provide support for the TableDVDS class actions in
 * compliance with the GUI.
 *
 * @author Tonae Patterson
 **********************************************************************/
public class TableDVDAction implements Serializable {
    private int index;
    private Action action;
    private DVD dvd;

    /*******************************************************************
     * This constructor sets the instance variables to the parameter
     * values obtained.
     * @param index The index of the dvd.
     * @param action Action done.
     * @param dvd The DVD being manipulated.
     ******************************************************************/
    public TableDVDAction(int index, Action action, DVD dvd) {
        this.index = index;
        this.action = action;
        this.dvd = dvd;
    }

    /*******************************************************************
     * Method to get the index of this DVD.
     * @return DVD index.
     ******************************************************************/
    public int getIndex() {
        return index;
    }

    /*******************************************************************
     * Method to get the action of this DVD.
     * @return action.
     ******************************************************************/
    public Action getAction() {
        return action;
    }

    /*******************************************************************
     * Method to get this DVD.
     * @return this DVD.
     ******************************************************************/
    public DVD getDvd() {
        return dvd;
    }
}
