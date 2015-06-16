/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.domain.Project;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class CreateProjectAction {
    Project createProject(int entrepreneurId, String title, String description, float minDonationValue, float targetValue, String limitDate) {
        Database database = Database.getInstance();
        Project project = new Project(entrepreneurId, title, description, minDonationValue, targetValue, limitDate);
        
        database.save(project);
    }
}
