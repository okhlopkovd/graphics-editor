package graphicseditor;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Container;

import java.io.File;

import graphicseditor.DrawingArea;

public class Editor implements ActionListener{
    private JFrame window;
    private DrawingArea drawArea;

    JButton pencilButton;
    JButton brushButton;
    JButton rubberButton;
    JButton magnifierButton;
    JButton lineButton;
    JButton rectangleButton;
    JButton circleButton;

    JMenuItem colorsMenuItem1;
    JMenuItem colorsMenuItem2;
    JMenuItem colorsMenuItem3;
    JMenuItem colorsMenuItem4;
    JMenuItem colorsMenuItem5;
    JMenuItem colorsMenuItem6;
    JMenuItem colorsMenuItem7;

    JMenuItem fileMenuItem1;
    JMenuItem fileMenuItem2;
    JMenuItem fileMenuItem3;
    JMenuItem fileMenuItem4;


    public Editor(int windowWidth, int windowHeight) {
        // creating a window

        window = new JFrame();

        window.setTitle("Graphics Editor");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(windowWidth, windowHeight);

        // creating a class for drawing area

        drawArea = new DrawingArea();
        Container content = window.getContentPane();
        content.add(drawArea, BorderLayout.CENTER);

        // creating a menu

        JMenuBar menuBar = new JMenuBar();

        // file menu

        JMenu fileMenu = new JMenu("File");

        fileMenuItem1 = new JMenuItem("New");
        fileMenuItem2 = new JMenuItem("Open");
        fileMenuItem3 = new JMenuItem("Save");
        fileMenuItem4 = new JMenuItem("Close");

        fileMenuItem1.addActionListener(this);
        fileMenuItem2.addActionListener(this);
        fileMenuItem3.addActionListener(this);
        fileMenuItem4.addActionListener(this);

        fileMenu.add(fileMenuItem1);
        fileMenu.add(fileMenuItem2);
        fileMenu.add(fileMenuItem3);
        fileMenu.add(fileMenuItem4);

        //  edit menu

        JMenu editMenu = new JMenu("Edit");

        JMenuItem editMenuItem1 = new JMenuItem("Cut");
        JMenuItem editMenuItem2 = new JMenuItem("Copy");
        JMenuItem editMenuItem3 = new JMenuItem("Paste");

        editMenu.add(editMenuItem1);
        editMenu.add(editMenuItem2);
        editMenu.add(editMenuItem3);

        // tools menu

        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem toolsMenuItem1 = new JMenuItem("Pencil");
        JMenuItem toolsMenuItem2 = new JMenuItem("Brush");
        JMenuItem toolsMenuItem3 = new JMenuItem("Rubber");
        JMenuItem toolsMenuItem4 = new JMenuItem("Magnifier");
        JMenuItem toolsMenuItem5 = new JMenuItem("Line");
        JMenuItem toolsMenuItem6 = new JMenuItem("Rectangle");
        JMenuItem toolsMenuItem7 = new JMenuItem("Circle");

        toolsMenu.add(toolsMenuItem1);
        toolsMenu.add(toolsMenuItem2);
        toolsMenu.add(toolsMenuItem3);
        toolsMenu.add(toolsMenuItem4);
        toolsMenu.add(toolsMenuItem5);
        toolsMenu.add(toolsMenuItem6);
        toolsMenu.add(toolsMenuItem7);

        // colors menu

        JMenu colorsMenu = new JMenu("Colors");

        colorsMenuItem1 = new JMenuItem("White");
        colorsMenuItem2 = new JMenuItem("Black");
        colorsMenuItem3 = new JMenuItem("Red");
        colorsMenuItem4 = new JMenuItem("Purple");
        colorsMenuItem5 = new JMenuItem("Pink");
        colorsMenuItem6 = new JMenuItem("Blue");
        colorsMenuItem7 = new JMenuItem("Green");

        colorsMenu.add(colorsMenuItem1);
        colorsMenu.add(colorsMenuItem2);
        colorsMenu.add(colorsMenuItem3);
        colorsMenu.add(colorsMenuItem4);
        colorsMenu.add(colorsMenuItem5);
        colorsMenu.add(colorsMenuItem6);
        colorsMenu.add(colorsMenuItem7);

        colorsMenuItem1.addActionListener(this);
        colorsMenuItem2.addActionListener(this);
        colorsMenuItem3.addActionListener(this);
        colorsMenuItem4.addActionListener(this);
        colorsMenuItem5.addActionListener(this);
        colorsMenuItem6.addActionListener(this);
        colorsMenuItem7.addActionListener(this);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(toolsMenu);
        menuBar.add(colorsMenu);

        // toolbar

        JToolBar toolbar = new JToolBar();

        // creating buttons for the toolbar

        JButton newButton = new JButton("New");
        JButton openButton = new JButton("Open");
        JButton saveButton = new JButton("Save");

        JButton cutButton = new JButton("Cut");
        JButton copyButton = new JButton("Copy");
        JButton pasteButton = new JButton("Paste");

        pencilButton = new JButton("Pencil");
        brushButton = new JButton("Brush");
        rubberButton = new JButton("Rubber");
        magnifierButton = new JButton("Magnifier");
        lineButton = new JButton("Line");
        rectangleButton = new JButton("Rectangle");
        circleButton = new JButton("Circle");

        pencilButton.addActionListener(e -> drawArea.pen());
        brushButton.addActionListener(e -> drawArea.brush());
        rubberButton.addActionListener(e -> drawArea.rubber());
        lineButton.addActionListener(e -> drawArea.line());
        rectangleButton.addActionListener(e -> drawArea.rectangle());
        circleButton.addActionListener(e -> drawArea.circle());

        toolbar.add(newButton);
        toolbar.add(openButton);
        toolbar.add(saveButton);

        toolbar.add(cutButton);
        toolbar.add(copyButton);
        toolbar.add(pasteButton);

        toolbar.add(pencilButton);
        toolbar.add(brushButton);
        toolbar.add(rubberButton);
        toolbar.add(magnifierButton);
        toolbar.add(lineButton);
        toolbar.add(rectangleButton);
        toolbar.add(circleButton);

        window.add(toolbar, BorderLayout.NORTH);
        window.setJMenuBar(menuBar);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == colorsMenuItem1) {
            drawArea.white();
        }
        else if (e.getSource() == colorsMenuItem2) {
            drawArea.black();
        }
        else if (e.getSource() == colorsMenuItem3) {
            drawArea.red();
        }
        else if (e.getSource() == colorsMenuItem4) {
            drawArea.purple();
        }
        else if (e.getSource() == colorsMenuItem5) {
            drawArea.pink();
        }
        else if (e.getSource() == colorsMenuItem6) {
            drawArea.blue();
        }
        else if (e.getSource() == colorsMenuItem7) {
            drawArea.green();
        }
        else if (e.getSource() == fileMenuItem1) {
             drawArea.clear();
        }
        else if (e.getSource() == fileMenuItem2) {
            FileDialog fileDialog = new FileDialog(window, "open", FileDialog.LOAD);
            fileDialog.setVisible(true);

            String loadPath = fileDialog.getDirectory() + fileDialog.getFile();
            drawArea.load(loadPath);
        }
        else if (e.getSource() == fileMenuItem3) {
            FileDialog fileDialog = new FileDialog(window, "save", FileDialog.SAVE);

            fileDialog.setFile("Untitled.png");
            fileDialog.setVisible(true);

            String savePath = fileDialog.getDirectory() + fileDialog.getFile();
            drawArea.save(savePath);
        }
        else if (e.getSource() == fileMenuItem4) {
            System.exit(0);
        }
    }
}