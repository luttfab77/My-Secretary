package sample;

import java.util.ArrayList;
import java.util.List;

public class PasswordManagement
{
    private List<Password> passwords;

    public PasswordManagement()
    {
        passwords = new ArrayList<>();
    }
    
    public void addPassword(Password iPassword){
        if (!this.passwords.contains(iPassword)){
            this.passwords.add(iPassword);
        }
    }
}
