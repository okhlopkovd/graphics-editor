package graphicseditor;

public class Pixel {
    private int red;
    private int green;
    private int blue;

    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setRBG(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
