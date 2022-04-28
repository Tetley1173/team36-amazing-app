package gui;

import components.wallButton;
import mazeFunctions.Maze;

import javax.swing.*;
import java.awt.*;

public class displayMaze {
    private static int HEIGHT = 5;
    private static int WIDTH = 30;
    private static int OFFSET = 10;

    public static void drawMaze(JPanel pnl, Maze maze) {
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                if (row == 0 && maze.getCell(row, col).getWall(0) == 1) pnl.add(new wallButton((col * (WIDTH + HEIGHT)) + HEIGHT + OFFSET, OFFSET, WIDTH, HEIGHT, 0));
                if (col == 0 && maze.getCell(row, col).getWall(3) == 1) pnl.add(new wallButton(OFFSET, (row * (WIDTH + HEIGHT)) + HEIGHT + OFFSET, HEIGHT, WIDTH, 3));
                if (maze.getCell(row, col).getWall(2) == 1) pnl.add(new wallButton(col * (WIDTH + HEIGHT) + HEIGHT + OFFSET, (row + 1) * (WIDTH + HEIGHT) + OFFSET, WIDTH, HEIGHT, 2));
                if (maze.getCell(row, col).getWall(1) == 1) pnl.add(new wallButton((col + 1) * (WIDTH + HEIGHT) + OFFSET, row * (WIDTH + HEIGHT) + HEIGHT + OFFSET, HEIGHT, WIDTH, 1));
            }
        }
    }
    public static void showEntryExit(JPanel pnl, Maze maze) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.RED);
        g.fillRect(maze.getEntryCell().getCol() * WIDTH + WIDTH /4 + HEIGHT + OFFSET,maze.getEntryCell().getRow() * WIDTH + WIDTH /4 + HEIGHT + OFFSET, WIDTH / 2, WIDTH / 2);
        g.setColor(Color.GREEN);
        g.fillRect(maze.getExitCell().getCol() * (WIDTH + HEIGHT) + WIDTH /4 + HEIGHT + OFFSET,maze.getExitCell().getRow() * (WIDTH + HEIGHT) + WIDTH /4 + HEIGHT + OFFSET, WIDTH / 2, WIDTH / 2);
    }
    public void showEntryExitIndicator(JPanel pnl, Maze maze){}
    public void showOptimumPath(JPanel pnl, Maze maze){}
    public void showOptimumPathReachingPercentage(JPanel pnl, Maze maze){}
    public void showDeadEndPercentage(JPanel pnl, Maze maze){}
}
