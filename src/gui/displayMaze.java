package gui;

import components.wallButton;
import components.wallButtonCollection;
import mazeFunctions.Maze;
import mazeFunctions.MazeGeneration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.WeakHashMap;

public class displayMaze{
    public static Color bgColor = Color.DARK_GRAY;
    public static Color hoverColor = Color.ORANGE;
    private static final int HEIGHT = 5;
    private static int WIDTH = 30;
    private static int OFFSET = 5;
    public static wallButtonCollection[][] wallButtons;

    public static void setWallButtons(Maze maze) {
        wallButtons = new wallButtonCollection[maze.getRows()][maze.getCols()];
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                wallButtons[row][col] = new wallButtonCollection(row, col);
            }
        }
    }
    public static void drawMaze(JPanel pnl, Maze maze) {
        pnl.setLayout(null);
        setWallButtons(maze);
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                int finalRow = row;
                int finalCol = col;
                if (row == 0){
                    if (maze.getCell(row, col).getWall(0) == 0) wallButtons[row][col].getWallButton(0).setBackground(bgColor);
                    pnl.add(wallButtons[row][col].getWallButton(0));
                    wallButtons[row][col].getWallButton(0).setBounds((col * (WIDTH + HEIGHT)) + HEIGHT + OFFSET, OFFSET, WIDTH, HEIGHT);
                }
                if (col == 0){
                    if (maze.getCell(row, col).getWall(3) == 0) wallButtons[row][col].getWallButton(3).setBackground(bgColor);
                    pnl.add(wallButtons[row][col].getWallButton(3));
                    wallButtons[row][col].getWallButton(3).setBounds(OFFSET, (row * (WIDTH + HEIGHT)) + HEIGHT + OFFSET, HEIGHT, WIDTH);
                }
                if (maze.getCell(row, col).getWall(2) == 0) wallButtons[row][col].getWallButton(2).setBackground(bgColor);
                if (maze.getCell(row, col).getWall(1) == 0) wallButtons[row][col].getWallButton(1).setBackground(bgColor);

                pnl.add(wallButtons[row][col].getWallButton(2));
                wallButtons[row][col].getWallButton(2).setBounds(col * (WIDTH + HEIGHT) + HEIGHT + OFFSET, (row + 1) * (WIDTH + HEIGHT) + OFFSET, WIDTH, HEIGHT);

                pnl.add(wallButtons[row][col].getWallButton(1));
                wallButtons[row][col].getWallButton(1).setBounds((col + 1) * (WIDTH + HEIGHT) + OFFSET, row * (WIDTH + HEIGHT) + HEIGHT + OFFSET, HEIGHT, WIDTH);

                wallButtons[row][col].getWallButton(0).addMouseListener(new MouseAdapter() {
                    Color color = wallButtons[finalRow][finalCol].getWallButton(0).getBackground();
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        wallButtons[finalRow][finalCol].getWallButton(0).setBackground(hoverColor);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (color == bgColor) color = wallButton.wallColor;
                        else if (color == wallButton.wallColor) color = bgColor;
                    }
                    @Override
                    public void mouseExited(MouseEvent me) {
                        wallButtons[finalRow][finalCol].getWallButton(0).setBackground(color);
                    }
                });
                wallButtons[row][col].getWallButton(1).addMouseListener(new MouseAdapter() {
                    Color color = wallButtons[finalRow][finalCol].getWallButton(1).getBackground();
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        wallButtons[finalRow][finalCol].getWallButton(1).setBackground(hoverColor);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (color == bgColor) color = wallButton.wallColor;
                        else if (color == wallButton.wallColor) color = bgColor;
                    }
                    @Override
                    public void mouseExited(MouseEvent me) {
                        wallButtons[finalRow][finalCol].getWallButton(1).setBackground(color);
                    }
                });
                wallButtons[row][col].getWallButton(2).addMouseListener(new MouseAdapter() {
                    Color color = wallButtons[finalRow][finalCol].getWallButton(2).getBackground();
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        wallButtons[finalRow][finalCol].getWallButton(2).setBackground(hoverColor);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (color == bgColor) color = wallButton.wallColor;
                        else if (color == wallButton.wallColor) color = bgColor;
                    }
                    @Override
                    public void mouseExited(MouseEvent me) {
                        wallButtons[finalRow][finalCol].getWallButton(2).setBackground(color);
                    }
                });
                wallButtons[row][col].getWallButton(3).addMouseListener(new MouseAdapter() {
                    Color color = wallButtons[finalRow][finalCol].getWallButton(3).getBackground();
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        wallButtons[finalRow][finalCol].getWallButton(3).setBackground(hoverColor);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (color == bgColor) color = wallButton.wallColor;
                        else if (color == wallButton.wallColor) color = bgColor;
                    }
                    @Override
                    public void mouseExited(MouseEvent me) {
                        wallButtons[finalRow][finalCol].getWallButton(3).setBackground(color);
                    }
                });

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
    public static void showDeadEndPercentage(JPanel pnl, Maze maze){
        double deadEndPercentage = MazeGeneration.deadEndPercentage(maze);
        pnl.removeAll();
        pnl.repaint();
        pnl.validate();
        pnl.add(new JLabel("Dead-end percentage: " + deadEndPercentage + "%"));
    }

}
