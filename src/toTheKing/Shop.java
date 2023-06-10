package toTheKing;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class provides a the functionality of a shop for the game
 */

public class Shop {

    private RenderWindow cWindow;
    private Sprite shopSprite;
    private Texture shopTexture;
    private ArrayList<ShopItem> shopItems;
    private Sprite[] cycleArray;
    private Sprite backToBountyButton;
    private Player[] players;
    private Text[] playersMoney;

    private int numberOfUpgradeItems = 0;
    private int numberOfNewItems = 0;

    /**
     * Creates an instance of shop that sets up the variables Initialises the render
     * window, the item arraylist, the array of the cycle buttons Calls the methods
     * set up the sprite for the shop, creates any initial items or upgrades Sorts
     * the locations of any sprites
     * 
     * @param nWindow the window that the board will be applied to
     */

    public Shop(RenderWindow nWindow, Player[] players) {
        cWindow = nWindow;
        shopItems = new ArrayList<ShopItem>();
        cycleArray = new Sprite[4];
        playersMoney = new Text[2];
        setUpShopSprite();
        createInitialUpgradesAndNewItems();
        sortSpriteLocations();
        this.players = players;
    }

    /**
     * Creates an instance of an upgrade that the player can use to upgrade their
     * player, adds it to the shopitems array and increases the number of upgrades
     * 
     * @param upgradeID   the id of the item wanting to be created,maps to a certian
     *                    upgrade
     * @param priceOfItem the price of the item
     */

    public void createUpgradeItem(int upgradeID, int priceOfItem) {
        UpgradeItem item = new UpgradeItem(upgradeID, priceOfItem);
        shopItems.add(item);
        numberOfUpgradeItems++;
    }

    /**
     * Creates an instance of an new item that the player can use to buy new items,
     * adds it to the shopitems array and increases the number of items
     * 
     * @param itemID   the id of the item wanting to be created,maps to a certian
     *                    item
     * @param priceOfItem the price of the item
     */

    public void createNewItem(int itemID, int priceOfItem) {
        NewItem item = new NewItem(itemID, priceOfItem);
        shopItems.add(item);
        numberOfNewItems++;
    }

    /**
     * Creates any initial upgrades or new items
     */

