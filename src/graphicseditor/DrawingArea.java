package graphicseditor;

import javax.imageio.ImageIO;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.*;
import java.awt.event.*;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import graphicseditor.Tool;
import graphicseditor.Line;

public class DrawingArea extends JPanel{
    private BufferedImage image;
    private Graphics2D graphics;

    private int curX, curY;
    private int oldX, oldY;

    private int width;
    private int height;

    private double scale = 1.0;
    private boolean zoomMode = false;
    private boolean shapeMode = false;
    private int currentFactor = 1;

    private Shape currentShape;
    private Tool currentTool = new Pencil();
    private Color currentColor = Color.black;

    public DrawingArea(int preferredWidth, int preferredHeight) {
        width = preferredWidth;
        height = preferredHeight;
        setLayout(new BorderLayout());

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(!shapeMode) {
                    oldX = (int)(e.getX()/ scale);
                    oldY = (int)(e.getY()/ scale);
                }
            }

            public void mouseClicked(MouseEvent e) {
                if (shapeMode) {
                    currentShape.paintShape(graphics, e.getX(), e.getY(), currentColor);
                }
                else if (zoomMode) {
                    scale *= 2;

                    if (scale > 4) {
                        scale = 1;
                        zoomMode = false;
                    }

                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (!shapeMode) {
                    curX = (int)(e.getX() / scale);
                    curY = (int)(e.getY() / scale);

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
        super.paintComponent(g);

        if (image == null) {
            image = (BufferedImage) createImage(width, height);
            graphics = (Graphics2D) image.getGraphics();
            graphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            clear();
        }

        if (zoomMode) {
            g.drawImage(image.getScaledInstance((int)(width*scale), (int)(height*scale), Image.SCALE_FAST), 0, 0, null);
        }
        else {
            g.drawImage(image, 0, 0, null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)(scale * width), (int)(scale*height));
    }

    public void clear() {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, width, height);
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

    public void setToZoomMode() { zoomMode = true;}
}
