package graphicseditor;

import javax.swing.JComponent;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DrawingArea extends JComponent{
    private Image image;
    private Graphics2D graphics;

    private int curX, curY;
    private int oldX, oldY;

    public DrawingArea() {
        setDoubleBuffered(false);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
    }
}
