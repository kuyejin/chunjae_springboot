package com.chunjae.test03.biz;

import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.entity.UserInfo;

import java.util.List;

public interface UserService {

    public List<Euser> userList() throws Exception;

    public Euser getUser(String id) throws Exception;
}
