package toTheKing;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;


public class SystemActive {
    protected Player[] players = new Player[2]; // Player One and Two
    protected RenderWindow window; // The game window
    protected LevelWindow[] levelWindow = new LevelWindow[10]; // All 15 levels in the game
    protected GUIWindow startMenu; // Start menu
    protected EscapeMenu escMenu; // Esc menu
    protected int currentStage; // the current stage of the window
    protected int currentLevel = 0; // the current game level
    float frame = 6;
    protected Enemy enemy;
    char dir = 'F';
    boolean nextLevel = false;
    protected Board bountyBoard;
    protected EndGame endGame;
    protected int[] enemy2 = {0, 11, 7, 8, 3, 4, 1, 10, 2, 12 };
    protected Shop shop;
    private String[] names = {"adam", "laverd", "highorc", "commander", "king"};
    private int currentBoard = 0;



    /**
     * System Active constructor, initialises all needed windows for the game
     */
    public SystemActive() {
        for (int i = 0; i < 10; i++)
            levelWindow[i] = new LevelWindow(i);

        startMenu = new GUIWindow(1, "To the King!");
        escMenu = new EscapeMenu(3, "Paused!");
        endGame = new EndGame();
        players[0] = new Player(0, 100, 10, 10, 5, true);
        players[1] = new Player(1, 100, 10, 10, 5, true);
        players[0].initPlayers();
        players[1].initPlayers();
    }

    /**
     * Loads the current window view based on the fed in number
     *
     * @param stageWindow current stage of the window
     */
    protected void viewLoader(int stageWindow) {

        switch (stageWindow) {
            case 0: // Start
                this.currentStage = stageWindow;
                window = startMenu.createGUIWindow();
                startMenu.loadStartTextures();
                startMenu.setUpGUIButtons();
                this.startUpMenu(startMenu);
                break;

            case 1: // Level
                window.close();
                this.currentStage = stageWindow;
                window = levelWindow[currentLevel].createLevel();
                createInstancePlayers();
                players[0].revivePlayer(levelWindow[currentLevel]);
                players[1].revivePlayer(levelWindow[currentLevel]);
                createInstanceLevel();
                System.out.println(levelWindow[currentLevel].getLevelComplete());
                this.startUpLevel();
                break;

            case 2: // Escape Menu
                this.currentStage = stageWindow;
                window = escMenu.createGUIWindow();
                escMenu.loadStartTextures();
                escMenu.setUpGUIButtons();
                this.startUpMenu(escMenu);
                break;

            case 3: // Closes the window
                window.close();
                break;

            case 4: // Bounty Menu
                this.currentStage = stageWindow;
                window = startMenu.createGUIWindow();
                window.clear();
                if(bountyBoard == null){
                    bountyBoard = new Board(window);
                }
                bountyBoard.createBounty(names[currentBoard], 0, "");
                currentBoard++;
                bountyBoard.setcWindow(window);
                startUpBountyBoard(bountyBoard);
                break;
            case 5:
                window = startMenu.createGUIWindow();
                window.clear();
                createInstancePlayers();
                if(shop == null){
                    shop = new Shop(window, players);
                }
                shop.setcWindow(window);
                startUpShop(shop);

            case 6:
                this.currentStage = stageWindow;
                window = endGame.createEndWindow();
                endGame.loadEndGameTexture();
                this.endGame();
                break;

        }


    }

    protected void endGame() {
        while (window.isOpen()) {
            endGame.drawEnd(window);
            window.display();

            for (Event event : window.pollEvents()) {
                switch (event.type) {
                    case CLOSED:
                    case KEY_PRESSED:
                        window.close();
                        break;
                }
            }
            window.clear();
        }
    }

    /**
     * Placeholder ready for the bounty board while loop
     */
    protected void startUpBountyBoard(Board board) {
        while (window.isOpen()) {
            window.display();
            board.drawBoard();
            for (Event event : window.pollEvents()) {
                // this.mousePress(event, guiWindow);
                if (Keyboard.isKeyPressed(Keyboard.Key.LEFT) || Mouse.isButtonPressed(Button.LEFT)) {
                    String goShop = board.buttonPress();
                    if(goShop.equals("shop")){
                        window.close();
                        viewLoader(5);
                    }
                    switch (goShop){
                        case "adam":
                        case "laverd":
                        case "highorc":
                        case "commander":
                        case "king":
                            viewLoader(1);
                            break;
                    }

                }
                switch (event.type) {
                    case CLOSED:
                        window.close();
                        break;
                }
            }

        }
    }

