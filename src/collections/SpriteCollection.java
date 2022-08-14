package collections;
import biuoop.DrawSurface;
import sprites.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 * the class save all the sprites and use the methods they implement.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * add the sprite to the list.
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * remove the sprite from the list.
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<>(this.sprites);
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
}