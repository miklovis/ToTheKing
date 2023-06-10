package toTheKing;

import org.jsfml.window.Mouse;

public class EscapeMenu extends GUIWindow {
    /**
     * Creates the GUI for given values by the game. Initialises how many buttons
     * will be created too
     *
     * @param buttonCount
     * @param guiName
     */
    public EscapeMenu(int buttonCount, String guiName) {
        super(buttonCount, guiName);
    }

    /**
     * Handles the button press for the Escape menu, and overrides the default
     * 
     * @return
     */
    protected int buttonPress() {
        double xPos = Mouse.getPosition(guiWindow).x;
        double yPos = Mouse.getPosition(guiWindow).y;
        if (xPos > 440 && xPos < 640) {
            double yStart = 350;
            double yStart2 = 400;

            for (int i = 0; i < buttonCount; i++) {
                if (yPos > yStart && yPos < yStart2) {
                    switch (i) {
                        case 0:
                            return 1;
                        case 1:
                            System.out.println("Non programmed save button");
                            return 0;
                        case 2:
                            return 3;
                    }
                }
                yStart += 70;
                yStart2 += 70;
            }

        }
        return 0;
    }

}
