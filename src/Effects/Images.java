package Effects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Images extends Frame {
    private static String screensPath = "images\\Screens\\";
    private static String blockssPath = "images\\Blocks\\";
    private static String path = "images\\";
    private static Image backgroundImage;
    private static Image gameOverImage;
    private static Image youWinImage;
    private static Image blueBlock;
    private static Image goldBlock;
    private static Image grayBlock;
    private static Image greenBlock;
    private static Image lightBlueBlock;
    private static Image orangeBlock;
    private static Image pinkBlock;
    private static Image redBlock;
    private static Image yellowBlock;
    private static Image whiteBlock;
    private static Image paddleImage;
    private static Image ballsImage;
    private static Image heartImage;

    public Images() {
        try {
            this.backgroundImage = ImageIO.read(new File(screensPath + "background.jpg"));
            this.gameOverImage = ImageIO.read(new File(screensPath + "GameOver.jpg"));
            this.youWinImage = ImageIO.read(new File(screensPath + "YouWin.jpg"));
            this.blueBlock = ImageIO.read(new File(blockssPath + "blue.png"));
            this.goldBlock = ImageIO.read(new File(blockssPath + "gold.png"));
            this.grayBlock = ImageIO.read(new File(blockssPath + "gray.png"));
            this.greenBlock = ImageIO.read(new File(blockssPath + "green.png"));
            this.lightBlueBlock = ImageIO.read(new File(blockssPath + "lightBlue.png"));
            this.orangeBlock = ImageIO.read(new File(blockssPath + "orange.png"));
            this.pinkBlock = ImageIO.read(new File(blockssPath + "pink.png"));
            this.redBlock = ImageIO.read(new File(blockssPath + "red.png"));
            this.yellowBlock = ImageIO.read(new File(blockssPath + "yellow.png"));
            this.whiteBlock = ImageIO.read(new File(blockssPath + "white.png"));
            this.paddleImage = ImageIO.read(new File(path + "paddle.png"));
            this.ballsImage = ImageIO.read(new File(path + "balls.png"));
            this.heartImage = ImageIO.read(new File(path + "heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static java.awt.Image getImage(int width, int height, String imageName) {
        if (imageName.equals("background")) {
            return backgroundImage.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("GameOver")) {
            return gameOverImage.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("YouWin")) {
            return youWinImage.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("paddle")) {
            return paddleImage.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("balls")) {
            return ballsImage.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("heart")) {
            return heartImage.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("blue")) {
            return blueBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("gold")) {
            return goldBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("gray")) {
            return grayBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("green")) {
            return greenBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("lightBlue")) {
            return lightBlueBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("orange")) {
            return orangeBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("pink")) {
            return pinkBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("red")) {
            return redBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("yellow")) {
            return yellowBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        if (imageName.equals("white")) {
            return whiteBlock.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        }
        return  null;
    }
}
