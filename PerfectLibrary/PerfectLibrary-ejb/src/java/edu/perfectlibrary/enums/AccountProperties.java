/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.enums;

/**
 *
 * @author Užívateľ
 */
public enum AccountProperties {
    EMAIL_ADDRESS("email");
    
    private final String properName;
    
    AccountProperties(String name){
        this.properName=name;
    }
    
    public String properName(){
        return properName;
    }
}
