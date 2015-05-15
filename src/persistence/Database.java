/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domainlogic.Project;
import domainlogic.User;
import domainlogic.Enterpreneur;
import domainlogic.Donator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

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
        
        populate();
    }
    
    private void populate() {
        
        // Users
        save(1, new Enterpreneur("ana", "123", "Ana", "Porto Alegre"));
        save(2, new Enterpreneur("bruno", "123", "Bruno", "Pelotas"));
        save(3, new Donator("carlos", "123", "Carlos", "Porto Alegre"));
        save(4, new Donator("diego", "123", "Diego", "Pelotas"));
        
        // Projects
        save(1, new Project(1, "Projeto 1 da Ana", "Sem descrição.", 10f, 1000f, "2016-01-01"));
        save(2, new Project(1, "Projeto 2 da Ana", "Sem descrição.", 20f, 2000f, "2017-01-01"));
        save(3, new Project(2, "Projeto 1 do Bruno", "Sem descrição.", 10f, 1000f, "2016-01-01"));
    }
    
    void save(Integer userId, User user) {
        users.put(userId, user);
    }
    
    void save(Integer projectId, Project project) {
        projects.put(projectId, project);
    }
    
    User getUser(Integer userId) {
        return users.get(userId);
    }
    
    Project getProject(Integer projectId) {
        return projects.get(projectId);
    }
    
    Collection<User> getAllUsers() {
        return users.values();
    }
    
    Collection<Project> getAllProjects() {
        return projects.values();
    }
}
