package at.mysecretary.model;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SerializationFactory
{
    private static SerializationFactory instance = null;

    /**
     * List objects contains all the objects, that are going to be written into the file.
     */
    private List<Serializable> objects;

    private SerializationFactory() {
        this.objects = new LinkedList<>();
    }


    /**
     * Returns the actual Instance.
     * With it, it is possible to access all the Methods in here, as if
     * the Methods were static.
     * For example:
     *      SerializationFactory.getInstance().persist();
     * @return
     */
    public static SerializationFactory getInstance() {
        if (instance == null) {
            instance = new SerializationFactory();
        }
        return instance;
    }


    /**
     * Saves an object into the List objects.
     * If this object already exits, it's going to be deleted and added as a ned Object.
     * @param ser
     */
    public void save(Serializable ser)
    {
        if (objects.contains(ser)) {
            objects.remove(ser);
        }
        objects.add(ser);
    }


    /**
     * Removes an object from the List objects, if objects contains the object
     * @param ser
     */
    public void remove(Serializable ser) {
        if (objects.contains(ser)) {
            objects.remove(ser);
        }

        System.out.println(objects);
    }


    /**
     * Checks, if object exists in objects List.
     * @param ser
     * @return
     */
    public boolean exists(Serializable ser) {
        if (objects.contains(ser)) {
            return true;
        }
        return false;
    }


    /**
     * Saves the objects List in a File called "serialObjects.ser".
     */
    public void persist() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serialObjects.ser"))) {
            //This line saves the objects into the File given.
            oos.writeObject(objects);
        }
        catch (IOException e) {
            // kann nicht auftreten
            e.printStackTrace();
        }
    }


    /**
     * Reads the objects out of the File called "serialObjects.ser" and saves every object
     * into the List objects.
     */
    public void restore() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serialObjects.ser"))) {
            //This line reads out the objects from the File given.
            objects = (List<Serializable>) ois.readObject();
            System.out.println(objects);
        }
        catch (FileNotFoundException e) {
            System.out.println("No Users found! Nothing to restore!");
        }
        catch (InvalidClassException e) {
            System.out.println("Catalog contains class description of different version! Nothing to restore!");
        }
        catch (ClassNotFoundException | IOException e) {
            // kann nicht auftreten
            e.printStackTrace();
        }
    }


    /**
     *
     * @param username
     * @param password
     * @return
     */
    public User selectUserByUsernamePassword(String username,String password)
    {
        User found = null;

        int i = 0;

        //Gets the user, with the username.
        while (i < objects.size() &&
                (!(objects.get(i) instanceof User) ||
                        (objects.get(i) instanceof User &&
                                !((User) (objects.get(i))).getUsername().equals(username)))) {
            i++;
        }

        if (i < objects.size() &&
                objects.get(i) instanceof User)
        {

            found = (User) objects.get(i);
            //Checks, if the password ist correct.
            if (PasswordManagement.encryptPassword(password).equals(found.getPasswordHash()))
            {
                return found;
            }
        }
        return null;
    }
}
