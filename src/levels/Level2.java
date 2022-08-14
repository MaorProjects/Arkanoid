package levels;

import animations.GameLevel;
import sprites.Background;
import geomtry.Point;
import geomtry.Rectangle;
import other.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the information of level 2.
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        int angle = Math.abs(new Random().nextInt() % 90) - 45;
        velocities.add(Velocity.fromAngleAndSpeed(angle, 7));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        if (GameLevel.onePlayer) {
            return 110;
        }
        if (GameLevel.twoPlayers) {
            return 85;
        }
        return 0;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        for (int k = 0; k < 2; k++) {
            for (int j = 0; j < 10; j++) {
                String[] rowColor = {"red", "blue", "green", "yellow", "orange",
                "lightBlue", "pink"};
                for (int i = 0; i < 5; i++) {
                    blocks.add(new Block(new Rectangle(new Point(
                            80 + 240 * k + 40 * i, 150 + 22 * j), 40, 22), rowColor[j % 7]));
                }
            }
        }
        return  blocks;
    }

    @Override
    public List<Block> unremovableBlocks() {
        return new ArrayList<>();
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public int numberOfBallsBonus() {
        return 1;
    }

    @Override
    public int numberOfLiveBonus() {
        return 0;
    }

    @Override
    public int numberOfBiggerPaddleBonus() {
        return 1;
    }
}
