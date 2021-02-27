package graphicseditor;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.RenderingHints;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

import tools.*;

public class DrawingArea extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
    private BufferedImage image;
    private Graphics2D imageGraphics;

    private int curX, curY;
    private int oldX, oldY;

    private int width;
    private int height;

    private double scale = 1.0;
    private int currentFactor = 1;

    private boolean zoomMode = false;
    private boolean textMode = false;
    private boolean selectionMode = false;

    private Tool currentTool = new Pencil();
    private Color currentColor = Color.black;

    private Selector currentSelection = null;
    private Text currentText = null;


    public DrawingArea(int preferredWidth, int preferredHeight) {
        width = preferredWidth;
        height = preferredHeight;

        setLayout(new BorderLayout());

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        curX = (int)(e.getX() / scale);
        curY = (int)(e.getY() / scale);

        if (selectionMode) {
            currentSelection.select(imageGraphics, e.getPoint());
            repaint();
        }
        else if (textMode) {
            currentText.selectArea(imageGraphics, oldX, oldY, curX, curY, currentColor, currentFactor);
            repaint();
        }
        else {
            if (imageGraphics != null) {
                currentTool.paint(imageGraphics, oldX, oldY, curX, curY, currentColor, currentFactor);
                repaint();
            }
        }

        oldX = curX;
        oldY = curY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (zoomMode) {
            scale *= 2;

            if (scale > 4) {
                scale = 1;
                zoomMode = false;
            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (selectionMode) {
            if (currentSelection == null) currentSelection = new Selector(image, e.getPoint());
            else {
                currentSelection.updateImage(image);
                currentSelection.reset(imageGraphics);
                currentSelection.setAnchorPoint(e.getPoint());
            }
        }
        else if (textMode) {
            if (currentText == null) currentText = new Text(image, e.getPoint());
            else {
                currentText.reset(imageGraphics);
                currentText.updateImage(image);;
                currentText.setAnchorPoint(e.getPoint());
            }
        }
        else {
            oldX = (int)(e.getX()/ scale);
            oldY = (int)(e.getY()/ scale);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentTool.reset(imageGraphics, image);
        if(selectionMode) { currentSelection.reset(imageGraphics); }
        else if (textMode) { requestFocus();}
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        char keyChar = keyEvent.getKeyChar();
        if (textMode && (int) keyChar <= 126 && (int) keyChar >= 32) {
            currentText.drawText(imageGraphics, keyChar);
            repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)(scale * width), (int)(scale*height));
    }

    public void clear() {
        imageGraphics.setPaint(Color.white);
        imageGraphics.fillRect(0, 0, width, height);
        imageGraphics.setPaint(Color.black);
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            image = (BufferedImage) createImage(width, height);
            imageGraphics = (Graphics2D) image.getGraphics();
            imageGraphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            clear();
        }

        if (zoomMode) {
            g.drawImage(
                    image.getScaledInstance((int) (width * scale), (int) (height * scale),
                    Image.SCALE_FAST), 0, 0, null
            );
        }
        else g.drawImage(image, 0, 0, null);
    }

    public void copy() {
        if (selectionMode) currentSelection.copy(imageGraphics);
    }

    public void paste() {
        if (selectionMode) currentSelection.paste(imageGraphics);
        repaint();
    }

    public void load(String loadPath) {
        BufferedImage loadingImage=null;

        try { loadingImage = ImageIO.read(new File(loadPath)); }
        catch (IOException ex) { ex.getLocalizedMessage(); }

        imageGraphics.drawImage(loadingImage, 0, 0, null);
        repaint();
    }

    public void save(String savePath) {
        BufferedImage buffer = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D savedGrapics = buffer.createGraphics();
        savedGrapics.drawImage(image, 0, 0, null);

        try { ImageIO.write(buffer, "PNG", new File(savePath)); }
        catch (IOException ex) { ex.getLocalizedMessage(); }
    }

    public void setTool(Tool newTool) { currentTool = newTool; }

    public void setSizeFactor(int newFactor) { currentFactor = newFactor; }

    public void setColor(Color color) { currentColor = color; }

    public void setZoomMode(boolean isOn) { zoomMode = isOn; }

    public void setSelectionMode(boolean isOn) { selectionMode = isOn; }

    public void setTextMode(boolean isOn) {
        if(textMode && currentText != null) currentText.reset(imageGraphics);
        textMode = isOn;
    }

    public BufferedImage getImage() { return image; }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent) { }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) { }

    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }
}
