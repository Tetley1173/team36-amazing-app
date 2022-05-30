package database;

import mazeFunctions.ImageAssetFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

import static collections.HelperMethods.*;

/**
 * Class for retrieving data from the XML file holding the address list.
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

    private Connection connection; // make this final if it's safe to do so.

    private PreparedStatement addAsset;

    // Not sure if this will be used yet delete(or not) once investigated#####################################
    private PreparedStatement getNameList;

    private PreparedStatement getAsset;

    private PreparedStatement deleteAsset;

    private PreparedStatement rowCount;

    /**
     * Creates the image asset table if it doesn't exist.
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

     */
    public void addImageFile(ImageAssetFile asset) {
        try {

            addAsset.setString(1, asset.getName());
// Delete once the bufferedImageToBlob() method has been tested.###################################
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(p.getImageFile(), "jpg", baos);
//            byte[] bytes = baos.toByteArray();
//
//
//            Blob blob = new SerialBlob( bytes );

            // this puts the data into the query

            /**
             * Use set byte!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
             */
            addAsset.setBytes(2,bufferedImageToByte(asset.getImageFile(), "jpg")); // use setByte
            addAsset.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); // Improve this behavior###########################################
        }
    }

    /**
     *
     */
    public Set<String> setImageFile() {
        Set<String> names = new TreeSet<String>();
        ResultSet rs = null;

        try {
            rs = getNameList.executeQuery();
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return names;
    }

    /**
     * Probably need to change getBlob to getBytes!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public ImageAssetFile getImageFile(String name) {

        ImageAssetFile a = new ImageAssetFile();
        ResultSet rs = null;
        try {
            getAsset.setString(1, name);
            rs = getAsset.executeQuery();
            rs.next();
            a.setName(rs.getString("name"));
            a.setImageFile(bytesToBufferedImage(rs.getBytes("name")));
        } catch (SQLException ex) {
            ex.printStackTrace(); // Improve error handling behavior ############################################
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * Copied and unchanged from the week 6 tutorial.
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
     * Unchanged from the week 6 tutorial.
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