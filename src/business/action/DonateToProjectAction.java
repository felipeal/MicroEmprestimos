/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.BusinessException;
import business.domain.Donation;
import business.domain.Donator;
import business.domain.Project;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class DonateToProjectAction extends AbstractAction {
    
    /**
     * Create a donation by the specified donator to the specified project
     * @param donatorId
     * @param projectId
     * @param amount
     * @throws BusinessException
     */
    public void donateToProject(int donatorId, int projectId, float amount) throws BusinessException {
        // TODO: Validate donator/project id
        
        Database database = Database.getInstance();
        
        // Validate amount
        Project project = database.getProject(projectId);
        Donator donator = database.getDonator(donatorId);
        
        if (amount == 0 || amount < project.getMinDonationAmount())
            throw new BusinessException("Invalid amount for project.");
        if (amount > donator.getBalance())
            throw new BusinessException("Insuficcient balance.");
        
        donator.setBalance(donator.getBalance() - amount);
        
        Donation donation = new Donation(donatorId, projectId, amount);
        database.save(donation);
        project.addDonation(donation.getId());
        donator.addDonation(donation.getId());
        System.out.println(donation.getId());
    }
}