    public void createInitialUpgradesAndNewItems() {
        createNewItem(6, 0);
        createNewItem(7, 0);
        createNewItem(8, 0);
        createNewItem(9, 0);
        createNewItem(10, 0);
        createNewItem(11, 0);
        createNewItem(12, 0);
        createUpgradeItem(3, 0);
        createUpgradeItem(4, 0);
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
     * Sets up the sprites for the shop, the background image. amd the cycle arrows
     */

    public void setUpShopSprite() {
        shopSprite = createSprite("img/Shop.png");
        for (int i = 0; i < cycleArray.length; i++) {
            Sprite buttonSprite;
            if (i == 0 || i == 2) {
                buttonSprite = createSprite("img/LeftArrow.png");
            } else {
                buttonSprite = createSprite("img/RightArrow.png");
            }
            cycleArray[i] = buttonSprite;
        }
        backToBountyButton = createSprite("img/ToBountyButton.png");

    }

    /**
     * Sorts out all the locations for the sprites
     */

    public void sortSpriteLocations() {
        if (shopItems.size() != 0) {
            float currentXLocationUpgradeItem = 200;
            float currentYLocationUpgradeItem = 250;
            float currentXLocationNewItem = 600;
            float currentYLocationNewItem = 250;
            float spacingForButton = 250;
            float spacingForText = 80;
            int numberOfUpgradesSorted = 0;
            int numberOfNewItemsSorted = 0;
            for (int i = 0; i < shopItems.size(); i++) {
                ArrayList<Sprite> currentSpriteArrayList = shopItems.get(i).getSpriteImages();
                ArrayList<Text> currentTextArrayList = shopItems.get(i).getItemTexts();
                int id = shopItems.get(i).getiD();
                // 0 for upgrade item 1 for new item
                if (id == 0) {
                    currentSpriteArrayList.get(0).setPosition(currentXLocationUpgradeItem, currentYLocationUpgradeItem);
                    currentSpriteArrayList.get(1).setPosition(currentXLocationUpgradeItem + spacingForButton,
                            currentYLocationUpgradeItem + 20);
                    currentTextArrayList.get(0).setPosition(currentXLocationUpgradeItem + spacingForText,
                            currentYLocationUpgradeItem + 15);
                    currentTextArrayList.get(1).setPosition(currentXLocationUpgradeItem + spacingForText,
                            currentYLocationUpgradeItem + 35);
                    currentYLocationUpgradeItem = currentYLocationUpgradeItem + 100;
                    numberOfUpgradesSorted++;
                    if (numberOfUpgradesSorted == maxNumberInCycle) {
                        currentYLocationUpgradeItem = 250;
                        numberOfUpgradesSorted = 0;
                    }
                } else {
                    currentSpriteArrayList.get(0).setPosition(currentXLocationNewItem, currentYLocationNewItem);
                    currentSpriteArrayList.get(1).setPosition(currentXLocationNewItem + spacingForButton,
                            currentYLocationNewItem + 20);
                    currentTextArrayList.get(0).setPosition(currentXLocationNewItem + spacingForText,
                            currentYLocationNewItem + 15);
                    currentTextArrayList.get(1).setPosition(currentXLocationNewItem + spacingForText,
                            currentYLocationNewItem + 35);
                    currentYLocationNewItem = currentYLocationNewItem + 100;
                    numberOfNewItemsSorted++;
                    if (numberOfNewItemsSorted == maxNumberInCycle) {
                        currentYLocationNewItem = 250;
                        numberOfNewItemsSorted = 0;
                    }
                }
            }
        }
        cycleArray[0].setPosition(420, 600);
        cycleArray[1].setPosition(460, 600);
        cycleArray[2].setPosition(820, 600);
        cycleArray[3].setPosition(860, 600);
        backToBountyButton.setPosition(40, 40);
    }

    /**
     * Function to check if a button has been clicked
     * 
     * @param buttonToCheck takes a sprite to check
     * @return true if the sprite has been clicked
     */

    public boolean buttonIsClicked(Sprite buttonToCheck) {
        double xPos = Mouse.getPosition(cWindow).x;
        double yPos = Mouse.getPosition(cWindow).y;
        float minXLocation = buttonToCheck.getPosition().x;
        float minYLocation = buttonToCheck.getPosition().y;
        Vector2i sizeOfButton = buttonToCheck.getTexture().getSize();
        Vector2f scale = buttonToCheck.getScale();
        int xSize = (int) (sizeOfButton.x * scale.x);
        int ySize = (int) (sizeOfButton.y * scale.y);
        float maxXLocation = minXLocation + xSize;
        float maxYLocation = minYLocation + ySize;
        if ((xPos >= minXLocation && xPos <= maxXLocation && yPos >= minYLocation && yPos <= maxYLocation)) {
            return true;
        }
        return false;
    }

    private int currentUpperBoundCycleUpgrade = 4;
    private int currentUpperBoundCycleNewItem = 4;
    private int currentLowerBoundCycleUpgrade = 0;
    private int currentLowerBoundCycleNewItem = 0;
    private int maxNumberInCycle = 4;

    /**
     * Sets the bounds of the shown items to only show the right items
     * 
     * @param direction the direction to cycle the items
     * @param type      whether to cycle the upgrade items or the new items
     */

    public void cycleThroughItems(int direction, int type) {
        // direction 0 left 1 right
        // type 0 cycle through upgrade 1 cycle through new items
        if (type == 0) {
            if (direction == 0) {
                if (currentLowerBoundCycleUpgrade - maxNumberInCycle >= 0) {
                    currentLowerBoundCycleUpgrade = currentLowerBoundCycleUpgrade - 4;
                    currentUpperBoundCycleUpgrade = currentUpperBoundCycleUpgrade - 4;
                    setAllDisplayedToFalse(0);
                }
            } else {
                if (currentUpperBoundCycleUpgrade + maxNumberInCycle < numberOfUpgradeItems + maxNumberInCycle) {
                    currentUpperBoundCycleUpgrade = currentUpperBoundCycleUpgrade + 4;
                    currentLowerBoundCycleUpgrade = currentLowerBoundCycleUpgrade + 4;
                    setAllDisplayedToFalse(0);
                }
            }
        } else {
            if (direction == 0) {
                if (currentLowerBoundCycleNewItem - maxNumberInCycle >= 0) {
                    currentLowerBoundCycleNewItem = currentLowerBoundCycleNewItem - 4;
                    currentUpperBoundCycleNewItem = currentUpperBoundCycleNewItem - 4;
                    setAllDisplayedToFalse(1);
                }
            } else {
                if (currentUpperBoundCycleNewItem + maxNumberInCycle < numberOfNewItems + maxNumberInCycle) {
                    currentUpperBoundCycleNewItem = currentUpperBoundCycleNewItem + 4;
                    currentLowerBoundCycleNewItem = currentLowerBoundCycleNewItem + 4;
                    setAllDisplayedToFalse(1);
                }
            }
        }
    }

    /**
     * Sets the isDisplayed field in the items to false
     * 
     * @param id whether to use the upgrade or new items
     */

    public void setAllDisplayedToFalse(int id) {
        for (int i = 0; i < shopItems.size(); i++) {
            if (shopItems.get(i).getiD() == id) {
                shopItems.get(i).setIsDisplayed(false);
            }
        }
    }


    /**
     * When a button is clicked, checks to see which sprite has been clicked, calls
     * appropriate method
     * @return backButtonClicked returns true if it should go back to the bounty board
     */

    public boolean buttonPress(int playerThatClicked) {
        boolean backButtonclicked = false;
        if (shopItems.size() != 0) {
            for (int i = 0; i < shopItems.size(); i++) {
                if (buttonIsClicked(shopItems.get(i).getSpriteImages().get(1)) == true && shopItems.get(i).isDisplayed) {
                    ShopItem currentItem = shopItems.get(i);
                    if(currentItem.canPlayerBuy(players[playerThatClicked].getPlayerGold().getcurrentGold())){
                        currentItem.givePlayerPurchase(players[playerThatClicked]);
                        players[playerThatClicked].getPlayerGold().addMoney(-(currentItem.getPrice()));
                    }
                }
            }
        }

        if(buttonIsClicked(backToBountyButton)){
            backButtonclicked = true;
        }

        for (int i = 0; i < cycleArray.length; i++) {
            if (buttonIsClicked(cycleArray[i]) == true) {
                switch (i) {
                    case 0:
                        cycleThroughItems(0, 0);
                        break;
                    case 1:
                        cycleThroughItems(1, 0);
                        break;

                    case 2:
                        cycleThroughItems(0, 1);
                        break;
                    case 3:
                        cycleThroughItems(1, 1);
                        break;
                }
            }
        }
        return backButtonclicked;
    }

    /**
     * Draws all of the sprites onto the render window
     */

    public void drawShop() {
        cWindow.draw(shopSprite);
        cWindow.draw(backToBountyButton);
        if (shopItems.size() != 0) {
            int numberOfNewUpgradesDrawn = 0;
            int numberOfNewItemsDrawn = 0;
            ArrayList<ShopItem> upgrades = new ArrayList<ShopItem>();
            ArrayList<ShopItem> items = new ArrayList<ShopItem>();
            for (int i = 0; i < shopItems.size(); i++) {
                if (shopItems.get(i).getiD() == 1) {
                    items.add(shopItems.get(i));
                } else {
                    upgrades.add(shopItems.get(i));
                }
            }
            if (upgrades.size() != 0) {
                for (int i = 0; i < upgrades.size(); i++) {
                    if (i >= currentLowerBoundCycleUpgrade && i < currentUpperBoundCycleUpgrade) {
                        upgrades.get(i).drawSprites(cWindow);
                        upgrades.get(i).setIsDisplayed(true);
                    }
                }
            }
            if (items.size() != 0) {
                for (int i = 0; i < items.size(); i++) {
                    if (i >= currentLowerBoundCycleNewItem && i < currentUpperBoundCycleNewItem) {
                        items.get(i).drawSprites(cWindow);
                        items.get(i).setIsDisplayed(true);
                    }
                }
            }
        }
        for (int i = 0; i < cycleArray.length; i++) {
            cWindow.draw(cycleArray[i]);
        }

        playersMoney[0] = shopItems.get(0).createText(players[0].getPlayerName() + " :" + players[0].getPlayerGold().getcurrentGold(), 20, Color.WHITE);
        playersMoney[1] = shopItems.get(0).createText(players[1].getPlayerName() + " :" + players[1].getPlayerGold().getcurrentGold(), 20, Color.WHITE);
        playersMoney[0].setPosition(400,10);
        playersMoney[1].setPosition(550,10);
        cWindow.draw(playersMoney[0]);
        cWindow.draw(playersMoney[1]);
    }

    /**
     * Changes the window to apply the graphics too
     * @param nWindow  the window to change to
     */

    public void setcWindow(RenderWindow nWindow){
        cWindow = nWindow;
    }
}