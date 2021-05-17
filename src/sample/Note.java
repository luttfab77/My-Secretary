package sample;

public class Note
{
    private String description, title;

    public Note(String description, String title)
    {
        this.description = description;
        this.title = title;
    }
    

    //Für Testzwecke.
    @Override
    public String toString()
    {
        return "\n\n-Note-" +
                "\nTitle:\t\t\t" + this.title +
                "\nDescription:\t" + this.description;
    }
}
