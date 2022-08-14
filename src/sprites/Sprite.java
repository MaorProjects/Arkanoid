package sprites;
import biuoop.DrawSurface;

/**
 * the interface describes the sprites (ball, blocks and paddle) of the game.
 */
public interface Sprite {
    /**
     * @param d draw the sprite to the screen.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed and each sprite does a certain action set for it
     * like move, stay in place and so on.
     */
    void timePassed();
}