import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static <T> void serialize(File targetFile, List<T> objectList){
        try {
            FileOutputStream fileOut = new FileOutputStream(targetFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(objectList);
            out.close();
            fileOut.close();
        }
        catch(FileNotFoundException f) {
            System.out.println("File not found");
            f.printStackTrace();
        }
        catch(IOException i) {
            i.printStackTrace();
        }
    }

    // deserialize whole list of any type
    public static <T> List<T> deserialize(File targetFile) {
        List<T> objectList = null;
        try {
            FileInputStream fileIn = new FileInputStream(targetFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            objectList = (List<T>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(FileNotFoundException f) {
            System.out.println("File not found");
            f.printStackTrace();
        }
        catch(EOFException e) {
            if (targetFile.length() == 0){
                return (new ArrayList<>());
            }
        }
        catch(IOException i) {
            i.printStackTrace();
        }
        catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return objectList;
    }
}
