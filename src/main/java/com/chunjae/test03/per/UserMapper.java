package com.chunjae.test03.per;


import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<Euser> userList() throws Exception;
    public Euser getUser(String id) throws Exception;


}
