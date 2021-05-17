package sample;

public class Command
{
    private String text, link;

    public Command(String text, String link)
    {
        this.text = text;
        this.link = link;
    }
    

    //Für Testzwecke.
    @Override
    public String toString()
    {
        return "\n\n-Command-" +
                "\nText:\t\t\t" + this.text +
                "\nLink:\t" + this.link;
    }
}
