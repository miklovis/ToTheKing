package toTheKing;

import org.jsfml.system.Clock;


/**
 * This class provides the shared functionality of all potions
 *
 * @author Royantha Romans
 */

public class Consumables {

    protected Player players;
    protected String current;
    protected Clock potionClock;
    protected Boolean active = false;
    protected Boolean used = false;

    protected int startingSpeed;
    protected int startingAttack;
    protected double startingHealth;

    public Consumables(Player players) {
        this.players = players;
        potionClock = new Clock();
    }

    public void setPot(String current) {
        this.current = current;
        used = false;
    }

    public void consumableCheck()
    {
        if (active)
        {
            if (potionClock.getElapsedTime().asSeconds() >= 20)
            {
                potionClock.restart();
                this.effectsReset();
                active = false;
                used = false;
                this.current = null;
            }
        }
    }

    public void grabLevelStartStats()
    {
        startingAttack = players.getPlayerAttack();
        startingHealth = players.getPlayerHealth();
        startingSpeed = players.getPlayerSpeed();
    }

    public void effectsReset()
    {
        players.setPlayerAttack(startingAttack);
        players.setPlayerHealth(startingHealth);
        players.setPlayerSpeed(startingSpeed);
    }

    public void speedPotion() {
        if (!used && !current.equals("null")) {
            grabLevelStartStats();
            used = true;
            players.setPlayerSpeed(this.startingSpeed + 6);
            potionClock.restart();
            active = true;
        }
    }

    public void strengthPotion() {
        if (!used && !current.equals("null")) {
            grabLevelStartStats();
            used = true;
            players.setPlayerAttack(this.startingAttack + 10);
            potionClock.restart();
            active = true;
        }
    }

    public void largeHealth() {
        if (!used && !current.equals("null")) {
            grabLevelStartStats();
            used = true;
            players.setPlayerHealth(this.startingHealth + 40);
            potionClock.restart();
            active = true;
        }
    }

    public void smallHealth() {
        if (!used && !current.equals("null")) {
            grabLevelStartStats();
            used = true;
            players.setPlayerHealth(this.startingHealth + 10);
            potionClock.restart();
            active = true;
        }
    }

    public void usePotions() {

        switch (current) {
            case "smallHealth":
                this.smallHealth();
                break;
            case "largeHealth":
                this.largeHealth();
                break;
            case "strength":
                this.strengthPotion();
                break;
            case "speed":
                this.speedPotion();
                break;
            case "null":
                break;
        }

    }

    public Boolean getActive() {
        return active;
    }
}