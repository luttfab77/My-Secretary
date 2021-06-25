package at.mysecretary.model;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SerializationFactory
{
    private static SerializationFactory instance = null;

    private List<Serializable> objects;

    private SerializationFactory() {
        this.objects = new LinkedList<>();
    }

    public static SerializationFactory getInstance() {
        if (instance == null) {
            instance = new SerializationFactory();
        }
        return instance;
    }


    public void save(Serializable ser)
    {
        if (objects.contains(ser)) {
            objects.remove(ser);
        }
        objects.add(ser);
    }

    public void remove(Serializable ser) {
        if (objects.contains(ser)) {
            objects.remove(ser);
        }

        System.out.println(objects);
    }

    public boolean exists(Serializable ser) {
        if (objects.contains(ser)) {
            return true;
        }
        return false;
    }


    public void persist() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serialObjects.ser"))) {
            oos.writeObject(objects);
        }
        catch (IOException e) {
            // kann nicht auftreten
            e.printStackTrace();
        }
    }

    public void restore() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serialObjects.ser"))) {
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
            if (PasswordManagement.encryptPassword(password).equals(found.getPasswordHash()))
            {
                return found;
            }
        }
        return null;

    }
}
