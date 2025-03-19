package com.khattitoffe.WebBluej.controller;
import com.khattitoffe.WebBluej.entity.User;
import com.khattitoffe.WebBluej.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import com.khattitoffe.WebBluej.service.UserEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.khattitoffe.WebBluej.service.UserEntry;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserEntry db;

    @PostMapping("/signUp")
    public ResponseEntity<String> createUser(@RequestBody User user)
    {
        if(db.saveUser(user))
            return ResponseEntity.ok("User Created "+user.getUsername());
        else
            return ResponseEntity.badRequest().body("User already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin user)
    {

    }

}
