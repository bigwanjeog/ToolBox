import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by RENAUD on 25/10/2016.
 */
public class XMLTools {

    // Write a XML file from an Object
    public static <T> void writeXML(T object, String fileName){
        XMLEncoder encoder = null;
        ArrayList<T> objects = new ArrayList<>();

        // Try to read and recover the object in the XML file
        try {
            ArrayList<T> tempObjects = readXML(fileName);
            for(T tempObject : tempObjects){
                //Put the object in an ArrayList
                objects.add(tempObject);
            }
        } catch (Exception e){
            System.out.println("No XML file created before");
        }

        // Check if the attribute object is an Array or an Object
        try{
            ArrayList<T> tempObjects = (ArrayList) object;
            for(T tempObject : tempObjects){
                // Put the object in an ArrayList
                objects.add(tempObject);
            }
        } catch (Exception e){
            // Add the object in the ArrayList if it's not a Array
            objects.add(object);
        }

        try{
            // Open encoder from the file
            encoder = new XMLEncoder(new FileOutputStream(fileName));

            // Serialization of the object
            encoder.writeObject(objects);
            encoder.flush();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            // Close encoder
            encoder.close();
        }
    }

    // Read a XML file and transfer it in an Object
    public static <T> T readXML(String fileName) {
        // Open decoder from the file
        XMLDecoder decoder = null;
        T object = null;
        try {
            // Open decoder from the file
            decoder = new XMLDecoder(new FileInputStream(fileName));
            // Put the XML object in an Object
            object = (T)decoder.readObject();

        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            // Close decoder
            decoder.close();
        }
        return object;
    }
}
