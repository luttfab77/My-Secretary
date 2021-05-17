package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Secretary
{
    private Image avatar;
    private List<Statement> statements;
    private List<Command> commands;

    public Secretary(Image avatar)
    {
        this.avatar = avatar;
        this.statements = new ArrayList<>();
        this.commands = new ArrayList<>();
    }
    
    public void addStatement(Statement iStatement){
        if (!this.statements.contains(iStatement)){
            this.statements.add(iStatement);
        }
    }

    public void addCommand(Command iCommand){
        if (!this.commands.contains(iCommand)){
            this.commands.add(iCommand);
        }
    }


    //Für Testzwecke.
    @Override
    public String toString()
    {
        StringBuilder reString = new StringBuilder("\n------Secretary------");

        for (Statement iStatement : this.statements){
            reString.append(iStatement.toString());
        }
        for (Command iCommand : this.commands){
            reString.append(iCommand.toString());
        }

        return reString.toString();
    }
}
