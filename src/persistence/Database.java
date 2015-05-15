/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domainlogic.Project;
import domainlogic.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Felipe
 */
public class Database {
    
    private Map<Integer, User> users;
    private Map<Integer, Project> projects;
    
    Database() {
        users = new HashMap<>();
        projects = new HashMap<>();
    }
    
    void save(Integer id, User user) {
        users.put(id, user);
    }
    
    void save(Integer id, Project project) {
        projects.put(id, project);
    }
    
    User getUser(Integer id) {
        return users.get(id);
    }
    
    Project getProject(Integer id) {
        return projects.get(id);
    }
    
    Collection<User> getAllUsers() {
        return users.values();
    }
    
    Collection<Project> getAllProjects() {
        return projects.values();
    }
}
