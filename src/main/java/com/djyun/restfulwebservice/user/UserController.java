package com.djyun.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) { // 생성자 주입
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    // GET /users/1 or /users/10 -> String
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){ // 자동으로 int converting
        return service.findOne(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){ // client -> Html form 데이터 등이 아닌 json,xml 등 object 타입으로 데이터를 받기 위함
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        // 적절한 상태 코드 관리, 클라이언트 측 네트워크 비용 줄이기
        return ResponseEntity.created(location).build();
    }
}
