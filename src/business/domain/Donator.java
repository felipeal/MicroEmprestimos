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
public class Donator extends User {
    public Donator(int id, String login, String password, String name, String location) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.location = location;
    }
}
