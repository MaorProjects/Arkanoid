package levels;

import animations.GameLevel;
import sprites.*;
import geomtry.Point;
import geomtry.Rectangle;
import other.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the information of level 1.
 */
public class Level1 implements LevelInformation {
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
            return 120;
        }
        if (GameLevel.twoPlayers) {
            return 90;
        }
        return 0;
    }

    @Override
    public String levelName() {
        return "Standart";
    }

    @Override
    public Sprite getBackground() {
        return new Background();
    }
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            String[] rowColor = {"red", "blue", "white", "green", "yellow"};
                for (int i = 0; i < 13; i++) {
                    blocks.add(new Block(new Rectangle(new Point(
                            10 + 44.6 * i, 80 + 28 * j), 45, 28), rowColor[j]));
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
        return 5;
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
