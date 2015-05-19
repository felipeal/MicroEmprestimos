/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import persistence.Database;

/**
 *
 * @author Felipe
 */
public abstract class AbstractAction {
    Database database;
    
    public AbstractAction(Database database) {
        this.database = database;
    }
}
