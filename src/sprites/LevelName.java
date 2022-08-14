package sprites;

import biuoop.DrawSurface;
import animations.GameLevel;

import java.awt.Color;

/**
 * the level name will be displayed at the top of the screen.
 */
public class LevelName implements Sprite {
    private String name;

    /**
     * constructor.
     * @param name the name of the level.
     */
    public LevelName(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(250, 19, "Level Name: " + name, 20);
    }

    @Override
    public void timePassed() {
    }
    /**
     * add the score indicator to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
