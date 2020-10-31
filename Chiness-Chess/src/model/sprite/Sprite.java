package model.sprite;

public abstract class Sprite {
    protected String imagePath;
    protected int width;
    protected int height;
    protected Color color;
    protected int row;
    protected int col;

    public Sprite(String imagePath, Color color,int width,int height) {
        this.imagePath = imagePath;
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getX() {
        return col * 60 + 37;
    }

    public int getY() {
        return row * 54 + 21;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setRowAndCol(int row,int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
