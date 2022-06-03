package gui;

import components.Cell;
import mazeFunctions.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class EditTab extends JFrame{
    // Constants
    public final static int MAZE_SETUP_PANEL_WIDTH = 200;
    public final static int MAZE_SETUP_PANEL_HEIGHT = 240;
    public final static int LOGO_PANEL_WIDTH = 200;
    public final static int LOGO_SETUP_PANEL_HEIGHT = 100;
    private final static String IMAGE_OUTPUT_FORMAT = "png";
    public final static Color MAZE_SETUP_PANEL_COLOR = new Color(0x4B566C);
    public final static Color BUTTON_COLOR = new Color(0xABA9C3);
    private final SpinnerNumberModel rowsDecisionNumModel = new SpinnerNumberModel(10, 2, 100, 1);
    private final SpinnerNumberModel colsDecisionNumModel = new SpinnerNumberModel(10, 2, 100, 1);
    private final SpinnerNumberModel logoRowsNumModel = new SpinnerNumberModel(2, 2, 7, 1);
    private final SpinnerNumberModel logoColsNumModel = new SpinnerNumberModel(2, 2, 7, 1);
    private final Dimension spinnerDim = new Dimension(70, 20);

    // Panels
    private final JPanel editTab, mazeEditPanel, setupAndInfoPanel, mazeSetupPanel, logoSetupPanel, mazeInfoPanel;

    // Buttons - Left panels
    private final JButton mazeGeneration, BlankGenerate, discardButton, exportMazeImage;
    private final JButton toggleOptimumPath, toggleEntryExit;
    private final JSpinner rowDecision, colDecision, logoRowDecision, logoColDecision;
    private final JCheckBox insertLogo;

    // Buttons - Right panels
    private final JButton saveMazeImage;


    private static boolean isEntryExitToggled = false;
    public static boolean isEntryExitToggled() { return isEntryExitToggled; }
    private static boolean isSolutionToggled = false;
    public static boolean isSolutionToggled() { return isSolutionToggled; }
    private static boolean hasMaze = false;
    public static boolean hasMaze() { return hasMaze;}

    // Maze
    private static Maze maze;
    private static ArrayList<Cell> optimalPath;
    public static ArrayList<Cell> getOptimalPath() { return optimalPath; }
    private static mazeCollection mazes;

    public EditTab() {
        mazes = new mazeCollection();
        // create the edit tab
        editTab = new JPanel(new BorderLayout());
        // the maze drawing panel
        mazeEditPanel = createMazeDrawPanel(MAZE_SETUP_PANEL_COLOR);
        // the left panel in the edit tab
        setupAndInfoPanel = createButtonPanel(Color.DARK_GRAY);
        // the top side of the left panel
        mazeSetupPanel = createSetupPanel();
        // logo setup panel
        logoSetupPanel = createLogoSetupPanel();
        // Panel displaying info of the maze
        mazeInfoPanel = createInfoPanel();
        // Temporary Panel on the right, will be using it in the future
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(MAZE_SETUP_PANEL_COLOR);
        rightPanel.setPreferredSize(new Dimension(MAZE_SETUP_PANEL_WIDTH,MAZE_SETUP_PANEL_HEIGHT));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));

        // Spinners for rows and cols inputs
        // rows and cols
        rowDecision = createSpinner(rowsDecisionNumModel, spinnerDim, true);
        colDecision = createSpinner(colsDecisionNumModel, spinnerDim, true);

        // Button for auto-gen maze display
        mazeGeneration = createButton("Generate", true, this::mazeGen);
        // Button for blank maze display
        BlankGenerate = createButton("Blank", true, this::blankMazeGen);
        // Button to erase the maze display
        discardButton = createButton("Discard", false, this::discardMaze);

        // Toggle the optimum path (should be a colored line)
        toggleOptimumPath = createButton("Show Optimum Solution", false, this::setToggleOptimumPath);
        // Toggle the Entry and Exit (Red - Entry, Green, Exit)
        toggleEntryExit= createButton("Show Entry/Exit", false, this::setToggleEntryExit);
        // Save the screenshot of the maze


        // number of rows and cols occupied by the logo
        logoRowDecision = createSpinner(logoRowsNumModel, spinnerDim, false);
        logoColDecision =  createSpinner(logoColsNumModel, spinnerDim, false);
        insertLogo = createCheckbox(this::putLogo);

        LogoSetupPanelLayoutSetup(logoSetupPanel);
        InfoPanelLayoutSetup(mazeSetupPanel);

        saveMazeImage = createButton("Save", false, this::saveMaze);
        exportMazeImage = createButton("Export as image",false, this::FolderExplorer);
        rightPanelSetup(rightPanel);


        editTab.add(mazeEditPanel, BorderLayout.CENTER);
        editTab.add(setupAndInfoPanel, BorderLayout.WEST);
        editTab.add(rightPanel, BorderLayout.EAST);
        setupAndInfoPanel.add(mazeSetupPanel, BorderLayout.NORTH);


        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(MAZE_SETUP_PANEL_COLOR);
        setupAndInfoPanel.add(pnl, BorderLayout.CENTER);
        pnl.add(logoSetupPanel, BorderLayout.NORTH);

        pnl.add(mazeInfoPanel, BorderLayout.SOUTH);

    }
    private void componentReset() {
        discardButton.setEnabled(!discardButton.isEnabled());
        toggleEntryExit.setEnabled(!toggleEntryExit.isEnabled());
        toggleOptimumPath.setEnabled(!toggleOptimumPath.isEnabled());
        mazeGeneration.setEnabled(!mazeGeneration.isEnabled());
        BlankGenerate.setEnabled(!BlankGenerate.isEnabled());
        rowDecision.setEnabled(!rowDecision.isEnabled());
        colDecision.setEnabled(!colDecision.isEnabled());
        exportMazeImage.setEnabled(!exportMazeImage.isEnabled());
    }
    private void InfoPanelLayoutSetup(JPanel mazeSetupPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.gridx = 0; gbc.gridy = 0;
        mazeSetupPanel.add(new JLabel("ROWS:"), gbc);
        gbc.gridy = 1;
        mazeSetupPanel.add(new JLabel("COLS:"), gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mazeSetupPanel.add(rowDecision, gbc);
        gbc.gridy = 1;
        mazeSetupPanel.add(colDecision, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1; gbc.gridheight = 1;
        mazeSetupPanel.add(mazeGeneration, gbc);
        gbc.gridy = 3;
        mazeSetupPanel.add(BlankGenerate, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 1; gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mazeSetupPanel.add(discardButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2; gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mazeSetupPanel.add(toggleOptimumPath, gbc);
        gbc.gridy = 5;
        mazeSetupPanel.add(toggleEntryExit, gbc);
    }
    private void LogoSetupPanelLayoutSetup(JPanel logoSetupPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1; gbc.gridheight = 1;
        logoSetupPanel.add(new JLabel("Insert logo:"), gbc);
        gbc.gridy = 1;
        logoSetupPanel.add(new JLabel("ROWS:"), gbc);
        gbc.gridy = 2;
        logoSetupPanel.add(new JLabel("COLS:"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1; gbc.gridy = 0;
        logoSetupPanel.add(insertLogo, gbc);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy = 1;
        logoSetupPanel.add(logoRowDecision, gbc);
        gbc.gridy = 2;
        logoSetupPanel.add(logoColDecision, gbc);
    }
    private void rightPanelSetup(JPanel rightPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2; gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(saveMazeImage, gbc);
        gbc.gridy = 1; gbc.weighty = 100;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        rightPanel.add(exportMazeImage, gbc);
    }
    private void FolderExplorer(ActionEvent event) {
        FileDialog folder = new FileDialog(new Frame(), "Save Image", FileDialog.SAVE);
        folder.setMultipleMode(false);
        folder.setVisible(true);
        if(folder.getDirectory().length() > 0){
            String aPath = folder.getFile();
            System.out.println(aPath);
            try {
                BufferedImage img = new BufferedImage(mazeEditPanel.getHeight(), mazeEditPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                mazeEditPanel.paint(img.getGraphics());
                ImageIO.write(img, IMAGE_OUTPUT_FORMAT, new File(folder.getDirectory(), folder.getFile() + "." + IMAGE_OUTPUT_FORMAT));
                JOptionPane.showMessageDialog(this,
                        "Done!",
                        "Image Export", JOptionPane.INFORMATION_MESSAGE);
            } catch (NullPointerException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Missing image file, please select an appropriate image file.",
                        "Missing image file: Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Invalid folder selected, please select an appropriate file.",
                        "Invalid folder selection: Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Missing image file, please select an appropriate image file.",
                    "Missing image file: Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void mazeGen(ActionEvent event) {
        try {
            hasMaze = true;
            createMazeObject();
            maze = MazeGeneration.genMaze(maze);
            // Display the auto-gen maze
            displayMazeObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void createMazeObject() throws Exception {
        int rows = (int)rowDecision.getValue();
        int cols = (int)colDecision.getValue();
        if (insertLogo.isSelected()) {
            int logoHeight = (int) logoRowDecision.getValue();
            int logoWidth = (int) logoColDecision.getValue();
            if (logoHeight >= rows - 1 || logoWidth >= cols - 1)
                throw new Exception("The logo is too big.\n" + " Choose a smaller size of logo or a bigger size of maze");
            // Choose Logo
            // Now will be using the entry image for testing
            BufferedImage companyLogo;
            try {
                companyLogo = ImageIO.read(new File("src/mazeFunctions/ENTRY_EXIT_IMAGES/Entry_red_circle.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            maze = new MazeWithoutImage(rows, cols);
            displayMaze.setWallButtons(maze);
            maze.setHasLogo(true);
            maze.setLogoDimension(logoHeight, logoWidth);
            int CELL_ID = maze.chooseLocation(rows, cols, logoHeight, logoWidth);
            int logoRow = CELL_ID / rows;
            int logoCol = CELL_ID % rows;
            maze.setLogoLocation(logoRow, logoCol);
            maze.spareLocation(logoRow, logoCol, logoHeight, logoWidth);
            displayMaze.setCellSize(mazeEditPanel, maze);
            maze.setLogoIcon1( new ImageIcon(companyLogo.getScaledInstance(
                    (logoWidth * (displayMaze.CELL_WIDTH + displayMaze.BUTTON_OFFSET)) - displayMaze.BUTTON_OFFSET,
                    (logoHeight * (displayMaze.CELL_HEIGHT + displayMaze.BUTTON_OFFSET)) - displayMaze.BUTTON_OFFSET,
                    Image.SCALE_DEFAULT)));
        }
        else {
            maze = new MazeWithoutImage(rows, cols);
            displayMaze.setCellSize(mazeEditPanel, maze);
            displayMaze.setWallButtons(maze);
        }
    }
    private void displayMazeObject() {
        componentReset();
        saveMazeImage.setEnabled(true);
        insertLogo.setEnabled(false);
        logoRowDecision.setEnabled(false);
        logoColDecision.setEnabled(false);
        // remove all the things in the maze panel
        mazeEditPanel.removeAll();

        if(insertLogo.isSelected())displayMaze.showLogo(mazeEditPanel, maze);
        displayMaze.drawMaze(mazeEditPanel, maze);
        mazeEditPanel.repaint();
        mazeEditPanel.revalidate();

        mazeInfoPanel.removeAll();
        displayMaze.showDeadEndPercentage(mazeInfoPanel, maze);
        displayMaze.showDimensionOfMaze(mazeInfoPanel, maze);
        mazeInfoPanel.repaint();
        mazeInfoPanel.revalidate();
    }

    private void blankMazeGen(ActionEvent event) {
        try {
            hasMaze = true;
            createMazeObject();
            // Display the auto-gen maze
            displayMazeObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void discardMaze(ActionEvent event) {
        componentReset();
        hasMaze = false;
        isSolutionToggled = false;
        toggleOptimumPath.setText("Show Optimal Solution");
        isEntryExitToggled = false;
        toggleEntryExit.setText("Show Entry and Exit");
        saveMazeImage.setEnabled(false);
        insertLogo.setEnabled(true);
        insertLogo.setSelected(false);
        logoRowDecision.setEnabled(false);
        logoRowDecision.setValue(2);
        logoColDecision.setEnabled(false);
        logoColDecision.setValue(2);
        toggleEntryExit.setSelected(false);
        toggleOptimumPath.setSelected(false);
        mazeEditPanel.removeAll();
        mazeEditPanel.repaint();
        mazeEditPanel.revalidate();
        mazeInfoPanel.removeAll();
        mazeInfoPanel.repaint();
        mazeInfoPanel.revalidate();
    }
    private void setToggleEntryExit(ActionEvent event) {
        isEntryExitToggled = !isEntryExitToggled;
        toggleEntryExit.setText(isEntryExitToggled? "Hide Entry & Exit":"Show Entry and Exit");
        mazeEditPanel.removeAll();
        if (isEntryExitToggled) displayMaze.showEntryExit(mazeEditPanel, maze);
        if (isSolutionToggled) displayMaze.showOptimumPath(mazeEditPanel, maze, optimalPath);
        if(maze.hasLogo()) displayMaze.showLogo(mazeEditPanel, maze);
        displayMaze.drawMaze(mazeEditPanel, maze);
        mazeEditPanel.repaint();
        mazeEditPanel.revalidate();
    }
    private void setToggleOptimumPath(ActionEvent event) {
        isSolutionToggled = !isSolutionToggled;
        mazeEditPanel.removeAll();
        MazeSolution mazeSolution;
        if (isSolutionToggled) {
            mazeSolution = new MazeSolution(maze);
            if (mazeSolution.isHasSolution()) {
                toggleOptimumPath.setText("Hide Optimal Solution");
                optimalPath = mazeSolution.getOptimalPath();
                displayMaze.showOptimumPath(mazeEditPanel, maze, optimalPath);

                System.out.println("Solvable");
                System.out.println("Reach percentage: " + mazeSolution.getPathPercentage() + "%");
            }
            else {
                System.out.println("Not solvable");
                isSolutionToggled = false;
            }
        }
        else {
            toggleOptimumPath.setText("Show Optimal Solution");
        }
        displayMaze.drawMaze(mazeEditPanel, maze);
        if(maze.hasLogo()) displayMaze.showLogo(mazeEditPanel, maze);
        if (isEntryExitToggled) displayMaze.showEntryExit(mazeEditPanel, maze);
        mazeEditPanel.revalidate();
        mazeEditPanel.repaint();


    }
    private void putLogo(ActionEvent event) {
        logoRowDecision.setEnabled(!logoRowDecision.isEnabled());
        logoColDecision.setEnabled(!logoColDecision.isEnabled());
    }
    private void saveMaze(ActionEvent event) {
        JFrame exportFrame = new JFrame();
        JPanel pnl = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JTextField maze_name = new JTextField();
        JTextField author = new JTextField();
        maze_name.setPreferredSize(new Dimension(100, 20));
        author.setPreferredSize(new Dimension(100, 20));

        JButton confirm = new JButton("Confirm");

        pnl.setMinimumSize(new Dimension(500, 500));

        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1; gbc.gridheight = 1;
        pnl.add(new JLabel("Maze name:"), gbc);
        gbc.gridy = 1;
        pnl.add(new JLabel("Author:"), gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;gbc.gridy = 0;
        pnl.add(maze_name, gbc);
        gbc.gridy = 1;
        pnl.add(author, gbc);
        gbc.gridwidth = 2;
        gbc.gridy = 2;
        gbc.gridx = 0;
        pnl.add(confirm, gbc);

        confirm.addActionListener(e -> {
            String mazeName = maze_name.getText();
            String authorName = author.getText();
            if (mazeName.isBlank() || authorName.isBlank()) JOptionPane.showMessageDialog(this,
                        "Please fill in the blank.");
            else {
                maze.setMazeName(mazeName);
                maze.setAuthor(authorName);
                mazes.addMaze(maze);
                exportFrame.dispose();
                JOptionPane.showMessageDialog(this,"Done");
            }

        });


        exportFrame.setTitle("Save maze");
        exportFrame.setPreferredSize(new Dimension(300, 300));
        exportFrame.getContentPane().add(pnl);
        exportFrame.setLocationRelativeTo(null);
        exportFrame.pack();
        exportFrame.setVisible(true);

    }

    private JButton createButton(String buttonName, boolean isEnable, ActionListener action) {
        JButton btn = new JButton(buttonName);
        btn.setBackground(BUTTON_COLOR);
        btn.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        btn.setPreferredSize(new Dimension (80, 30));
        btn.setEnabled(isEnable);
        btn.addActionListener(action);
        return btn;
    }
    private JSpinner createSpinner(SpinnerNumberModel numModel, Dimension dim, boolean isEnable) {
        JSpinner spinner = new JSpinner(numModel);
        spinner.setPreferredSize(dim);
        spinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        spinner.setEnabled(isEnable);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setBackground(MAZE_SETUP_PANEL_COLOR);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setForeground(Color.WHITE);
        return spinner;
    }
    private JCheckBox createCheckbox(ActionListener action) {
        JCheckBox checkbox = new JCheckBox();
        checkbox.setBackground(MAZE_SETUP_PANEL_COLOR);
        checkbox.setBorder(BorderFactory.createEmptyBorder());
        checkbox.setHorizontalAlignment(SwingConstants.CENTER);
        checkbox.setVerticalAlignment(SwingConstants.CENTER);
        checkbox.addActionListener(action);
        return checkbox;
    }
    // create the maze drawing panel
    private JPanel createMazeDrawPanel(Color bgColor) {
        JPanel mazeDraw = new JPanel();
        mazeDraw.setBackground(bgColor);
        mazeDraw.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        return mazeDraw;
    }
    // create the buttonPanel
    private JPanel createButtonPanel(Color bgColor) {
        JPanel buttonPnl = new JPanel(new BorderLayout());
        buttonPnl.setBackground(bgColor);
        buttonPnl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        return buttonPnl;
    }
    private JPanel createSetupPanel() {
        JPanel setupPanel = new JPanel(new GridBagLayout());
        setupPanel.setPreferredSize(new Dimension(MAZE_SETUP_PANEL_WIDTH, MAZE_SETUP_PANEL_HEIGHT));
        setupPanel.setBackground(MAZE_SETUP_PANEL_COLOR);
        setupPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        return setupPanel;
    }
    private JPanel createLogoSetupPanel() {
        JPanel logoSetupPanel = new JPanel(new GridBagLayout());
        logoSetupPanel.setPreferredSize(new Dimension(LOGO_PANEL_WIDTH, LOGO_SETUP_PANEL_HEIGHT));
        logoSetupPanel.setBackground(MAZE_SETUP_PANEL_COLOR);
        logoSetupPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        return logoSetupPanel;
    }
    private JPanel createInfoPanel() {
        JPanel infoPanel =  new JPanel(new GridLayout(3, 1));
        infoPanel.setBackground(MAZE_SETUP_PANEL_COLOR);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
        return infoPanel;
    }


    public JPanel getTab() { return editTab; }
    public static Maze getMaze() { return maze; }
    public static mazeCollection getMazes() { return mazes; }

}