    protected  void startUpShop(Shop shop){
        while (window.isOpen()) {
            window.display();
            shop.drawShop();
            // Handle events
            for (Event event : window.pollEvents()) {
                // this.mousePress(event, guiWindow);
                if (Keyboard.isKeyPressed(Keyboard.Key.LEFT) || Mouse.isButtonPressed(Button.LEFT)) {
                    boolean goBack = shop.buttonPress(0);
                    if(goBack == true){
                        window.close();
                        viewLoader(1);
                    }
                }
                if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT) || Mouse.isButtonPressed(Button.LEFT)) {
                    boolean goBack = shop.buttonPress(1);
                    if(goBack == true){
                        window.close();
                        viewLoader(1);
                    }
                }
            }
        }
    }

    /**
     * Loop responsible for maintaining any menu built using GUIWindow
     *
     * @param guiWindow which instance of this class it is currently using
     */
    protected void startUpMenu(GUIWindow guiWindow) {

        while (window.isOpen()) {
            guiWindow.drawMenu(window);
            guiWindow.createGUIButtons();
            window.display();

            // Handle events
            for (Event event : window.pollEvents()) {
                this.mousePress(event, guiWindow);
                switch (event.type) {
                    case CLOSED:
                        window.close();
                        break;
                }
            }
            window.clear();
        }
    }

    /**
     * Starts up the level based off of the current level, and loads players onto it
     */
    public void startUpLevel() {

        // Main loop
        while (window.isOpen()) {

            levelWindow[currentLevel].drawLevel();
            players[0].createPlayer(window);
            players[1].createPlayer(window);
            levelWindow[currentLevel].updatePlayerInfo(players[0]);
            levelWindow[currentLevel].updatePlayerInfo(players[1]);
            levelWindow[currentLevel].updateEnemyInfo(enemy);
            enemy.createEnemy(window);
            enemy.moveTowardsPlayer();
            boolean check = levelWindow[currentLevel].checkDeath(players);

            if (check)
            {
                viewLoader(currentStage);
            }
            if (levelWindow[currentLevel].getLevelComplete() && nextLevel)
            {
                winCheck();
            }

            window.display();

            // Handle events
            for (Event event : window.pollEvents()) {
                this.keyPress(event);

            }
            window.clear();
        }
    }


    /**
     * Loads all instances for players
     */
    protected void createInstancePlayers() {
        enemy = new Enemy(enemy2[currentLevel], players[0], players[1]);
        enemy.initEnemy();

    }

    /**
     * Loads all instances for the levels
     */
    protected void createInstanceLevel() {
        levelWindow[currentLevel].loadLevelTextures();
        levelWindow[currentLevel].displayPlayerInfo(players[0]);
        levelWindow[currentLevel].displayPlayerInfo(players[1]);
        levelWindow[currentLevel].displayEnemyInfo(enemy);
    }

    /**
     * Handles mouse event pages only
     *
     * @param event     the event which occurred
     * @param guiWindow the current GUI window being edited (mouse events only occur
     *                  on)
     */
    public void mousePress(Event event, GUIWindow guiWindow) {
        switch (event.type) {
            case CLOSED:
                window.close();
                break;
            case MOUSE_BUTTON_PRESSED:
                if (guiWindow != null) {
                    int temp = guiWindow.buttonPress();
                    if (temp != 0 && temp != currentStage && temp != 3) {
                        window.close();
                        viewLoader(4);
                    }
                    if (temp == 3) {
                        window.close();
                    }
                    break;
                }

        }
    }


    /**
     * Handles any keyboard events (only possible on levels)
     *
     * @param event the event which occurred
     */
    public void keyPress(Event event) {
        float intensityB = 0;

        switch (event.type) {
            case CLOSED:
                window.close();
                break;
            case KEY_PRESSED:
                if (Keyboard.isKeyPressed(Keyboard.Key.RCONTROL)) {
                    players[0].potUsage();
                }
                if (Keyboard.isKeyPressed(Keyboard.Key.Q)) {
                    players[1].potUsage();
                }
                if (Keyboard.isKeyPressed(Keyboard.Key.LEFT) && players[0].getPlayerStatus()) {
                    nextLevel = players[0].setPlayerPosition(-this.players[0].playerSpeed, 0, dir = 'B', levelWindow[currentLevel]);
                    frame = 0;
                }
                if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT) && players[0].getPlayerStatus()) {
                    nextLevel = players[0].setPlayerPosition(this.players[0].playerSpeed, 0, dir = 'F', levelWindow[currentLevel]);
                    frame = 0;
                }
                if (Keyboard.isKeyPressed(Keyboard.Key.A) && players[1].getPlayerStatus()) {
                    nextLevel = players[1].setPlayerPosition(-this.players[1].playerSpeed, 0, dir = 'B', levelWindow[currentLevel]);
                    frame = 0;

                }
                if (Keyboard.isKeyPressed(Keyboard.Key.D) && players[1].getPlayerStatus()) {
                    nextLevel = players[1].setPlayerPosition(this.players[1].playerSpeed, 0, dir = 'F', levelWindow[currentLevel]);

                    frame = 0;

                }
                if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)) {
                    window.close();
                    viewLoader(2);
                }

                if (Keyboard.isKeyPressed(Keyboard.Key.W) && players[1].getPlayerStatus()) {
                    players[1].animateBow(5, dir);

                }

                if (Keyboard.isKeyPressed(Keyboard.Key.S) && players[1].getPlayerStatus()) {
                    players[1].animateBow(6, dir);
                }

                if (Keyboard.isKeyPressed(Keyboard.Key.UP) && players[0].getPlayerStatus()) {

                    if (frame < 6)
                        frame = 6;
                    if (frame > 8)
                        frame = 8;

                    frame++;
                    players[0].animateBow(frame, dir);
                }
                if (Keyboard.isKeyPressed(Keyboard.Key.DOWN) && players[0].getPlayerStatus()) {
                    if (frame < 6) {
                        frame = 6;
                    }
                    frame--;
                    players[0].animateBow(frame, dir);
                }

                if (Keyboard.isKeyPressed(Keyboard.Key.RSHIFT) && players[0].getPlayerStatus()) {
                    if (frame < 5)
                        frame = 5;
                    intensityB = frame - (5 / 2);
                    frame = 5;
                    players[0].animateBow(frame, dir);
                    players[0].shootBow(enemy, intensityB, dir);
                }

                if (Keyboard.isKeyPressed(Keyboard.Key.SPACE) && players[1].getPlayerStatus()) {
                    if (frame < 5)
                        frame = 5;
                    intensityB = frame - (5 / 2);
                    frame = 5;
                    players[1].animateBow(frame, dir);
                    players[1].shootBow(enemy, intensityB, dir);
                }
                // TODO: Implement enter and shift for bow release. Space and ctrl for jumping
                break;
        }

        if (levelWindow[currentLevel].getLevelComplete() && nextLevel)
        {
            winCheck();
        }

    }

    /**
     * Temp void to just check the levels are still working, toggled with S.
     * Removing in implementation
     */
    protected void tempLevelScroll(int stageWindow) {
        window.close();
        levelWindow[currentLevel].setLevelComplete(false);
        nextLevel = false;
        currentLevel++;
        viewLoader(stageWindow);
    }

    protected void winCheck()
    {
        if (currentLevel == 9)
        {
            window.close();
            nextLevel = false;
            viewLoader(6);
            players[0].getPlayerGold().addMoney(10000);
            players[1].getPlayerGold().addMoney(10000);

        }
        else if (!(currentLevel % 2 == 0))
        {
            tempLevelScroll(4);
            int reward = 0;
            if(currentLevel == 1){
                reward = 1500;
            }else if(currentLevel == 3){
                reward = 2200;
            }
            else if(currentLevel == 5){
                reward = 3500;
            }
            else if(currentLevel == 7){
                reward = 5000;
            }
            players[0].getPlayerGold().addMoney(reward);
            players[1].getPlayerGold().addMoney(reward);
        }
        else
        {
            tempLevelScroll(1);
        }
    }

}
