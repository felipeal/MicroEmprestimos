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
    protected final int id;
    protected String login;
    protected String password;
    
    protected String name;
    protected String location;
    
    public User (int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}
