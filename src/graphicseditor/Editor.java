package graphicseditor;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Container;

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

    public Editor(int windowWidth, int windowHeight) {
        // creating a window

        window = new JFrame();

        window.setTitle("Graphics Editor");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(windowWidth, windowHeight);

        // creating a class for drawing area

        DrawingArea drawArea = new DrawingArea();
        Container content = window.getContentPane();
        content.add(drawArea, BorderLayout.CENTER);

        // creating a menu

        JMenuBar menuBar = new JMenuBar();

        // file menu

        JMenu fileMenu = new JMenu("File");

        JMenuItem fileMenuItem1 = new JMenuItem("New");
        JMenuItem fileMenuItem2 = new JMenuItem("Open");
        JMenuItem fileMenuItem3 = new JMenuItem("Save");
        JMenuItem fileMenuItem4 = new JMenuItem("Close");

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

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(toolsMenu);

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

    }
}