package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class HowManyPlayers implements Animation{
    private KeyboardSensor keyboard;
    private Color player1Color = Color.blue;
    private Color player2Color = Color.white;

    public HowManyPlayers(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(player1Color);
        if (keyboard.isPressed(KeyboardSensor.DOWN_KEY) && player1Color.equals(Color.blue)) {
            player1Color = Color.white;
            player2Color = Color.blue;
        }
        d.drawText(200,200, "1 player", 50);
        d.setColor(player2Color);
        if (keyboard.isPressed(KeyboardSensor.UP_KEY) && player1Color.equals(Color.white)) {
            player1Color = Color.blue;
            player2Color = Color.white;
        }
        d.drawText(200,300, "2 player", 50);
    }

    @Override
    public boolean shouldStop() {
        if (keyboard.isPressed(KeyboardSensor.ENTER_KEY)) {
            if (player1Color.equals(Color.blue)) {
                GameLevel.onePlayer  = true;
            } else {
                GameLevel.twoPlayers = true;
            }
            return true;
        }
        return false;
    }
}
