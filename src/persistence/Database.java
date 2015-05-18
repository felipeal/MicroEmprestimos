/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import business.domain.Project;
import business.domain.User;
import business.domain.Enterpreneur;
import business.domain.Donator;
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
    
    public Database() {
        users = new HashMap<>();
        projects = new HashMap<>();
        
        populate();
    }
    
    private void populate() {
        
        int id;
        
        // Users
        id = 0;
        save(new Enterpreneur(id++, "ana", "123", "Ana", "Porto Alegre"));
        save(new Enterpreneur(id++, "bruno", "123", "Bruno", "Pelotas"));
        save(new Donator(id++, "carlos", "123", "Carlos", "Porto Alegre"));
        save(new Donator(id++, "diego", "123", "Diego", "Pelotas"));
        
        // Projects
        id = 0;
        save(new Project(id++, 0, "Projeto 1 da Ana", "Sem descrição.", 10f, 1000f, "2016-01-01"));
        save(new Project(id++, 0, "Projeto 2 da Ana", "Sem descrição.", 20f, 2000f, "2017-01-01"));
        save(new Project(id++, 1, "Projeto 1 do Bruno", "Sem descrição.", 10f, 1000f, "2016-01-01"));
    }
    
    public void save(User user) {
        users.put(user.getId(), user);
    }
    
    public void save(Project project) {
        projects.put(project.getId(), project);
    }
    
    public User getUser(int userId) {
        return users.get(userId);
    }
    
    public Project getProject(int projectId) {
        return projects.get(projectId);
    }
    
    public Collection<User> getAllUsers() {
        return users.values();
    }
    
    public Collection<Project> getAllProjects() {
        return projects.values();
    }
}
