package projectCuatro;

import java.util.GregorianCalendar;

/********************************************************************************
 * Project 3 - "RedBox-like" program
 * Create a program that simulates a DVD rental store.
 * @author patterto
 *******************************************************************************/
public class Game extends DVD {
    /** Game DVD compatible console type.. EX: xbox **/
    private PlayerType player;

    /********************************************************************************
     * Method returns a double of selected DVD(Game) cost based on the date it
     * is rented.
     * // Cost function not correct //
     * @param ActualDatRented - Date DVD is rented.
     *******************************************************************************/
    public double getCost(GregorianCalendar ActualDatRented) {
        return super.getCost(ActualDatRented) * Math.abs(super.daysBetween(rentedOn, dueBack));
    }

    /********************************************************************************
     * DVD(Game) class default constructor(No param)
     *********************************3**********************************************/
    public Game() {
        super();
    }

    /********************************************************************************
     * DVD(Game) class constructor
     * @param rentedOn - Gregorian Calender date "this" DVD is rented on.
     * @param dueBack - Gregorian Calender date "this" DVD is due for return.
     * @param title - String representation of "this" DVD title.
     * @param name - String representation name of person who is renting "this" DVD.
     * @param player - Type of Game DVD compatible console.
     *******************************************************************************/
    public Game(GregorianCalendar rentedOn, GregorianCalendar dueBack,
                String title, String name, PlayerType player) {
        super(rentedOn, dueBack, title, name);
        this.player = player;
    }

    /********************************************************************************
     * DVD(Game) class constructor
     * @param player - Type of Game DVD compatible console.
     *******************************************************************************/
    public Game(PlayerType player) {
        super();
        this.player = player;
    }

    /********************************************************************************
     * Returns the String of compatible console type of "this" DVD.
     *******************************************************************************/
    public PlayerType getPlayer() {
        return player;
    }

    /********************************************************************************
     * Method to set the compatible console type of "this" DVD.
     *******************************************************************************/
    public void setPlayer(PlayerType player) {
        this.player = player;
    }

}
