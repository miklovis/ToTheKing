package toTheKing;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * This class provides a model for a bounty poster for a game
 *
 * @author Leyton Taylor
 */

public class Bounty {

    private Sprite bountyPoster;
    private Texture bountyPosterTexture;
    private String name;
    private int reward;
    private String imagePath = "img/Bounties/Bounty.png";

    /**
     * Creates an instance of Bounty that calls the set up bounty method to set up
     * the bounty
     *
     * @param name   the name of the bounty
     * @param reward the reward for completing the bounty
     */

    public Bounty(String name, int reward, String bountyImage) {
        this.name = name;
        this.reward = reward;
        if(bountyImage != ""){
            this.imagePath = bountyImage;
        }
        setUpBounty(name);
    }

    /**
     * The method which creates the sprite based upon the image loaded Creates the
     * bounty image
     */

    private void setUpBounty(String name) {
        switch (name){
            case "adam":
                imagePath = "img/Bounties/AdamBounty.png";
                reward = 1500;
                break;
            case "laverd":
                imagePath = "img/Bounties/LaverdBounty.png";
                reward = 2200;
                break;
            case "highorc":
                imagePath = "img/Bounties/HighOrcBounty.png";
                reward = 3500;
                break;
            case "commander":
                imagePath = "img/Bounties/CommanderBounty.png";
                reward = 5000;
                break;
            case "king":
                imagePath = "img/Bounties/KingBounty.png";
                reward = 10000;
                break;
        }

        bountyPosterTexture = new Texture();
        try {
            bountyPosterTexture.loadFromFile(Paths.get(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bountyPoster = new Sprite(bountyPosterTexture);
    }

    public String loadLevel( ){
        return name;
    }

    /**
     * Returns the bounty poster sprite
     *
     * @return bountyPoster the sprite to be returned
     */

    public Sprite getBountyImage() {
        return bountyPoster;
    }

    /**
     * Returns the bounty poster texture
     *
     * @return bountyPosterTecture the texture to be returned
     */

    public Texture getBountyPosterTexture() {
        return bountyPosterTexture;
    }

    /**
     * Method to scale the poster down
     *
     * @param xScale the amount to scale the x size by
     * @param yScale the amount to scale the y size by
     */

    public void resizeBounty(float xScale, float yScale) {
        bountyPoster.setScale(xScale, yScale);
    }

    /**
     * Gets the reward for compelting the bounty
     *
     * @return reward the amount of the reward
     */

    public int getReward(){
        return reward;
    }

    /**
     * Gets the name for the bounty
     *
     * @return name the name of the bounty
     */

    public String getName(){
        return name;
    }

}