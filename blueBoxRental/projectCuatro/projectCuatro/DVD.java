package projectCuatro;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*********************************************************************
 * Project 4 - "RedBox-Like Program"
 * - Double Linked List with Tail
 * @author TonaePatterson
 ********************************************************************/
public class DVD implements Serializable {
    private static final long serialVersionUID = 1L;
    /** Date of DVD rental **/
    protected GregorianCalendar rentedOn;
    /** Date DVD rental is due **/
    protected GregorianCalendar dueBack;
    /** String of DVD title **/
    protected String title;
    /** String of DVD renter name **/
    protected String nameOfRenter;

    /********************************************************************************
     * Method returns a double of selected DVD cost based on the date it is rented.
     * @param ActualDatRented - Date DVD is rented.
     *******************************************************************************/
    public double getCost(GregorianCalendar ActualDatRented) {
        double cost;
//         Date date = Calendar.getInstance().getTime();
//         get today's date.
//		Calendar c = Calendar.getInstance();
//		c.setTime(soldOn.getTime());
//		c.add(Calendar.DATE, 1);  // number of days to add to make it 12am next day.
//		soldOn.setTime(c.getTime());

        Calendar dueBackTemp = Calendar.getInstance();
        dueBackTemp.setTime(dueBack.getTime());
        if (ActualDatRented.before(dueBack))
            cost = 3;
        else {
            int count = 0;
            while (dueBackTemp.before(ActualDatRented)) {
                dueBackTemp.add(Calendar.DATE, 1);
                count++;
            }
            cost = Math.min(3 + count, 50);
        }

        return cost;
    }

    /********************************************************************************
     * DVD class default constructor(No param)
     *******************************************************************************/
    public DVD() {
    }

    /********************************************************************************
     * DVD class constructor
     * @param rentedOn - Gregorian Calender date "this" DVD is rented on.
     * @param dueBack - Gregorian Calender date "this" DVD is due for return.
     * @param title - String representation of "this" DVD title.
     * @param name - String representation name of person who is renting "this" DVD.
     *******************************************************************************/
    public DVD(GregorianCalendar rentedOn, GregorianCalendar dueBack, String title, String name) {
        super();
        this.rentedOn = rentedOn;
        this.dueBack = dueBack;
        this.title = title;
        this.nameOfRenter = name;
    }

    /********************************************************************************
     * Returns Gregorian Calender date "this" DVD was rented on.
     *******************************************************************************/
    public GregorianCalendar getRentedOn() {
        return rentedOn;
    }

    /********************************************************************************
     * Method to set the date "this" DVD is rented on.
     * @param opened - Gregorian Calender date "this" DVD is rented.
     *******************************************************************************/
    public void setRentedOn(GregorianCalendar opened) {
        this.rentedOn = opened;
    }

    /********************************************************************************
     * Returns the date "this" DVD is due to be returned.
     *******************************************************************************/
    public GregorianCalendar getDueBack() {
        return dueBack;
    }

    /********************************************************************************
     * Method to set the due date of "this" DVD.
     * @param dueBack - Gregorian Calender date "this" book is due back.
     *******************************************************************************/
    public void setDueBack(GregorianCalendar dueBack) {
        this.dueBack = dueBack;
    }

    /********************************************************************************
     * Returns the string representation of "this" DVD title.
     *******************************************************************************/
    public String getTitle() {
        return title;
    }

    /********************************************************************************
     * Method to set the string representation of "this" DVD title.
     * @param title - String title of DVD.
     *******************************************************************************/
    public void setTitle(String title) {
        this.title = title;
    }

    /********************************************************************************
     * Returns the name of person renting "this" DVD.
     *******************************************************************************/
    public String getNameOfRenter() {
        return nameOfRenter;
    }

    /********************************************************************************
     * Method to set the name of the person renting "this" DVD.
     * @param nameOfRenter - String name representation of "this" DVD renter.
     *******************************************************************************/
    public void setNameOfRenter(String nameOfRenter) {
        this.nameOfRenter = nameOfRenter;
    }

    /********************************************************************************
     * Method to get the days a DVD was rented.
     * @param d1 - date of the DVD rental.
     * @param d2 - date the DVD is due for return.
     * @return days between dates d1 and d2.
     *******************************************************************************/
    public int daysBetween(GregorianCalendar d1, GregorianCalendar d2){
        Date date = d1.getTime();
        Date date2 = d2.getTime();
        return (int)((date.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
    }

    /********************************************************************************
     * Method to convert DVD rental information to string
     * @return - string of this DVD rental info.
     *******************************************************************************/
    public String toString(){
        String dvdToString = "";
        dvdToString = "Customer: " + getNameOfRenter() + ", Title: " + getTitle()
                + ", Date Rented: " + DateFormat.getDateInstance(DateFormat.SHORT).
                format(getRentedOn().getTime()) + ", Due Date: " +
                DateFormat.getDateInstance(DateFormat.SHORT).
                        format(getDueBack().getTime());
        return dvdToString;
    }
}
