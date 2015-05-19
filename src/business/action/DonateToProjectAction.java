/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.domain.Donation;
import business.domain.Project;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class DonateToProjectAction extends AbstractAction {

    public DonateToProjectAction(Database database) {
        super(database);
    }
    
    void donateToProject(int projectId, float amount) {
        // TODO: Validate project id
        // TODO: Validate amount > 0
        
        // Validate amount
        Project project = database.getProject(projectId);
        if (amount == 0 || amount < project.getMinDonationAmount())
            throw new BusinessException("Invalid amount for project.");
        if (amount > currentDonatorId.getBalance())
            throw new BusinessException("Insuficcient balance.");
        
        Donation donation = new Donation(currentDonatorId, amount);
        database.save(donation);
        project.addDonation(donation.getId());
    }
}
