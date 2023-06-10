package toTheKing;

import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import java.io.IOException;
import java.util.Random;
import java.nio.file.Paths;

public class Enemy {
    private int enemyType;
    private double currentHP;
    private int attackDamage;
    private int attackRange;
    private int movementSpeed;
    private boolean aliveStatus;
    private Clock hitTimer;
    private int maxWidth = 100;


    Sprite enemy;

    Player playerOne;
    Player playerTwo;

    Texture enemyTexture;

    public Enemy(Player playerOne, Player playerTwo) {
        Random rand = new Random();
        this.enemyType = rand.nextInt(11);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.hitTimer = new Clock();
    }

    public Enemy(int enemyType, Player playerOne, Player playerTwo) {
        this.enemyType = enemyType;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.hitTimer = new Clock();
    }

    /**
     * @return current HP of enemy
     */
    public double getCurrentHP() {
        return this.currentHP;
    }

    /**
     * @param currentHP double of HP to be set for enemy
     */
    public void setCurrentHP(double currentHP) {
        this.currentHP = currentHP;
    }

    /**
     * Method for depleting enemy's HP and knocking it back when it is hit.
     *
     * @param hittingPlayer the ID value of player hitting enemy. 0 - PlayerOne, 1 -
     *                      PlayerTwo
     */
    public void enemyHit(Player hittingPlayer) {
        this.currentHP -= hittingPlayer.getPlayerAttack();

        if (hittingPlayer.player.getPosition().x > enemy.getPosition().x) {
            enemy.setPosition(enemy.getPosition().x - 20 * movementSpeed, enemy.getPosition().y);
        } else if (hittingPlayer.player.getPosition().x < enemy.getPosition().x) {
            enemy.setPosition(enemy.getPosition().x + 20 * movementSpeed, enemy.getPosition().y);
        }

    }
    /**
     * Method to draw Enemy onto the window
     *
     * @param window Window onto which to draw Enemy
     */
    public void createEnemy(RenderWindow window) {
        if (aliveStatus) window.draw(enemy);
    }

    /**
     * Method to initialize enemy, assign stats according to enemy type ID given in
     * the constructor method.
     */
    public void initEnemy() {
        switch (this.enemyType) {
            case 0:

                enemyTexture = new Texture();
                this.movementSpeed = 1;
                this.attackDamage = 1;
                this.attackRange = 20;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/LowMerc.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

            case 1:
                enemyTexture = new Texture();
                this.movementSpeed = 1;
                this.attackDamage = 2;
                this.attackRange = 40;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/MediumMerc.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;
            case 2:
                enemyTexture = new Texture();
                this.movementSpeed = 5;
                this.attackDamage = 10;
                this.attackRange = 20;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/HighMerc.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

            case 3:
                enemyTexture = new Texture();
                this.movementSpeed = 2;
                this.attackDamage = 3;
                this.attackRange = 20;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/Farmer.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

            case 4:
                enemyTexture = new Texture();
                this.movementSpeed = 5;
                this.attackDamage = 10;
                this.attackRange = 40;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/LeadFarmer.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

            case 5:
                enemyTexture = new Texture();
                this.movementSpeed = 3;
                this.attackDamage = 15;
                this.attackRange = 20;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/LowBandit.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

            case 6:
                enemyTexture = new Texture();
                this.movementSpeed = 5;
                this.attackDamage = 25;
                this.attackRange = 40;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/MediumBandit.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

            case 7:
                enemyTexture = new Texture();
                this.movementSpeed = 7;
                this.attackDamage = 20;
                this.attackRange = 30;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/LowOrc.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

            case 8:
                enemyTexture = new Texture();
                this.movementSpeed = 5;
                this.attackDamage = 40;
                this.attackRange = 50;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/HighOrc.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;


            case 9:
                enemyTexture = new Texture();
                this.movementSpeed = 7;
                this.attackDamage = 15;
                this.attackRange = 30;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/Soldier.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;


            case 10:
                enemyTexture = new Texture();
                this.movementSpeed = 8;
                this.attackDamage = 40;
                this.attackRange = 50;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/HighCommander.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

            case 11:
                enemyTexture = new Texture();
                this.movementSpeed = 2;
                this.attackDamage = 60;
                this.attackRange = 30;
                this.currentHP = 10;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/Adam.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

            case 12:
                enemyTexture = new Texture();
                this.movementSpeed = 12;
                this.attackDamage = 60;
                this.attackRange = 30;
                this.currentHP = 10D;
                this.aliveStatus = true;
                try {
                    enemyTexture.loadFromFile(Paths.get("img/Enemy/King.png")); // your placeholder here
                } catch (IOException e) {
                    e.printStackTrace();
                }

                enemy = new Sprite(enemyTexture);
                randomEnemyPos();

                break;

        }
    }

    public void randomEnemyPos(){
        Random rand = new Random();
        int xPos = rand.nextInt(1080 - 450 - this.attackRange);
        enemy.setPosition(1080 - xPos, 510);
    }

    public int getWidth()
    {
        return maxWidth;
    }


