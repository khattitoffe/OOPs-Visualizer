package com.khattitoffe.WebBluej.service;
import com.khattitoffe.WebBluej.entity.User;
import com.khattitoffe.WebBluej.entity.UserLogin;
import com.khattitoffe.WebBluej.repository.CreateUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class UserEntry {

    @Autowired
    private CreateUserRepo createUserRepo;

    public boolean saveUser(User user)
    {
        try {
            createUserRepo.save(user);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public boolean userExists(UserLogin user)
    {
        if(createUserRepo.existsByusername(user.getUsername()))
        {
            User user= // not completed yet
        }
    }
}
