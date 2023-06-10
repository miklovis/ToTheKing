package toTheKing;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.VideoMode;

import java.nio.file.Paths;

public class EndGame {
    protected Texture endgame;
    protected Sprite endMenu;
    protected RenderWindow endWindow;

    public void drawEnd(RenderWindow window) {
        window.draw(endMenu);
    }
    public void loadEndGameTexture()
    {
        endgame = new Texture();
        try {
            endgame.loadFromFile(Paths.get("img/level/winscreen.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        endMenu = new Sprite(endgame);
    }

    public RenderWindow createEndWindow()
    {
        endWindow = new RenderWindow();
        endWindow.create(new VideoMode(1080, 720), "The End!");
        endWindow.setFramerateLimit(30);
        return endWindow;
    }
}
