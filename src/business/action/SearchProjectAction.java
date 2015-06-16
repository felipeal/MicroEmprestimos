/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.domain.Donator;
import business.domain.Entrepreneur;
import business.domain.Project;
import java.util.ArrayList;
import java.util.Collection;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class SearchProjectAction extends AbstractAction {
    
    public Collection<Project> searchByTitle(String searchTerm) {
        
        Database database = Database.getInstance();
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getTitle().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByDescription(String searchTerm) {
        
        Database database = Database.getInstance();
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getDescription().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByRemainingAmount(float min, float max) {
        
        Database database = Database.getInstance();
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getRemainingAmount() >= min && project.getRemainingAmount() <= max) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByAchievedAmount(float min, float max) {
        
        Database database = Database.getInstance();
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getDonatedAmount() >= min && project.getDonatedAmount() <= max) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByExpirationDate(String dateMin, String dateMax) {
        
        Database database = Database.getInstance();
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getLimitDate().compareTo(dateMin) >= 0 && // Greater than dateMin
                project.getLimitDate().compareTo(dateMax) <= 0) { // Smaller than dateMax
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByEntrepreneur(String searchTerm) {
        
        Database database = Database.getInstance();
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            Entrepreneur entrepreneur = database.getEntrepreneur(project.getEntrepreneurId());
            if (entrepreneur.getName().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Collection<Project> searchByLocation(String searchTerm) {
        
        Database database = Database.getInstance();
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            Entrepreneur entrepreneur = database.getEntrepreneur(project.getEntrepreneurId());
            if (entrepreneur.getLocation().contains(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
    
    public Project getSelectedProject(int projectId) {
        Database database = Database.getInstance();
        return database.getProject(projectId);
    }
    
    public Entrepreneur getSelectedProjectEntrepreneur(int projectId) {
        Database database = Database.getInstance();
        Project project = database.getProject(projectId);
        return database.getEntrepreneur(project.getEntrepreneurId());
    }
    
    public Donator getCurrentDonator(int donatorId) {
        Database database = Database.getInstance();
        return database.getDonator(donatorId);
    }
}
