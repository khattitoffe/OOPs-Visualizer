package com.khattitoffe.WebBluej.service;
import com.khattitoffe.WebBluej.entity.UserData;
import com.khattitoffe.WebBluej.entity.UserLogin;
import com.khattitoffe.WebBluej.repository.CreateUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserEntry {

    @Autowired
    private CreateUserRepo createUserRepo;

    public boolean saveUser(UserData user) {
        try {
            createUserRepo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    /*
    public boolean userExists(User user)
    {
        if(createUserRepo.existsByemail((user.getEmail())))
            return true;
        return false;
    }
    */
    // email vvalidation.. takees email and then verify if it is valid or not using javax.mail lib
    public boolean verifyEmail(UserData user){
        try {
            InternetAddress eAddress = new InternetAddress(user.getEmail());
            eAddress.validate();// vverifies email if invalid throws addressexception

            if(createUserRepo.existsByemail(user.getEmail()))
                // if email is already in DB throws exception
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already exists");

            if(createUserRepo.existsByusername(user.getUsername()))
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exists");

            return true; // returns true jab username is not existing in db and too
        }
        catch(AddressException e){
            return false;
        }
    }

    public boolean userExists(UserLogin user)
    {
        if(createUserRepo.existsByusername(user.getUsername()))
        {
            UserData userDB= createUserRepo.findByusername(user.getUsername());// not completed yet
            if(userDB.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }


}
