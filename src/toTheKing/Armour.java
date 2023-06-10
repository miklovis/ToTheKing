package toTheKing;

import org.jsfml.graphics.*;

import toTheKing.Player;
import java.util.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Armour {
    protected int lightArmourRating = 50;
    protected int heavyArmourRating = 50;
    protected int strongArmourRating = 50;

    protected Sprite ArmourType;
    protected int playerNumber;
    protected Player player;

    protected int[] armourTypes = { lightArmourRating, heavyArmourRating, strongArmourRating };

    protected int defense;
    protected int attack;
    protected int speed;

    protected boolean lArmour = false; // Sets current state of Light Armour for Character
    protected boolean hArmour = false; // Sets current state of Heavy Armour for Character
    protected boolean sArmour = false; // Sets current state of Strong Armour for Character

    public Armour(Player player, int defense, int attack, int speed) {
        this.defense = defense;
        this.attack = attack;
        this.speed = speed;
        this.player = player;
        playerNumber = player.getPlayerNumber();
    }

    public Sprite getArmourSprite(){ return ArmourType;}

    public void setArmourDefense(int defense) {
        this.defense = defense;
    }

    public void setArmourAttack(int attack) {
        this.attack = attack;
    }

    public void setArmourSpeed(int speed) { this.speed = speed; }

    public int getArmourDefense() {
        return defense;
    }

    public double getArmourAttack() {
        return attack;
    }

    public int getArmourSpeed() {
        return speed;
    }

    /**
     * This method adds the Sprite of the Light Armour to the  Character. If
     * the Player activates the Light Armour the speed of the character increases.
     */

    public void addLightArmour() {
        lArmour = true;
        if (lArmour == true && speed == 100) {
            Texture lightArmourCharacterOne = new Texture();
            String path = "";
            if(playerNumber == 0){
                path = "img/NewArmour/POneSpeed.png";
            }else if(playerNumber == 1){
                path = "img/NewArmour/PTwoSpeed.png";;
            }
            try {
                lightArmourCharacterOne.loadFromFile(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArmourType = new Sprite(lightArmourCharacterOne);
            this.player.setPlayerArmour("lightArmour");

            speed = speed + lightArmourRating;
        }
    }

    /**
     * This method removes the Sprite of the Light Armour from the Character
     * and sets the Character with the Base Armour. If Player deactivates the
     * Light Armour, the speed of the character will be reset to its original value.
     */

    public void removeLightArmour() {
        lArmour = false;
        if (lArmour && speed >= 100) {

            Texture baseArmourCharacterOne = new Texture();
            String path = "";
            if(playerNumber == 0){
                path = "img/NewArmour/POneBase.png";
            }else if(playerNumber == 1){
                path = "img/NewArmour/PTwoBase.png";
            }

            try {
                baseArmourCharacterOne.loadFromFile(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArmourType = new Sprite(baseArmourCharacterOne);
            speed = speed - lightArmourRating;
        }

    }

    /**
     * This method adds the Sprite of the Heavy Armour to the First Character. If
     * the Player 1 activates the Heavy Armour the speed of the character increases.
     */

    public void addHeavyArmour() {
        hArmour = true;
        if (hArmour == true && defense == 100) {
            Texture heavyArmourCharacterOne = new Texture();
            String path = "";
            if(playerNumber == 0){
                path = "img/NewArmour/POneDefence.png";
                this.player.setPlayerArmour("heavyArmour");

            }else if(playerNumber == 1){
                path = "img/NewArmour/PTwoDefence.png";
                this.player.setPlayerArmour("heavyArmour");

            }

            try {
                heavyArmourCharacterOne.loadFromFile(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArmourType = new Sprite(heavyArmourCharacterOne);
            defense = defense + heavyArmourRating;
        }
    }

    /**
     * This method removes the Sprite of the Heavy Armour from the First Character
     * and sets the Character with the Base Armour. If Player 1 deactivates the
     * Heavy Armour, the speed of the character will be reset to its original value.
     */

    public void removeHeavyArmour() {
        hArmour = false;
        if (hArmour && defense >= 100) {
            Texture baseArmourCharacterOne = new Texture();
            String path = "";
            if(playerNumber == 0){
                path = "img/NewArmour/POneBase.png";
            }else if(playerNumber == 1){
                path = "img/NewArmour/PTwoBase.png";
            }

            try {
                baseArmourCharacterOne.loadFromFile(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArmourType = new Sprite(baseArmourCharacterOne);

            defense = defense - heavyArmourRating;
        }
    }

    /**
     * This method adds the Sprite of the Strong Armour to the First Character. If
     * the Player 1 activates the Strong Armour the speed of the character
     * increases.
     */

    public void addStrongArmour() {
        sArmour = true;
        if (sArmour == true && attack == 100) {
            Texture strongArmourCharacterOne = new Texture();
            String path = "";
            if(playerNumber == 0){
                path = "img/NewArmour/POneAttack.png";
            }else if(playerNumber == 1){
                path = "img/NewArmour/PTwoAttack.png";
            }
            try {
                strongArmourCharacterOne.loadFromFile(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArmourType = new Sprite(strongArmourCharacterOne);
            this.player.setPlayerArmour("strongArmour");

            attack = attack + heavyArmourRating;
        }
    }

    /**
     * This method removes the Sprite of the Strong Armour from the First Character
     * and sets the Character with the Base Armour. If Player 1 deactivates the
     * Strong Armour, the speed of the character will be reset to its original
     * value.
     */

    public void removeStrongArmour() {
        sArmour = false;
        if (sArmour && attack >= 100) {
            Texture baseArmourCharacterOne = new Texture();
            String path = "";
            if(playerNumber == 0){
                path = "img/NewArmour/POneBase.png";
            }else if(playerNumber == 1){
                path = "img/NewArmour/PTwoBase.png";
            }
            try {
                baseArmourCharacterOne.loadFromFile(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArmourType = new Sprite(baseArmourCharacterOne);
            attack = attack - heavyArmourRating;
        }
    }

}