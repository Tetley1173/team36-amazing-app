package gui;

import components.wallButtonCollection;
import mazeFunctions.Maze;

import javax.swing.*;
import java.awt.*;

public class displayMaze{
    private static final int HEIGHT = 5;
    private static int WIDTH = 30;
    private static int OFFSET = 5;
    public static wallButtonCollection[][] wallButtons;

    public static void setWallButtons(Maze maze) {
        wallButtons = new wallButtonCollection[maze.getRows()][maze.getCols()];
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                wallButtons[row][col] = new wallButtonCollection(row, col, WIDTH, HEIGHT, OFFSET);
            }
        }
    }
    public static void drawMaze(JPanel pnl, Maze maze) {
        setWallButtons(maze);
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                if (row == 0){
                    if (maze.getCell(row, col).getWall(0) == 0)
                        wallButtons[row][col].getWallButton(0).setContentAreaFilled(false);
                    pnl.add(wallButtons[row][col].getWallButton(0));
                }
                if (col == 0){
                    if (maze.getCell(row, col).getWall(3) == 0)
                        wallButtons[row][col].getWallButton(3).setContentAreaFilled(false);
                    pnl.add(wallButtons[row][col].getWallButton(3));
                }
                if (maze.getCell(row, col).getWall(2) == 0)
                    wallButtons[row][col].getWallButton(2).setContentAreaFilled(false);
                if (maze.getCell(row, col).getWall(1) == 0)
                    wallButtons[row][col].getWallButton(1).setContentAreaFilled(false);

                pnl.add(wallButtons[row][col].getWallButton(2));
                pnl.add(wallButtons[row][col].getWallButton(1));

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
