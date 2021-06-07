package sample;

import java.io.Serializable;

public class Command implements Serializable
{
    private String text, link;
    private String username;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Command(String text, String link)
    {
        this.text = text;
        this.link = link;
    }

    public Command(){
        this.text = "";
        this.link = "";
    }
    

    //Für Testzwecke.
    @Override
    public String toString()
    {
        return "\n\n-Command-" +
                "\nText:\t\t\t" + this.text +
                "\nLink:\t\t\t" + this.link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Command iCommand = (Command) o;

        return getUsername().equals(iCommand.getUsername()) && getText().equals(iCommand.getText()) &&
                getLink().equals(iCommand.getLink());
    }
}
