package graphicseditor;

import graphicseditor.Pixel;

public class Board {
    private int height;
    private int width;
    Pixel[][] board;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        board = new Pixel[height][width];
    }
}
