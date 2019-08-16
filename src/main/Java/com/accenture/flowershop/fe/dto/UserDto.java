package com.accenture.flowershop.fe.dto;

import org.hibernate.usertype.UserType;
import org.springframework.web.bind.annotation.Mapping;
import sun.security.util.Password;

import java.math.BigDecimal;

public class UserDto {
    private Long idUser;
    private UserType role;
    private String name;
    private String lastName;
    private String middleName;
    private String email;
    private String login;
    private Password password;
    private String address;
    private String phoneNumber;
    private BigDecimal balance;
    private Integer discount;

    public UserDto() {
    }

    @Mapping("id")
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public com.accenture.flowershop.fe.enums.UserType getRole() {return role;}

    public void setRole(UserType role) {this.role = role;}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}