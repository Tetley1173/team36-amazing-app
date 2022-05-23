package database;

import mazeFunctions.ImageAssetFile;
import collections.HelperMethods;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

import static collections.HelperMethods.blobToBufferedImage;
import static collections.HelperMethods.bufferedImageToBlob;

/**
 * Class for retrieving data from the XML file holding the address list.
 */
//public class ImageAssetDataSource implements AssetsDataInterface {
//
//    public static final String CREATE_TABLE =
//            "CREATE TABLE IF NOT EXISTS assetFiles ("
//                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE," // from https://stackoverflow.com/a/41028314
//                    + "name VARCHAR(30) NOT NULL UNIQUE,"
//                    + "imageFile BLOB(MAX),";
//
//    private static final String INSERT_ASSET = "INSERT INTO assetFiles (name, imageFile) VALUES (?, ?, ?, ?, ?);";
//
//    private static final String GET_NAMES = "SELECT name FROM assetFiles";
//
//    private static final String GET_ASSET = "SELECT * FROM assetFiles WHERE name=?";
//
//    private static final String DELETE_ASSET = "DELETE FROM assetFiles WHERE name=?";
//
//    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM address";
//
//    private Connection connection;
//
//    private PreparedStatement addAsset;
//
//    // Not sure if this will be used yet delete(or not) once investigated#####################################
//    private PreparedStatement getNameList;
//
//    private PreparedStatement getAsset;
//
//    private PreparedStatement deleteAsset;
//
//    private PreparedStatement rowCount;
//
//    public ImageAssetDataSource() {
//        connection = DBConnection.getInstance();
//        try {
//            Statement st = connection.createStatement();
//            st.execute(CREATE_TABLE);
//            addAsset = connection.prepareStatement(INSERT_ASSET);
//            getNameList = connection.prepareStatement(GET_NAMES);
//            getAsset = connection.prepareStatement(GET_ASSET);
//            deleteAsset = connection.prepareStatement(DELETE_ASSET);
//            rowCount = connection.prepareStatement(COUNT_ROWS);
//        } catch (SQLException ex) {
//            // Improve this behavior ########################################################
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//
//     */
//    public void addImageFile(ImageAssetFile p) {
//        try {
//
//            addAsset.setString(1, p.getName());
//// Delete once the bufferedImageToBlob() method has been tested.###################################
////            ByteArrayOutputStream baos = new ByteArrayOutputStream();
////            ImageIO.write(p.getImageFile(), "jpg", baos);
////            byte[] bytes = baos.toByteArray();
////
////
////            Blob blob = new SerialBlob( bytes );
//
//            // this puts the data into the query
//            addAsset.setBlob(2, bufferedImageToBlob(p.getImageFile(), "jpg"));
//            addAsset.execute();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace(); // Improve this behavior###########################################
//        }
//    }
//
//    /**
//     * @see dataExercisenew.AddressBookDataSource#nameSet()
//     * Probably don't need this one I previously deleted it from the interface. Remove once confirmed########################
//     */
////    public Set<String> setImageFile() {
////        Set<String> names = new TreeSet<String>();
////        ResultSet rs = null;
////
////        try {
////            rs = getNameList.executeQuery();
////            while (rs.next()) {
////                names.add(rs.getString("name"));
////            }
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////
////        return names;
////    }
//
//    /**
//     * @see dataExercise.AddressBookDataSource#getPerson(java.lang.String)
//     * Continue from here ###### Continue from here ###### Continue from here ###### Continue from here ######
//     *  Continue from here ###### Continue from here ###### Continue from here ###### Continue from here ######
//     *   Continue from here ###### Continue from here ###### Continue from here ###### Continue from here ######
//     */
//    public ImageAssetFile getImageFile(String name) {
//
//        // I prepared this method for converting a Blob
////        blobToBufferedImage();
//
//        ImageAssetFile p = new ImageAssetFile();
//        ResultSet rs = null;
//        /* BEGIN MISSING CODE */
////        try {
////            getPerson.setString(1, name);
////            rs = getPerson.executeQuery();
////            rs.next();
////            p.setName(rs.getString("name"));
////            p.setStreet(rs.getString("street"));
////            p.setSuburb(rs.getString("suburb"));
////            p.setPhone(rs.getString("phone"));
////            p.setEmail(rs.getString("email"));
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
//        /* END MISSING CODE */
//        return p;
//    }
//
//    /**
//     * @see dataExercise.AddressBookDataSource#getSize()
//     */
//    public int getSize() {
//        ResultSet rs = null;
//        int rows = 0;
//
//        /* BEGIN MISSING CODE */
//        try {
//            rs = rowCount.executeQuery();
//            rs.next();
//            rows = rs.getInt(1);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        /* END MISSING CODE */
//
//        return rows;
//    }
//
//    /**
//     * @see dataExercise.AddressBookDataSource#deletePerson(java.lang.String)
//     */
//    public void deletePerson(String name) {
//        /* BEGIN MISSING CODE */
////        try {
////            deletePerson.setString(1, name);
////            deletePerson.executeUpdate();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
//        /* END MISSING CODE */
//    }
//
//    /**
//     * @see dataExercise.AddressBookDataSource#close()
//     */
//    public void close() {
//        /* BEGIN MISSING CODE */
//        try {
//            connection.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        /* END MISSING CODE */
//    }
//}