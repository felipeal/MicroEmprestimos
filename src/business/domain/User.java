/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.domain;

/**
 *
 * @author Felipe
 */
public abstract class User {
    protected int id;
    protected String login;
    protected String password;
    
    protected String name;
    protected String location;

    public User (String login, String password, String name, String location) {
        this.id = -1;
        this.login = login;
        this.password = password;
        this.name = name;
        this.location = location;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getName() {
        return name;
    }   
    
    public String getLocation() {
        return location;
    }
}
