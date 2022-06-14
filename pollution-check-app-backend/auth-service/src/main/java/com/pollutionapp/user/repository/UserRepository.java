package com.pollutionapp.user.repository;

import com.pollutionapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
* This class is implementing the JpaRepository interface for Note.
* Annotate this class with @Repository annotation
* */

@Repository
public interface UserRepository extends JpaRepository<User,String>{

	public User findUserByEmailAndPassword(String userId, String password);

}
