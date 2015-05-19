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
    protected int id;
    int donatorId;
    float amount;
    
    public Donation(int donatorId, float amount) {
        this.id = -1;
        this.donatorId = donatorId;
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
}
