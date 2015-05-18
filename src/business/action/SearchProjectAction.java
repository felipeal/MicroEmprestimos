/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.domain.Project;
import business.domain.User;
import java.util.ArrayList;
import java.util.Collection;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class SearchProjectAction {
    
    Database database;
    
    public SearchProjectAction(Database database) {
        this.database = database;
    }
    
    public Collection<Project> searchByTitle(String searchTerm) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getTitle().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByDescription(String searchTerm) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getDescription().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByRemainingAmount(float min, float max) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getRemainingAmount() >= min && project.getRemainingAmount() <= max) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByAchievedAmount(float min, float max) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getDonatedAmount() >= min && project.getDonatedAmount() <= max) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    //public Collection<Project> searchByExpirationDate(Date min, Date max) {
    //
    //}
    
    public Collection<Project> searchByEnterpreneur(String searchTerm) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            User user = database.getUser(project.getEnterpreneurId());
            if (user.getName().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByLocation(String searchTerm) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            User user = database.getUser(project.getEnterpreneurId());
            if (user.getLocation().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
}
