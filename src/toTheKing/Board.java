package toTheKing;

import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.graphics.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class provides a model for a bounty board for a game
 *
 * @author Leyton Taylor
 */

public class Board {

	private RenderWindow cWindow;
	private Sprite bountyBoard;
	private Texture bountyBoardTexture;
	private ArrayList<Bounty> bounties;
	private Sprite toShopButton;

	/**
	 * Creates an instance of board that calls the set up board method to set up the
	 * board Initialises the bounties Array List
	 *
	 * @param nWindow the window that the board will be applied to
	 */

	public Board(RenderWindow nWindow) {
		cWindow = nWindow;
		bounties = new ArrayList<Bounty>();

		setUpBoard();
		//createTempBounties();

	}

	/**
	 * Method to set up the board Creates a sprite using the texture from a png
	 * Calls the resize board method to resize the board to the window
	 */

	public void setUpBoard() {
		bountyBoardTexture = new Texture();
		try {
			bountyBoardTexture.loadFromFile(Paths.get("img/BountyBoard.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bountyBoard = new Sprite(bountyBoardTexture);
		resizeBoard();
		if (bounties.size() != 0) {
			sortBountyPositions();
		}
		Texture shopButtonT = new Texture();
		try {
			shopButtonT.loadFromFile(Paths.get("img/ToShopButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		toShopButton = new Sprite(shopButtonT);
		toShopButton.setPosition(40,40);
	}



	/**
	 * Method to resize the board based upon the size of the window Resizes bounty
	 * posters too
	 */

	public void resizeBoard() {
		int xSizeWindow = cWindow.getSize().x;
		int ySizeWindow = cWindow.getSize().y;
		Vector2i sizeBoard = bountyBoardTexture.getSize();
		float xScale = (float) xSizeWindow / sizeBoard.x;
		float yScale = (float) ySizeWindow / sizeBoard.y;
		bountyBoard.setScale(xScale, yScale);
		if (bounties.size() != 0) {
			for (int i = 0; i < bounties.size(); i++) {
				bounties.get(i).resizeBounty(xScale, yScale);
			}
		}
	}

	/**
	 * Method to create a bounty with the name and reward provided Adds the bounty
	 * to the arraylist
	 *
	 * @param name   the name of the bounty
	 * @param reward the reward of the bounty
	 */

	public void createBounty(String name, int reward, String bountyPath) {
		Bounty newBounty = new Bounty(name, reward, bountyPath);
		if (bounties.size() == 1)
		{
			bounties.remove(0);
		}
		bounties.add(newBounty);
		resizeBoard();
		sortBountyPositions();
	}

	/**
	 * Sorts the bounty poster positions based on the number of posters Centres them
	 * around the middle
	 */

	public void sortBountyPositions() {
		int xMidPoint = cWindow.getSize().x / 2;
		int yMidPoint = cWindow.getSize().y / 2;
		int numberOfPosters = bounties.size();
		Vector2i sizeOfPoster = bounties.get(0).getBountyPosterTexture().getSize();
		Vector2f scale = bounties.get(0).getBountyImage().getScale();
		int xSize = (int) (sizeOfPoster.x * scale.x);
		int ySize = (int) (sizeOfPoster.y * scale.y);
		int spacing = xSize / 10;
		int startPosition = 0;
		if (numberOfPosters == 1) {
			startPosition = xMidPoint;
		} else {
			if (numberOfPosters % 2 == 0) {
				startPosition = xMidPoint - (numberOfPosters / 2) * (spacing + xSize) + (xSize / 2 + spacing);
			} else {
				startPosition = xMidPoint - (numberOfPosters / 2) * (spacing + xSize);
			}
		}

		for (int i = 0; i < numberOfPosters; i++) {
			int xMovePos = (startPosition + (i * spacing) + (i * xSize) - (xSize / 2));
			int yMovePos = (yMidPoint - (ySize / 2));
			bounties.get(i).getBountyImage().setPosition(xMovePos, yMovePos);
		}
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

	/**
	 * Method to track a button press for the different posters Will load a level
	 * when clicked
	 * @return goShop returns true if the button to go to the shop is clicked
	 */

	public String buttonPress() {
		double xPos = Mouse.getPosition(cWindow).x;
		double yPos = Mouse.getPosition(cWindow).y;
		boolean goShop = false;
		String name;
		if (bounties.size() != 0) {
			for (int i = 0; i < bounties.size(); i++) {
				if(buttonIsClicked(bounties.get(i).getBountyImage())){
					System.out.println(bounties.get(i).getName());
					return bounties.get(i).loadLevel();
				}
			}
		}
		if(buttonIsClicked(toShopButton)){
			return "shop";
		}
		return "null";


	}

	/**
	 * Method to draw both the board and the bounties on the window stored within
	 * the class
	 */

	public void drawBoard() {
		cWindow.draw(bountyBoard);
		cWindow.draw(toShopButton);
		if (bounties.size() != 0) {
			for (int i = 0; i < bounties.size(); i++) {
				cWindow.draw(bounties.get(i).getBountyImage());
			}
		}
	}

	/**
	 * Returns the sprite of the board
	 *
	 * @return bountyBoard the board to be returned
	 */

	public Sprite getBoard() {
		return bountyBoard;
	}

	/**
	 * Changes the window to apply the graphics too
	 * @param nWindow  the window to change to
	 */

	public void setcWindow(RenderWindow nWindow){
		cWindow = nWindow;
	}

}