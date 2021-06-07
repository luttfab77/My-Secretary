package sample;

import java.io.Serializable;

public class Statement implements Serializable
{
    private String text;
    private String username;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Statement(String text)
    {
        this.text = text;
    }

    public Statement(){
        this.text = "";
    }
    

    //Für Testzwecke.
    @Override
    public String toString()
    {
        return "\n\n-Statement-" +
                "\nText:\t\t\t" + this.text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Statement iStatement = (Statement) o;

        return getUsername().equals(iStatement.getUsername()) && getText().equals(iStatement.getText());
    }
}
