package com.chunjae.test03.per;


import com.chunjae.test03.entity.Euser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<Euser> getUserList() throws Exception;
    public Euser getUser(String name) throws Exception;

    void getWithdraw(String name);
    void getActivate(String name);
    void getDormant(String name);

    Euser getByEmail(String email);
    Euser getByName(String name);

    Euser loginCheck(String email);
    Euser login(String email);


    //Euser findById(Euser user);
    Euser findById(String email, String tel);

    //Euser findByPw(Euser user);
    Euser findByPw(String email, String tel, String name);


    public void userInsert(Euser user) ;
    public void userUpdate(Euser user) ;


    public void userDelete(String name);
}
