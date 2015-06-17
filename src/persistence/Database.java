/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import business.domain.Donation;
import business.domain.Project;
import business.domain.Entrepreneur;
import business.domain.Donator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Felipe
 */
public class Database {
    
    private Map<Integer, Entrepreneur> entrepreneurs;
    private Map<Integer, Donator> donators;
    private Map<Integer, Project> projects;
    private Map<Integer, Donation> donations;
    
    private int nextEntrepreneurId = 0;
    private int nextDonatorId = 0;
    private int nextProjectId = 0;
    private int nextDonationId = 0;
    
    private static final Database INSTANCE = new Database(); // Singleton pattern
    
    private Database() {
        entrepreneurs = new HashMap<>();
        donators = new HashMap<>();
        projects = new HashMap<>();
        donations = new HashMap<>();
        
        populate();
    }
    
    /**
     * Get database singleton instance
     * @return singleton's instance
     */
    public static Database getInstance() {
        return INSTANCE;
    }
    
    private void populate() {
        
        // Users
        Entrepreneur ana = new Entrepreneur("ana", "123", "Ana", "Porto Alegre");
        Entrepreneur bruno = new Entrepreneur("bruno", "123", "Bruno", "Pelotas");

        Donator carlos = new Donator("carlos", "123", "Carlos", 100f);
        Donator diego = new Donator("diego", "123", "Diego", 100f);

        save(ana);
        save(bruno);
        save(carlos);
        save(diego);
        
        // Projects
        save(new Project(ana.getId(), "Projeto 1 da Ana", "Sem descrição.", 10f, 1000f, "2016.01.01"));
        save(new Project(ana.getId(), "Projeto 2 da Ana", "Sem descrição.", 20f, 2000f, "2017.01.01"));
        save(new Project(bruno.getId(), "Projeto 1 do Bruno", "Sem descrição.", 10f, 1000f, "2016.01.01"));
    }
    
    /**
     * Adds an entrepreneur to the database, setting its ID incrementally
     * @param entrepreneur
     */
    public void save(Entrepreneur entrepreneur) {
        entrepreneur.setId(nextEntrepreneurId++);
        entrepreneurs.put(entrepreneur.getId(), entrepreneur);
    }
    
    /**
     * Adds a donator to the database, setting its ID incrementally
     * @param donator
     */
    public void save(Donator donator) {
        donator.setId(nextDonatorId++);
        donators.put(donator.getId(), donator);
    }
    
    /**
     * Adds a project to the database, setting its ID incrementally
     * @param project
     */
    public void save(Project project) {
        project.setId(nextProjectId++);
        projects.put(project.getId(), project);
    }
    
    /**
     * Adds a donation to the database, setting its ID incrementally
     * @param donation
     */
    public void save(Donation donation) {
        donation.setId(nextDonationId++);
        donations.put(donation.getId(), donation);
        for (int ids : donations.keySet()) {
            System.out.println("Tabelinha de ids no database: " + ids);
        }
    }
    
    public Entrepreneur getEntrepreneur(int entrepreneurId) {
        return entrepreneurs.get(entrepreneurId);
    }
    
    public Donator getDonator(int donatorId) {
        return donators.get(donatorId);
    }
    
    public Project getProject(int projectId) {
        return projects.get(projectId);
    }
    
    public Donation getDonation(int donationId) {
        if (!donations.containsKey(donationId))
            return null;
        
        return donations.get(donationId);
    }
    
    public Collection<Project> getAllProjects() {
        return projects.values();
    }
    
    public Collection<Entrepreneur> getAllEntrepreneurs() {
        return entrepreneurs.values();
    }
    
    public Collection<Donator> getAllDonators() {
        return donators.values();
    }
}
