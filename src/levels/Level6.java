package levels;

import animations.GameLevel;
import geomtry.Point;
import geomtry.Rectangle;
import other.Velocity;
import sprites.Background;
import sprites.Block;
import sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level6 implements LevelInformation{
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        int angle = Math.abs(new Random().nextInt() % 90) - 45;
        velocities.add(Velocity.fromAngleAndSpeed(angle, 6));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        if (GameLevel.onePlayer) {
            return 100;
        }
        if (GameLevel.twoPlayers) {
            return 80;
        }
        return 0;
    }

    @Override
    public String levelName() {
        return "Alien";
    }

    @Override
    public Sprite getBackground() {
        return new Background();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        String color;
        for (int l = 0; l < 3; l++) {
            color = "yellow";
            blocks.add(new Block(new Rectangle(new Point(143.8, 70), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(411.4, 70), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(143.8, 92), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(411.4, 92), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(188.4, 114), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(366.8, 114), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(188.4, 136), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(366.8, 136), 44.6, 22), color));
            color = "green";
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 7; i++) {
                    blocks.add(new Block(new Rectangle(new Point(143.8 + 44.6 * i, 158 + 22 * j),
                            44.6, 22), color));
                }
            }
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 9; i++) {
                    if(i == 2 || i == 6) {
                        color = "red";
                    }
                    blocks.add(new Block(new Rectangle(new Point(99.2 + 44.6 * i, 202 + 22 * j),
                            44.6, 22), color));
                    color = "green";
                }
            }
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 11; i++) {
                    blocks.add(new Block(new Rectangle(new Point(54.6 + 44.6 * i, 246 + 22 * j),
                            44.6, 22), color));
                }
            }
            for (int i = 0; i < 7; i++) {
                blocks.add(new Block(new Rectangle(new Point(143.8 + 44.6 * i, 312),
                        44.6,22), color));
            }
            blocks.add(new Block(new Rectangle(new Point(54.6, 312), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(500.8, 312), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(54.6, 334), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(143.8, 334), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(411.6, 334), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(500.8, 334), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(54.6, 356), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(143.8, 356), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(411.6, 356), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(500.8, 356), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(188.4, 378), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(233, 378), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(322.2, 378), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(366.8, 378), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(188.4, 400), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(233, 400), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(322.2, 400), 44.6, 22), color));
            blocks.add(new Block(new Rectangle(new Point(366.8, 400), 44.6, 22), color));

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
        return 3;
    }

    @Override
    public int numberOfLiveBonus() {
        return 1;
    }

    @Override
    public int numberOfBiggerPaddleBonus() {
        return 1;
    }
}
