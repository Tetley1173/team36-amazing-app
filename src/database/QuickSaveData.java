//package database;
//
//import mazeFunctions.QuickSaveFile;
//
//import javax.swing.DefaultListModel;
//import javax.swing.ListModel;
//
//public class QuickSaveData {
//
//    DefaultListModel listModel;
//
//    QuickSaveInterface QuickSave;
//
//    /**
//     * Constructor initializes the list model that holds names as Strings and
//     * attempts to read any data saved from previous invocations of the
//     * application.
//     *
//     */
//
//    public QuickSaveData(){
//
//        listModel = new DefaultListModel();
//        QuickSave = new QuickSaveDataSource();
//
//        // add the retrieved data to the list model
//        for (String name : QuickSave.nameSet()){
//            listModel.addElement(name);
//        }
//    }
//
//    public Object add(QuickSaveFile i) {
//
//        // check to see if the person is already in the book
//        // if not add to the address book and the list model
//        if (!listModel.contains(i.getMazeName())) {
//            listModel.addElement(i.getMazeImage());
//            QuickSave.AddQuickSave(i);
//        }
//
//        /**
//         * Based on the name of the person in the Quicksave, delete the person.
//         *
//         * @param key
//         */
//        public void remove(Object i) {
//
//            // remove from both list and map
//            listModel.removeElement(i);
//            QuickSave.deleteQuickSave((String) i);
//
//        }
//
//        /**
//         * Saves the data in the Quicksave using a persistence
//         * mechanism.
//         */
//        public void persist() {
//            QuickSave.close();
//        }
//
//        /**
//         * Retrieves Person details from the model.
//         *
//         * @param key the name to retrieve.
//         * @return the Person object related to the name.
//         */
//        public QuickSaveFile get(Object i) {
//            return QuickSave.getMazeImage(String i);
//        }
//
//        /**
//         * Accessor for the list model.
//         *
//         * @return the listModel to display.
//         */
//        public ListModel getModel() {
//            return listModel;
//        }
//
//        /**
//         * @return the number of names in the Address Book.
//         */
//        public int getSize() {
//            return QuickSave.getSize();
//        }
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
