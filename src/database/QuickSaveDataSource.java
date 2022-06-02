package database;

import mazeFunctions.ImageAssetFile;
import mazeFunctions.Maze;
import mazeFunctions.MazeWithoutImage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

import static collections.HelperMethods.*;

/**
 * This class creates the quickSaveFiles table that contains the images for the maze. It contains methods for loading, saving and deleting
 * data from the database. Note that this was copied from the week 6 tutorial and adapted to be compatible with this project.
 *
 * @author Dilshan Perera & Georgia Meszaros simon
 */

public class QuickSaveDataSource implements QuickSaveInterface {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS quickSaveFiles ("
                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE," // from https://stackoverflow.com/a/41028314
                    + "name VARCHAR(30) NOT NULL UNIQUE;";

    private static final String INSERT_QUICKSAVE = "INSERT INTO quickSaveFiles (name, imageFile) VALUES (?, ?);";

    private static final String GET_NAMES = "SELECT name FROM quickSaveFiles";

    private static final String GET_QUICKSAVE = "SELECT * FROM quickSaveFiles WHERE name=?";

    private static final String DELETE_QUICKSAVE = "DELETE FROM quickSaveFiles WHERE name=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM quickSaveFiles";

    private final Connection connection;

    private PreparedStatement addQuickSave;

    private PreparedStatement getNameList;

    private PreparedStatement getQuickSave;

    private PreparedStatement deleteQuickSave;

    private PreparedStatement rowCount;


    public QuickSaveDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);
            addQuickSave = connection.prepareStatement(INSERT_QUICKSAVE);
            getNameList = connection.prepareStatement(GET_NAMES);
            getQuickSave = connection.prepareStatement(GET_QUICKSAVE);
            deleteQuickSave = connection.prepareStatement(DELETE_QUICKSAVE);
            rowCount = connection.prepareStatement(COUNT_ROWS);
        } catch (SQLException ex) {
            // Improve this behavior ########################################################
            ex.printStackTrace();
        }
    }

/*
    public void AddQuickSave(MazeWithoutImage image){

        MazeWithoutImage.setString(1,image.getName());
        //MazeWithoutImage.setBytes(2,bufferedImageToByte(image.(), "png"));
        Maze.excute();

    }
*/

/*
    public ImageAssetFile getImageFile(String name) {

        ImageAssetFile a = new ImageAssetFile();
        ResultSet rs = null;
        try {
            getAsset.setString(1, name);
            rs = getAsset.executeQuery();
            rs.next();
            a.setName(rs.getString("name"));
            a.setImageFile(bytesToBufferedImage(rs.getBytes("imageFile")));
        } catch (SQLException ex) {
            ex.printStackTrace(); // Improve error handling behavior ############################################
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }
*/

    /**
     * Returns the size of the imageAsset table.
     *
     * @return an integer representing the number of rows in the imageAsset table.
     */
    public int getSize() {
        ResultSet rs = null;
        int rows = 0;

        try {
            rs = rowCount.executeQuery();
            rs.next();
            rows = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }

    /**
     * Unchanged from the week 6 tutorial. Deletes a row from the imageAsset table.
     * Warning: This method is untested as of 31/05/2022.
     */
    public void deleteQuickSave(String name) {
        try {
            deleteQuickSave.setString(1, name);
            deleteQuickSave.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
