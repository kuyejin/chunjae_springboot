package com.chunjae.test03.entity;

import lombok.Data;

@Data
public class Euser {
    private Integer id;         //PK
    private String name;        //NOT NULL
    private String passwd ;     //NOT NULL
    private String username;    //NOT NULL
    private String email;       //NOT NULL
    private String address;
    private String tel;
    private String regdate;    //DEFAULT CURRENT_TIME
    private String lev;        //DEFAULT 'USER'
    private String act;        //DEFAULT 'JOIN'
}
