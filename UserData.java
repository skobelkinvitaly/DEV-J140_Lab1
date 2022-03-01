/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authorization;

/**
 *
 * @author denis
 */
public class UserData {
    private String name;
    private String password;

    public UserData(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public boolean checkPassword(){
        if(password.length()<8)return false;
        String regex = "A-Za-z0-9!+_";
        if(!password.replaceAll("[" + regex + "]", "").isEmpty())return false;
        return true;
    }
}
