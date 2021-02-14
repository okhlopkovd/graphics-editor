package graphicseditor;

import java.awt.Color;

public class Pixel {
    Color value;

    public Pixel(Color value) {
        this.value = value;
    }

    public void setRGB(Color value) {
        this.value = value;
    }

    public Color getRGB() {
        return value;
    }
}
