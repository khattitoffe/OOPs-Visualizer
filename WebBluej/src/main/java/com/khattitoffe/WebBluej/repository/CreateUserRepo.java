package com.khattitoffe.WebBluej.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.khattitoffe.WebBluej.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateUserRepo extends MongoRepository<User,String>{
    @Query
    boolean existsBysuername(String username);// no need to implement, spring automatically implements by methodname
    @Query
    User findBysuername(String username);
}
