package com.rihua.jap.jpa02.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//主表
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer company_id;
    @Column(name = "company_name")
    private String company_name;
    @Column(name = "company_address")
    private String company_address;
    @OneToMany(targetEntity = User.class,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "lkm_user_id",referencedColumnName = "id")
    private List<User> users=new ArrayList<>();

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_id=" + company_id +
                ", company_name='" + company_name + '\'' +
                ", company_address='" + company_address + '\'' +
                ", users=" + users +
                '}';
    }
}
