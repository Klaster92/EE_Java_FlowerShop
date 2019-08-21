package com.accenture.flowershop.be.Entity.User;

import com.accenture.flowershop.be.Entity.Order.Order;
import com.accenture.flowershop.fe.enums.UserType;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

@XmlRootElement
@Entity(name = "User")
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "generator", sequenceName = "USER_SEQ", allocationSize = 50, initialValue = 2)
    @Column(name = "ID_USER")
    @XmlElement(name = "id")
    private Long id;

    @Column(name = "LOGIN")
    @XmlElement(name = "role")
    private String login;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    @XmlElement(name = "role")
    private UserType role = UserType.USER;

    @XmlElement(name = "FIRST_NAME")
    @Column(name = "FIRST_NAME")
    private String name;

    @XmlElement(name = "LAST_NAME")
    @Column(name = "LAST_NAME")
    private String lastName;

    @XmlElement(name = "MIDDLE_NAME")
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @XmlElement(name = "PHONENUMBER")
    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @XmlElement(name = "ADRESS")
    @Column(name = "ADDRESS")
    private String address;

    @XmlElement(name = "PASSWORD")
    @Column(name = "PASSWORD")
    private String password;

    @XmlElement(name = "BALANCE")
    @Column(name = "BALANCE")
    private BigDecimal balance = BigDecimal.valueOf(2000);

    @XmlElement(name = "DISCOUNT")
    @Column(name = "DISCOUNT")
    private int discount = 3;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public List<Order> getOrders() {return orders;}
}
