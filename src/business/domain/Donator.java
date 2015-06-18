/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class Donator extends User {
    
    private float balance;
    private List<Integer> donationIds;
    
    /**
     * Constructor for new donator
     * @param login unique login
     * @param password
     * @param name real name
     * @param balance initial balance
     */
    public Donator(String login, String password, String name, float balance) {
        super(login, password, name);
        this.balance = balance;
        this.donationIds = new ArrayList<>();
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    
    public void addDonation(int donationId) {
        donationIds.add(donationId);
    }
    
    /**
     * Returns all the donations made by the donator
     * @return
     */
    public Collection<Donation> getAllDonations() {
        
        Database database = Database.getInstance();
        LinkedHashSet<Donation> foundDonations = new LinkedHashSet<>();
        
        for (Integer donationId : donationIds) {
            foundDonations.add(database.getDonation(donationId));
        }
        
        return foundDonations;
    }
}
