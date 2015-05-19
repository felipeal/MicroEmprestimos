/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.domain.Enterpreneur;
import business.domain.Project;
import java.util.ArrayList;
import java.util.Collection;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class SearchProjectAction extends AbstractAction {

    public SearchProjectAction(Database database) {
        super(database);
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
    
    public Collection<Project> searchByExpirationDate(String dateMin, String dateMax) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getLimitDate().compareTo(dateMin) >= 0 && // Greater than dateMin
                project.getLimitDate().compareTo(dateMax) <= 0) { // Smaller than dateMax
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByEnterpreneur(String searchTerm) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            Enterpreneur enterpreneur = database.getEnterpreneur(project.getEnterpreneurId());
            if (enterpreneur.getName().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByLocation(String searchTerm) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            Enterpreneur enterpreneur = database.getEnterpreneur(project.getEnterpreneurId());
            if (enterpreneur.getLocation().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Project getSelectedProject(int projectId) {
        return database.getProject(projectId);
    }
}
