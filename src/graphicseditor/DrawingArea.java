package graphicseditor;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;

import graphicseditor.Tool;
import graphicseditor.Line;

public class DrawingArea extends JComponent{
    private Image image;
    private Graphics2D graphics;

    private int curX, curY;
    private int oldX, oldY;

    private boolean shapeMode = false;
    private int currentFactor = 1;

    private Shape currentShape;
    private Tool currentTool = new Pencil();
    private Color currentColor = Color.black;

    private BufferedImage buffer;

    public DrawingArea() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(!shapeMode) {
                    oldX = e.getX();
                    oldY = e.getY();
                }
            }

            public void mouseClicked(MouseEvent e) {
                if(shapeMode) {
                    currentShape.paintShape(graphics, e.getX(), e.getY(), currentColor);
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (!shapeMode) {
                    curX = e.getX();
                    curY = e.getY();

                    if (graphics != null) {
                        currentTool.paint(graphics, oldX, oldY, curX, curY, currentColor, currentFactor);
                        repaint();

                        oldX = curX;
                        oldY = curY;
                    }
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            graphics = (Graphics2D) image.getGraphics();
            graphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);
        repaint();
    }

    public void brush() {
        currentTool = new Brush(10);
        shapeMode = false;
    }

    public void pen() {
        currentTool = new Pencil();
        shapeMode = false;
    }

    public void rubber() {
        currentTool = new Rubber(10);
        shapeMode = false;
    }

    public void line() {
        shapeMode = true;
        currentShape = new Line();
    }

    public void rectangle() {
        shapeMode = true;
        currentShape = new Rectangle();
    }

    public void circle() {
        shapeMode = true;
        currentShape = new Circle();
    }

    public void black() {
        currentColor = Color.black;
    }

    public void white() {
        currentColor = Color.white;
    }

    public void red() {
        currentColor = Color.red;
    }

    public void blue() {
        currentColor = Color.blue;
    }

    public void pink() {
        currentColor = Color.pink;
    }

    public void purple() {
        currentColor = Color.magenta;
    }

    public void green() {
        currentColor = Color.green;
    }

    public void load(String loadPath) {
        BufferedImage loadingImage=null;

        try {
            loadingImage = ImageIO.read(new File(loadPath));
        }
        catch (IOException ex) {
            ex.getLocalizedMessage();
        }

        graphics.drawImage(loadingImage, 0, 0, null);
        repaint();
    }

    public void save(String savePath) {
        BufferedImage buffer = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D savedGrapics = buffer.createGraphics();
        savedGrapics.drawImage(image, 0, 0, null);

        try {
            ImageIO.write(buffer, "PNG", new File(savePath));
        }
        catch (IOException ex) {
            ex.getLocalizedMessage();
        }
    }

    public void setSizeFactor(int newFactor) { currentFactor = newFactor; }
}
