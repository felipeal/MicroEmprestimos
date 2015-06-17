/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class Entrepreneur extends User {
    
    private List<Integer> projectIds;
    private String location;
    
    /**
     * Constructor for entrepreneur
     * @param login unique login
     * @param password
     * @param name real name
     * @param location where the entrepreneur resides
     */
    public Entrepreneur(String login, String password, String name, String location) {
        super(login, password, name);
        this.location = location;
        this.projectIds = new ArrayList<>();
    }
    
    public String getLocation() {
        return location;
    }
}
