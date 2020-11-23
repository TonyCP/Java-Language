package projectCuatro;

import java.util.GregorianCalendar;

/********************************************************************************
 * Project 3 - "RedBox-like" program
 * Create a program that simulates a DVD rental store.
 * @author patterto
 *******************************************************************************/
public class BluRay extends DVD {
    /** BluRay Type **/
    private boolean doubleLayer;

    /********************************************************************************
     * Method returns a double of selected DVD(BluRay) cost based on the date it
     * is rented.
     * // Cost function not correct //
     * @param ActualDatRented - Date DVD is rented.
     ********************************************************************/
    public double getCost(GregorianCalendar ActualDatRented) {
        return super.getCost(ActualDatRented) * Math.abs(super.
                daysBetween(rentedOn, dueBack));
    }

    /********************************************************************************
     * DVD(BluRay) class default constructor(No param)
     *******************************************************************************/
    public BluRay() {
        super();
    }

    /********************************************************************************
     * DVD(BluRay) class constructor
     * @param rentedOn - Gregorian Calender date "this" DVD is rented on.
     * @param dueBack - Gregorian Calender date "this" DVD is due for return.
     * @param title - String representation of "this" DVD title.
     * @param name - String representation name of person who is renting "this" DVD.
     * @param doubleLayer - BluRay type.
     *******************************************************************************/
    public BluRay(GregorianCalendar rentedOn, GregorianCalendar dueBack,
                  String title, String name, boolean doubleLayer) {
        super(rentedOn, dueBack, title, name);
        this.doubleLayer = doubleLayer;
    }

    /********************************************************************************
     * DVD(BlueRay) constructor with layer type params.
     *******************************************************************************/
    public BluRay(boolean doubleLayer) {
        super();
        this.doubleLayer = doubleLayer;
    }

    /********************************************************************************
     * Returns boolean if this type of BluRay is double layered.
     * @return true/false.
     *******************************************************************************/
    public Boolean isDoubleLayer() {
        return doubleLayer;
    }

    /********************************************************************************
     * Method to set the BluRay type.
     * @param doubleLayer - true/false depending on type.
     *******************************************************************************/
    public void setDoubleLayer(boolean doubleLayer) {
        this.doubleLayer = doubleLayer;
    }

}

