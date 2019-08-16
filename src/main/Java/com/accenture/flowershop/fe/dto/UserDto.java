package com.accenture.flowershop.fe.dto;

import sun.security.util.Password;

import java.math.BigDecimal;

public class UserDto {
    public boolean isAdmin;
    public String name;
    public String lastName;
    public String middleName;
    public String email;
    public String login;
    public Password password;
    public String address;
    public String phoneNumber;
    public BigDecimal balance;
    public Integer discount;
}