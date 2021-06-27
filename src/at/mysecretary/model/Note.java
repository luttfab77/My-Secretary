package at.mysecretary.model;

import java.io.Serializable;

public class Note implements Serializable
{
    private String description, title;
    private String username;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Note(String description, String title)
    {
        this.description = description;
        this.title = title;
    }

    public Note(){
        this.description = "";
        this.title = "";
        this.username = "";
    }



    /**
     * Only for test purposes.
     * @return string
     */
    @Override
    public String toString()
    {
        return "\n\n-Note-" +
                "\nTitle:\t\t\t" + this.title +
                "\nDescription:\t" + this.description;
    }


    /**
     * Method to check, if the note already exists or not.
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

        Note iNote = (Note) o;

        return getUsername().equals(iNote.getUsername()) && getTitle().equals(iNote.getTitle()) && getDescription().equals(iNote.getDescription());
    }
}
