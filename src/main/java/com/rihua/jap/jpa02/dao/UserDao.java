package com.rihua.jap.jpa02.dao;

import com.rihua.jap.jpa02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    @Modifying
    @Transactional
    @Query(value = " update user set name = ?2 where id = ?1 ",nativeQuery = true)
    public void updateUser(int id,String name);

    public List<User> findUsersByAge(int age);

    @Transactional
    @Query(value = "select * from user",nativeQuery = true)
    public List<User> findAllUser();
    @Transactional
    @Query(value = "select * from user order by id desc ",nativeQuery = true)
    public List<User> findAllUserDesc();
    @Query(value = "select count(id) from user ",nativeQuery = true)
    public int getUserCount();
    @Query(value = "select * from  user where name like %?1%",nativeQuery = true)
    public List<User> findUsersByName(String name);
}
