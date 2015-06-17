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
    
    private int id;
    private int entrepreneurId;
    private String title;
    private String description;
    private List<Integer> donationIds;
    private float minDonationAmount;
    private float targetValue;
    private String limitDate; // TODO: Usar classe para data
    private boolean done;
    
    /**
     * Constructor for new projects
     * @param entrepreneurId project manager
     * @param title
     * @param description
     * @param minDonationValue in credits
     * @param targetValue in credits
     * @param limitDate
     */
    public Project(int entrepreneurId, String title, String description, float minDonationValue, float targetValue, String limitDate) {
        this.id = -1;
        this.entrepreneurId = entrepreneurId;
        this.title = title;
        this.description = description;
        this.donationIds = new ArrayList<>();
        this.minDonationAmount = minDonationValue;
        this.targetValue = targetValue;
        this.limitDate = limitDate;
        this.done = false;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getEntrepreneurId() {
        return entrepreneurId;
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
        Database database = Database.getInstance();
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

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        this.done = true;
    }
}
