package toTheKing;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class provides a the functionality of a item that can be represented in
 * the shop
 */

public class ShopItem {

    protected ArrayList<Sprite> spriteImages;
    protected ArrayList<Text> itemTexts;
    protected int iD;
    protected double priceOfItem;
    protected boolean isDisplayed;

    /**
     * Creates an instance of shopitem that sets up the variables Initialises the
     * array list of sprite images and item texts , sets the price and id and sets
     * the displayed field to false
     * 
     * @param ID of the item, represents either an upgrade or a new item
     */

    public ShopItem(int ID, double priceOfItem) {
        spriteImages = new ArrayList<Sprite>();
        itemTexts = new ArrayList<Text>();
        this.iD = ID;
        this.priceOfItem = priceOfItem;
        isDisplayed = false;
    }

    /**
     * Function to create a text
     *
     * @param text   the text to be made
     * @param size   the size of the text
     * @param colour the colour of the text
     * @return Text the text to be returned
     */

    public Text createText(String text, int size, Color colour) {
        Font tempFont = new Font();
        try {
            tempFont.loadFromFile(Paths.get("img/KozGoPr6NRegular.otf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Text tempText = new Text();
        tempText.setFont(tempFont);
        tempText.setColor(colour);
        tempText.setString(text);
        tempText.setCharacterSize(size);
        return tempText;
    }

    /**
     * Function to create a sprite by taking the image path of the item
     *
     * @param imagePath the path of the image
     * @return tempSprite the sprite to be returned
     */

    public Sprite createSprite(String imagePath) {
        Texture tempTexture = new Texture();
        try {
            tempTexture.loadFromFile(Paths.get(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sprite tempSprite = new Sprite(tempTexture);
        return tempSprite;
    }

    /**
     * Draws the sprites within the different arraylists to an Render Window
     *
     * @param cWindow the window to draw to
     */

    public void drawSprites(RenderWindow cWindow) {
        if (spriteImages.size() != 0) {
            for (int i = 0; i < spriteImages.size(); i++) {
                cWindow.draw(spriteImages.get(i));
            }
        }
        if (itemTexts.size() != 0) {
            for (int i = 0; i < itemTexts.size(); i++) {
                cWindow.draw(itemTexts.get(i));
            }
        }
    }

    /**
     * Checks to see if the player has enough money to afford this item
     *
     * @param playerMoney the amount the player has
     */

    public boolean canPlayerBuy(double playerMoney) {
        if (playerMoney >= priceOfItem) {
            return true;
        }
        return false;
    }

    /**
     * Method to be overriden in subclasses, performs the action of what they bought
     *
     * @param playerToGivePurchase the player to give the purchase to
     */

    public void givePlayerPurchase(Player playerToGivePurchase) {
    }

    /**
     * Returns the id of the item
     *
     * @return iD the id of the item
     */

    public int getiD() {
        return iD;
    }

    /**
     * Returns if the sprite is currently displayed
     *
     * @return isDisplayed returns true if the sprite is displayed
     */

    public boolean getIsDisplayed() {
        return isDisplayed;
    }

    /**
     * Sets the displayed field
     *
     * @param displayed sets the isDisplayed field to this
     */

    public void setIsDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    /**
     * Returns the array list of sprites
     *
     * @return spriteImages the array list of sprites
     */

    public ArrayList<Sprite> getSpriteImages() {
        return spriteImages;
    }

    /**
     * Returns the array list of texts
     *
     * @return itemTexts the array list of texts
     */

    public ArrayList<Text> getItemTexts() {
        return itemTexts;
    }

    /**
     * Returns the price of the item
     *
     * @return priceOfItem the price of the item
     */

    public double getPrice() {
        return priceOfItem;
    }

    /**
     * Sets the price of the item
     *
     * @param priceToSet the new price
     */

    public void setPrice(int priceToSet) {
        priceOfItem = priceToSet;
    }

}