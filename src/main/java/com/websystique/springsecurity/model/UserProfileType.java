package com.websystique.springsecurity.model;

/**
 * 
 * 
 * 
 * @author lwk
 * @version $Id: UserProfileType.java, v 0.1 2017-06-16 15:03:04 lwk Exp$
 */
public enum UserProfileType {
    
    /**
     * 
     */
    USER("USER"), 
    
    /**
     * 
     */
    DBA("DBA"), 
    
    /**
     * 
     */
    ADMIN("ADMIN");

    String userProfileType;

    private UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType() {
        return userProfileType;
    }

}
