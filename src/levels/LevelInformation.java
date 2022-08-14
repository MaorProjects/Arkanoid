package levels;

import other.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * save the information of the level.
 */
public interface LevelInformation {
    /**
     * @return number of balls that the games start with.
     */
    int numberOfBalls();
    /**
     * @return list of the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();
    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();
    /**
     * @return the width of the paddle.
     */
    int paddleWidth();
    //

    /**
     * the level name will be displayed at the top of the screen.
     * @return the level name.
     */
    String levelName();
    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();
    /**
     * @return the blocks that make up this level
     */
    List<Block> blocks();

    /**
     * @return the blocks that make up this level and cant be removed.
     */
    List<Block> unremovableBlocks();
    /**
     * @return the number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
    int numberOfBallsBonus();
    int numberOfLiveBonus();
    int numberOfBiggerPaddleBonus();

}


