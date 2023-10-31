package com.chunjae.test03.per;

import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.entity.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserMapperImpl implements UserMapper {


    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Euser> userList() throws Exception {
        return sqlSession.selectList("userinfo.userList");
    }

    @Override
    public Euser getUser(String id) throws Exception {
        return sqlSession.selectOne("userinfo.getUser", id);
    }
}