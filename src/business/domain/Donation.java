/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.domain;

/**
 * 
 * @author Felipe
 */
public class Donation {
    private int id;
    private int donatorId;
    private int projectId;
    private float amount;
    
    /**
     * Create new donation for donator.
     * @param donatorId
     * @param amount in credits
     */
    public Donation(int donatorId, int projectId, float amount) {
        this.id = -1;
        this.donatorId = donatorId;
        this.projectId = projectId;
        this.amount = amount;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getDonatorId() {
        return donatorId;
    }

    public float getAmount() {
        return amount;
    }

    public int getProjectId() {
        return projectId;
    }
}
