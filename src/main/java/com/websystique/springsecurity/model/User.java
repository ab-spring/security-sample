package com.websystique.springsecurity.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "APP_USER")
public class User implements UserDetails {

    /*
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id;

    @Column(name = "SSO_ID", unique = true, nullable = false)
    private String            ssoId;

    @Column(name = "PASSWORD", nullable = false)
    private String            password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String            firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String            lastName;

    @Column(name = "EMAIL", nullable = false)
    private String            email;

    @Column(name = "STATE", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private State             state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "APP_USER_USER_PROFILE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private Set<UserProfile>  userProfiles     = new HashSet<UserProfile>();

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id
     *            value to be assigned to property id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>ssoId</tt>.
     * 
     * @return property value of ssoId
     */
    public String getSsoId() {
        return ssoId;
    }

    /**
     * Setter method for property <tt>ssoId</tt>.
     * 
     * @param ssoId
     *            value to be assigned to property ssoId
     */
    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    /**
     * Getter method for property <tt>password</tt>.
     * 
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     * 
     * @param password
     *            value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>firstName</tt>.
     * 
     * @return property value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for property <tt>firstName</tt>.
     * 
     * @param firstName
     *            value to be assigned to property firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method for property <tt>lastName</tt>.
     * 
     * @return property value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for property <tt>lastName</tt>.
     * 
     * @param lastName
     *            value to be assigned to property lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method for property <tt>email</tt>.
     * 
     * @return property value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for property <tt>email</tt>.
     * 
     * @param email
     *            value to be assigned to property email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for property <tt>state</tt>.
     * 
     * @return property value of state
     */
    public State getState() {
        return state;
    }

    /**
     * Setter method for property <tt>state</tt>.
     * 
     * @param state
     *            value to be assigned to property state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Getter method for property <tt>userProfiles</tt>.
     * 
     * @return property value of userProfiles
     */
    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    /**
     * Setter method for property <tt>userProfiles</tt>.
     * 
     * @param userProfiles
     *            value to be assigned to property userProfiles
     */
    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (ssoId == null) {
            if (other.ssoId != null)
                return false;
        } else if (!ssoId.equals(other.ssoId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", state=" + state + ", userProfiles=" + userProfiles + "]";
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserProfile userProfile : getUserProfiles()) {
            System.out.println("UserProfile : " + userProfile);
            authorities.add(new SimpleGrantedAuthority(userProfile.getType().name()));
        }
        return authorities;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    @Override
    public String getUsername() {
        return ssoId;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}