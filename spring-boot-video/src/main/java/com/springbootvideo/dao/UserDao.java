package com.springbootvideo.dao;

import com.springbootvideo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/26 13:28
 * @Description:
 */
@Mapper
public interface UserDao {

    List<User> findByCond(HashMap<String,String> map);


    User findOne(HashMap<String,String> map);

    void save(User user);

    void update(User user);

}
