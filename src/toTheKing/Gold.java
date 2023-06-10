package toTheKing;

/**
 * This class gold allocation system that will add to the current amount of gold
 * the player currently has at the end of the level.
 *
 * @author Royantha Romans
 */
public class Gold {

    private double currentGold = 0; // current amount of gold the player hass
    private Player player;

    public Gold(double currentGold, Player player) {
        this.currentGold = currentGold;
        this.player = player;
    }

    /**
     * Gold allocation method where amount of gold player gains is calucualted
     * depending on their hp.
     *
     *
     */
    public void goldAllocation() {

        // Player players = new Player();
        double hp = player.getPlayerHealth();
        double addedGold = 0;
        if (hp > 50) {
            addedGold = hp * 5;

        } else {
            addedGold = hp * 2;
        }
        currentGold = currentGold + addedGold;
    }

    /**
     * addMoney method which adds the inputted amount of gold
     * Can be used to take away gold
     * @param addedGold amount of gold to add
     */
    public void addMoney(double addedGold) {
        currentGold = currentGold + addedGold;
        // System.out.println(currentGold);
    }

    /**
     * @return  currentGold amount of gold the player has
     */

    public double getcurrentGold() {
        return currentGold;
    }

    /**
     * @param currentGold set the current gold
     */


    public void setcurrentGold(double currentGold) {
        this.currentGold = currentGold;
    }


}
