package com.khattitoffe.WebBluej.controller;
import com.khattitoffe.WebBluej.entity.UserData;
import com.khattitoffe.WebBluej.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import com.khattitoffe.WebBluej.service.UserEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserEntry db;

    @PostMapping("/signUp")
    public ResponseEntity<String> createUser(@RequestBody UserData user)
    {
        if(!db.verifyEmail((user))) // verrifies email and checks if its already in db or not
            return ResponseEntity.badRequest().body("Invalid email");

        if(db.saveUser(user))
            return ResponseEntity.ok("User Created " + user.getUsername());
        else
            return ResponseEntity.badRequest().body("User already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin user)
    {
        if(db.userExists(user))
            return ResponseEntity.ok().body("Login Successful");
        return ResponseEntity.badRequest().body("Invalid username or password");
    }

}
