package com.chunjae.test03.entity;

import lombok.Data;

@Data
public class UserInfo {
    private String userId ;
    private String password ;
    private String userName;
    private String email;
    private String tel;
    private String regdate;
    private int point;


    private String using;
    private String authorities;
    private String authority ;
    private int authorityNm;
    private String auth;

}
