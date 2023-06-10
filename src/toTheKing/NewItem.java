package toTheKing;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;

/**
 * This class provides a the functionality of a new item the player can buy
 */

public class NewItem extends ShopItem {

    private int itemID;

    /**
     * Creates an instance of an new item, calls super with 1 and the price Calls
     * the add new item
     * 
     * @param priceOfItem the price of the new item
     * @param itemID      id of the item, represents a certian item
     */

    public NewItem(int itemID, double priceOfItem) {
        super(1, priceOfItem);
        this.itemID = itemID;
        addNewItem();
    }

    /**
     * Creates a new item based on the id passed in
     */

    public void addNewItem() {
        String path = "img/ShopImage.png";
        String itemText = "Temp";
        System.out.println("New Item ID : " + itemID);
        switch (itemID) {
            case 0:
                path = "img/ShopArrows/DarkArrow.png";
                itemText = "Dark Arrow";
                priceOfItem = 200;
                break;
            case 1:
                path = "img/ShopArrows/ExplosiveArrow.png";
                itemText = "Explosive Arrow";
                priceOfItem = 200;
                break;
            case 2:
                path = "img/ShopArrows/FireArrow.png";
                itemText = "Fire Arrow";
                priceOfItem = 200;
                break;
            case 3:
                path = "img/ShopArrows/IceArrow.png";
                itemText = "Ice Arrow";
                priceOfItem = 200;
                break;
            case 4:
                path = "img/ShopArrows/LightningArrow.png";
                itemText = "Lightning Arrow";
                priceOfItem = 200;
                break;
            case 5:
                path = "img/ShopArrows/RopeArrow.png";
                itemText = "Rope Arrow";
                priceOfItem = 200;
                break;
            case 6:
                path = "img/ShopPotions/LargeHealth.png";
                itemText = "L Health Potion";
                priceOfItem = 500;
                break;
            case 7:
                path = "img/ShopPotions/SmallHealth.png";
                itemText = "S Health Potion";
                priceOfItem = 150;
                break;
            case 8:
                path = "img/ShopPotions/Speed.png";
                itemText = "Speed Potion";
                priceOfItem = 350;
                break;
            case 9:
                path = "img/ShopPotions/Strength.png";
                itemText = "Strength Potion";
                priceOfItem = 450;
                break;
            case 10:
                path = "img/ArmourShop/AttackArmourShop.png";
                itemText = "Attack Armour";
                priceOfItem = 3500;
                break;
            case 11:
                path = "img/ArmourShop/DefenceArmourShop.png";
                itemText = "Defence Armour";
                priceOfItem = 3500;
                break;
            case 12:
                path = "img/ArmourShop/SpeedArmourShop.png";
                itemText = "Speed Armour";
                priceOfItem = 3500;
                break;
        }
        Sprite itemSprite = createSprite(path);
        spriteImages.add(itemSprite);
        Sprite buyButton = createSprite("img/BuyButton.png");
        spriteImages.add(buyButton);
        Text newItem = createText(itemText, 20, Color.WHITE);
        itemTexts.add(newItem);
        Text itemPrice = createText("Price : " + priceOfItem, 15, Color.WHITE);
        itemTexts.add(itemPrice);
    }

    /**
     * Gives the player the purchase
     * 
     * @param playerToGivePurchase the player to give the purchase
     */

    public void givePlayerPurchase(Player playerToGivePurchase) {
        switch (itemID) {
            case 6:
                playerToGivePurchase.getPotion().setPot("largeHealth");
                break;
            case 7:
                playerToGivePurchase.getPotion().setPot("smallHealth");
                break;
            case 8:
                playerToGivePurchase.getPotion().setPot("speed");
                break;
            case 9:
                playerToGivePurchase.getPotion().setPot("strength");
                break;
            case 10:
                playerToGivePurchase.getArmour().removeHeavyArmour();
                playerToGivePurchase.getArmour().removeLightArmour();
                playerToGivePurchase.getArmour().addStrongArmour();
                break;
            case 11:
                playerToGivePurchase.getArmour().addHeavyArmour();
                playerToGivePurchase.getArmour().removeLightArmour();
                playerToGivePurchase.getArmour().removeStrongArmour();
                break;
            case 12:
                playerToGivePurchase.getArmour().removeHeavyArmour();
                playerToGivePurchase.getArmour().addLightArmour();
                playerToGivePurchase.getArmour().removeStrongArmour();
                break;

        }
    }

}
