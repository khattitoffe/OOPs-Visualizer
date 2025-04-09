package com.khattitoffe.WebBluej.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.khattitoffe.WebBluej.entity.UserData;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreateUserRepo extends MongoRepository<UserData,String>{

    boolean existsByemail(String email);

    boolean existsByusername(String username);// no need to implement, spring automatically implements by methodname

    UserData findByusername(String username);

}
