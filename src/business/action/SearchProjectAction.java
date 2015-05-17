/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.domain.Project;
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
    
    Collection<Project> searchByTitle(String searchTerm) {
        
        ArrayList<Project> foundProjects = new ArrayList<>();
        
        for (Project project : database.getAllProjects()) {
            if (project.getTitle().equals(searchTerm)) {
                foundProjects.add(project);
            }
        }
        
        return foundProjects;
    }
}
