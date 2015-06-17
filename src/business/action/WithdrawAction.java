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
public class WithdrawAction extends AbstractAction {
    
    Project withdraw(int projectId) {
        
        Database database = Database.getInstance();
        Project project = database.getProject(projectId);
        
        if (!project.isDone() && project.getDonatedAmount() >= project.getTargetValue()) {
            project.setDone();
        }
        
        return project;
    }
}