    /**
     * Method to move towards/attack player. If not in range, enemy moves towards
     * the closer alive player. If in range, attack one of the players.
     */
    public void moveTowardsPlayer() {
        if (this.aliveStatus) {

            float xPlayer1Left = this.playerOne.player.getPosition().x;

            float xPlayer1Right = xPlayer1Left + this.playerOne.getPlayerWidth();

            float xPlayer2Left = this.playerTwo.player.getPosition().x;

            float xPlayer2Right = xPlayer2Left + this.playerTwo.getPlayerWidth();

            float xLeft = enemy.getPosition().x;
            float yPos = enemy.getPosition().y;
            float xRight = xLeft + maxWidth;
            float closest = -1;
            boolean leftSide = false;

            boolean P1Status = this.playerOne.getPlayerStatus();
            boolean P2Status = this.playerTwo.getPlayerStatus();

            boolean P1Check = (xPlayer1Right > (xLeft - attackRange) && xPlayer1Left < (xRight + attackRange));
            boolean P2Check = (xPlayer2Right > (xLeft - attackRange) && xPlayer2Left < (xRight + attackRange));

            if ((P1Check || P2Check) && (P1Status && P2Status) && (hitTimer.getElapsedTime().asSeconds() > 0.5)) { // if enemy's in range to attack, choose which one to attack
                hitTimer.restart();
                if (xPlayer1Left < (xRight + attackRange) && xPlayer1Right > (xLeft - attackRange)) {
                    this.playerOne.playerHit(this.attackDamage);
                } else if (xPlayer2Left < (xRight + attackRange) && xPlayer2Right > (xLeft - attackRange)) {
                    this.playerTwo.playerHit(this.attackDamage);
                }
            } else if (P1Check && !P2Status) {
                if (xPlayer1Left < (xRight + attackRange) && xPlayer1Right > (xLeft - attackRange)) {
                    this.playerOne.playerHit(this.attackDamage);
                }
            } else if (P2Check && !P1Status) {
                if (xPlayer2Left < (xRight + attackRange) && xPlayer2Right > (xLeft - attackRange)) {
                    this.playerTwo.playerHit(this.attackDamage);
                }
            } else {
                if (P1Status && P2Status) {
                    if (Math.abs(xPlayer1Right - (xLeft - attackRange)) > Math.abs(xPlayer1Left - (xRight + attackRange))) {
                        closest = Math.abs(xPlayer1Left - (xRight + attackRange));
                        leftSide = true;
                    } else
                        closest = Math.abs(xPlayer1Right - (xLeft - attackRange));

                    if (Math.abs((xRight + attackRange) - xPlayer2Left) > Math.abs(xPlayer2Right - (xLeft - attackRange))) {
                        if (closest > Math.abs(xPlayer2Right - (xLeft - attackRange))) {
                            enemy.setPosition(xLeft - movementSpeed, yPos);
                            enemy.setTextureRect(new IntRect(100, 0, maxWidth, enemyTexture.getSize().y));
                        }
                        else if (leftSide) {
                                enemy.setPosition(xLeft + movementSpeed, yPos);
                                enemy.setTextureRect(new IntRect(0, 0, maxWidth, enemyTexture.getSize().y));
                            }
                        else {
                                enemy.setPosition(xLeft - movementSpeed, yPos);
                                enemy.setTextureRect(new IntRect(100, 0, maxWidth, enemyTexture.getSize().y));
                            }
                    } else {
                        if (closest > Math.abs((xRight + attackRange) - xPlayer2Left)) {
                            enemy.setPosition(xLeft + movementSpeed, yPos);
                            enemy.setTextureRect(new IntRect(0, 0, maxWidth, enemyTexture.getSize().y));
                        }
                        else if (leftSide) {
                            enemy.setPosition(xLeft + movementSpeed, yPos);
                            enemy.setTextureRect(new IntRect(0, 0, maxWidth, enemyTexture.getSize().y));
                        }
                        else {
                            enemy.setPosition(xLeft - movementSpeed, yPos);
                            enemy.setTextureRect(new IntRect(100, 0, maxWidth, enemyTexture.getSize().y));
                        }
                    }
                } else if (!P1Status) {
                    if (Math.abs(xPlayer2Right - (xLeft - attackRange)) > Math.abs(xPlayer2Left - (xRight + attackRange)))
                        enemy.setPosition(xLeft + movementSpeed, yPos);
                    else
                        enemy.setPosition(xLeft - movementSpeed, yPos);

                } else {
                    if (Math.abs(xPlayer1Right - (xLeft - attackRange)) > Math.abs(xPlayer1Left - (xRight + attackRange)))
                        enemy.setPosition(xLeft + movementSpeed, yPos);
                    else
                        enemy.setPosition(xLeft - movementSpeed, yPos);
                }
            }
        }
    }

    /**
     * Getter for the alive status of the enemy
     * @return whether enemy is alive
     */
    public boolean getAliveStatus() {
        return aliveStatus;
    }

    /**
     * Modifies the current status to passed parameter
     * @param aliveStatus The new status of enemy
     */
    public void setAliveStatus(boolean aliveStatus) {
        this.aliveStatus = aliveStatus;
    }
}