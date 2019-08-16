package com.accenture.flowershop.be.Entity.User;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.math.BigDecimal;

@Entity(name = "User")
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",updatable = false)
    private Long id;

    @Column(name = "LOGIN",unique = true, updatable = false)
    private String login;

    @Column(name = "FIRST_NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BALANCE")
    private BigDecimal balance = BigDecimal.valueOf(2000);

    @Column(name = "DISCOUNT")
    private int discount = 3;

    @Column(name = "EMAIL",unique = true)
    private String email;

    @Column(name = "ISADMIN")
    private boolean isAdmin = false;

    public User(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login =login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}
