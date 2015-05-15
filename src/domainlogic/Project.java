/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainlogic;

import java.util.Date;

/**
 *
 * @author Felipe
 */
public class Project {
    
    Integer enterpreneurId;
    String title;
    String description;
    Float minDonationValue;
    Float targetValue;
    Date limitDate;
    
    public Project(Integer enterpreneurId, String title, String description, Float minDonationValue, Float targetValue, Date limitDate) {
        this.enterpreneurId = enterpreneurId;
        this.title = title;
        this.description = description;
        this.minDonationValue = minDonationValue;
        this.targetValue = targetValue;
        this.limitDate = limitDate;
    }
}
