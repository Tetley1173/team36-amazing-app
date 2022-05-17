package gui;

import components.wallButton;
import components.wallButtonCollection;
import mazeFunctions.Maze;
import mazeFunctions.MazeGeneration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

public class displayMaze{
    // Image constant
    public static BufferedImage DEFAULT_ENTRY_IMAGE;
    static {
        try {
            DEFAULT_ENTRY_IMAGE = ImageIO.read(new File("src/mazeFunctions/ENTRY_EXIT_IMAGES/Entry_red_circle.png"));
        } catch (IOException e) {
            throw new RuntimeException("No ENTRY IMAGE!!!");
        }
    }
    public static BufferedImage DEFAULT_EXIT_IMAGE;
    static {
        try {
            DEFAULT_EXIT_IMAGE = ImageIO.read(new File("src/mazeFunctions/ENTRY_EXIT_IMAGES/EXIT_red_star.png"));
        } catch (IOException e) {
            throw new RuntimeException("No EXIT IMAGE!!!");
        }
    }

    // Images
    private static ImageIcon defaultEntryIcon;
    private static ImageIcon defaultExitIcon;

    public static Color bgColor = Color.DARK_GRAY;
    public static Color hoverColor = Color.ORANGE;
    private static final int BUTTON_OFFSET = 5;
    private static final int OFFSET = 20;
    private static int WIDTH, HEIGHT;
    private static int OFFSET_X, OFFSET_Y;
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
        WIDTH = (int) Math.floor((pnl.getHeight() - (BUTTON_OFFSET * maze.getCols()) - 2*OFFSET)/maze.getCols());
        HEIGHT = (int) Math.floor((pnl.getHeight() - (BUTTON_OFFSET * maze.getRows()) - 2*OFFSET)/maze.getRows());
        OFFSET_X = (pnl.getHeight() - ( WIDTH + BUTTON_OFFSET ) * maze.getCols())/2;
        OFFSET_Y = (pnl.getHeight() - ( HEIGHT + BUTTON_OFFSET ) * maze.getRows())/2;

        pnl.setLayout(null);
        setWallButtons(maze);
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                int finalRow = row;
                int finalCol = col;
                if (row == 0){
                    if (maze.getCell(row, col).getWall(0) == 0) wallButtons[row][col].getWallButton(0).setBackground(bgColor);
                    pnl.add(wallButtons[row][col].getWallButton(0));
                    wallButtons[row][col].getWallButton(0).setBounds((col * (WIDTH + BUTTON_OFFSET)) + BUTTON_OFFSET + OFFSET_X, OFFSET_Y, WIDTH, BUTTON_OFFSET);
                }
                if (col == 0){
                    if (maze.getCell(row, col).getWall(3) == 0) wallButtons[row][col].getWallButton(3).setBackground(bgColor);
                    pnl.add(wallButtons[row][col].getWallButton(3));
                    wallButtons[row][col].getWallButton(3).setBounds(OFFSET_X, (row * (HEIGHT + BUTTON_OFFSET)) + BUTTON_OFFSET + OFFSET_Y, BUTTON_OFFSET, HEIGHT);
                }
                if (maze.getCell(row, col).getWall(2) == 0) wallButtons[row][col].getWallButton(2).setBackground(bgColor);
                if (maze.getCell(row, col).getWall(1) == 0) wallButtons[row][col].getWallButton(1).setBackground(bgColor);

                pnl.add(wallButtons[row][col].getWallButton(2));
                wallButtons[row][col].getWallButton(2).setBounds(col * (WIDTH + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_X, (row + 1) * (HEIGHT + BUTTON_OFFSET) + OFFSET_Y, WIDTH, BUTTON_OFFSET);

                pnl.add(wallButtons[row][col].getWallButton(1));
                wallButtons[row][col].getWallButton(1).setBounds((col + 1) * (WIDTH + BUTTON_OFFSET) + OFFSET_X, row * (HEIGHT + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_Y, BUTTON_OFFSET, HEIGHT);

                maze.setEntryCell();
                maze.setExitCell();

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
        defaultEntryIcon = new ImageIcon(DEFAULT_ENTRY_IMAGE.getScaledInstance(WIDTH*3/4, HEIGHT*3/4, Image.SCALE_DEFAULT));
        defaultExitIcon = new ImageIcon(DEFAULT_EXIT_IMAGE.getScaledInstance(WIDTH*3/4, HEIGHT*3/4, Image.SCALE_DEFAULT));
        JLabel entry = new JLabel(defaultEntryIcon);
        entry.setBounds(maze.getEntryCell().getCol() * (WIDTH + BUTTON_OFFSET) + OFFSET_X*5/4,
                        maze.getEntryCell().getRow() * HEIGHT*(HEIGHT + BUTTON_OFFSET) + OFFSET_Y*5/4,
                     WIDTH, HEIGHT);
        JLabel exit = new JLabel(defaultExitIcon);
        exit.setBounds(maze.getExitCell().getCol() * (WIDTH + BUTTON_OFFSET) + OFFSET_X*5/4,
                       maze.getExitCell().getRow() * (HEIGHT + BUTTON_OFFSET) + OFFSET_Y*5/4,
                    WIDTH, HEIGHT);
        pnl.add(entry);
        pnl.add(exit);
    }

    public void showOptimumPath(JPanel pnl, Maze maze){}
    public void showOptimumPathReachingPercentage(JPanel pnl, Maze maze){}
    public static void showDeadEndPercentage(JPanel pnl, Maze maze){
        double deadEndPercentage = MazeGeneration.deadEndPercentage(maze);
        pnl.add(new JLabel("Dead-end percentage: " + (new BigDecimal(deadEndPercentage)).round(new MathContext(4)).doubleValue() + "%"));
    }
    public static void showDimensionOfMaze(JPanel pnl, Maze maze) {
        pnl.add(new JLabel("ROWS: " + maze.getRows()));
        pnl.add(new JLabel("COLS: " + maze.getCols()));
    }
}
