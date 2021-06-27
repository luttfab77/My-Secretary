package at.mysecretary.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class PasswordManagement implements Serializable
{
    private List<Password> passwords;
    private String username;

    public List<Password> getPasswords() { return passwords; }
    public void setPasswords(List<Password> passwords) { this.passwords = passwords; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public PasswordManagement()
    {
        passwords = new ArrayList<>();
    }
    
    public void addPassword(Password iPassword){
        iPassword.setUsername(getUsername());
        if (!this.passwords.contains(iPassword)){
            this.passwords.add(iPassword);
        }
    }

    public void removePassword(Password iPassword){
        iPassword.setUsername(getUsername());
        if (this.passwords.contains(iPassword)){
            this.passwords.remove(iPassword);
            SerializationFactory.getInstance().remove(iPassword);
        }
    }

    public static String encryptPassword(String password)
    {
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes(), 0, password.length());
            return new BigInteger(1, m.digest()).toString(16);
        }catch (NoSuchAlgorithmException e){
            //tritt nie auf.
            System.out.println("Fehler beim Verschlüsseln von Passwort \""+password+"\".");
            return null;
        }
    }


    /**
     * Only for test purposes.
     * @return string
     */
    @Override
    public String toString()
    {
        StringBuilder reString = new StringBuilder("\n------PasswordManagement------");

        for (Password iPassword : this.passwords){
            reString.append(iPassword.toString());
        }

        return reString.toString();
    }


    /**
     * Method to check, if the password already exists or not.
     * Helps the Method contains() out, to find the right object.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PasswordManagement iPasswordManagement = (PasswordManagement) o;

        return getUsername().equals(iPasswordManagement.getUsername());
    }
}
