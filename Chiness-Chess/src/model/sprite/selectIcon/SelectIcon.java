package model.sprite.selectIcon;

import model.sprite.Color;
import model.sprite.Sprite;


public class SelectIcon extends Sprite {

    public SelectIcon(String imagePath, Color color, int width, int height) {
        super(imagePath, color, width, height);
    }

    public Color getColor() {
        return color;
    }
}
