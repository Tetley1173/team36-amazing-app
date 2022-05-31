package database;

import mazeFunctions.ImageAssetFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

import static collections.HelperMethods.*;

/**
 * This class creates the assetFiles table that contains the images for the maze. It contains methods for loading, saving and deleting
 * data from the database. Note that this was copied from the week 6 tutorial and adapted to be compatible with this project.
 *
 * @author Shannon Tetley
 */
public class ImageAssetDataSource implements AssetsDataInterface {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS assetFiles ("
                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE," // from https://stackoverflow.com/a/41028314
                    + "name VARCHAR(30) NOT NULL UNIQUE,"
                    + "imageFile BLOB);";

    private static final String INSERT_ASSET = "INSERT INTO assetFiles (name, imageFile) VALUES (?, ?);";

    private static final String GET_NAMES = "SELECT name FROM assetFiles";

    private static final String GET_ASSET = "SELECT * FROM assetFiles WHERE name=?";

    private static final String DELETE_ASSET = "DELETE FROM assetFiles WHERE name=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM assetFiles";

    private final Connection connection;

    private PreparedStatement addAsset;

    private PreparedStatement getNameList;

    private PreparedStatement getAsset;

    private PreparedStatement deleteAsset;

    private PreparedStatement rowCount;

    /**
     * Creates the image asset table if it doesn't exist.
     *
     * @author Shannon Tetley
     */
    public ImageAssetDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);
            addAsset = connection.prepareStatement(INSERT_ASSET);
            getNameList = connection.prepareStatement(GET_NAMES);
            getAsset = connection.prepareStatement(GET_ASSET);
            deleteAsset = connection.prepareStatement(DELETE_ASSET);
            rowCount = connection.prepareStatement(COUNT_ROWS);
        } catch (SQLException ex) {
            // Improve this behavior ########################################################
            ex.printStackTrace();
        }
    }

    /**
     * Adds an image file and its name to the database.
     *
     * @param asset a ImageAssetFile object containing an image to be added to the database.
     * @author Shannon Tetley
     */
    public void addImageFile(ImageAssetFile asset) {
        try {

            addAsset.setString(1, asset.getName());

            //
            //Change this so it checks for unique names.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //
            addAsset.setBytes(2,bufferedImageToByte(asset.getImageFile(), "jpg")); // use setByte
            addAsset.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); // Improve this behavior###########################################
        }
    }

    /**
     * Retrieves an image file from the database. It is returned as an ImageAssetFile object that is ready to be copied
     * into an awaiting ImageAssetFile object.
     *
     * @param name The name(string) to search for.
     * @return ImageAssetFile
     */
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

    /**
     * Copied and unchanged from the week 6 tutorial. Returns the size of the imageAsset table.
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
    public void deleteAsset(String name) {
        try {
            deleteAsset.setString(1, name);
            deleteAsset.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Closes the database connection. Code copied from week 6 tutorial.
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}