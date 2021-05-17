package sample;

public class Statement
{
    private String text;

    public Statement(String text)
    {
        this.text = text;
    }
    

    //Für Testzwecke.
    @Override
    public String toString()
    {
        return "\n\n-Statement-" +
                "\nText:\t\t\t" + this.text;
    }
}
