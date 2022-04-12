import Components.Cell;
import mazeFunctions.mazeGen;

import javax.swing.*;
import java.awt.*;

public class Grid {
    private final int size = 30; // Size of 1 cell
    private final int X_dim, Y_dim; // Dimension of the maze grid
    private final int rows, cols; // # of rows and columns of the maze
    private final Cell[] startEndCell;
    private final mazeGen maze;
    private final int OFFSET = 20;

    public Grid(int rows, int cols) {
        maze = new  mazeGen(rows, cols);
        maze.createMaze();
        startEndCell = maze.genMaze();
        this.X_dim = rows * size;
        this.Y_dim = cols * size;
        this.rows = rows;
        this.cols = cols;
    }

    public void drawMaze(JPanel pnl) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.white);
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
//                if (maze.mazeWall(row, col, 0) == 1) pnl.add(new customizedButton(row, col,0));
//                if (maze.mazeWall(row, col, 1) == 1) pnl.add(new customizedButton(row, col,1));
//                if (maze.mazeWall(row, col, 2) == 1) pnl.add(new customizedButton(row, col,2));
//                if (maze.mazeWall(row, col, 3) == 1) pnl.add(new customizedButton(row, col,3));
                if (maze.mazeWall(row, col, 0) == 1) g.drawLine(col * this.size + OFFSET,row * this.size + OFFSET,(col + 1) * this.size + OFFSET,row * this.size + OFFSET);
                if (maze.mazeWall(row, col, 3) == 1) g.drawLine(col * this.size + OFFSET,row * this.size + OFFSET,col * this.size + OFFSET,(row + 1) * this.size + OFFSET);
                if (maze.mazeWall(row, col, 2) == 1) g.drawLine(col * this.size + OFFSET,(row + 1) * this.size + OFFSET,(col + 1) * this.size + OFFSET,(row + 1) * this.size + OFFSET);
                if (maze.mazeWall(row, col, 1) == 1) g.drawLine((col + 1) * this.size + OFFSET,row * this.size + OFFSET,(col + 1) * this.size + OFFSET,(row + 1) * this.size + OFFSET);
            }
        }
    }

    public void showEntryExit(JPanel pnl) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.RED);
        g.fillRect(this.startEndCell[0].getCol() * this.size + 2 + OFFSET,this.startEndCell[0].getRow() * this.size + 2 + OFFSET,this.size / 2,this.size / 2);
        g.setColor(Color.GREEN);
        g.fillRect(this.startEndCell[1].getCol() * this.size + 2 + OFFSET,this.startEndCell[1].getRow() * this.size + 2 + OFFSET,this.size / 2,this.size / 2);
    }

    public int getX_dim() {return this.X_dim;}
    public int getY_dim() {return this.Y_dim;}
    public int getNoRows() {return this.rows;}
    public int getNoCols() {return this.cols;}
    public int getCellSize() {return this.size;}

}
