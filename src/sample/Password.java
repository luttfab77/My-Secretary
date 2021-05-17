package sample;

import javafx.scene.image.Image;

public class Password
{
    private String association;
    private Image logo;
    private String password,username;

    public Password(String association, Image logo, String password, String username)
    {
        this.association = association;
        this.logo = logo;
        this.password = password;
        this.username = username;
    }
}
