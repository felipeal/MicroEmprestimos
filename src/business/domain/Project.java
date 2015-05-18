/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.domain;

import java.util.Date;

/**
 *
 * @author Felipe
 */
public class Project {
    
    private final int id;
    private int enterpreneurId;
    private String title;
    private String description;
    private float donatedAmount;
    private float minDonationAmount;
    private float targetValue;
    private String limitDate; // TODO: Usar classe para data
    
    public Project(int id, int enterpreneurId, String title, String description, float minDonationValue, float targetValue, String limitDate) {
        this.id = id;
        this.enterpreneurId = enterpreneurId;
        this.title = title;
        this.description = description;
        this.donatedAmount = 0;
        this.minDonationAmount = minDonationValue;
        this.targetValue = targetValue;
        this.limitDate = limitDate;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }

    public float getRemainingAmount() {
        if (donatedAmount < minDonationAmount)
            return minDonationAmount - donatedAmount;
        else
            return 0;
    }
    
    public float getDonatedAmount() {
        return donatedAmount;
    }
}
