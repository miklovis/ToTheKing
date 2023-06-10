package toTheKing;

import org.jsfml.graphics.*;
import org.jsfml.system.Clock;

import java.io.IOException;
import java.nio.file.Paths;

public class Player {

    protected String[] playerName = { "Oswald", "William" }; // Players current character name
    protected int playerNumber; // Players current number value (0 or 1)
    protected double playerHealth; // Players current health value
    protected int playerArmor; // Players current armor value
    protected int playerSpeed; // Players current speed value
    protected int playerAttack; // Players current attack value
    protected boolean playerStatus; // The current status state of player
    protected Sprite player; // Sprite of play
    protected Texture playerTexture; // Texture of current player
    protected double frame = 0; // Current texture frame to display
    protected int textF = 100; // Textures frame width, used for mathematical scaling
    protected char tempDir = 'F'; // Latest stored direction character is facing
    protected IntRect current; // Current IntRect of the seen texture on the player
    protected double currentScaleHealth;
    protected int temp = this.playerArmor;
    protected int originX;
    protected int originY;
    protected Clock hitTimer;
    protected Armour playerArmour;
    protected Gold playerMoney;
    protected Consumables consumables;


    /**
     * Creates instance of player which can be used within all classes
     *
     * @param playerNumber Current number value for identification
     * @param playerHealth Players set health value
     * @param playerArmor  Players set Armor Value
     * @param playerSpeed  Players current speed
     * @param playerAttack Players attack rating
     * @param playerStatus Declares whether player is alive or not
     */
    public Player(int playerNumber, double playerHealth, int playerArmor, int playerSpeed, int playerAttack,
                  boolean playerStatus) {
        this.playerNumber = playerNumber;
        this.playerHealth = playerHealth;
        this.playerArmor = playerArmor;
        this.playerSpeed = playerSpeed;
        this.playerAttack = playerAttack;
        this.playerStatus = playerStatus;
        hitTimer = new Clock();
        playerMoney = new Gold(5000, this);
        playerArmour = new Armour(this, 100, 100, 100);
        this.consumables = new Consumables(this);
    }


