package toTheKing;

import org.jsfml.graphics.*;
import org.jsfml.window.*;

import java.io.IOException;
import java.nio.file.Paths;

public class GUIWindow {

    protected int buttonCount; // Amount of buttons GUI has. MAX 4
    protected String guiName; // GUI display name

    protected static RenderWindow guiWindow; // Window for GUI
    protected Sprite[] guiButtons; // Rectangle buttons for GUI

    protected Texture[] buttonTextures = new Texture[4];
    protected Texture menu;
    protected Sprite onscreenMenu;


    protected Texture escMenu;
    protected Sprite escGame;

    /**
     * Creates the GUI for given values by the game. Initialises how many buttons
     * will be created too
     *
     */
    public GUIWindow(int buttonCount, String guiName) {
        this.buttonCount = buttonCount;
        this.guiName = guiName;
        guiButtons = new Sprite[buttonCount];
    }



    public void loadStartTextures()
    {
        menu = new Texture();
        try {
            menu.loadFromFile(Paths.get("img/level/homescreen.png"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        onscreenMenu = new Sprite(menu);
    }



    public void drawMenu(RenderWindow window)
    {
        window.draw(onscreenMenu);
    }
    /**
     * Creates the rendered GUI for the game.
     *
     * @return rendered GUI window
     */

    protected RenderWindow createGUIWindow() {
        guiWindow = new RenderWindow();
        guiWindow.create(new VideoMode(1080, 720), guiName);
        guiWindow.setFramerateLimit(30);
        return guiWindow;
    }

    /**
     * Populates the GUI with buttons VIA loop
     */
    protected void setUpGUIButtons() {
        int v1Pos = 350;
        for (int i = 0; i < buttonCount; i++) {
            buttonTextures[i] = new Texture();
            try {
                buttonTextures[i].loadFromFile(Paths.get("img/button/GUIButton" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            guiButtons[i] = new Sprite(buttonTextures[i]);
            guiButtons[i].setPosition(440, v1Pos);
            v1Pos += 70;
            guiWindow.draw(guiButtons[i]);
        }
    }

    /**
     * Creates the button for the refresh loop
     *
     */
    protected void createGUIButtons() {
        for (int i = 0; i < buttonCount; i++) {
            guiWindow.draw(guiButtons[i]);
        }

    }

    /**
     * Checks the location of the mouse on press and selects button where
     * appropriate
     */

    protected int buttonPress() {
        double xPos = Mouse.getPosition(guiWindow).x;
        double yPos = Mouse.getPosition(guiWindow).y;

        if (xPos > 440 && xPos < 640) {
            double yStart = 350;
            double yStart2 = 400;

            if (yPos > yStart && yPos < yStart2) {
                return 1;
            }
        }
        return 0;
    }

}
