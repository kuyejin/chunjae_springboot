package com.chunjae.test03.biz;

import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.entity.UserInfo;
import com.chunjae.test03.per.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Euser> userList() throws Exception {
        return userMapper.userList();
    }

    @Override
    public Euser getUser(String id) throws Exception {
        return userMapper.getUser(id);
    }
}