    /**
     * Method to initialise the enemy and its textures
     */
    public void initPlayers() {
        switch (this.playerNumber) {
            case 0: // Oswald
                playerTexture = new Texture();
                try {
                    playerTexture.loadFromFile(Paths.get("img/NewArmour/POneBase.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player = new Sprite(playerTexture);
                originX = 100;
                originY = 510;
                player.setPosition(originX, originY);

                break;
            case 1: // William
                playerTexture = new Texture();
                try {
                    playerTexture.loadFromFile(Paths.get("img/NewArmour/PTwoBase.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player = new Sprite(playerTexture);
                originX = 200;
                originY = 510;
                player.setPosition(originX, originY);

                break;
        }
        player.setTextureRect(current = new IntRect(0, 0, 100, 150));
        consumables.setPot("smallHealth");
    }


    /**
     * Method to draw player onto the window, able to be recursively called
     *
     * @param window Window onto which to draw the Player
     */
    public void createPlayer(RenderWindow window) {
        if (this.playerStatus)
        {
            window.draw(player);
            consumables.consumableCheck();
        }
    }

    public void potUsage()
    {
        consumables.usePotions();
    }

    /**
     * Sets the current players position, and the direction they are facing
     *
     * @param v   X movement
     * @param v1  Y movement
     * @param Dir Direction the player is facing
     */
    public boolean setPlayerPosition(float v, float v1, char Dir, LevelWindow levelWindow) {

        float tempWidth = 100; // Width value for the sprite
        float tempBoundLW = 0; // LEFT bounds
        float tempBoundRW = 1080; // RIGHT bounds

        float xPos = this.player.getPosition().x;
        float yPos = this.player.getPosition().y;

        if ((xPos >= tempBoundLW) && (xPos <= tempBoundRW - tempWidth)) {
            this.player.move(v, v1);
            this.animateWalkingForward(Dir);
        } else {

            if (xPos < tempBoundLW) {
                this.player.setPosition(tempBoundLW + 10, yPos);
            }
            if (xPos > tempBoundRW - tempWidth && !levelWindow.getLevelComplete()) {
                this.player.setPosition(tempBoundRW - tempWidth, yPos);
            }
            if (xPos > tempBoundRW - tempWidth && levelWindow.getLevelComplete()) {
                this.player.setPosition(originX, originY);
                return true;
            }

        }
        return false;
    }

    /**
     * Animates bow for when drawing back, or returning to neutral
     *
     * @param feed The current state of bow
     * @param Dir  The direction to animate facing
     */
    public void animateBow(double feed, char Dir) {
        int i1 = 0;
        if (Dir == 'B')
            i1 = 150;
        player.setTextureRect(current = new IntRect((int) (100 * feed), i1, 100, 150));
    }

    /**
     * Changes texture to reflect current movement
     *
     * @param Dir The direction to animate facing
     */
    public void animateWalkingForward(char Dir) {
        if (tempDir != Dir)
            frame = Math.round(frame);
        if (frame == 5)
            frame = 0;
        int i1 = 0;
        if (Dir == 'B')
            i1 = 150;
        if (frame % 1 == 0) {
            int temp = (int) (textF * frame);
            player.setTextureRect(current = new IntRect(temp, i1, 100, 150));

        }
        frame += 0.5;
    }

    /**
     * Hits the player based on fed damage and armor factored in
     *
     * @param damage The damage dealt by the enemy
     */
    public void playerHit(int damage) {
        if (this.playerArmor == temp)
            currentScaleHealth = this.playerHealth + this.playerArmor;
        else
            currentScaleHealth = this.playerHealth;
        this.playerHealth = (double) Math.round((currentScaleHealth - damage) * 100) / 100;
        if (this.playerArmor > 0)
            this.playerArmor -= 0.5;
    }

    /**
     * Hits based on distance and direction, using B and F to check direction arrow
     * goes "through" opponent As well, adds distance punishment system to stop
     * players being able to just spam high level arrows
     *
     * @param enemy     Enemy currently being aimed at
     * @param intensity Intensity of the bow being shot
     * @param dir       Direction in which the player is facing
     */
    public void shootBow(Enemy enemy, float intensity, char dir) {

        if (hitTimer.getElapsedTime().asSeconds() > 1) {
            hitTimer.restart();
            float enemyLPos = enemy.enemy.getPosition().x;
            float enemyRPos = enemyLPos + enemy.enemyTexture.getSize().x;
            float playerLPos = player.getPosition().x;
            float playerRPos = playerLPos + this.getPlayerWidth();
            float arrowPosition = +(this.distanceCalculator(intensity, dir) + playerRPos);

            switch (dir) {
                case 'F':
                    if (arrowPosition >= enemyLPos && arrowPosition <= (enemyRPos + 50)) {
                        this.hitEnemy(enemy, 100);
                    } else if (arrowPosition >= enemyLPos && arrowPosition <= enemyRPos + 100) {
                        this.hitEnemy(enemy, 70);
                    } else if (arrowPosition >= enemyLPos && arrowPosition <= enemyRPos + 170) {
                        this.hitEnemy(enemy, 40);
                    }
                    break;
                case 'B':
                    if (arrowPosition <= enemyRPos && arrowPosition >= enemyLPos - 50) {
                        this.hitEnemy(enemy, 100);
                    } else if (arrowPosition <= enemyRPos && arrowPosition >= enemyLPos - 100) {
                        this.hitEnemy(enemy, 70);
                    } else if (arrowPosition <= enemyRPos && arrowPosition >= enemyLPos - 170) {
                        this.hitEnemy(enemy, 40);
                    }
                    break;
            }
        }

    }

    /**
     * Registers the new values for the enemies health, and the damage inflicted.
     *
     * @param enemy      The enemy that has taken damage
     * @param percentage The percentage hit that is to be applied
     */
    public void hitEnemy(Enemy enemy, double percentage) {
        double dmg = this.playerAttack * percentage / 100;
        double hploss = (double) Math.round((enemy.getCurrentHP() - dmg) * 100) / 100;
        enemy.setCurrentHP(hploss);
        enemy.enemyHit(this);

    }

    /**
     * Calculates the distance in which the bow has been shot
     *
     * @param intensity Intensity level of the bow
     * @param dir       Direction in which the arrow needs to fire
     * @return The arrows hit coordinates
     */
    public float distanceCalculator(float intensity, char dir) {
        switch (dir) {
            case 'B':
                return -(intensity * 100);
            case 'F':
                return intensity * 100;
        }
        return 0;
    }

    /**
     * Returns the characters names
     *
     * @return Character name
     */
    public String getPlayerName() {
        return playerName[playerNumber];
    }

    /**
     * Returns the player number
     *
     * @return player number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Returns the player number
     *
     * @return player number
     */
    public Consumables getPotion() {
        return consumables;
    }


    public Gold getPlayerGold() {
        return playerMoney;
    }

    /**
     * Returns player armor value
     *
     * @return player armor
     */
    public int getPlayerArmor() {
        return playerArmor;
    }

    /**
     * Returns current players health
     *
     * @return player health
     */
    public double getPlayerHealth() {
        return playerHealth;
    }

    /**
     * Returns the current player status
     *
     * @return player status
     */
    public boolean getPlayerStatus() {
        return playerStatus;
    }

    /**
     * Sets current player status
     *
     * @param playerStatus player status
     */
    public void setPlayerStatus(boolean playerStatus) {
        this.playerStatus = playerStatus;
    }

    /**
     * Returns players armour class
     *
     * @return player armour
     */
    public Armour getArmour() {
        return playerArmour;
    }

    /**
     * Returns player attack rating
     *
     * @return player attack
     */
    public int getPlayerAttack() {
        return playerAttack;
    }

    /**
     * Sets the attack of the player to the input
     *
     * @param newAttack the new value of player attack
     */
    public void setPlayerAttack(int newAttack) {
        playerAttack = newAttack;
    }

    /**
     * Sets the health of the player to the input
     *
     * @param newHealth the new value of player health
     */
    public void setPlayerHealth(double newHealth) {
        System.out.println(newHealth);

        this.playerHealth = newHealth;
        System.out.println(this.playerHealth);

    }

    /**
     * Returns width of the sprites current display
     *
     * @return width of player
     */
    public float getPlayerWidth() {
        return this.current.width;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    public void setPlayerArmour(String playerArmour) {

        player = this.playerArmour.getArmourSprite();
        player.setTextureRect(current);
                if (playerNumber == 0){
                    originX = 100;
                    originY = 510;
                    player.setPosition(originX, originY);
                }
                if (playerNumber == 1) {
                    originX = 200;
                    originY = 510;
                    player.setPosition(originX, originY);
                }


    }
}
