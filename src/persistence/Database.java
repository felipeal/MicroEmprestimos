/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import business.domain.Donation;
import business.domain.Project;
import business.domain.Enterpreneur;
import business.domain.Donator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author Felipe
 */
public class Database {
    
    private Map<Integer, Enterpreneur> enterpreneurs;
    private Map<Integer, Donator> donators;
    private Map<Integer, Project> projects;
    private Map<Integer, Donation> donations;
    
    private int nextEnterpreneurId = 0;
    private int nextDonatorId = 0;
    private int nextProjectId = 0;
    private int nextDonationId = 0;
    
    public Database() {
        enterpreneurs = new HashMap<>();
        donators = new HashMap<>();
        projects = new HashMap<>();
        donations = new HashMap<>();
        
        populate();
    }
    
    private void populate() {
        
        // Users
        Enterpreneur ana = new Enterpreneur("ana", "123", "Ana", "Porto Alegre");
        Enterpreneur bruno = new Enterpreneur("bruno", "123", "Bruno", "Pelotas");
<<<<<<< HEAD
        Donator carlos = new Donator("carlos", "123", "Carlos", "Porto Alegre", 5000f);
        Donator diego = new Donator("diego", "123", "Diego", "Pelotas", 2000f);
=======
        Donator carlos = new Donator("carlos", "123", "Carlos", "Porto Alegre", 100f);
        Donator diego = new Donator("diego", "123", "Diego", "Pelotas", 100f);
>>>>>>> origin/master
        
        save(ana);
        save(bruno);
        save(carlos);
        save(diego);
        
        // Projects
        save(new Project(this, ana.getId(), "Projeto 1 da Ana", "Sem descrição.", 10f, 1000f, "2016.01.01"));
        save(new Project(this, ana.getId(), "Projeto 2 da Ana", "Sem descrição.", 20f, 2000f, "2017.01.01"));
        save(new Project(this, bruno.getId(), "Projeto 1 do Bruno", "Sem descrição.", 10f, 1000f, "2016.01.01"));
    }
    
    public void save(Enterpreneur enterpreneur) {
        enterpreneur.setId(nextEnterpreneurId++);
        enterpreneurs.put(enterpreneur.getId(), enterpreneur);
    }
    
    public void save(Donator donator) {
        donator.setId(nextDonatorId++);
        donators.put(donator.getId(), donator);
    }
    
    public void save(Project project) {
        project.setId(nextProjectId++);
        projects.put(project.getId(), project);
    }
    
    public void save(Donation donation) {
        donation.setId(nextDonationId++);
        donations.put(donation.getId(), donation);
    }
    
    public Enterpreneur getEnterpreneur(int enterpreneurId) {
        return enterpreneurs.get(enterpreneurId);
    }
    
    public Donator getDonator(int donatorId) {
        return donators.get(donatorId);
    }
    
    public Project getProject(int projectId) {
        return projects.get(projectId);
    }
    
    public Donation getDonation(int donationId) {
        return donations.get(donationId);
    }
    
    public Collection<Project> getAllProjects() {
        return projects.values();
    }
}
