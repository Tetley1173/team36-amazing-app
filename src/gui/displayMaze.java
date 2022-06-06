package gui;

import components.Cell;
import components.wallButton;
import components.wallButtonCollection;
import mazeFunctions.Maze;
import mazeFunctions.MazeGeneration;
import mazeFunctions.MazeSolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class displayMaze{
    // Image constant
    public static BufferedImage DEFAULT_ENTRY_IMAGE, DEFAULT_EXIT_IMAGE;
    static {
        try {
            DEFAULT_ENTRY_IMAGE = ImageIO.read(new File("src/mazeFunctions/ENTRY_EXIT_IMAGES/Entry_red_circle.png"));
        } catch (IOException e) {
            throw new RuntimeException("No ENTRY IMAGE!!!");
        }
    }
    static {
        try {
            DEFAULT_EXIT_IMAGE = ImageIO.read(new File("src/mazeFunctions/ENTRY_EXIT_IMAGES/EXIT_red_star.png"));
        } catch (IOException e) {
            throw new RuntimeException("No EXIT IMAGE!!!");
        }
    }

    public static BufferedImage UP, DOWN, LEFT, RIGHT;
    
    static {
        try {
            UP = ImageIO.read(new File("src/mazeFunctions/PATH_IMAGES/up.png"));
        } catch (IOException e) {
            throw new RuntimeException("No UP ARROW IMAGE!!!");
        }
    }
    static {
        try {
            DOWN = ImageIO.read(new File("src/mazeFunctions/PATH_IMAGES/down.png"));
        } catch (IOException e) {
            throw new RuntimeException("No DOWN ARROW IMAGE!!!");
        }
    }
    static {
        try {
            LEFT = ImageIO.read(new File("src/mazeFunctions/PATH_IMAGES/left.png"));
        } catch (IOException e) {
            throw new RuntimeException("No LEFT ARROW IMAGE!!!");
        }
    }
    static {
        try {
            RIGHT = ImageIO.read(new File("src/mazeFunctions/PATH_IMAGES/right.png"));
        } catch (IOException e) {
            throw new RuntimeException("No RIGHT ARROW IMAGE!!!");
        }
    }

    public static Color bgColor = EditTab.MAZE_SETUP_PANEL_COLOR;
    public static Color hoverColor = new Color(0x333A48);
    public static final int BUTTON_OFFSET = 5;
    private static final int OFFSET = 20;
    public static int CELL_WIDTH, CELL_HEIGHT;
    public static int OFFSET_X, OFFSET_Y;
    public static wallButtonCollection[][] wallButtons;
    public static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

    public static void setCellSize(JPanel pnl, Maze maze) {
        CELL_WIDTH = (int) Math.floor((pnl.getWidth() - (BUTTON_OFFSET * maze.getCols()) - 2.0 * OFFSET) / maze.getCols());
        CELL_HEIGHT = (int) Math.floor((pnl.getHeight() - (BUTTON_OFFSET * maze.getRows()) - 2.0 * OFFSET) / maze.getRows());
        OFFSET_X = (pnl.getWidth() - ( CELL_WIDTH + BUTTON_OFFSET ) * maze.getCols()) / 2;
        OFFSET_Y = (pnl.getHeight() - ( CELL_HEIGHT + BUTTON_OFFSET ) * maze.getRows()) / 2;
        if (EditTab.hasMaze()) {
            pnl.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    pnl.removeAll();
                    displayMaze.resizeMaze(pnl, EditTab.getMaze());
                    displayMaze.drawMaze(pnl, EditTab.getMaze());
                    if (EditTab.isEntryExitToggled()) showEntryExit(pnl, EditTab.getMaze());
                    if (EditTab.isSolutionToggled()) showOptimumPath(pnl, EditTab.getMaze(), EditTab.getOptimalPath());
                    if (EditTab.getMaze().hasLogo()) showLogo(pnl, EditTab.getMaze());
                    pnl.repaint();
                    pnl.revalidate();
                }
            });
        }

    }
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
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                int finalRow = row;
                int finalCol = col;
                if (row == 0){
                    if (maze.getCell(row, col).getWall(0) == 0) wallButtons[row][col].getWallButton(0).setBackground(bgColor);
                    pnl.add(wallButtons[row][col].getWallButton(0));
                    wallButtons[row][col].getWallButton(0).setBounds((col * (CELL_WIDTH + BUTTON_OFFSET)) + BUTTON_OFFSET + OFFSET_X, OFFSET_Y, CELL_WIDTH, BUTTON_OFFSET);
                }
                if (col == 0){
                    if (maze.getCell(row, col).getWall(3) == 0) wallButtons[row][col].getWallButton(3).setBackground(bgColor);
                    pnl.add(wallButtons[row][col].getWallButton(3));
                    wallButtons[row][col].getWallButton(3).setBounds(OFFSET_X, (row * (CELL_HEIGHT + BUTTON_OFFSET)) + BUTTON_OFFSET + OFFSET_Y, BUTTON_OFFSET, CELL_HEIGHT);
                }
                if (maze.getCell(row, col).getWall(2) == 0) wallButtons[row][col].getWallButton(2).setBackground(bgColor);
                if (maze.getCell(row, col).getWall(1) == 0) wallButtons[row][col].getWallButton(1).setBackground(bgColor);

                pnl.add(wallButtons[row][col].getWallButton(2));
                wallButtons[row][col].getWallButton(2).setBounds(col * (CELL_WIDTH + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_X, (row + 1) * (CELL_HEIGHT + BUTTON_OFFSET) + OFFSET_Y, CELL_WIDTH, BUTTON_OFFSET);

                pnl.add(wallButtons[row][col].getWallButton(1));
                wallButtons[row][col].getWallButton(1).setBounds((col + 1) * (CELL_WIDTH + BUTTON_OFFSET) + OFFSET_X, row * (CELL_HEIGHT + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_Y, BUTTON_OFFSET, CELL_HEIGHT);

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

    public static void resizeMaze(JPanel pnl, Maze maze) {
        int w = pnl.getWidth();
        int h = pnl.getHeight();
        CELL_WIDTH = (int) Math.floor((w - (BUTTON_OFFSET * maze.getCols()) - 2.0 * OFFSET) / maze.getCols());
        CELL_HEIGHT = (int) Math.floor((h - (BUTTON_OFFSET * maze.getRows()) - 2.0 * OFFSET) / maze.getRows());
        OFFSET_X = (w - ( CELL_WIDTH + BUTTON_OFFSET ) * maze.getCols()) / 2;
        OFFSET_Y = (h - ( CELL_HEIGHT + BUTTON_OFFSET ) * maze.getRows()) / 2;
        pnl.setBounds(0, 0, w, h);
        pnl.repaint();
        pnl.revalidate();
    }

    public static void showEntryExit(JPanel pnl, Maze maze) {
        ImageIcon defaultEntryIcon = new ImageIcon(DEFAULT_ENTRY_IMAGE.getScaledInstance(CELL_WIDTH * 3 / 4, CELL_HEIGHT * 3 / 4, Image.SCALE_DEFAULT));
        ImageIcon defaultExitIcon = new ImageIcon(DEFAULT_EXIT_IMAGE.getScaledInstance(CELL_WIDTH * 3 / 4, CELL_HEIGHT * 3 / 4, Image.SCALE_DEFAULT));
        JLabel entry = new JLabel(defaultEntryIcon);
        entry.setBounds(maze.getEntryCell().getCol() * (CELL_WIDTH + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_X,
                maze.getEntryCell().getRow() * (CELL_HEIGHT + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_Y,
                CELL_WIDTH, CELL_HEIGHT);
        JLabel exit = new JLabel(defaultExitIcon);
        exit.setBounds(maze.getExitCell().getCol() * (CELL_WIDTH + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_X,
                maze.getExitCell().getRow() * (CELL_HEIGHT + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_Y,
                CELL_WIDTH, CELL_HEIGHT);
        pnl.add(entry);
        pnl.add(exit);
    }
    public static void showOptimumPath(JPanel pnl, Maze maze, ArrayList<Cell> optimalPath){
        ImageIcon upIcon = new ImageIcon(UP.getScaledInstance(CELL_WIDTH / 2, CELL_HEIGHT / 2, Image.SCALE_DEFAULT));
        ImageIcon downIcon = new ImageIcon(DOWN.getScaledInstance(CELL_WIDTH / 2, CELL_HEIGHT / 2, Image.SCALE_DEFAULT));
        ImageIcon leftIcon = new ImageIcon(LEFT.getScaledInstance(CELL_WIDTH / 2, CELL_HEIGHT / 2, Image.SCALE_DEFAULT));
        ImageIcon rightIcon = new ImageIcon(RIGHT.getScaledInstance(CELL_WIDTH / 2, CELL_HEIGHT / 2, Image.SCALE_DEFAULT));
        Cell previous = null;
        for (Cell c: optimalPath) {
            JLabel path;

            if (c.equals(maze.getEntryCell()) || c.equals(maze.getExitCell())) {
                previous = c;
                continue;
            }

            if (c.getRow() == previous.getRow()) {
                if (c.getCol() > previous.getCol()) {
                    path = new JLabel(leftIcon);
                    path.setBounds(c.getCol() * (CELL_WIDTH + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_X,
                            c.getRow() * (CELL_HEIGHT + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_Y,
                            CELL_WIDTH, CELL_HEIGHT);
                    pnl.add(path);
                }
                else {
                    path = new JLabel(rightIcon);
                    path.setBounds(c.getCol() * (CELL_WIDTH + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_X,
                            c.getRow() * (CELL_HEIGHT + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_Y,
                            CELL_WIDTH, CELL_HEIGHT);
                    pnl.add(path);
                }
            }
            else {
                if (c.getRow() > previous.getRow()) {
                    path = new JLabel(upIcon);
                    path.setBounds(c.getCol() * (CELL_WIDTH + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_X,
                            c.getRow() * (CELL_HEIGHT + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_Y,
                            CELL_WIDTH, CELL_HEIGHT);
                    pnl.add(path);
                }
                else {
                    path = new JLabel(downIcon);
                    path.setBounds(c.getCol() * (CELL_WIDTH + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_X,
                            c.getRow() * (CELL_HEIGHT + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_Y,
                            CELL_WIDTH, CELL_HEIGHT);
                    pnl.add(path);
                }
            }
            previous = c;
        }
        pnl.repaint();
        pnl.revalidate();
    }
    public static void showLogo(JPanel pnl, Maze maze) {
        ImageIcon logo = maze.getLogoIcon1();
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(maze.getLogoCol() * (CELL_WIDTH + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_X,
                maze.getLogoRow() * (CELL_HEIGHT + BUTTON_OFFSET) + BUTTON_OFFSET + OFFSET_Y,
                maze.getLogoWidth() * (CELL_WIDTH + BUTTON_OFFSET) - BUTTON_OFFSET,
                maze.getLogoHeight() * (CELL_HEIGHT + BUTTON_OFFSET) - BUTTON_OFFSET);
        pnl.add(logoLabel);
        pnl.repaint();
        pnl.revalidate();
    }
    public static void showDeadEndPercentage (JPanel pnl, Maze maze){
        double deadEndPercentage = MazeGeneration.deadEndPercentage(maze);
        pnl.add(new JLabel(" Dead-end percentage: " + (new BigDecimal(deadEndPercentage)).round(new MathContext(4)).doubleValue() + "%"));
    }
    public static void showDimensionOfMaze (JPanel pnl, Maze maze) {
        pnl.add(new JLabel(" ROWS: " + maze.getRows()));
        pnl.add(new JLabel(" COLS: " + maze.getCols()));
    }
    public static void showAuthor (JPanel pnl, Maze maze) {
        pnl.add(new JLabel(" Authors: " + (maze.getAuthor()==null? " - " : maze.getAuthor())));
    }
    public static void showCreatedDateTime (JPanel pnl, Maze maze) {
        String createdTime = maze.getCreatedDateTime().format(format);
        pnl.add(new JLabel(" Created time: " + createdTime));
    }
    public static void showLastEditedTime (JPanel pnl, Maze maze) {
        String lastEditTime = maze.getCreatedDateTime().format(format);
        pnl.add(new JLabel(" Last edited time: " + lastEditTime));
    }
    public static void showPathReachingPercentage (JPanel pnl,  MazeSolution mazeSolution) {
        if (mazeSolution != null) {
            double percentage = new BigDecimal(mazeSolution.getPathPercentage()).round(new MathContext(4)).doubleValue();
            pnl.add(new JLabel(" Path reach percentage: " + percentage + "% "));
            pnl.repaint();
            pnl.revalidate();
        }

    }
    public static void showMazeName (JPanel pnl, Maze maze) {
        pnl.add(new JLabel(" Maze name: " + (maze.getMazeName() == null? " - " : maze.getMazeName())));
    }
}
