package sprites;

import biuoop.DrawSurface;
import other.Counter;
import animations.GameLevel;

import java.awt.Color;

/**
 * charge to count the score.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreIndicator;

    /**
     * constructor.
     * @param scoreCounter count the score.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreIndicator = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawText(100, 19, "score: " + scoreIndicator.getValue(), 20);
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
