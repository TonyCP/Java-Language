package GameModel;

import CardModel.wildCard;
import GameView.card;
import Interface.gameConstants;
import Interface.unoConstants;
import gameControl.gameController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

/******************************************************************************
 * The AI class serves as artificial intelligence player of the game. This
 * class is to be played against the user.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class AI extends Player implements gameConstants {

    /******************************************************************************
     * Empty default class constructor for set up.
     ******************************************************************************/
    public AI(String name) {
        setName(name);
        super.setCards();
    }

    /******************************************************************************
     * This method controls the move of the games AI.
     * ** TO BE CORRECTED AND IMPLEMENTED AFTER BASIC FUNC PASSES ENOUGH TESTING **
     * @param topCard - card for play comparision.
     * @return - true if AI can make valid move - else false.
     ******************************************************************************/
    public boolean play(card topCard, gameController gc) {
        boolean done = false;
        final Color currentColor;
        final String value = topCard.getValue();
        final int type = topCard.getType();

        if (topCard.getType() == WILD) {
            currentColor = ((wildCard) topCard).getWildColor();
        } else
            currentColor = topCard.getColor();


        List<card> tempHand = getPlayerHand().stream().filter(c -> (c.getType() == type && c.getValue().equals(value)) ||
                c.getColor() == currentColor || c.getValue().equals(value)).collect(Collectors.toList());

        if (tempHand.stream().findFirst().isPresent()) {
            try {
                List<card> filterHand = tempHand.stream().filter(c -> c.getType() == card.ACTION).collect(Collectors.toList());

                if (filterHand.stream().findFirst().isPresent()) {
                    gc.playCard(filterHand.get(0));

                } else {
                    gc.playCard(tempHand.get(0));
                }

                done = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // if no card was found, play wild card
        if (!done) {

            tempHand = getPlayerHand().stream().filter(c -> c.getType() == unoConstants.WILD).collect(Collectors.toList());
            if (tempHand.stream().findFirst().isPresent()) {
                gc.playCard(tempHand.get(0));

                done = true;
            }
        }

        if (getPlayerHandTotal() == 1 || getPlayerHandTotal() == 2)
            setSayUNO(true);


        return done;
    }

    /******************************************************************************
     * Creates the pressing action for the computer.
     *
     * @param  card - card for play comparision.
     * ******************************************************************************/
    public void computerPressCard(card card) {
        MouseEvent doPress = new MouseEvent(card, MouseEvent.MOUSE_PRESSED,
                System.currentTimeMillis(),
                (int) MouseEvent.MOUSE_EVENT_MASK, 5, 5, 1, true);

        card.dispatchEvent(doPress);

        MouseEvent doRelease = new MouseEvent(card, MouseEvent.MOUSE_RELEASED,
                System.currentTimeMillis(),
                (int) MouseEvent.MOUSE_EVENT_MASK, 5, 5, 1, true);

        card.dispatchEvent(doRelease);

    }

    /******************************************************************************
     * The computer is picking it's wild color based on the color that it has the most
     * of in its hand, as that is the normal strategy. The colorToReturn numbers refer to
     * the order the colors are from this list:
     *     Color[] cardCOLORS = {RED, YELLOW, GREEN, BLUE};
     * @return int - the color that the AI is choosing for the wild color
     * ******************************************************************************/
    public int pickWildColor() {
        int blue = 0;
        int yellow = 0;
        int red = 0;
        int green = 0;
        int colorToReturn = 0;
        int max = 0;

        for (card currentCard : getPlayerHand()) {
            if (currentCard.getColor().equals(unoConstants.RED)) {
                red++;
                if (red > max) {
                    max = red;
                    colorToReturn = 0;
                }
            }

            if (currentCard.getColor().equals(unoConstants.YELLOW)) {
                yellow++;
                if (yellow > max) {
                    max = yellow;
                    colorToReturn = 1;
                }
            }

            if (currentCard.getColor().equals(unoConstants.GREEN)) {
                green++;
                if (green > max) {
                    max = green;
                    colorToReturn = 2;
                }
            }

            if (currentCard.getColor().equals(unoConstants.BLUE)) {
                blue++;
                if (blue > max) {
                    max = blue;
                    colorToReturn = 3;
                }
            }
        }

        System.out.println("AI's colors: blue - " + blue + " green - " + green + " red - " + red + " yellow - " + yellow);
        return colorToReturn;
    }
}
