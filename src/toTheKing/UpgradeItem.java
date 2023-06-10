package toTheKing;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;

/**
 * This class provides a the functionality of a upgrade the player can perform
 */

public class UpgradeItem extends ShopItem {

    private int upgradeID;
    /**
     * Creates an instance of an upgrade item, calls super with 0 and the price
     * Calls the add upgrade function
     * 
     * @param priceOfItem the price of the upgrade
     * @param upgradeID   id of the item, represents a certain upgrade
     */

    public UpgradeItem(int upgradeID, double priceOfItem) {
        super(0, priceOfItem);
        this.upgradeID = upgradeID;
        addUpgrade();
    }

    /**
     * Creates an upgrade based on the id passed in
     */

    public void addUpgrade() {
        String path = "img/ShopImage.png";
        String upgradeText = "Temp";
        System.out.println("Upgrade ID : " + upgradeID);
        switch (upgradeID) {
            case 3:
                path = "img/BowTypesShop/String.png";
                upgradeText = "+ Damage";
                priceOfItem = 200;
                break;
            case 4:
                path = "img/HeartButton.png";
                upgradeText = "+ Health";
                priceOfItem = 210;
                break;
        }
        Sprite upgradeSprite = createSprite(path);
        spriteImages.add(upgradeSprite);
        Sprite buyButton = createSprite("img/BuyButton.png");
        spriteImages.add(buyButton);
        Text upgradeItem = createText(upgradeText, 20, Color.WHITE);
        itemTexts.add(upgradeItem);
        Text upgradePrice = createText("Price : " + priceOfItem, 15, Color.WHITE);
        itemTexts.add(upgradePrice);

    }

    /**
     * Gives the player the purchase
     * 
     * @param playerToGivePurchase the player to give the purchase
     */

    public void givePlayerPurchase(Player playerToGivePurchase) {
        Vector2f positionBefore;
        Text upgradePrice;
        switch (upgradeID) {
            case 3:
                playerToGivePurchase.setPlayerAttack(playerToGivePurchase.getPlayerAttack() + 5);
                priceOfItem = priceOfItem + 150;
                positionBefore = itemTexts.get(1).getPosition();
                upgradePrice = createText("Price : " + priceOfItem, 15, Color.WHITE);
                upgradePrice.setPosition(positionBefore);
                itemTexts.set(1, upgradePrice);

                break;
            case 4:
                playerToGivePurchase.setPlayerHealth(playerToGivePurchase.getPlayerHealth() + 20);
                priceOfItem = priceOfItem + 180;
                positionBefore = itemTexts.get(1).getPosition();
                upgradePrice = createText("Price : " + priceOfItem, 15, Color.WHITE);
                upgradePrice.setPosition(positionBefore);
                itemTexts.set(1, upgradePrice);
                break;
        }
    }

}
