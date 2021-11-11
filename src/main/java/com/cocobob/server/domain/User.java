package com.cocobob.server.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    private String email;
    private String password;
    private String sex;
    private double rating;
    private String birth;
    private float cnt;
}