package at.mysecretary.model;

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


    /**
     * Only for test purposes.
     * @return string
     */
    @Override
    public String toString()
    {
        return "\n\n-Statement-" +
                "\nText:\t\t\t" + this.text;
    }


    /**
     * Method to check, if the statement already exists or not.
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

        Statement iStatement = (Statement) o;

        return getUsername().equals(iStatement.getUsername()) && getText().equals(iStatement.getText());
    }
}
