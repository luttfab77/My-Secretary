package at.mysecretary.model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Secretary implements Serializable
{
    private Image avatar;
    private List<Statement> statements;
    private List<Command> commands;
    private String username;

    public Image getAvatar() { return avatar; }
    public void setAvatar(Image avatar) { this.avatar = avatar; }
    public List<Statement> getStatements() { return statements; }
    public void setStatements(List<Statement> statements) { this.statements = statements; }
    public List<Command> getCommands() { return commands; }
    public void setCommands(List<Command> commands) { this.commands = commands; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Secretary(Image avatar)
    {
        this.avatar = avatar;
        this.statements = new ArrayList<>();
        this.commands = new ArrayList<>();
    }

    public Secretary(){
        this.avatar = null;
        this.statements = new ArrayList<>();
        this.commands = new ArrayList<>();
        this.username = "";
    }

    /**
     * Adds a statement if it exists.
     * @param iStatement
     */
    public void addStatement(Statement iStatement){
        iStatement.setUsername(getUsername());
        if (!this.statements.contains(iStatement)){
            this.statements.add(iStatement);
        }
    }

    /**
     * Removes a statement if it exists.
     * @param iStatement
     */
    public void removeStatement(Statement iStatement){
        iStatement.setUsername(getUsername());
        if (this.statements.contains(iStatement)){
            this.statements.remove(iStatement);
            SerializationFactory.getInstance().remove(iStatement);
        }
    }

    /**
     * Adds a command, if it doesn't exist.
     * @param iCommand
     */
    public void addCommand(Command iCommand){
        iCommand.setUsername(getUsername());
        if (!this.commands.contains(iCommand)){
            this.commands.add(iCommand);
        }
    }

    /**
     * Removes a command, if it exists.
     * @param iCommand
     */
    public void removeCommand(Command iCommand){
        iCommand.setUsername(getUsername());
        if (this.commands.contains(iCommand)){
            this.commands.remove(iCommand);
            SerializationFactory.getInstance().remove(iCommand);
        }
    }


    /**
     * Only for test purposes.
     * @return string
     */
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


    /**
     * Method to check, if the secretary already exists or not.
     * Helps the Method contains() out, to find the right object.
     * This method isn't used so much, because you don't delete
     * your secretary that often, I believe.
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

        Secretary iSecretary = (Secretary) o;

        return getUsername().equals(iSecretary.getUsername());
    }
}
