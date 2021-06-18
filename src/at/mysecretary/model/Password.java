package at.mysecretary.model;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Password implements Serializable
{
    private String association;
    private Image logo;
    private String passwd,usr;
    private String username;

    public String getAssociation() { return association; }
    public void setAssociation(String association) { this.association = association; }
    public Image getLogo() { return logo; }
    public void setLogo(Image logo) { this.logo = logo; }
    public String getPasswd() { return passwd; }
    public void setPasswd(String passwd) { this.passwd = passwd; }
    public String getUsr() { return usr; }
    public void setUsr(String usr) { this.usr = usr; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Password(String association, Image logo, String passwd, String usr)
    {
        this.association = association;
        this.logo = logo;
        this.passwd = passwd;
        this.usr = usr;
    }

    public Password(){
        this.association = "";
        this.logo = null;
        this.passwd = "";
        this.usr = "";
    }


    //Für Testzwecke.
    @Override
    public String toString()
    {
        return "\n\n-Password-" +
                "\nAssociation:\t" + this.association +
                "\nPassword:\t\t" + this.passwd +
                "\nUsername:\t\t" + this.usr;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Password iPassword = (Password) o;

        return getUsr().equals(iPassword.getUsr()) && getPasswd().equals(iPassword.getPasswd()) &&
                getAssociation().equals(iPassword.getAssociation());
    }
}
