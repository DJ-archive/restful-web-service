package com.djyun.restfulwebservice.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static { // 클래스 변수 초기화 블록
        users.add(new User(1,"djyun", new Date()));
        users.add(new User(2,"Alice", new Date()));
        users.add(new User(3,"Ellen", new Date()));
    }
    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        /*
        for (User user : users){
            if (user.getId()==id){
                return user;
            }
        }
        return null;

         */
        return users.stream()
                .filter(user -> user.getId()==id)
                .findFirst().orElseGet(null);
    }
}
