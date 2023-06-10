package toTheKing;

import org.jsfml.graphics.*;
import org.jsfml.window.*;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * The type Level window.
 */
public class LevelWindow {

    protected int levelNumber; // current level
    protected static RenderWindow levelWindow; // the rendered level window
    protected Sprite[] playerPhoto = new Sprite[2]; // the two player photos for the ui
    protected Texture levelTexture; // the levels background
    protected Texture[] levelGui = new Texture[2]; // texture for the playerphotos / gui
    protected Sprite onscreenLevel; // sprite for level background

    protected Font font; // the text font
    protected Text[] textInfo0 = new Text[4]; // text displayed for player 0
    protected Text[] textInfo1 = new Text[4]; // text displayed for player 1
    protected Text enemyTextHP;

    protected Color[] infoColor = { Color.BLACK, Color.GREEN, Color.RED, Color.BLUE }; // the color order for the text
    protected String[] stringInfo = new String[4]; // the information stored on the text
    protected Boolean levelComplete = false;

    protected Boolean[] alive = new Boolean[2];
    protected IntRect current;
    protected IntRect current1;



    /**
     * Constructor for the level, sets the known level number
     *
     * @param levelNumber level game is currently on
     */
    public LevelWindow(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Boolean getLevelComplete() {
        return levelComplete;
    }

    public void setLevelComplete(Boolean levelComplete) {
        this.levelComplete = levelComplete;
    }

    /**
     * Creates the level render for each level
     * 
     * @return the rendered level
     */
    protected RenderWindow createLevel() {
        // Creates the level
        levelWindow = new RenderWindow();
        levelWindow.create(new VideoMode(1080, 720), "Level");
        levelWindow.setFramerateLimit(30);
        return levelWindow;
    }

    /**
     * Added for the loop to consistently draw elements as needed on the game
     */
    protected void drawLevel() {
        levelWindow.draw(onscreenLevel);
        levelWindow.draw(playerPhoto[0]);
        levelWindow.draw(playerPhoto[1]);

        for (int i = 0; i < 4; i++)
            levelWindow.draw(textInfo0[i]);

        for (int i = 0; i < 4; i++)
            levelWindow.draw(textInfo1[i]);

    }

    /**
     * All needed textures loaded when required
     */
    protected void loadLevelTextures() {

        levelTexture = new Texture();
        try {
            levelTexture.loadFromFile(Paths.get("img/level/BG" + levelNumber + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        onscreenLevel = new Sprite(levelTexture);

        levelGui[0] = new Texture();
        try {
            levelGui[0].loadFromFile(Paths.get("img/levelHeads/POneHeads.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        levelGui[1] = new Texture();
        try {
            levelGui[1].loadFromFile(Paths.get("img/levelHeads/PTwoHeads.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        playerPhoto[0] = new Sprite(levelGui[0]);
        playerPhoto[0].setPosition(30, 30);
        playerPhoto[0].setTextureRect(current = new IntRect(0,0,182,182));

        playerPhoto[1] = new Sprite(levelGui[1]);
        playerPhoto[1].setPosition(860, 30);
        playerPhoto[1].setTextureRect(current1 = new IntRect(0,0,182,182));


        font = new Font();
        try {
            font.loadFromFile(Paths.get("font/theboldfont.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the information and allows us to show health
     *
     * @param enemy the enemy referred to in display
     */
    protected void displayEnemyInfo(Enemy enemy)
    {
        float enemyX = enemy.enemy.getPosition().x;
        float enemyY = enemy.enemy.getPosition().y;
        String enemyHP = enemy.getCurrentHP() + "";

        enemyTextHP = new Text(enemyHP, font, 20);
        enemyTextHP.setColor(infoColor[1]);
        enemyTextHP.setPosition(enemyX, enemyY-25);
        levelWindow.draw(enemyTextHP);

    }

    /**
     * Updates enemies health information on each cycle
     *
     * @param enemy the enemy needing the update
     */
    protected void updateEnemyInfo(Enemy enemy)
    {
        if (enemy.getAliveStatus())
        {
        float enemyX = enemy.enemy.getPosition().x;
        float enemyY = enemy.enemy.getPosition().y;
        String enemyHP = enemy.getCurrentHP() + "";

        enemyTextHP.setPosition(enemyX, enemyY-25);
        enemyTextHP.setString(enemyHP);
        enemyTextHP.setColor(infoColor[1]);
        levelWindow.draw(enemyTextHP);
        this.enemyDeath(enemy);
        }
        else
        {
            enemyTextHP.setPosition(100, 500);
            enemyTextHP.setString("LEVEL COMPLETE!");
            enemyTextHP.setCharacterSize(50);
            enemyTextHP.setColor(infoColor[1]);
            levelWindow.draw(enemyTextHP);
        }
    }

    /**
     * Displays the information and allows us to update
     *
     * @param player which player requires creating
     *
     */
    protected void displayPlayerInfo(Player player) {

        int v1 = 30;
        stringInfo[0] = player.getPlayerName();
        stringInfo[1] = player.getPlayerHealth() + "";
        stringInfo[2] = player.getPlayerAttack() + "";
        stringInfo[3] = player.getPlayerArmor() + "";

        for (int i = 0; i < 4; i++) {
            switch (player.getPlayerNumber()) {
                case 0:
                    textInfo0[i] = new Text(stringInfo[i], font, 30);
                    textInfo0[i].setColor(infoColor[i]);
                    textInfo0[i].setPosition(220, v1);
                    v1 += 35;
                    break;

                case 1:
                    textInfo1[i] = new Text(stringInfo[i], font, 30);
                    textInfo1[i].setColor(infoColor[i]);
                    textInfo1[i].setPosition(735, v1);
                    v1 += 35;
                    break;
            }

        }

    }

    /**
     * Updates the player info based upon detail given
     *
     * @param player the player which needs updating
     */
    protected void updatePlayerInfo(Player player) {

        stringInfo[0] = player.getPlayerName();
        stringInfo[1] = player.getPlayerHealth() + "";
        stringInfo[2] = player.getPlayerAttack() + "";
        stringInfo[3] = player.getPlayerArmor() + "";

        switch (player.getPlayerNumber()) {
            case 0:
                if (player.getPlayerStatus()) {

                    for (int i = 0; i < 4; i++) {
                        textInfo0[i].setString(stringInfo[i]);
                        levelWindow.draw(textInfo0[i]);
                    }
                    playerDeath(player);
                }
                break;
            case 1:
                if (player.getPlayerStatus()) {
                    for (int i = 0; i < 4; i++) {
                        textInfo1[i].setString(stringInfo[i]);
                        levelWindow.draw(textInfo1[i]);
                    }
                    playerDeath(player);
                }
                break;
        }

    }

    /**
     * Depending on the player fed in, we check if they're currently alive. If not, then
     * their status is cleared and they are removed from the playing field
     * @param player the player updated
     */
    protected void playerDeath(Player player) {
        if (player.getPlayerHealth() < 0) {
            switch (player.getPlayerNumber()) {
                case 0:
                        for (int i = 0; i < 4; i++) {
                            textInfo0[i].setString("D E A D");
                            levelWindow.draw(textInfo0[i]);
                        }
                        alive[0] = false;
                        player.setPlayerStatus(false);

                    break;
                case 1:
                        for (int i = 0; i < 4; i++) {
                            textInfo1[i].setString("D E A D");
                            levelWindow.draw(textInfo1[i]);
                        }
                        alive[1] = false;
                        player.setPlayerStatus(false);

                    break;
                }
        }

    }

    /**
     * We check if the enemy has been defeated, upon so, we disable it and then it is
     * removed from the screen and disabled from use.
     * @param enemy the enemy killed
     */
    protected void enemyDeath(Enemy enemy) {
        if (enemy.getCurrentHP() <= 0) {
            enemy.setAliveStatus(false);
            levelComplete = true;
        }
    }


    protected boolean checkDeath(Player[] player)
    {
        return !(player[0].getPlayerStatus() && player[1].getPlayerStatus());
    }
}
