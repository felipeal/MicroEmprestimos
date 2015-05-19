/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class Project {
    
    private Database database;
    
    private int id;
    private int enterpreneurId;
    private String title;
    private String description;
    private List<Integer> donationIds;
    private float minDonationAmount;
    private float targetValue;
    private String limitDate; // TODO: Usar classe para data
    
    public Project(Database database, int enterpreneurId, String title, String description, float minDonationValue, float targetValue, String limitDate) {
        this.id = -1;
        this.database = database;
        this.enterpreneurId = enterpreneurId;
        this.title = title;
        this.description = description;
        this.donationIds = new ArrayList<>();
        this.minDonationAmount = minDonationValue;
        this.targetValue = targetValue;
        this.limitDate = limitDate;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getEnterpreneurId() {
        return enterpreneurId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }

    public float getRemainingAmount() {
        if (getDonatedAmount() < targetValue)
            return targetValue - getDonatedAmount();
        else
            return 0;
    }
    
    public void addDonation(int donationId) {
        donationIds.add(donationId);
    }
    
    public float getDonatedAmount() {
        float donatedAmount = 0;
        
        for (Integer donationId : donationIds) {
            donatedAmount += database.getDonation(donationId).getAmount();
        }
        
        return donatedAmount;
    }
    
    public float getMinDonationAmount() {
        return minDonationAmount;
    }
    
    public float getTargetValue() {
        return targetValue;
    }
    
    public String getLimitDate() {
        return limitDate;
    }
}
