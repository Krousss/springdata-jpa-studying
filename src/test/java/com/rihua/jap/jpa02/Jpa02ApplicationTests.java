package com.rihua.jap.jpa02;

import com.rihua.jap.jpa02.dao.UserDao;
import com.rihua.jap.jpa02.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Jpa02ApplicationTests {

    @Resource
    private UserDao userDao;

    @Test
    void contextLoads() {
    }

    @Test
    public void saveUser(){
        User user=new User();
        user.setName("Andy");
        user.setAge(16);
        user.setAddress("Califonia");
        userDao.save(user);

    }

    @Test
    public void deleteUser(){
        User user=userDao.getOne(1);
        userDao.delete(user);
    }

    @Test
    public void update(){
        userDao.updateUser(2,"Nancy");
    }

    @Test
    public void findUsersByAge(){
        int age=16;
        List<User> list=userDao.findUsersByAge(age);
        System.out.println(list);
    }

    @Test
    public void findAllUser(){
        List<User> list=userDao.findAllUser();
        System.out.println(list);
    }

    @Test
    public void findAllUserDesc(){
        List<User> list=userDao.findAllUserDesc();
        System.out.println(list);
    }

    @Test
    public void getUserCount(){
        System.out.println(userDao.getUserCount());
    }

    @Test
    public void findUserByName(){
        String name="c";
        List<User> list=userDao.findUsersByName(name);
        System.out.println(list);
    }

    @Test
    public void findUsers(){
        Specification<User> specification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<>();
                    Predicate p1=criteriaBuilder.like(root.get("name"),"%n%");
                    list.add(p1);
                    Predicate p2=criteriaBuilder.equal(root.get("age"),16);
                    list.add(p2);
                    Predicate endPredicate=criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
                    return endPredicate;
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable=PageRequest.of(0,2,sort);
        Page<User> page=userDao.findAll(specification,pageable);
        System.out.println(page.getContent());
    }

    @Test
    public void findUsersByAddress(){
        Specification<User> specification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.equal(root.get("address"),"Califonia");
                return predicate;
            }
        };
        System.out.println(userDao.findAll(specification));
    }


}
