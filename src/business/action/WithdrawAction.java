/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.BusinessException;
import business.domain.Project;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class WithdrawAction extends AbstractAction {
    
    public void withdraw(int projectId, int entrepreneurId) throws BusinessException {
        
        Database database = Database.getInstance();
        Project project = database.getProject(projectId);
        
        if (project.isDone()) {
            throw new BusinessException("Already withdrawed.");
        }
        
        if (project.getEntrepreneurId() != entrepreneurId) {
            throw new BusinessException("Client is not project's owner.");
        }
        
        if (project.getDonatedAmount() < project.getTargetValue()) {
            throw new BusinessException("Donations haven't reached target value.");
        }
        
        project.setDone();
    }
}
