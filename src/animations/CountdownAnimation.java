package animations;
import biuoop.DrawSurface;
import collections.SpriteCollection;
import java.awt.Color;


/**
 * the animation countdown from 3 to 1 any level before it started.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private  double numOfSeconds;
    private int countFrom;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();

    /**
     * constructor.
     * @param numOfSeconds how many time the countdown took.
     * @param countFrom from which number the countdown start.
     * @param gameScreen the sprites of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.yellow);
        if (countFrom == 0) {
            d.drawText(290, d.getHeight() / 2, "GO", 32);
        } else {
            d.drawText(300, d.getHeight() / 2, Integer.toString(countFrom), 32);
        }
        if (countFrom < 3) {
            sleeper.sleepFor((int) numOfSeconds * 1000 / 3);
        }
        countFrom--;
    }

    @Override
    public boolean shouldStop() {
        if (countFrom < 0) {
            sleeper.sleepFor((int) numOfSeconds * 1000 / 3);
        }
        return countFrom < 0;
    }
}
