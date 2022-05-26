package gui;

import mazeFunctions.Maze;
import mazeFunctions.MazeGeneration;
import mazeFunctions.MazeWithoutImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EditTab extends JFrame{
    // Constants
    public final static int MAZE_SETUP_PANEL_WIDTH = 200;
    public final static int MAZE_SETUP_PANEL_HEIGHT = 240;
    public final static int LOGO_PANEL_WIDTH = 200;
    public final static int LOGO_SETUP_PANEL_HEIGHT = 100;
    private final static String IMAGE_OUTPUT_FORMAT = "png";
    private final Color MAZE_SETUP_PANEL_COLOR = new Color(0x4B566C);
    private final Color BUTTON_COLOR = new Color(0xABA9C3);
    private final SpinnerNumberModel rowsDecisionNumModel = new SpinnerNumberModel(20, 2, 100, 1);
    private final SpinnerNumberModel colsDecisionNumModel = new SpinnerNumberModel(20, 2, 100, 1);
    private final SpinnerNumberModel logoRowsNumModel = new SpinnerNumberModel(2, 2, 7, 1);
    private final SpinnerNumberModel logoColsNumModel = new SpinnerNumberModel(2, 2, 7, 1);
    private final Dimension spinnerDim = new Dimension(70, 20);

    // Panels
    private final JPanel editTab, mazeEditPanel, setupAndInfoPanel, mazeSetupPanel, logoSetupPanel, mazeInfoPanel;
    // Buttons
    private final JButton mazeGeneration, BlankGenerate, EraseButton, ScreenShot;
    private final JButton toggleOptimumPath, toggleEntryExit;
    private final JSpinner rowDecision, colDecision, logoRowDecision, logoColDecision;
    private final JCheckBox insertLogo;


    private boolean isEntryExitToggled = false;
    private boolean isSolutionToggled = false;

    // Maze
    public static Maze maze;

    public EditTab() {
        // create the edit tab
        editTab = new JPanel(new BorderLayout());
        // the maze drawing panel
        mazeEditPanel = createMazeDrawPanel(Color.DARK_GRAY);
        // the left panel in the edit tab
        setupAndInfoPanel = createButtonPanel(Color.DARK_GRAY);
        // the top side of the left panel
        mazeSetupPanel = createSetupPanel();
        // logo setup panel
        logoSetupPanel = createLogoSetupPanel();
        // Panel displaying info of the maze
        mazeInfoPanel = createInfoPanel();
        // Temporary Panel on the right, will be using it in the future
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(MAZE_SETUP_PANEL_COLOR);
        rightPanel.setPreferredSize(new Dimension(MAZE_SETUP_PANEL_WIDTH,MAZE_SETUP_PANEL_HEIGHT));

        // Spinners for rows and cols inputs
        // rows and cols
        rowDecision = createSpinner(rowsDecisionNumModel, spinnerDim, true);
        colDecision = createSpinner(colsDecisionNumModel, spinnerDim, true);

        // Button for auto-gen maze display
        mazeGeneration = createButton("Generate", true, this::mazeGen);
        // Button for blank maze display
        BlankGenerate = createButton("Blank", true, this::blankMazeGen);
        // Button to erase the maze display
        EraseButton = createButton("Erase", false, this::eraseMaze);

        // Toggle the optimum path (should be a colored line)
        toggleOptimumPath = createButton("Optimum Solution", false, this::setToggleOptimumPath);
        // Toggle the Entry and Exit (Red - Entry, Green, Exit)
        toggleEntryExit= createButton("Show Entry/Exit", false, this::setToggleEntryExit);
        // Save the screenshot of the maze
        ScreenShot = createButton("Screenshot",false, this::FolderExplorer);

        // number of rows and cols occupied by the logo
        logoRowDecision = createSpinner(logoRowsNumModel, spinnerDim, false);
        logoColDecision =  createSpinner(logoColsNumModel, spinnerDim, false);
        insertLogo = createCheckbox(this::putLogo);

        LogoSetupPanelLayoutSetup();
        InfoPanelLayoutSetup();

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
        EraseButton.setEnabled(!EraseButton.isEnabled());
        toggleEntryExit.setEnabled(!toggleEntryExit.isEnabled());
        toggleOptimumPath.setEnabled(!toggleOptimumPath.isEnabled());
        ScreenShot.setEnabled(!ScreenShot.isEnabled());
        mazeGeneration.setEnabled(!mazeGeneration.isEnabled());
        BlankGenerate.setEnabled(!BlankGenerate.isEnabled());
        rowDecision.setEnabled(!rowDecision.isEnabled());
        colDecision.setEnabled(!colDecision.isEnabled());
    }
    private void InfoPanelLayoutSetup() {
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
        mazeSetupPanel.add(EraseButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2; gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mazeSetupPanel.add(toggleOptimumPath, gbc);
        gbc.gridy = 5;
        mazeSetupPanel.add(toggleEntryExit, gbc);
        gbc.gridy = 6; gbc.gridwidth = 2;
        mazeSetupPanel.add(ScreenShot, gbc);
    }
    private void LogoSetupPanelLayoutSetup() {
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

            System.out.println("Has logo");
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
            maze.setLogo( new ImageIcon(companyLogo.getScaledInstance(
                    (logoWidth * (displayMaze.CELL_WIDTH + displayMaze.BUTTON_OFFSET)) - displayMaze.BUTTON_OFFSET,
                    (logoHeight * (displayMaze.CELL_HEIGHT + displayMaze.BUTTON_OFFSET)) - displayMaze.BUTTON_OFFSET,
                    Image.SCALE_DEFAULT)));
        }
        else {
            System.out.println("No logo");
            maze = new MazeWithoutImage(rows, cols);
            displayMaze.setCellSize(mazeEditPanel, maze);
            displayMaze.setWallButtons(maze);
        }
    }
    private void displayMazeObject() {
        componentReset();
        insertLogo.setEnabled(false);
        logoRowDecision.setEnabled(false);
        logoColDecision.setEnabled(false);
        // remove all the things in the maze panel
        mazeEditPanel.removeAll();

        if(insertLogo.isSelected())displayMaze.showLogo(mazeEditPanel, maze);
        displayMaze.drawMaze(mazeEditPanel, maze);
        mazeEditPanel.repaint();

        mazeInfoPanel.removeAll();
        displayMaze.showDeadEndPercentage(mazeInfoPanel, maze);
        displayMaze.showDimensionOfMaze(mazeInfoPanel, maze);
        mazeInfoPanel.repaint();
    }

    private void blankMazeGen(ActionEvent event) {
        try {
            createMazeObject();
            // Display the auto-gen maze
            displayMazeObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void eraseMaze(ActionEvent event) {
        componentReset();
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
        mazeEditPanel.validate();
        mazeInfoPanel.removeAll();
        mazeInfoPanel.repaint();
        mazeInfoPanel.validate();
    }
    private void setToggleEntryExit(ActionEvent event) {
        isEntryExitToggled = !isEntryExitToggled;
        toggleEntryExit.setText(isEntryExitToggled? "Hide Entry & Exit":"Show Entry and Exit");
        mazeEditPanel.removeAll();
        if (isEntryExitToggled) displayMaze.showEntryExit(mazeEditPanel, maze);
        if(maze.getHasLogo()) displayMaze.showLogo(mazeEditPanel, maze);
        displayMaze.drawMaze(mazeEditPanel, maze);
        mazeEditPanel.repaint();
    }
    private void setToggleOptimumPath(ActionEvent event) {
    }
    private void putLogo(ActionEvent event) {
        logoRowDecision.setEnabled(!logoRowDecision.isEnabled());
        logoColDecision.setEnabled(!logoColDecision.isEnabled());
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
        return mazeDraw;
    }
    // create the buttonPanel
    private JPanel createButtonPanel(Color bgColor) {
        JPanel buttonPnl = new JPanel(new BorderLayout());
        buttonPnl.setBackground(bgColor);
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


}